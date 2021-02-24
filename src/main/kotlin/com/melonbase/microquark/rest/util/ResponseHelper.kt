package com.melonbase.microquark.rest.util

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

fun ok(entity: Any?): Response = Response.ok(entity).build()

fun accepted(): Response = Response.accepted().build()

fun noContent(): Response = Response.noContent().build()

fun notFound(): Response = Response.status(Response.Status.NOT_FOUND).build()

fun badRequest(reason: String): Response {
  return Response.status(Response.Status.BAD_REQUEST)
    .type(MediaType.TEXT_PLAIN)
    .entity(reason)
    .build()
}