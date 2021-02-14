package com.melonbase.microquark.microstream

import com.melonbase.microquark.microstream.storage.loadStorageFilesystem
import com.melonbase.microquark.microstream.storage.loadStorageJdbc
import com.melonbase.microquark.microstream.storage.loadStorageMem
import com.melonbase.microquark.microstream.storage.loadStorageMongoDb
import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.ConfigProvider
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.enterprise.inject.Produces
import javax.inject.Singleton

internal const val CONFIG_STORAGE_TYPE = "microstream.storage.type"
internal const val DEFAULT_STORAGE_TYPE = "mem"

@Singleton
class StorageManagerProducer {

  @get:Produces
  lateinit var storage: StorageManager
    private set

  @PostConstruct
  fun init() {
    LOG.info("Starting StorageManager.")

    val storageType = getStorageType()
    storage = when (storageType) {
      StorageType.MEM -> loadStorageMem()
      StorageType.FILESYSTEM -> loadStorageFilesystem()
      StorageType.JDBC -> loadStorageJdbc()
      StorageType.MONGODB -> loadStorageMongoDb()
      else -> throw IllegalArgumentException("Unsupported storage type: '$storageType'")
    }

    LOG.info("StorageManager started. Database name={}", storage.databaseName())
  }

  private fun getStorageType(): String {
    return ConfigProvider.getConfig()
      .getOptionalValue(CONFIG_STORAGE_TYPE, String::class.java)
      .orElse(DEFAULT_STORAGE_TYPE)
  }

  companion object {
    private val LOG = LoggerFactory.getLogger(StorageManagerProducer::class.java)
  }
}