package com.melonbase.microquark.repo.data;

import java.util.HashMap;
import java.util.Map;

public class Voting {

  private Map<Integer, Boolean> votesByAbstimmungId = new HashMap<>();

  public Map<Integer, Boolean> getVotesByAbstimmungId() {
    return votesByAbstimmungId;
  }

  public void addVote(int abstimmungId, boolean vote) {
    votesByAbstimmungId.put(abstimmungId, vote);
  }
}