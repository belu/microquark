package com.melonbase.microquark.repo.data;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Election {

  private int id;

  private LocalDate date = LocalDate.EPOCH;

  private Set<Abstimmung> abstimmungen = new HashSet<>();

  private Map<Canton, Result> resultsByCanton = new EnumMap<>(Canton.class);

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Set<Abstimmung> getAbstimmungen() {
    return abstimmungen;
  }

  public void addAbstimmung(Abstimmung abstimmung) {
    abstimmungen.add(abstimmung);
  }

  public Map<Canton, Result> getResultsByCanton() {
    return resultsByCanton;
  }

  public void addResultByCanton(Canton canton, Result result) {
    resultsByCanton.put(canton, result);
  }
}