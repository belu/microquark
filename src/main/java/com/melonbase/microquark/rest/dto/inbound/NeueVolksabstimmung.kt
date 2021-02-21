package com.melonbase.microquark.rest.dto.inbound;

import java.time.LocalDate;
import java.util.List;

public class NeueVolksabstimmung {

  private LocalDate datum;
  private List<String> vorlagen;

  public NeueVolksabstimmung() {
    // Jackson
  }

  public NeueVolksabstimmung(LocalDate datum, List<String> vorlagen) {
    this.datum = datum;
    this.vorlagen = vorlagen;
  }

  public LocalDate getDatum() {
    return datum;
  }

  public List<String> getVorlagen() {
    return vorlagen;
  }

  @Override
  public String toString() {
    return "VolksabstimmungDto{" +
        "datum=" + datum +
        '}';
  }
}
