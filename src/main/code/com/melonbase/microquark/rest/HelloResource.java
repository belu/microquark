package com.melonbase.microquark.rest;

import com.melonbase.microquark.service.HelloService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

  @Inject
  HelloService helloService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getHello() {
    return helloService.getHello();
  }

  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  public void setHello(String msg) {
    helloService.setHello(msg);
  }
}
