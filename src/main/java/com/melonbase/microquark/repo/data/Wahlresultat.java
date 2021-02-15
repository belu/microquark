package com.melonbase.microquark.repo.data;

import java.util.List;
import java.util.Map;

public class Wahlresultat {

  private Map<Kanton, List<Boolean>> stimmenByKanton;

  public Wahlresultat(Map<Kanton, List<Boolean>> stimmenByKanton) {
    this.stimmenByKanton = stimmenByKanton;
  }

  public Map<Kanton, List<Boolean>> getStimmenByKanton() {
    return stimmenByKanton;
  }
}
