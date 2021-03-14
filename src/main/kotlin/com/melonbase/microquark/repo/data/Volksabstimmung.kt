package com.melonbase.microquark.repo.data

import java.time.LocalDate

class Volksabstimmung(val datum: LocalDate, val vorlagen: List<Vorlage> = emptyList())