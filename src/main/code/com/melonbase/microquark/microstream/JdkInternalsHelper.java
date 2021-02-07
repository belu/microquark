package com.melonbase.microquark.microstream;

import one.microstream.memory.sun.JdkInternals;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Prints names of all static fields in JdkInternals.
 * <p>
 * As Decompilation of the microstream source code is not (yet) allowed,
 * this is a way to identify the fields hat have to be substituted for native build.
 */
class JdkInternalsHelper {

  public static void main(String[] args) {
    var fields = JdkInternals.class.getDeclaredFields();
    Arrays.stream(fields)
        .filter(field -> Modifier.isStatic(field.getModifiers()))
        .forEach(System.out::println);
  }
}
