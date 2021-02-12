package com.melonbase.microquark.microstream;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import one.microstream.storage.types.StorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class MicroStreamLifecycleBean {

  private static final Logger LOG = LoggerFactory.getLogger(MicroStreamLifecycleBean.class);

  @Inject
  StorageManager storage;

  void onStart(@Observes StartupEvent ev) {
    LOG.info("MicroStream storage active: {}", storage.isActive());
    LOG.info("MicroStream storage running: {}", storage.isRunning());
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOG.info("Shutting down MicroStream storage.");

    storage.shutdown();

    LOG.info("MicroStream storage successfully shut down.");
  }
}
