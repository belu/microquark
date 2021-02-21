package com.melonbase.microquark.rest.dto

import java.time.LocalDate

data class VolksabstimmungResultatDto(val datum: LocalDate, val vorlageResultate: List<VorlageResultatDto>)