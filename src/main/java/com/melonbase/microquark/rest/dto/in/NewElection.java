package com.melonbase.microquark.rest.dto.in;

import java.time.LocalDate;
import java.util.List;

public class NewElection {

  private LocalDate date;
  private List<NewAbstimmung> abstimmungen;

  public LocalDate getDate() {
    return date;
  }

  public List<NewAbstimmung> getAbstimmungen() {
    return abstimmungen;
  }
}
