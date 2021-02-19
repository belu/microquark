package com.melonbase.microquark.microstream

import mu.KotlinLogging
import one.microstream.reference.LazyReferenceManager
import org.eclipse.microprofile.config.ConfigProvider
import java.time.Duration

internal const val CONFIG_LAZYCHECKER_DURATION = "microstream.lazychecker.duration"
internal const val CONFIG_LAZYCHECKER_MEMORY_QUOTA = "microstream.lazychecker.memoryquota"

internal const val DEFAULT_LAZYCHECKER_DURATION = "PT5M"
internal const val DEFAULT_LAZYCHECKER_MEMORY_QUOTA = 0.75

private val log = KotlinLogging.logger {}

fun customizeLazyReferenceManager() {
  LazyReferenceManager.set(
    LazyReferenceManager.New(
      one.microstream.reference.Lazy.Checker(
        getLazyCheckerDurationMillis(),
        getLazyCheckerMemoryQuota()
      )
    )
  )
}

private fun getLazyCheckerDurationMillis(): Long {
  val durationConfig = ConfigProvider.getConfig()
    .getOptionalValue(CONFIG_LAZYCHECKER_DURATION, String::class.java)
    .orElse(DEFAULT_LAZYCHECKER_DURATION)

  val duration = Duration.parse(durationConfig)
  log.info("MicroStream LazyChecker duration: $duration")
  return duration.toMillis()
}

private fun getLazyCheckerMemoryQuota(): Double {
  val memoryQuota = ConfigProvider.getConfig()
    .getOptionalValue(CONFIG_LAZYCHECKER_MEMORY_QUOTA, Double::class.java)
    .orElse(DEFAULT_LAZYCHECKER_MEMORY_QUOTA)
  log.info("MicroStream LazyChecker memory quota: $memoryQuota")
  return memoryQuota
}