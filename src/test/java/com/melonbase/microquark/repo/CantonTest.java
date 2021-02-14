package com.melonbase.microquark.repo;

import com.melonbase.microquark.repo.data.Canton;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CantonTest {

  @Test
  void correctNumberOfCantons() {
    assertEquals(26, Canton.values().length);
  }

  @Test
  void correctTotalInhabitants() {
    assertEquals(8_551_744, Stream.of(Canton.values())
        .mapToLong(canton -> canton.getInhabitants())
        .sum());
  }
}
