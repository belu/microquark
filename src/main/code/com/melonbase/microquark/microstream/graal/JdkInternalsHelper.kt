package com.melonbase.microquark.microstream.graal

import one.microstream.memory.sun.JdkInternals
import java.lang.reflect.Modifier
import java.util.*

/**
 * Prints names of all static fields in JdkInternals.
 *
 * As MicroStream is not open sourced yet, this is a way to legally identify the fields that
 * have to be substituted for native build. See class MicrostreamSubstitutions.
 */
internal object JdkInternalsHelper {

  @JvmStatic
  fun main(args: Array<String>) {
    val fields = JdkInternals::class.java.declaredFields
    Arrays.stream(fields)
      .filter { field -> Modifier.isStatic(field.modifiers) }
      .forEach { field -> println(field) }
  }
}