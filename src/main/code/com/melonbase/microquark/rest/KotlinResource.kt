package com.melonbase.microquark.rest

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("kotlin")
class KotlinResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  fun getKotlin(): String {
    return "Kotlin, baby!"
  }
}