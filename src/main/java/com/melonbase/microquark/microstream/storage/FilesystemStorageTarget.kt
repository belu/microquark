package com.melonbase.microquark.microstream.storage

import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.ConfigProvider
import java.nio.file.Paths

private const val CONFIG_STORAGE_FILESYSTEM_PATH = "microstream.storage.fs.path"
private const val DEFAULT_FILESYSTEM_PATH = "data"

private const val DATABASE_NAME = "microstream@filesystem"

fun loadStorageFilesystem(dataRoot: DataRoot): StorageManager {
  val path = ConfigProvider.getConfig()
    .getOptionalValue(CONFIG_STORAGE_FILESYSTEM_PATH, String::class.java)
    .orElse(DEFAULT_FILESYSTEM_PATH)

  return createStorageManager(Paths.get(path), dataRoot, "$DATABASE_NAME:$path")
}