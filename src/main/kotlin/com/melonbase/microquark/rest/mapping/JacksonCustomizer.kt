package com.melonbase.microquark.rest.mapping

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.inject.Singleton

/*
 * Obsolete when Quarkus 1.13 is released
 * https://github.com/quarkusio/quarkus/pull/15139
 */
@Singleton
class JacksonCustomizer : ObjectMapperCustomizer {

  override fun customize(mapper: ObjectMapper) {
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
  }
}