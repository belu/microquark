package com.melonbase.microquark.microstream;

import com.google.common.jimfs.Jimfs;
import com.melonbase.microquark.repo.DataRoot;
import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
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
      default:
        throw new IllegalArgumentException("Unsupported storage type: '" + storageType + "'");
    }
    storage = sm;

    LOG.info("StorageManager started. Database name={}", storage.databaseName());
  }

  private StorageManager loadStorageFilesystem() {
    final StorageManager sm = EmbeddedStorage.Foundation(Paths.get(storageFilesystemPath))
        .onConnectionFoundation(it ->
            it.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread().getContextClassLoader()))
        )
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
        .setDataBaseName("in-memory by jimfs")
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
