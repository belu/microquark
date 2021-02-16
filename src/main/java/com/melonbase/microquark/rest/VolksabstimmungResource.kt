package com.melonbase.microquark.rest

import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.rest.dto.VolksabstimmungWithIdDto
import com.melonbase.microquark.service.*
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("volksabstimmungen")
class VolksabstimmungResource @Inject constructor(val service: ElectionsService) {

  @Context
  lateinit var uriInfo: UriInfo

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getVolksabstimmungen(): Set<VolksabstimmungWithIdDto> {
    return service.getVolksabstimmungen()
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  fun getVolksabstimmung(@PathParam("id") id: Int): Response {
    val volksabstimmung = service.getVolksabstimmung(id) ?: return notFound()
    return Response.ok(volksabstimmung).build()
  }

  @POST
  fun addVolksabstimmung(volksabstimmungDto: VolksabstimmungDto): Response {
    val volksabstimmung = service.addVolksabstimmung(volksabstimmungDto)

    val location = uriInfo.absolutePathBuilder.path(volksabstimmung.id.toString()).build()
    return Response.created(location).build()
  }

  @POST
  @Path("{id}/abstimmen")
  fun performAbstimmung(@PathParam("id") id: Int): Response {
    val result = service.performAbstimmung(id)
    return when (result) {
      is SuccessResult -> accepted()
      is SuccessWithDataResult<*> -> ok(result.entity)
      is NotFoundResult -> notFound()
      is RejectedResult -> badRequest(result.reason)
    }
  }

  @GET
  @Path("{id}/result")
  @Produces(MediaType.TEXT_PLAIN)
  fun getResult(@PathParam("id") id: Int): Response {
    val result = service.getResult(id)
    return when (result) {
      is SuccessResult -> accepted()
      is SuccessWithDataResult<*> -> ok(result.entity)
      is NotFoundResult -> notFound()
      is RejectedResult -> badRequest(result.reason)
    }
  }

  private fun ok(entity: Any?): Response {
    return Response.ok(entity).build()
  }

  private fun accepted(): Response {
    return Response.accepted().build()
  }

  private fun badRequest(reason: String): Response {
    return Response.status(Response.Status.BAD_REQUEST)
      .type(MediaType.TEXT_PLAIN)
      .entity(reason)
      .build()
  }

  private fun notFound(): Response {
    return Response.status(Response.Status.NOT_FOUND).build()
  }
}