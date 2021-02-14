package com.melonbase.microquark.rest.interceptor;

import io.vertx.core.http.HttpServerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class LoggingResponseFilter implements ContainerResponseFilter {

  private static final Logger LOG = LoggerFactory.getLogger(LoggingResponseFilter.class);

  @Context
  UriInfo info;

  @Context
  HttpServerRequest request;

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    final String method = requestContext.getMethod();
    final String path = info.getPath();
    final int statusCode = responseContext.getStatus();

    LOG.info("Response {} {} with status {}", method, path, statusCode);
  }
}
