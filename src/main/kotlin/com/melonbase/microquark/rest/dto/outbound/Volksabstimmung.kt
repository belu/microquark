package com.melonbase.microquark.rest.dto.outbound

import io.quarkus.runtime.annotations.RegisterForReflection
import java.time.LocalDate

@RegisterForReflection
data class Volksabstimmung(val datum: LocalDate, val vorlagen: List<String>)