package com.melonbase.microquark.rest.dto.outbound

import com.melonbase.microquark.repo.data.Kanton
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class VorlageResultat(
  val beschreibung: String,
  val bundesresultat: Resultat,
  val kantonsresultate: Map<Kanton, Resultat>
)