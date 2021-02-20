package com.melonbase.microquark.repo

import com.melonbase.microquark.microstream.getDataRoot
import com.melonbase.microquark.repo.data.Kanton
import com.melonbase.microquark.repo.data.Volksabstimmung
import com.melonbase.microquark.repo.data.Vorlage
import com.melonbase.microquark.repo.data.Wahlresultat
import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.service.NotFoundResult
import com.melonbase.microquark.service.RejectedResult
import com.melonbase.microquark.service.ServiceResult
import com.melonbase.microquark.service.SuccessResult
import com.melonbase.microquark.service.SuccessWithDataResult
import one.microstream.storage.types.StorageManager
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.stream.Collectors
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import kotlin.concurrent.withLock
import kotlin.math.roundToInt
import kotlin.random.Random

@ApplicationScoped
class ElectionsRepo @Inject constructor(private val storage: StorageManager) {

  private val readWriteLock = ReentrantReadWriteLock()
  private val read = readWriteLock.readLock()
  private val write = readWriteLock.writeLock()

  fun getVolksabstimmungen(): Set<Volksabstimmung> {
    read.withLock {
      return storage.getDataRoot().volksabstimmungen
    }
  }

  fun getVolksabstimmung(datum: LocalDate): Volksabstimmung? {
    read.withLock {
      return storage.getDataRoot().volksabstimmungen.find { it.datum == datum }
    }
  }

  fun addVolksabstimmung(volksabstimmung: VolksabstimmungDto): ServiceResult<Volksabstimmung> {
    write.withLock {
      if (getVolksabstimmung(volksabstimmung.datum) != null) {
        return RejectedResult("Es existiert bereits eine Volksabstimmung am '${volksabstimmung.datum}'.")
      }

      val neueVolksabstimmung = Volksabstimmung().apply {
        datum = volksabstimmung.datum
        vorlagen = convertVorlagen(volksabstimmung.vorlagen)
      }

      val root = storage.getDataRoot()
      root.volksabstimmungen.add(neueVolksabstimmung)
      storage.store(root.volksabstimmungen)

      return SuccessWithDataResult(neueVolksabstimmung)
    }
  }

  fun deleteVolksabstimmung(datum: LocalDate): ServiceResult<Nothing> {
    write.withLock {
      val successful = storage.getDataRoot().volksabstimmungen.removeIf { v -> v.datum == datum }
      if (successful) {
        storage.store(storage.getDataRoot().volksabstimmungen)
        return SuccessResult
      }
      return NotFoundResult
    }
  }

  fun performAbstimmung(datum: LocalDate): ServiceResult<Nothing> {
    write.withLock {
      val root = storage.getDataRoot()

      val volksabstimmung = root.volksabstimmungen.find { it.datum == datum } ?: return NotFoundResult

      val gewaehlt = volksabstimmung.vorlagen.any { it.wahlresultat != null }
      if (gewaehlt) {
        return RejectedResult("Es wurde bereits abgestimmt.")
      }

      volksabstimmung.vorlagen.forEach { vorlage ->
        println(
          """
          
          Abstimmung für Vorlage:
          ${vorlage.beschreibung}
          
        """.trimIndent()
        )

        val stimmenByKanton = Kanton.values().map { kanton ->
          println("Berechne Stimmen für Kanton $kanton mit ${kanton.numberOfInhabitants} Einwohnern.")

          val stimmbeteiligung = Random.nextDouble(10.0, 100.0).toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)

          val numVoters = (kanton.numberOfInhabitants * stimmbeteiligung.toDouble() / 100.0).roundToInt()
          println("Stimmbeteiligung $stimmbeteiligung% = $numVoters Stimmen")

          val schwelle = Random.nextDouble(0.0, 100.0)
          val ergebnis = List(numVoters) { Random.nextDouble(0.0, 100.0) > schwelle }

          Pair(kanton, ergebnis)
        }.toMap()

        vorlage.wahlresultat = Wahlresultat(stimmenByKanton)
        storage.store(vorlage)
      }
    }
    return SuccessResult
  }

  val absolute = DecimalFormat("#,###.##")
  val percent = DecimalFormat("#,###.00")

  fun getResult(datum: LocalDate): ServiceResult<String> {
    read.withLock {
      val volksabstimmung = getVolksabstimmung(datum) ?: return NotFoundResult

      val nochNichtAbgestimmt =
        volksabstimmung.vorlagen.any { it.wahlresultat == null }
      if (nochNichtAbgestimmt) {
        return RejectedResult("Abstimmung wurde noch nicht durchgeführt.")
      }

      volksabstimmung.vorlagen.forEach { vorlage ->
        println(
          """
           |
           |################################################################################
           | ${vorlage.beschreibung}
           |################################################################################
           |
        """.trimMargin()
        )

        vorlage.wahlresultat.stimmenByKanton.forEach { (kanton, stimmen) ->
          val einwohner = kanton.numberOfInhabitants
          val anzahlStimmen = stimmen.size
          val wahlbeteiligung = 100.0 / einwohner * anzahlStimmen

          val jaStimmen = stimmen.count { it == true }
          val neinStimmen = anzahlStimmen - jaStimmen

          val jaProzent = 100.0 / anzahlStimmen * jaStimmen
          val neinProzent = 100.0 - jaProzent

          println(
            """
              |
              |+++++ $kanton +++++
              |
              |Einwohner: ${absolute.format(einwohner)}
              |Abgegebene Stimmen: ${absolute.format(anzahlStimmen)}
              |Wahlbeteiligung: ${percent.format(wahlbeteiligung)}%
              |
              |Ja: ${percent.format(jaProzent)}% (${absolute.format(jaStimmen)} Stimmen)
              |Nein: ${percent.format(neinProzent)}% (${absolute.format(neinStimmen)} Stimmen)
              |
            """.trimMargin()
          )
        }
      }
      return SuccessWithDataResult("TODO")
    }
  }

  private fun convertVorlagen(vorlagen: List<String>): List<Vorlage> {
    return vorlagen
      .map { vorlage -> Vorlage(vorlage) }
      .stream()
      .collect(Collectors.toUnmodifiableList())
  }
}