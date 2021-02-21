package com.melonbase.microquark.rest.dto;

import com.melonbase.microquark.repo.data.Kanton;

import java.util.Collections;
import java.util.Map;

public class VorlageResultatDto {

  public final String beschreibung;
  public final ResultatDto bundesresultat;
  public final Map<Kanton, ResultatDto> kantonsresultate;

  public VorlageResultatDto(String beschreibung, ResultatDto bundesresultat, Map<Kanton, ResultatDto> kantonsresultate) {
    this.beschreibung = beschreibung;
    this.bundesresultat = bundesresultat;
    this.kantonsresultate = Collections.unmodifiableMap(kantonsresultate);
  }
}
