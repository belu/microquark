package com.melonbase.microquark.microstream.storage

import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.ConfigProvider
import java.nio.file.Paths

private const val CONFIG_STORAGE_FILESYSTEM_PATH = "microstream.storage.fs.path"
private const val DEFAULT_PATH = "data"

private const val DATABASE_NAME = "microstream@filesystem"

fun loadStorageFilesystem(): StorageManager {
  val path = ConfigProvider.getConfig()
    .getOptionalValue(CONFIG_STORAGE_FILESYSTEM_PATH, String::class.java)
    .orElse(DEFAULT_PATH)

  val databaseName = "$DATABASE_NAME:$path"

  return createStorageManager(Paths.get(path), databaseName)
}