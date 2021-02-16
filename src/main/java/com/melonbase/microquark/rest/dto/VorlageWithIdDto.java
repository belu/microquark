package com.melonbase.microquark.rest.dto;

public class VorlageWithIdDto {

  private int id;
  private String beschreibung;

  public VorlageWithIdDto(int id, String beschreibung) {
    this.id = id;
    this.beschreibung = beschreibung;
  }

  public int getId() {
    return id;
  }

  public String getBeschreibung() {
    return beschreibung;
  }
}
