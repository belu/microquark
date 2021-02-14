package com.melonbase.microquark.rest.mapping

import com.melonbase.microquark.repo.data.Election
import com.melonbase.microquark.rest.dto.out.ElectionOverview
import java.util.stream.Collectors

fun Set<Election>.mapToDto(): Set<ElectionOverview> {
  return this.stream().map { it.mapToDto() }.collect(Collectors.toUnmodifiableSet())
}

fun Election.mapToDto(): ElectionOverview {
  return ElectionOverview(this.id, this.date)
}