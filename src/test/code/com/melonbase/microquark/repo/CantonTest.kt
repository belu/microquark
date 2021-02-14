package com.melonbase.microquark.repo

import com.melonbase.microquark.repo.data.Canton
import kotlin.test.Test
import kotlin.test.assertEquals

class CantonTest {

  @Test
  internal fun correctNumberOfCantons() {
    assertEquals(26, Canton.values().size)
  }

  @Test
  internal fun correctTotalInhabitants() {
    assertEquals(8_551_744, Canton.values().sumOf { it.inhabitants })
  }
}