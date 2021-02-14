package com.melonbase.microquark.microstream.storage

import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.afs.ADirectory
import one.microstream.reflect.ClassLoaderProvider
import one.microstream.storage.types.EmbeddedStorage
import one.microstream.storage.types.EmbeddedStorageFoundation
import one.microstream.storage.types.StorageManager
import java.nio.file.Path

fun createStorageManager(path: Path, dataRoot: DataRoot, dataBaseName: String): StorageManager {
  return EmbeddedStorage.Foundation(path).withAppClassLoader().setup(dataRoot, dataBaseName)
}

fun createStorageManager(path: ADirectory, dataRoot: DataRoot, dataBaseName: String): StorageManager {
  return EmbeddedStorage.Foundation(path).withAppClassLoader().setup(dataRoot, dataBaseName)
}

private fun EmbeddedStorageFoundation<*>.withAppClassLoader(): EmbeddedStorageFoundation<*> {
  return this.onConnectionFoundation {
    it.setClassLoaderProvider(
      ClassLoaderProvider.New(
        Thread.currentThread().contextClassLoader
      )
    )
  }
}

private fun EmbeddedStorageFoundation<*>.setup(
  dataRoot: DataRoot,
  dataBaseName: String
): StorageManager {
  return this.setDataBaseName(dataBaseName)
    .start(dataRoot)
    .apply { storeRoot() }
}