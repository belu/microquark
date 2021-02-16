package com.melonbase.microquark.repo.data;

public class Vorlage {

  private int id;
  private String beschreibung;
  private Wahlresultat wahlresultat;

  public Vorlage(int id, String beschreibung) {
    this.id = id;
    this.beschreibung = beschreibung;
  }

  public int getId() {
    return id;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public Wahlresultat getWahlresultat() {
    return wahlresultat;
  }

  public void setWahlresultat(Wahlresultat wahlresultat) {
    this.wahlresultat = wahlresultat;
  }
}
