package com.melonbase.microquark.repo;

import java.util.HashSet;
import java.util.Set;

public class DataRoot {

  private final Set<Election> elections = new HashSet<>();

  public Set<Election> getElections() {
    return elections;
  }

  public void addElection(Election election) {
    elections.add(election);
  }
}