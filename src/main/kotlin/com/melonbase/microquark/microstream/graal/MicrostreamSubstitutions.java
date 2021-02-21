package com.melonbase.microquark.microstream.graal;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.RecomputeFieldValue.Kind;
import com.oracle.svm.core.annotate.TargetClass;
import one.microstream.memory.sun.JdkInternals;
import one.microstream.memory.sun.SunJdk8Internals;

@TargetClass(SunJdk8Internals.class)
final class Target_one_microstream_memory_sun_SunJdk8Internals {

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.ArrayList",
      name = "elementData"
  )
  static long OFFSET_ArrayList_elementData;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.ArrayList",
      name = "size"
  )
  static long OFFSET_ArrayList_size;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.HashSet",
      name = "map"
  )
  static long OFFSET_HashSet_map;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.HashMap",
      name = "loadFactor"
  )
  static long OFFSET_HashMap_loadFactor;


  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.Hashtable",
      name = "loadFactor"
  )
  static long OFFSET_Hashtable_loadFactor;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      // loadFactor is not defined in LinkedHashMap but in superclass HashMap
      declClassName = "java.util.HashMap",
      name = "loadFactor"
  )
  static long OFFSET_LinkedHashMap_loadFactor;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.LinkedHashMap",
      name = "accessOrder"
  )
  static long OFFSET_LinkedHashMap_accessOrder;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.PriorityQueue",
      name = "queue"
  )
  static long OFFSET_PriorityQueue_queue;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.PriorityQueue",
      name = "size"
  )
  static long OFFSET_PriorityQueue_size;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.Vector",
      name = "elementData"
  )
  static long OFFSET_Vector_elementData;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.Vector",
      name = "elementCount"
  )
  static long OFFSET_Vector_elementCount;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.Vector",
      name = "capacityIncrement"
  )
  static long OFFSET_Vector_capacityIncrement;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.util.Properties",
      name = "defaults"
  )
  static long OFFSET_Properties_Defaults;
}

@TargetClass(JdkInternals.class)
final class Target_one_microstream_memory_sun_JdkInternals {

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.nio.Buffer",
      name = "address"
  )
  static long FIELD_OFFSET_Buffer_address;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.nio.DirectByteBuffer",
      name = "cleaner"
  )
  static long FIELD_OFFSET_DirectByteBuffer_cleaner;

  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "jdk.internal.ref.Cleaner",
      name = "thunk"
  )
  static long FIELD_OFFSET_Cleaner_thunk;
}
