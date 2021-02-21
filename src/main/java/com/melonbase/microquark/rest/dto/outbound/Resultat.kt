package com.melonbase.microquark.rest.dto.outbound

import java.math.BigDecimal

data class Resultat private constructor(
  val einwohner: Int?,
  val abgegebeneStimmen: Int?,
  val wahlbeteiligungProzent: BigDecimal?,
  val jaStimmen: Int?,
  val neinStimmen: Int?,
  val jaProzent: BigDecimal?,
  val neinProzent: BigDecimal?
) {

  data class Builder(
    var einwohner: Int? = null,
    var abgegebeneStimmen: Int? = null,
    var wahlbeteiligungProzent: BigDecimal? = null,
    var jaStimmen: Int? = null,
    var neinStimmen: Int? = null,
    var jaProzent: BigDecimal? = null,
    var neinProzent: BigDecimal? = null
  ) {

    fun einwohner(einwohner: Int) = apply { this.einwohner = einwohner }

    fun abgegebeneStimmen(abgegebeneStimmen: Int) = apply { this.abgegebeneStimmen = abgegebeneStimmen }

    fun wahlbeteiligungProzent(wahlbeteiligungProzent: BigDecimal) =
      apply { this.wahlbeteiligungProzent = wahlbeteiligungProzent }

    fun jaStimmen(jaStimmen: Int) = apply { this.jaStimmen = jaStimmen }

    fun neinStimmen(neinStimmen: Int) = apply { this.neinStimmen = neinStimmen }

    fun jaProzent(jaProzent: BigDecimal) = apply { this.jaProzent = jaProzent }

    fun neinProzent(neinProzent: BigDecimal) = apply { this.neinProzent = neinProzent }

    fun build() = Resultat(
      einwohner, abgegebeneStimmen, wahlbeteiligungProzent,
      jaStimmen, neinStimmen, jaProzent, neinProzent
    )
  }
}