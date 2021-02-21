package com.melonbase.microquark.microstream.storage

import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.afs.ADirectory
import one.microstream.jdk8.java.util.BinaryHandlersJDK8.registerJDK8TypeHandlers
import one.microstream.reflect.ClassLoaderProvider
import one.microstream.storage.types.EmbeddedStorage
import one.microstream.storage.types.EmbeddedStorageFoundation
import one.microstream.storage.types.StorageManager
import java.nio.file.Path

fun createStorageManager(path: Path, dataBaseName: String): StorageManager {
  return EmbeddedStorage.Foundation(path).withAppClassLoader().setup(dataBaseName)
}

fun createStorageManager(path: ADirectory, dataBaseName: String): StorageManager {
  return EmbeddedStorage.Foundation(path).withAppClassLoader().setup(dataBaseName)
}

private fun EmbeddedStorageFoundation<*>.withAppClassLoader(): EmbeddedStorageFoundation<*> {
  return this.onConnectionFoundation {
    it.classLoaderProvider = ClassLoaderProvider.New(Thread.currentThread().contextClassLoader)
    registerJDK8TypeHandlers(it)
  }
}

private fun EmbeddedStorageFoundation<*>.setup(dataBaseName: String): StorageManager {
  return this.setDataBaseName(dataBaseName)
    .start(DataRoot())
    .apply { storeRoot() }
}