package com.melonbase.microquark.repo

import com.melonbase.microquark.microstream.getDataRoot
import com.melonbase.microquark.repo.data.Kanton
import com.melonbase.microquark.repo.data.Volksabstimmung
import com.melonbase.microquark.repo.data.Vorlage
import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.rest.dto.VorlageDto
import com.melonbase.microquark.service.*
import one.microstream.storage.types.StorageManager
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.stream.Collectors
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import kotlin.concurrent.withLock
import kotlin.math.roundToInt
import kotlin.random.Random

@ApplicationScoped
class ElectionsRepo @Inject constructor(
  private val storage: StorageManager
) {

  private val readWriteLock = ReentrantReadWriteLock()
  private val read = readWriteLock.readLock()
  private val write = readWriteLock.writeLock()

  fun getVolksabstimmungen(): Set<Volksabstimmung> {
    read.withLock {
      return storage.getDataRoot().volksabstimmungen
    }
  }

  fun getVolksabstimmung(id: Int): Volksabstimmung? {
    read.withLock {
      return storage.getDataRoot().volksabstimmungen.find { it.id == id }
    }
  }

  fun addVolksabstimmung(volksabstimmung: VolksabstimmungDto): Volksabstimmung {
    write.withLock {
      val neueVolksabstimmung = Volksabstimmung().apply {
        id = getMaxVolksabstimmungId() + 1
        datum = volksabstimmung.datum
        vorlagen = convertVorlagen(volksabstimmung.vorlagen)
      }

      val root = storage.getDataRoot()
      root.volksabstimmungen.add(neueVolksabstimmung)
      storage.store(root.volksabstimmungen)

      return neueVolksabstimmung
    }
  }

  fun performAbstimmung(id: Int): ServiceResult {
    write.withLock {
      val root = storage.getDataRoot()

      val volksabstimmung = root.volksabstimmungen.find { it.id == id } ?: return NotFoundResult

      val gewaehlt = volksabstimmung.vorlagen.any { it.stimmenByKanton != null }
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

        vorlage.stimmenByKanton = HashMap(Kanton.values().size)

        Kanton.values().map { kanton ->
          println("Berechne Stimmen für Kanton $kanton mit ${kanton.numberOfInhabitants} Einwohnern.")

          val numVoters = (kanton.numberOfInhabitants * vorlage.stimmbeteiligung.toDouble() / 100.0).roundToInt()
          println("Stimmbeteiligung ${vorlage.stimmbeteiligung}% = $numVoters Stimmen")

          val ergebnis = List(numVoters) { Random.nextBoolean() }
          vorlage.stimmenByKanton.put(kanton, ergebnis)
        }
        storage.store(vorlage)
      }
    }
    return SuccessResult
  }

  val absolute = DecimalFormat("#,###.##")
  val percent = DecimalFormat("#,###.00")

  fun getResult(id: Int): ServiceResult {
    read.withLock {
      val volksabstimmung = getVolksabstimmung(id) ?: return NotFoundResult

      val nochNichtAbgestimmt =
        volksabstimmung.vorlagen.any { it.stimmenByKanton == null || it.stimmenByKanton.isEmpty() }
      if (nochNichtAbgestimmt) {
        volksabstimmung.vorlagen.forEach {
          println(it.stimmenByKanton)
        }
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

        vorlage.stimmenByKanton.entries.stream()
          .forEach { entry ->
            val kanton = entry.key

            val einwohner = kanton.numberOfInhabitants
            val stimmen = entry.value.size
            val wahlbeteiligung = 100.0 / einwohner * stimmen

            val jaStimmen = entry.value.count { it == true }
            val neinStimmen = stimmen - jaStimmen

            println(
              """
              |
              |+++++ $kanton +++++
              |
              |Einwohner: ${absolute.format(einwohner)}
              |Abgegebene Stimmen: ${absolute.format(stimmen)}
              |Wahlbeteiligung: ${percent.format(wahlbeteiligung)}%
              |
              |Ja: ${absolute.format(jaStimmen)}
              |Nein: ${absolute.format(neinStimmen)}
              |
            """.trimMargin()
            )
          }
      }
      return SuccessWithDataResult("blah")
    }
  }

  private fun convertVorlagen(vorlagen: List<VorlageDto>): List<Vorlage> {
    return vorlagen
      .mapIndexed { index, vorlage ->
        Vorlage(index + 1, vorlage.beschreibung, vorlage.stimmbeteiligung)
      }
      .stream()
      .collect(Collectors.toUnmodifiableList())
  }

  private fun getMaxVolksabstimmungId(): Int {
    return storage.getDataRoot().volksabstimmungen.stream()
      .mapToInt { it.id }
      .max()
      .orElse(0)
  }
}