package com.melonbase.microquark.rest.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class VolksabstimmungResultatDto {

  public final LocalDate datum;
  public final List<VorlageResultatDto> vorlageResultate;

  public VolksabstimmungResultatDto(LocalDate datum, List<VorlageResultatDto> vorlageResultate) {
    this.datum = datum;
    this.vorlageResultate = Collections.unmodifiableList(vorlageResultate);
  }
}
