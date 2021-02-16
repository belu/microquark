package com.melonbase.microquark.repo.data;

import java.time.LocalDate;
import java.util.List;

public class Volksabstimmung {

  private int id;
  private LocalDate datum;
  private List<Vorlage> vorlagen;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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
