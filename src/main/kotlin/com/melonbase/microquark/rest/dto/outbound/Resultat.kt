package com.melonbase.microquark.rest.dto.outbound

import io.quarkus.runtime.annotations.RegisterForReflection
import java.math.BigDecimal

@RegisterForReflection
data class Resultat private constructor(
  val einwohner: Int,
  val abgegebeneStimmen: Int,
  val wahlbeteiligungProzent: BigDecimal,
  val jaStimmen: Int,
  val neinStimmen: Int,
  val jaProzent: BigDecimal,
  val neinProzent: BigDecimal
) {

  data class Builder(
    var einwohner: Int = 0,
    var abgegebeneStimmen: Int = 0,
    var wahlbeteiligungProzent: BigDecimal = BigDecimal.ZERO,
    var jaStimmen: Int = 0,
    var neinStimmen: Int = 0,
    var jaProzent: BigDecimal = BigDecimal.ZERO,
    var neinProzent: BigDecimal = BigDecimal.ZERO
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