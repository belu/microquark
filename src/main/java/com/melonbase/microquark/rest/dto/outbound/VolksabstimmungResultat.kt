package com.melonbase.microquark.rest.dto.outbound

import java.time.LocalDate

data class VolksabstimmungResultat(val datum: LocalDate, val vorlageResultate: List<VorlageResultat>)