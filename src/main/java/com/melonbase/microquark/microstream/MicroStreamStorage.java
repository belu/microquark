package com.melonbase.microquark.microstream;

import com.melonbase.microquark.repo.DataRoot;
import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.nio.file.Paths;

@Singleton
public class MicroStreamStorage {

  private static final Logger log = LoggerFactory.getLogger(MicroStreamStorage.class);

  private final DataRoot root = new DataRoot();

  private StorageManager storage;

  @ConfigProperty(name = "microstream.storage.path", defaultValue = "data")
  String storagePath;

  @PostConstruct
  void init() {
    log.info("Starting StorageManager.");

    storage = EmbeddedStorage.Foundation(Paths.get(storagePath))
        .onConnectionFoundation(it ->
            it.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread().getContextClassLoader()))
        )
        .start(root);
    storage.storeRoot();

    log.info("StorageManager started. Database name={}", storage.databaseName());
  }

  public StorageManager getStorage() {
    return storage;
  }

  public DataRoot getRoot() {
    return root;
  }

  public void storeRoot() {
    storage.storeRoot();
  }

  public void shutdown() {
    storage.shutdown();
  }
}
