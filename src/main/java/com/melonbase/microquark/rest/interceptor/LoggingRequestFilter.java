package com.melonbase.microquark.rest.interceptor;

import io.vertx.core.http.HttpServerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingRequestFilter implements ContainerRequestFilter {

  private static final Logger LOG = LoggerFactory.getLogger(LoggingRequestFilter.class);

  @Context
  UriInfo info;

  @Context
  HttpServerRequest request;

  @Override
  public void filter(final ContainerRequestContext context) {
    final String method = context.getMethod();
    final String path = info.getPath();
    final String address = request.remoteAddress().toString();

    LOG.info("Request {} {} from IP {}", method, path, address);
  }
}
