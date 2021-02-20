package com.melonbase.microquark.rest.mapping;

import javax.ws.rs.ext.ParamConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateParamConverter implements ParamConverter<LocalDate> {

  @Override
  public LocalDate fromString(String value) {
    return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
  }

  @Override
  public String toString(LocalDate value) {
    return value.format(DateTimeFormatter.ISO_LOCAL_DATE);
  }
}
