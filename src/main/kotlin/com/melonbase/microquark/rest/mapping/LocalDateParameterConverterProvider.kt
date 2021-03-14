package com.melonbase.microquark.rest.mapping

import java.lang.reflect.Type
import java.time.LocalDate
import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider

/*
 * Obsolete when Quarkus 1.13 is released
 * https://github.com/quarkusio/quarkus/pull/15451
 */
@Provider
class LocalDateParameterConverterProvider : ParamConverterProvider {

  override fun <T> getConverter(
    rawType: Class<T>,
    genericType: Type,
    annotations: Array<Annotation>
  ): ParamConverter<T>? {
    return if (LocalDate::class.java == rawType) {
      @Suppress("UNCHECKED_CAST")
      LocalDateParamConverter() as ParamConverter<T>
    } else null
  }
}