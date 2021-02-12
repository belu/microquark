package com.melonbase.microquark.repo

import one.microstream.storage.types.StorageManager
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import kotlin.concurrent.withLock

@ApplicationScoped
class HelloRepo @Inject constructor(
  private val storage: StorageManager,
  private val root: DataRoot
) {

  private val readWriteLock = ReentrantReadWriteLock()
  private val r = readWriteLock.readLock()
  private val w = readWriteLock.writeLock()

  fun getHello(): String? {
    r.withLock {
      return root.message
    }
  }

  fun setHello(msg: String) {
    w.withLock {
      root.message = msg
      storage.storeRoot()
    }
  }
}