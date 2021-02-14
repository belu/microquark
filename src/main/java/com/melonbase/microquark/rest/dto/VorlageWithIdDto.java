package com.melonbase.microquark.rest.dto;

import java.math.BigDecimal;

public class VorlageWithIdDto {

  private int id;
  private String beschreibung;
  private BigDecimal stimmbeteiligung;

  public VorlageWithIdDto(int id, String beschreibung, BigDecimal stimmbeteiligung) {
    this.id = id;
    this.beschreibung = beschreibung;
    this.stimmbeteiligung = stimmbeteiligung;
  }

  public int getId() {
    return id;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public BigDecimal getStimmbeteiligung() {
    return stimmbeteiligung;
  }
}
