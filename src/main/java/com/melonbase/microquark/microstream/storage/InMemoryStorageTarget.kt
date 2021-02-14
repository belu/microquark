package com.melonbase.microquark.microstream.storage

import com.google.common.jimfs.Jimfs
import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.storage.types.StorageManager

private const val DEFAULT_PATH = "data"

private const val DATABASE_NAME = "microstream@in-memory"

fun loadStorageMem(): StorageManager {
  val fs = Jimfs.newFileSystem()

  return createStorageManager(fs.getPath(DEFAULT_PATH), DATABASE_NAME)
}