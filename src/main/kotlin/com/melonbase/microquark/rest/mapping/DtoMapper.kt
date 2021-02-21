package com.melonbase.microquark.rest.mapping

import com.melonbase.microquark.repo.data.Volksabstimmung
import com.melonbase.microquark.repo.data.Vorlage
import java.util.stream.Collectors

fun Set<Volksabstimmung>.mapToDto(): Set<com.melonbase.microquark.rest.dto.outbound.Volksabstimmung> {
  return this.stream().map { it.mapToDto() }.collect(Collectors.toUnmodifiableSet())
}

fun Volksabstimmung.mapToDto(): com.melonbase.microquark.rest.dto.outbound.Volksabstimmung {
  return com.melonbase.microquark.rest.dto.outbound.Volksabstimmung(
    this.datum,
    this.vorlagen.mapToDto()
  )
}

fun List<Vorlage>.mapToDto(): List<String> {
  return this.stream().map { it.beschreibung }.collect(Collectors.toUnmodifiableList())
}