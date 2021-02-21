package com.melonbase.microquark.rest.dto.inbound

import java.time.LocalDate

data class NeueVolksabstimmung(val datum: LocalDate, val vorlagen: List<String>) {

  // Jackson
  constructor() : this(LocalDate.EPOCH, listOf())
}