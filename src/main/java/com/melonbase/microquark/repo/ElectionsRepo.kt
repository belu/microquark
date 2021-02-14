package com.melonbase.microquark.repo

import com.melonbase.microquark.microstream.getDataRoot
import com.melonbase.microquark.repo.data.Volksabstimmung
import com.melonbase.microquark.repo.data.Vorlage
import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.rest.dto.VorlageDto
import one.microstream.storage.types.StorageManager
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.stream.Collectors
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import kotlin.concurrent.withLock

@ApplicationScoped
class ElectionsRepo @Inject constructor(
  private val storage: StorageManager
) {

  private val readWriteLock = ReentrantReadWriteLock()
  private val r = readWriteLock.readLock()
  private val w = readWriteLock.writeLock()

  fun getVolksabstimmungen(): Set<Volksabstimmung> {
    r.withLock {
      return storage.getDataRoot().volksabstimmungen
    }
  }

  fun getVolksabstimmung(id: Int): Volksabstimmung? {
    r.withLock {
      return storage.getDataRoot().volksabstimmungen.find { it.id == id }
    }
  }

  fun addVolksabstimmung(volksabstimmung: VolksabstimmungDto): Volksabstimmung {
    w.withLock {
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