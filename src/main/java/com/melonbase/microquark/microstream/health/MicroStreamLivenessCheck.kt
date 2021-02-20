package com.melonbase.microquark.microstream.health

import one.microstream.storage.types.StorageManager
import org.eclipse.microprofile.health.HealthCheck
import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Liveness
import org.eclipse.microprofile.health.Readiness
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@Liveness
@ApplicationScoped
class MicroStreamLivenessCheck @Inject constructor(val storage: StorageManager) : HealthCheck {

  private val up = HealthCheckResponse.up("MicroStream storage live")

  override fun call(): HealthCheckResponse {
    return if (storage.isRunning) {
      up
    } else {
      HealthCheckResponse.down("MicroStream storage not running")
    }
  }
}

@Readiness
@ApplicationScoped
class MicroStreamReadinessCheck @Inject constructor(val storage: StorageManager) : HealthCheck {

  private val up = HealthCheckResponse.up("MicroStream storage ready")

  override fun call(): HealthCheckResponse {
    return if (storage.isRunning) {
      up
    } else {
      HealthCheckResponse.down("MicroStream storage not running")
    }
  }
}