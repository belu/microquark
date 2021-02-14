package com.melonbase.microquark.microstream.storage

import com.google.common.jimfs.Jimfs
import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.storage.types.StorageManager

private const val DATABASE_NAME = "microstream@in-memory"

fun loadStorageMem(dataRoot: DataRoot): StorageManager {
  val fs = Jimfs.newFileSystem()

  return createStorageManager(fs.getPath("data"), dataRoot, DATABASE_NAME)
}