package com.melonbase.microquark.rest

import com.melonbase.microquark.rest.dto.inbound.NeueVolksabstimmung
import com.melonbase.microquark.rest.dto.outbound.Volksabstimmung
import com.melonbase.microquark.rest.dto.outbound.VolksabstimmungResultat
import com.melonbase.microquark.rest.util.accepted
import com.melonbase.microquark.rest.util.badRequest
import com.melonbase.microquark.rest.util.noContent
import com.melonbase.microquark.rest.util.notFound
import com.melonbase.microquark.rest.util.ok
import com.melonbase.microquark.service.ElectionsService
import com.melonbase.microquark.service.NotFound
import com.melonbase.microquark.service.Failure
import com.melonbase.microquark.service.VoidSuccess
import com.melonbase.microquark.service.Success
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
    fun success(result: Success<Volksabstimmung>): Response {
      val location = uriInfo.absolutePathBuilder.path(result.entity.datum.toString()).build()
      return Response.created(location).build()
    }

    return when (val result = service.addVolksabstimmung(volksabstimmung)) {
      NotFound -> notFound()
      is Failure -> badRequest(result.reason)
      VoidSuccess -> accepted()
      is Success -> success(result)
    }
  }

  @DELETE
  @Path("{datum}")
  fun deleteVolksabstimmung(@PathParam("datum") datum: LocalDate): Response {
    return when (val result = service.deleteVolksabstimmung(datum)) {
      NotFound -> notFound()
      is Failure -> badRequest(result.reason)
      VoidSuccess, is Success -> noContent()
    }
  }

  @POST
  @Path("{datum}/abstimmen")
  fun performAbstimmung(@PathParam("datum") datum: LocalDate): Response {
    return when (val result = service.performAbstimmung(datum)) {
      VoidSuccess -> accepted()
      is Success -> noContent()
      NotFound -> notFound()
      is Failure -> badRequest(result.reason)
    }
  }

  @GET
  @Path("{datum}/result")
  @Produces(MediaType.APPLICATION_JSON)
  fun getResult(@PathParam("datum") datum: LocalDate): Response {
    return when (val result = service.getResult(datum)) {
      VoidSuccess -> accepted()
      is Success<VolksabstimmungResultat> -> ok(result.entity)
      NotFound -> notFound()
      is Failure -> badRequest(result.reason)
    }
  }
}