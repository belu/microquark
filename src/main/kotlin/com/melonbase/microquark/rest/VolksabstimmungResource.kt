package com.melonbase.microquark.rest

import com.melonbase.microquark.rest.dto.inbound.NeueVolksabstimmung
import com.melonbase.microquark.rest.dto.outbound.Volksabstimmung
import com.melonbase.microquark.rest.dto.outbound.VolksabstimmungResultat
import com.melonbase.microquark.service.ElectionsService
import com.melonbase.microquark.service.NotFoundResult
import com.melonbase.microquark.service.RejectedResult
import com.melonbase.microquark.service.SuccessResult
import com.melonbase.microquark.service.SuccessWithDataResult
import java.time.LocalDate
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
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
  fun getVolksabstimmungen(): Set<Volksabstimmung> {
    return service.getVolksabstimmungen()
  }

  @GET
  @Path("{datum}")
  @Produces(MediaType.APPLICATION_JSON)
  fun getVolksabstimmung(@PathParam("datum") datum: LocalDate): Response {
    val volksabstimmung = service.getVolksabstimmung(datum) ?: return notFound()
    return Response.ok(volksabstimmung).build()
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  fun addVolksabstimmung(volksabstimmung: NeueVolksabstimmung): Response {
    fun success(result: SuccessWithDataResult<Volksabstimmung>): Response {
      val location = uriInfo.absolutePathBuilder.path(result.entity.datum.toString()).build()
      return Response.created(location).build()
    }

    return when (val result = service.addVolksabstimmung(volksabstimmung)) {
      NotFoundResult -> notFound()
      is RejectedResult -> badRequest(result.reason)
      SuccessResult -> accepted()
      is SuccessWithDataResult -> success(result)
    }
  }

  @DELETE
  @Path("{datum}")
  fun deleteVolksabstimmung(@PathParam("datum") datum: LocalDate): Response {
    return when (val result = service.deleteVolksabstimmung(datum)) {
      NotFoundResult -> notFound()
      is RejectedResult -> badRequest(result.reason)
      SuccessResult -> noContent()
      is SuccessWithDataResult -> noContent()
    }
  }

  @POST
  @Path("{datum}/abstimmen")
  fun performAbstimmung(@PathParam("datum") datum: LocalDate): Response {
    return when (val result = service.performAbstimmung(datum)) {
      is SuccessResult -> accepted()
      is SuccessWithDataResult -> noContent()
      is NotFoundResult -> notFound()
      is RejectedResult -> badRequest(result.reason)
    }
  }

  @GET
  @Path("{datum}/result")
  @Produces(MediaType.APPLICATION_JSON)
  fun getResult(@PathParam("datum") datum: LocalDate): Response {
    return when (val result = service.getResult(datum)) {
      is SuccessResult -> accepted()
      is SuccessWithDataResult<VolksabstimmungResultat> -> ok(result.entity)
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

  private fun noContent(): Response {
    return Response.noContent().build()
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