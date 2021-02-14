package com.melonbase.microquark.rest.dto;

import java.time.LocalDate;
import java.util.List;

public class VolksabstimmungDto {

  private LocalDate datum;
  private List<VorlageDto> vorlagen;

  public VolksabstimmungDto() {
    // Jackson
  }

  public VolksabstimmungDto(LocalDate datum, List<VorlageDto> vorlagen) {
    this.datum = datum;
    this.vorlagen = vorlagen;
  }

  public LocalDate getDatum() {
    return datum;
  }

  public List<VorlageDto> getVorlagen() {
    return vorlagen;
  }
}
