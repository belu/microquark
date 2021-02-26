package com.melonbase.microquark.service

import com.melonbase.microquark.repo.ElectionsRepo
import com.melonbase.microquark.rest.dto.inbound.NeueVolksabstimmung
import com.melonbase.microquark.rest.dto.outbound.Volksabstimmung
import com.melonbase.microquark.rest.dto.outbound.VolksabstimmungResultat
import java.time.LocalDate
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ElectionsService @Inject constructor(val repo: ElectionsRepo) {

  fun getVolksabstimmungen(): Set<Volksabstimmung> {
    return repo.getVolksabstimmungen()
  }

  fun getVolksabstimmung(datum: LocalDate): Volksabstimmung? {
    return repo.getVolksabstimmung(datum)
  }

  fun deleteVolksabstimmung(datum: LocalDate): Result<Nothing> {
    return repo.deleteVolksabstimmung(datum)
  }

  fun addVolksabstimmung(volksabstimmung: NeueVolksabstimmung): Result<Volksabstimmung> {
    return repo.addVolksabstimmung(volksabstimmung)
  }

  fun performAbstimmung(datum: LocalDate): Result<Nothing> {
    return repo.performAbstimmung(datum)
  }

  fun getResult(datum: LocalDate): Result<VolksabstimmungResultat> {
    return repo.getResult(datum)
  }
}