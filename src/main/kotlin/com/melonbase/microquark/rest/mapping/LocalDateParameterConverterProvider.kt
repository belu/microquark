package com.melonbase.microquark.rest.mapping

import java.lang.reflect.Type
import java.time.LocalDate
import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider

@Provider
class LocalDateParameterConverterProvider : ParamConverterProvider {

  override fun <T> getConverter(
    rawType: Class<T>,
    genericType: Type,
    annotations: Array<Annotation>
  ): ParamConverter<T>? {
    return if (LocalDate::class.java == rawType) {
      LocalDateParamConverter() as ParamConverter<T>
    } else null
  }
}