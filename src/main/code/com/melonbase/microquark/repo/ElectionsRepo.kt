package com.melonbase.microquark.repo

import one.microstream.storage.types.StorageManager
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import kotlin.concurrent.withLock

@ApplicationScoped
class ElectionsRepo @Inject constructor(
  private val storage: StorageManager,
  private val root: DataRoot
) {

  private val readWriteLock = ReentrantReadWriteLock()
  private val r = readWriteLock.readLock()
  private val w = readWriteLock.writeLock()

  fun getElections(): Set<Election> {
    r.withLock {
      return root.elections
    }
  }

  fun addElection(election: Election) {
    w.withLock {
      root.elections.add(election)
      storage.store(root.elections)
    }
  }
}