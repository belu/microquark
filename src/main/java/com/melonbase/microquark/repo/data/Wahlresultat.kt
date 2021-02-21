package com.melonbase.microquark.repo.data;

import one.microstream.reference.Lazy;

import java.util.List;
import java.util.Map;

public class Wahlresultat {

  private Lazy<Map<Kanton, List<Boolean>>> stimmenByKanton;

  public Wahlresultat(Map<Kanton, List<Boolean>> stimmenByKanton) {
    this.stimmenByKanton = Lazy.Reference(stimmenByKanton);
  }

  public Map<Kanton, List<Boolean>> getStimmenByKanton() {
    return Lazy.get(stimmenByKanton);
  }
}
