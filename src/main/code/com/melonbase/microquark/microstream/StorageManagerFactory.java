package com.melonbase.microquark.microstream;

import com.google.common.jimfs.Jimfs;
import com.melonbase.microquark.repo.DataRoot;
import com.mongodb.client.MongoClients;
import one.microstream.afs.blobstore.BlobStoreFileSystem;
import one.microstream.afs.mongodb.MongoDbConnector;
import one.microstream.afs.sql.SqlConnector;
import one.microstream.afs.sql.SqlFileSystem;
import one.microstream.afs.sql.SqlProvider;
import one.microstream.afs.sql.SqlProviderMariaDb;
import one.microstream.afs.sql.SqlProviderPostgres;
import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.nio.file.Paths;

@Singleton
public class StorageManagerFactory {

  private static final Logger LOG = LoggerFactory.getLogger(StorageManagerFactory.class);

  private final DataRoot root = new DataRoot();

  private StorageManager storage;

  @ConfigProperty(name = "microstream.storage.type", defaultValue = "mem")
  String storageType;

  @ConfigProperty(name = "microstream.storage.fs.path", defaultValue = "data")
  String storageFilesystemPath;

  @PostConstruct
  void init() {
    LOG.info("Starting StorageManager.");

    final StorageManager sm;
    switch (storageType) {
      case StorageType.MEM:
        sm = loadStorageMem();
        break;
      case StorageType.FILESYSTEM:
        sm = loadStorageFilesystem();
        break;
      case StorageType.JDBC:
        sm = loadStorageJdbc();
        break;
      case StorageType.MONGODB:
        sm = loadStorageMongoDb();
        break;
      default:
        throw new IllegalArgumentException("Unsupported storage type: '" + storageType + "'");
    }
    storage = sm;

    LOG.info("StorageManager started. Database name={}", storage.databaseName());
  }

  private static final String CONFIG_MONGODB_CONNECTION_STRING = "quarkus.mongodb.connection-string";

  private StorageManager loadStorageMongoDb() {
    var conn = ConfigProvider.getConfig().getOptionalValue(CONFIG_MONGODB_CONNECTION_STRING, String.class);
    if (conn.isEmpty()) {
      throw new IllegalStateException("Storage type '" + StorageType.MONGODB + "', but no MongoDB connection defined.");
    }

    var mongoClient = MongoClients.create();
    var db = mongoClient.getDatabase("db");
    var fileSystem = BlobStoreFileSystem.New(
        MongoDbConnector.Caching(db)
    );

    final StorageManager sm = EmbeddedStorage.Foundation(fileSystem.ensureDirectoryPath("microstream_storage"))
        .onConnectionFoundation(it ->
            it.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread().getContextClassLoader()))
        )
        .setDataBaseName("microstream@" + StorageType.MONGODB)
        .start(root);
    sm.storeRoot();
    return sm;
  }

  private static final String CONFIG_DATASOURCE_DB_KIND = "quarkus.datasource.db-kind";

  private StorageManager loadStorageJdbc() {
    var datasource = CDI.current().select(DataSource.class).get();

    var dbKind = ConfigProvider.getConfig()
        .getOptionalValue(CONFIG_DATASOURCE_DB_KIND, String.class);
    if (dbKind.isEmpty()) {
      throw new IllegalStateException("Storage type '" + StorageType.JDBC + "', but no datasource defined.");
    }

    final SqlProvider sqlProvider;
    switch (dbKind.get()) {
      case "postgresql":
      case "pgsql":
      case "pg":
        sqlProvider = SqlProviderPostgres.New(datasource);
        break;
      case "mariadb":
        sqlProvider = SqlProviderMariaDb.New(datasource);
        break;
      default:
        throw new IllegalArgumentException("Unsupported DB kind: " + dbKind.get());
    }

    var sqlFileSystem = SqlFileSystem.New(SqlConnector.Caching(sqlProvider));
    final StorageManager sm = EmbeddedStorage.Foundation(sqlFileSystem.ensureDirectoryPath("microstream_storage"))
        .onConnectionFoundation(it ->
            it.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread().getContextClassLoader()))
        )
        .setDataBaseName("microstream@" + dbKind.get())
        .start(root);
    sm.storeRoot();
    return sm;
  }

  private StorageManager loadStorageFilesystem() {
    final StorageManager sm = EmbeddedStorage.Foundation(Paths.get(storageFilesystemPath))
        .onConnectionFoundation(it ->
            it.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread().getContextClassLoader()))
        )
        .setDataBaseName("microstream@filesystem:" + storageFilesystemPath)
        .start(root);
    sm.storeRoot();
    return sm;
  }

  private StorageManager loadStorageMem() {
    var fs = Jimfs.newFileSystem();

    final StorageManager sm = EmbeddedStorage.Foundation(fs.getPath("data"))
        .onConnectionFoundation(it ->
            it.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread().getContextClassLoader()))
        )
        .setDataBaseName("microstream@in-memory")
        .start(root);
    sm.storeRoot();
    return sm;
  }

  @Produces
  public StorageManager getStorageManager() {
    return storage;
  }

  @Produces
  public DataRoot getDataRoot() {
    return root;
  }
}
