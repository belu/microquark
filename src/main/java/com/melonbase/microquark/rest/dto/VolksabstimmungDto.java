package com.melonbase.microquark.rest.dto;

import java.time.LocalDate;
import java.util.List;

public class VolksabstimmungDto {

  private LocalDate datum;
  private List<String> vorlagen;

  public VolksabstimmungDto() {
    // Jackson
  }

  public VolksabstimmungDto(LocalDate datum, List<String> vorlagen) {
    this.datum = datum;
    this.vorlagen = vorlagen;
  }

  public LocalDate getDatum() {
    return datum;
  }

  public List<String> getVorlagen() {
    return vorlagen;
  }
}
