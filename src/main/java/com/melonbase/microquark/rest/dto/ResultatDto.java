package com.melonbase.microquark.rest.dto;

import java.math.BigDecimal;

public class ResultatDto {

  public final Integer einwohner;
  public final Integer abgegebeneStimmen;
  public final BigDecimal wahlbeteiligungProzent;

  public final Integer jaStimmen;
  public final Integer neinStimmen;

  public final BigDecimal jaProzent;
  public final BigDecimal neinProzent;

  private ResultatDto(
      Integer einwohner,
      Integer abgegebeneStimmen,
      BigDecimal wahlbeteiligungProzent,
      Integer jaStimmen,
      Integer neinStimmen,
      BigDecimal jaProzent,
      BigDecimal neinProzent
  ) {
    this.einwohner = einwohner;
    this.abgegebeneStimmen = abgegebeneStimmen;
    this.wahlbeteiligungProzent = wahlbeteiligungProzent;
    this.jaStimmen = jaStimmen;
    this.neinStimmen = neinStimmen;
    this.jaProzent = jaProzent;
    this.neinProzent = neinProzent;
  }

  public static class Builder {

    private Integer einwohner;
    private Integer abgegebeneStimmen;
    private BigDecimal wahlbeteiligungProzent;
    private Integer jaStimmen;
    private Integer neinStimmen;
    private BigDecimal jaProzent;
    private BigDecimal neinProzent;

    public Builder einwohner(Integer einwohner) {
      this.einwohner = einwohner;
      return this;
    }

    public Builder abgegebeneStimmen(Integer abgegebeneStimmen) {
      this.abgegebeneStimmen = abgegebeneStimmen;
      return this;
    }

    public Builder wahlbeteiligungProzent(BigDecimal wahlbeteiligungProzent) {
      this.wahlbeteiligungProzent = wahlbeteiligungProzent;
      return this;
    }

    public Builder jaStimmen(Integer jaStimmen) {
      this.jaStimmen = jaStimmen;
      return this;
    }

    public Builder neinStimmen(Integer neinStimmen) {
      this.neinStimmen = neinStimmen;
      return this;
    }

    public Builder jaProzent(BigDecimal jaProzent) {
      this.jaProzent = jaProzent;
      return this;
    }

    public Builder neinProzent(BigDecimal neinProzent) {
      this.neinProzent = neinProzent;
      return this;
    }

    public ResultatDto build() {
      return new ResultatDto(einwohner, abgegebeneStimmen, wahlbeteiligungProzent,
          jaStimmen, neinStimmen, jaProzent, neinProzent);
    }
  }
}
