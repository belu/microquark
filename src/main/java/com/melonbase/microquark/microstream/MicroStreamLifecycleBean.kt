package com.melonbase.microquark.microstream

import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import one.microstream.storage.types.StorageManager
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject

@ApplicationScoped
class MicroStreamLifecycleBean @Inject constructor(val storage: StorageManager) {

  fun onStart(@Observes ev: StartupEvent) {
    LOG.info("MicroStream storage running: {}", storage.isRunning)
  }

  fun onStop(@Observes ev: ShutdownEvent) {
    LOG.info("Shutting down MicroStream storage.")
    storage.shutdown()
    LOG.info("MicroStream storage successfully shut down.")
  }

  companion object {
    private val LOG = LoggerFactory.getLogger(MicroStreamLifecycleBean::class.java)
  }
}