package com.melonbase.microquark.microstream

import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.storage.types.StorageManager

fun StorageManager.getDataRoot(): DataRoot {
  return this.root() as DataRoot
}