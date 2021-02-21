package com.melonbase.microquark.repo.data

import one.microstream.reference.Lazy

class Wahlresultat(stimmenByKanton: Map<Kanton, List<Boolean>>) {

  private val stimmenByKanton: Lazy<Map<Kanton, List<Boolean>>> = Lazy.Reference(stimmenByKanton)

  fun getStimmenByKanton(): Map<Kanton, List<Boolean>> {
    return Lazy.get(stimmenByKanton)
  }
}