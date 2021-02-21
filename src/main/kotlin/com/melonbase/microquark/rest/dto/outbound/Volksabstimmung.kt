package com.melonbase.microquark.rest.dto.outbound

import java.time.LocalDate

data class Volksabstimmung(val datum: LocalDate, val vorlagen: List<String>)