package com.melonbase.microquark.microstream;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class MicroStreamLifecycleBean {

  private static final Logger LOG = LoggerFactory.getLogger(MicroStreamLifecycleBean.class);

  @Inject
  MicroStreamStorage storage;

  void onStart(@Observes StartupEvent ev) {
    LOG.info("microstream storage active: {}", storage.getStorage().isActive());
    LOG.info("microstream storage running: {}", storage.getStorage().isRunning());
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOG.info("Shutting down microstream storage.");

    storage.shutdown();

    LOG.info("microstream storage successfully shut down.");
  }
}
