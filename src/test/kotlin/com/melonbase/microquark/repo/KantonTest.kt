package com.melonbase.microquark.repo

import com.melonbase.microquark.repo.data.Kanton
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KantonTest {

  @Test
  fun correctNumberOfCantons() {
    assertEquals(26, Kanton.values().size)
  }

  @Test
  fun correctTotalInhabitants() {
    assertEquals(8_551_744, Kanton.values().sumOf { it.einwohner })
  }
}