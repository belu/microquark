package com.melonbase.microquark.service

import com.melonbase.microquark.repo.ElectionsRepo
import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.rest.mapping.mapToDto
import java.time.LocalDate
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ElectionsService @Inject constructor(val repo: ElectionsRepo) {

  fun getVolksabstimmungen(): Set<VolksabstimmungDto> {
    return repo.getVolksabstimmungen().mapToDto()
  }

  fun getVolksabstimmung(datum: LocalDate): VolksabstimmungDto? {
    return repo.getVolksabstimmung(datum)?.mapToDto()
  }

  fun deleteVolksabstimmung(datum: LocalDate): ServiceResult<Nothing> {
    return repo.deleteVolksabstimmung(datum)
  }

  fun addVolksabstimmung(volksabstimmung: VolksabstimmungDto): ServiceResult<VolksabstimmungDto> {
    return repo.addVolksabstimmung(volksabstimmung).mapToDto()
  }

  fun performAbstimmung(datum: LocalDate): ServiceResult<Nothing> {
    return repo.performAbstimmung(datum)
  }

  fun getResult(datum: LocalDate): ServiceResult<String> {
    return repo.getResult(datum)
  }
}