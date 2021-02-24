package com.melonbase.microquark.repo.data

import one.microstream.reference.Lazy

class Vorlage(var beschreibung: String) {

  private var wahlresultat: Lazy<Wahlresultat> = Lazy.Reference(null)

  fun getWahlresultat(): Wahlresultat? = Lazy.get(wahlresultat)

  fun setWahlresultat(wahlresultat: Wahlresultat) {
    this.wahlresultat = Lazy.Reference(wahlresultat)
  }
}