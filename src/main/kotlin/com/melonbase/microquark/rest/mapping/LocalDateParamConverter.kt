package com.melonbase.microquark.rest.mapping

import javax.ws.rs.ext.ParamConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/*
 * Obsolete when Quarkus 1.13 is released
 * https://github.com/quarkusio/quarkus/pull/15451
 */
class LocalDateParamConverter : ParamConverter<LocalDate> {

  override fun fromString(value: String): LocalDate {
    return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE)
  }

  override fun toString(value: LocalDate): String {
    return value.format(DateTimeFormatter.ISO_LOCAL_DATE)
  }
}