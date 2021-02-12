package com.melonbase.microquark.microstream

import com.melonbase.microquark.microstream.target.loadStorageFilesystem
import com.melonbase.microquark.microstream.target.loadStorageJdbc
import com.melonbase.microquark.microstream.target.loadStorageMem
import com.melonbase.microquark.microstream.target.loadStorageMongoDb
import com.melonbase.microquark.repo.DataRoot
import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.enterprise.inject.Produces
import javax.inject.Singleton

@Singleton
class StorageManagerProducer {

  @get:Produces
  val dataRoot = DataRoot()

  @get:Produces
  lateinit var storage: StorageManager
    private set

  @ConfigProperty(name = "microstream.storage.type", defaultValue = "mem")
  lateinit var storageType: String

  @PostConstruct
  fun init() {
    LOG.info("Starting StorageManager.")

    storage = when (storageType) {
      StorageType.MEM -> loadStorageMem(dataRoot)
      StorageType.FILESYSTEM -> loadStorageFilesystem(dataRoot)
      StorageType.JDBC -> loadStorageJdbc(dataRoot)
      StorageType.MONGODB -> loadStorageMongoDb(dataRoot)
      else -> throw IllegalArgumentException("Unsupported storage type: '$storageType'")
    }

    LOG.info("StorageManager started. Database name={}", storage.databaseName())
  }

  companion object {
    private val LOG = LoggerFactory.getLogger(StorageManagerProducer::class.java)
  }
}