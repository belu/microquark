package com.melonbase.microquark.rest.dto.outbound

import java.math.BigDecimal

data class Resultat(
  val einwohner: Int,
  val abgegebeneStimmen: Int,
  val wahlbeteiligungProzent: BigDecimal,
  val jaStimmen: Int,
  val neinStimmen: Int,
  val jaProzent: BigDecimal,
  val neinProzent: BigDecimal
) {

  class Builder {

    private var einwohner: Int? = null
    private var abgegebeneStimmen: Int? = null
    private var wahlbeteiligungProzent: BigDecimal? = null
    private var jaStimmen: Int? = null
    private var neinStimmen: Int? = null
    private var jaProzent: BigDecimal? = null
    private var neinProzent: BigDecimal? = null

    fun einwohner(einwohner: Int?): Builder {
      this.einwohner = einwohner
      return this
    }

    fun abgegebeneStimmen(abgegebeneStimmen: Int?): Builder {
      this.abgegebeneStimmen = abgegebeneStimmen
      return this
    }

    fun wahlbeteiligungProzent(wahlbeteiligungProzent: BigDecimal?): Builder {
      this.wahlbeteiligungProzent = wahlbeteiligungProzent
      return this
    }

    fun jaStimmen(jaStimmen: Int?): Builder {
      this.jaStimmen = jaStimmen
      return this
    }

    fun neinStimmen(neinStimmen: Int?): Builder {
      this.neinStimmen = neinStimmen
      return this
    }

    fun jaProzent(jaProzent: BigDecimal?): Builder {
      this.jaProzent = jaProzent
      return this
    }

    fun neinProzent(neinProzent: BigDecimal?): Builder {
      this.neinProzent = neinProzent
      return this
    }

    fun build(): Resultat {
      return Resultat(
        einwohner!!, abgegebeneStimmen!!, wahlbeteiligungProzent!!,
        jaStimmen!!, neinStimmen!!, jaProzent!!, neinProzent!!
      )
    }
  }
}