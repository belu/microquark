package com.melonbase.microquark.repo.data;

import java.util.ArrayList;
import java.util.List;

public class Result {

  private List<Voting> votings = new ArrayList<>();

  public List<Voting> getVotings() {
    return votings;
  }

  public void addVote(Voting voting) {
    votings.add(voting);
  }
}