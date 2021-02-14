package com.melonbase.microquark.rest

import com.melonbase.microquark.rest.dto.out.ElectionOverview
import com.melonbase.microquark.rest.dto.`in`.NewElection
import com.melonbase.microquark.service.ElectionsService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("elections")
class ElectionsResource @Inject constructor(val service: ElectionsService) {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getElectionsOverview(): Set<ElectionOverview> {
    return service.getElections()
  }

  @POST
  fun addElection(newElection: NewElection) {
    service.addElection(newElection)
  }
}