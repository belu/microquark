package com.melonbase.microquark.rest.mapping;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Provider
public class LocalDateParameterConverterProvider implements ParamConverterProvider {

  @Override
  public <T> ParamConverter<T> getConverter(
      final Class<T> rawType,
      final Type genericType,
      final Annotation[] annotations
  ) {
    if (LocalDate.class.equals(rawType)) {
      return (ParamConverter<T>) new LocalDateParamConverter();
    }
    return null;
  }
}
