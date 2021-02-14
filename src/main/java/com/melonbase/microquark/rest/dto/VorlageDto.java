package com.melonbase.microquark.rest.dto;

import java.math.BigDecimal;

public class VorlageDto {

  private String beschreibung;
  private BigDecimal stimmbeteiligung;

  public VorlageDto() {
    // Jackson
  }

  public VorlageDto(String beschreibung, BigDecimal stimmbeteiligung) {
    this.beschreibung = beschreibung;
    this.stimmbeteiligung = stimmbeteiligung;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public BigDecimal getStimmbeteiligung() {
    return stimmbeteiligung;
  }
}
