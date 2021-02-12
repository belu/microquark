package com.melonbase.microquark.rest

import com.melonbase.microquark.service.HelloService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("hello")
class HelloResource @Inject constructor(val helloService: HelloService) {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  fun getHello(): String? {
    return helloService.getHello()
  }

  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  fun setHello(msg: String) {
    helloService.setHello(msg)
  }
}