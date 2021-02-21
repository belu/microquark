package com.melonbase.microquark.repo.data;

import java.time.LocalDate;
import java.util.List;

public class Volksabstimmung {

  private LocalDate datum;
  private List<Vorlage> vorlagen;

  public LocalDate getDatum() {
    return datum;
  }

  public void setDatum(LocalDate datum) {
    this.datum = datum;
  }

  public List<Vorlage> getVorlagen() {
    return vorlagen;
  }

  public void setVorlagen(List<Vorlage> vorlagen) {
    this.vorlagen = vorlagen;
  }
}
