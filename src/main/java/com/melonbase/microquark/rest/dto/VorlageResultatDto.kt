package com.melonbase.microquark.rest.dto

import com.melonbase.microquark.repo.data.Kanton

data class VorlageResultatDto(
  val beschreibung: String,
  val bundesresultat: ResultatDto,
  val kantonsresultate: Map<Kanton, ResultatDto>
)