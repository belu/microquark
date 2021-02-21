package com.melonbase.microquark.repo.data

import java.time.LocalDate

class Volksabstimmung(var datum: LocalDate, var vorlagen: List<Vorlage> = mutableListOf())