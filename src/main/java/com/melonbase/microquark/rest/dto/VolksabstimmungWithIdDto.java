package com.melonbase.microquark.rest.dto;

import java.time.LocalDate;
import java.util.List;

public class VolksabstimmungWithIdDto {

  private int id;
  private LocalDate datum;
  private List<VorlageWithIdDto> vorlagen;

  public VolksabstimmungWithIdDto(int id, LocalDate datum, List<VorlageWithIdDto> vorlagen) {
    this.id = id;
    this.datum = datum;
    this.vorlagen = vorlagen;
  }

  public int getId() {
    return id;
  }

  public LocalDate getDatum() {
    return datum;
  }

  public List<VorlageWithIdDto> getVorlagen() {
    return vorlagen;
  }
}
