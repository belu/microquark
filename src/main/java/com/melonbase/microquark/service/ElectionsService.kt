package com.melonbase.microquark.service

import com.melonbase.microquark.repo.ElectionsRepo
import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.rest.dto.VolksabstimmungWithIdDto
import com.melonbase.microquark.rest.mapping.mapToDto
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ElectionsService @Inject constructor(val repo: ElectionsRepo) {

  fun getVolksabstimmungen(): Set<VolksabstimmungWithIdDto> {
    return repo.getVolksabstimmungen().mapToDto()
  }

  fun addVolksabstimmung(volksabstimmung: VolksabstimmungDto): VolksabstimmungWithIdDto {
    return repo.addVolksabstimmung(volksabstimmung).mapToDto()
  }

  fun getVolksabstimmung(id: Int): VolksabstimmungWithIdDto? {
    return repo.getVolksabstimmung(id)?.mapToDto()
  }

  fun performAbstimmung(id: Int): ServiceResult {
    return repo.performAbstimmung(id)
  }

  fun getResult(id: Int): ServiceResult {
    return repo.getResult(id)
  }
}