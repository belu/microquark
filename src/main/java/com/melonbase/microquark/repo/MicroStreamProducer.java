package com.melonbase.microquark.repo;

import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.inject.Produces;
import java.nio.file.Paths;

public class MicroStreamProducer {

  @ConfigProperty(name = "microstream.storage.path", defaultValue = "data")
  String storagePath;

  private final DataRoot root = new DataRoot();

  @Produces
  public StorageManager getStorageManager() {
    return EmbeddedStorage.Foundation(Paths.get(storagePath))
        .onConnectionFoundation(it ->
            it.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread().getContextClassLoader()))
        )
        .start(root);
  }

  @Produces
  public DataRoot getDataRoot() {
    return root;
  }
}
