package com.melonbase.microquark.microstream

import com.melonbase.microquark.microstream.storage.loadStorageFilesystem
import com.melonbase.microquark.microstream.storage.loadStorageJdbc
import com.melonbase.microquark.microstream.storage.loadStorageMem
import com.melonbase.microquark.microstream.storage.loadStorageMongoDb
import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.enterprise.inject.Produces
import javax.inject.Singleton

@Singleton
class StorageManagerProducer {

  @get:Produces
  lateinit var storage: StorageManager
    private set

  @ConfigProperty(name = "microstream.storage.type", defaultValue = "mem")
  lateinit var storageType: String

  @PostConstruct
  fun init() {
    LOG.info("Starting StorageManager.")

    storage = when (storageType) {
      StorageType.MEM -> loadStorageMem()
      StorageType.FILESYSTEM -> loadStorageFilesystem()
      StorageType.JDBC -> loadStorageJdbc()
      StorageType.MONGODB -> loadStorageMongoDb()
      else -> throw IllegalArgumentException("Unsupported storage type: '$storageType'")
    }

    LOG.info("StorageManager started. Database name={}", storage.databaseName())
  }

  companion object {
    private val LOG = LoggerFactory.getLogger(StorageManagerProducer::class.java)
  }
}