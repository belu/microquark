package com.melonbase.microquark.repo.data

import one.microstream.reference.Lazy

class Vorlage(var beschreibung: String) {

  private var lazyWahlresultat: Lazy<Wahlresultat> = Lazy.Reference(null)

  fun getWahlresultat(): Wahlresultat? = Lazy.get(lazyWahlresultat)

  fun setWahlresultat(wahlresultat: Wahlresultat) {
    lazyWahlresultat = Lazy.Reference(wahlresultat)
  }
}