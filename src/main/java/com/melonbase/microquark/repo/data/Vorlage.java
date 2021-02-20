package com.melonbase.microquark.repo.data;

public class Vorlage {

  private String beschreibung;
  private Wahlresultat wahlresultat;

  public Vorlage(String beschreibung) {
    this.beschreibung = beschreibung;
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
