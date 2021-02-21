package com.melonbase.microquark.microstream

import com.melonbase.microquark.microstream.storage.loadStorageFilesystem
import com.melonbase.microquark.microstream.storage.loadStorageJdbc
import com.melonbase.microquark.microstream.storage.loadStorageMem
import com.melonbase.microquark.microstream.storage.loadStorageMongoDb
import mu.KotlinLogging
import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.ConfigProvider
import javax.annotation.PostConstruct
import javax.enterprise.inject.Produces
import javax.inject.Singleton

internal const val CONFIG_STORAGE_TYPE = "microstream.storage.type"
internal const val DEFAULT_STORAGE_TYPE = "mem"

private val log = KotlinLogging.logger {}

@Singleton
class StorageManagerProducer {

  @get:Produces
  lateinit var storage: StorageManager
    private set

  @PostConstruct
  fun init() {
    customizeLazyReferenceManager()

    log.info("Starting StorageManager.")

    val storageType = getStorageType()
    storage = when (storageType) {
      MEM -> loadStorageMem()
      FILESYSTEM -> loadStorageFilesystem()
      JDBC -> loadStorageJdbc()
      MONGODB -> loadStorageMongoDb()
      else -> throw IllegalArgumentException("Unsupported storage type: '$storageType'")
    }

    log.info("StorageManager started. Database name={}", storage.databaseName())
  }

  private fun getStorageType(): String {
    return ConfigProvider.getConfig()
      .getOptionalValue(CONFIG_STORAGE_TYPE, String::class.java)
      .orElse(DEFAULT_STORAGE_TYPE)
  }
}