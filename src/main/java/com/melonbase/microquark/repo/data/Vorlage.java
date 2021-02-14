package com.melonbase.microquark.repo.data;

import java.math.BigDecimal;

public class Vorlage {

  private int id;
  private String beschreibung;
  private BigDecimal stimmbeteiligung;

  public Vorlage(int id, String beschreibung, BigDecimal stimmbeteiligung) {
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
