package com.melonbase.microquark.microstream

import one.microstream.memory.sun.JdkInternals
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.*

/**
 * Prints names of all static fields in JdkInternals.
 *
 *
 * As Decompilation of the microstream source code is not (yet) allowed,
 * this is a way to identify the fields that have to be substituted for native build.
 */
internal object JdkInternalsHelper {

  @JvmStatic
  fun main(args: Array<String>) {
    val fields = JdkInternals::class.java.declaredFields
    Arrays.stream(fields)
      .filter { field: Field -> Modifier.isStatic(field.modifiers) }
      .forEach { x: Field? -> println(x) }
  }
}