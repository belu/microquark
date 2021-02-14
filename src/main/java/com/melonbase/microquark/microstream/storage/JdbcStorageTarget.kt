package com.melonbase.microquark.microstream.storage

import com.melonbase.microquark.microstream.StorageType
import com.melonbase.microquark.repo.data.DataRoot
import one.microstream.afs.sql.SqlConnector
import one.microstream.afs.sql.SqlFileSystem
import one.microstream.afs.sql.SqlProviderMariaDb
import one.microstream.afs.sql.SqlProviderPostgres
import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.config.ConfigProvider
import javax.enterprise.inject.spi.CDI
import javax.sql.DataSource

private const val CONFIG_DATASOURCE_DB_KIND = "quarkus.datasource.db-kind"

private const val DATABASE_NAME = "microstream@jdbc"

fun loadStorageJdbc(dataRoot: DataRoot): StorageManager {
  val datasource = CDI.current().select(DataSource::class.java).get()

  val dbKind = ConfigProvider.getConfig()
    .getOptionalValue(CONFIG_DATASOURCE_DB_KIND, String::class.java)
    .orElseThrow {
      IllegalStateException("Storage type '${StorageType.JDBC}', but no datasource defined.")
    }

  val sqlProvider = when (dbKind) {
    "postgresql", "pgsql", "pg" -> SqlProviderPostgres.New(datasource)
    "mariadb" -> SqlProviderMariaDb.New(datasource)
    else -> throw IllegalArgumentException("Unsupported DB kind: $dbKind")
  }
  val sqlFileSystem = SqlFileSystem.New(SqlConnector.Caching(sqlProvider))
    .ensureDirectoryPath("microstream")

  return createStorageManager(sqlFileSystem, dataRoot, "$DATABASE_NAME:${dbKind}")
}