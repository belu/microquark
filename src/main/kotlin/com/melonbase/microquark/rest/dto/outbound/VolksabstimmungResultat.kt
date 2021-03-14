package com.melonbase.microquark.rest.dto.outbound

import io.quarkus.runtime.annotations.RegisterForReflection
import java.time.LocalDate

@RegisterForReflection
data class VolksabstimmungResultat(val datum: LocalDate, val vorlageResultate: List<VorlageResultat>)