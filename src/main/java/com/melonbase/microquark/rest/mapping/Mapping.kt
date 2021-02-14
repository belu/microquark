package com.melonbase.microquark.rest.mapping

import com.melonbase.microquark.repo.data.Volksabstimmung
import com.melonbase.microquark.repo.data.Vorlage
import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.rest.dto.VolksabstimmungWithIdDto
import com.melonbase.microquark.rest.dto.VorlageDto
import com.melonbase.microquark.rest.dto.VorlageWithIdDto
import java.util.stream.Collectors

fun Set<Volksabstimmung>.mapToDto(): Set<VolksabstimmungWithIdDto> {
  return this.stream().map { it.mapToDto() }.collect(Collectors.toUnmodifiableSet())
}

fun Volksabstimmung.mapToDto(): VolksabstimmungWithIdDto {
  return VolksabstimmungWithIdDto(this.id, this.datum, this.vorlagen.mapToDto())
}

fun List<Vorlage>.mapToDto(): List<VorlageWithIdDto> {
  return this.stream().map { it.mapToDto() }.collect(Collectors.toUnmodifiableList())
}

fun Vorlage.mapToDto(): VorlageWithIdDto {
  return VorlageWithIdDto(this.id, this.beschreibung, this.stimmbeteiligung)
}