package com.melonbase.microquark.repo.data;

import java.util.HashSet;
import java.util.Set;

public class DataRoot {

  private Set<Volksabstimmung> volksabstimmungen = new HashSet<>();

  public Set<Volksabstimmung> getVolksabstimmungen() {
    return volksabstimmungen;
  }
}
