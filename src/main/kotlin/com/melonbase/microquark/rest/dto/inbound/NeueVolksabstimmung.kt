package com.melonbase.microquark.rest.dto.inbound

import io.quarkus.runtime.annotations.RegisterForReflection
import java.time.LocalDate

@RegisterForReflection
data class NeueVolksabstimmung(val datum: LocalDate, val vorlagen: List<String>)