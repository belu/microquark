package com.melonbase.microquark.repo

import kotlin.test.Test
import kotlin.test.assertEquals

class KantonTest {

  @Test
  internal fun correctNumberOfKantone() {
    assertEquals(26, Kanton.values().size)
  }

  @Test
  internal fun correctTotalInhabitants() {
    assertEquals(8_551_744, Kanton.values().sumOf { it.inhabitants })
  }
}