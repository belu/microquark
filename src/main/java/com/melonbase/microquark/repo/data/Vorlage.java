package com.melonbase.microquark.repo.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Vorlage {

  private int id;
  private String beschreibung;
  private BigDecimal stimmbeteiligung;
  private Map<Kanton, List<Boolean>> stimmenByKanton;

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

  public Map<Kanton, List<Boolean>> getStimmenByKanton() {
    return stimmenByKanton;
  }

  public void setStimmenByKanton(Map<Kanton, List<Boolean>> stimmenByKanton) {
    this.stimmenByKanton = stimmenByKanton;
  }
}
