package com.melonbase.microquark.rest.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.inject.Singleton;

@Singleton
public class JacksonCustomizer implements ObjectMapperCustomizer {

  @Override
  public void customize(ObjectMapper mapper) {
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }
}
