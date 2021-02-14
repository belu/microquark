package com.melonbase.microquark.rest.dto.out;

import java.time.LocalDate;

public class ElectionOverview {

  private int id;
  private LocalDate date;

  public ElectionOverview(int id, LocalDate date) {
    this.id = id;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public LocalDate getDate() {
    return date;
  }
}
