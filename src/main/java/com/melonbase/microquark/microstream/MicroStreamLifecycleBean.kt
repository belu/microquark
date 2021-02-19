package com.melonbase.microquark.microstream

import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import mu.KotlinLogging
import one.microstream.storage.types.StorageManager
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject

private val log = KotlinLogging.logger {}

@ApplicationScoped
class MicroStreamLifecycleBean @Inject constructor(val storage: StorageManager) {

  fun onStart(@Observes ev: StartupEvent) {
    log.info("MicroStream storage running: {}", storage.isRunning)
  }

  fun onStop(@Observes ev: ShutdownEvent) {
    log.info("Shutting down MicroStream storage.")
    storage.shutdown()
    log.info("MicroStream storage successfully shut down.")
  }
}