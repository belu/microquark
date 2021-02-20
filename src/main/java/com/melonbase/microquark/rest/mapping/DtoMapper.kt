package com.melonbase.microquark.rest.mapping

import com.melonbase.microquark.repo.data.Volksabstimmung
import com.melonbase.microquark.repo.data.Vorlage
import com.melonbase.microquark.rest.dto.VolksabstimmungDto
import com.melonbase.microquark.service.NotFoundResult
import com.melonbase.microquark.service.RejectedResult
import com.melonbase.microquark.service.ServiceResult
import com.melonbase.microquark.service.SuccessResult
import com.melonbase.microquark.service.SuccessWithDataResult
import java.util.stream.Collectors

fun Set<Volksabstimmung>.mapToDto(): Set<VolksabstimmungDto> {
  return this.stream().map { it.mapToDto() }.collect(Collectors.toUnmodifiableSet())
}

fun Volksabstimmung.mapToDto(): VolksabstimmungDto {
  return VolksabstimmungDto(this.datum, this.vorlagen.mapToDto())
}

fun List<Vorlage>.mapToDto(): List<String> {
  return this.stream().map { it.beschreibung }.collect(Collectors.toUnmodifiableList())
}

fun ServiceResult<Volksabstimmung>.mapToDto(): ServiceResult<VolksabstimmungDto> {
  return when (this) {
    is SuccessWithDataResult -> SuccessWithDataResult(this.entity.mapToDto())
    NotFoundResult -> NotFoundResult
    is RejectedResult -> RejectedResult(this.reason)
    SuccessResult -> SuccessResult
  }
}