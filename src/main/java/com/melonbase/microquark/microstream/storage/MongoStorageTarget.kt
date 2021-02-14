package com.melonbase.microquark.microstream.storage

import com.melonbase.microquark.microstream.StorageType
import com.melonbase.microquark.repo.data.DataRoot
import com.mongodb.client.MongoClients
import one.microstream.afs.blobstore.BlobStoreFileSystem
import one.microstream.afs.mongodb.MongoDbConnector
import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.ConfigProvider

private const val CONFIG_MONGODB_CONNECTION_STRING = "quarkus.mongodb.connection-string"

private const val DATABASE_NAME = "microstream@mongodb"

fun loadStorageMongoDb(): StorageManager {
  val connectionString = ConfigProvider.getConfig()
    .getOptionalValue(CONFIG_MONGODB_CONNECTION_STRING, String::class.java)
    .orElseThrow {
      IllegalStateException("Storage type '${StorageType.MONGODB}', but no MongoDB connection defined.")
    }

  val db = MongoClients.create(connectionString).getDatabase("db")

  val path = BlobStoreFileSystem.New(
    MongoDbConnector.Caching(db)
  ).ensureDirectoryPath("microstream_storage")

  return createStorageManager(path, DATABASE_NAME)
}