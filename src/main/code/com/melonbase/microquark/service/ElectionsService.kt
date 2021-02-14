package com.melonbase.microquark.service

import com.melonbase.microquark.repo.data.Abstimmung
import com.melonbase.microquark.repo.data.Election
import com.melonbase.microquark.repo.ElectionsRepo
import com.melonbase.microquark.rest.dto.out.ElectionOverview
import com.melonbase.microquark.rest.dto.`in`.NewElection
import com.melonbase.microquark.rest.mapping.mapToDto
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ElectionsService @Inject constructor(val repo: ElectionsRepo) {

  fun getElections(): Set<ElectionOverview> {
    return repo.getElections().mapToDto()
  }

  fun addElection(newElection: NewElection) {
    val election = Election().apply {
      id = 1
      date = newElection.date

      newElection.abstimmungen.forEach {
        addAbstimmung(Abstimmung().apply {
          id = 7
          description = it.description
        })
      }
    }

    repo.addElection(election)
  }
}