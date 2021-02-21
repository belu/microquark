package com.melonbase.microquark.repo;

import com.melonbase.microquark.repo.data.Kanton;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KantonTest {

  @Test
  void correctNumberOfCantons() {
    assertEquals(26, Kanton.values().length);
  }

  @Test
  void correctTotalInhabitants() {
    assertEquals(8_551_744, Stream.of(Kanton.values())
        .mapToLong(Kanton::getEinwohner)
        .sum());
  }
}
