package com.melonbase.microquark.rest.dto.outbound

import com.melonbase.microquark.repo.data.Kanton

data class VorlageResultat(
  val beschreibung: String,
  val bundesresultat: Resultat,
  val kantonsresultate: Map<Kanton, Resultat>
)