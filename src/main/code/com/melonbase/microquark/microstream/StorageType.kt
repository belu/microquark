package com.melonbase.microquark.microstream

internal interface StorageType {

  companion object {
    const val MEM = "mem"
    const val FILESYSTEM = "filesystem"
    const val JDBC = "jdbc"
    const val MONGODB = "mongodb"
  }
}