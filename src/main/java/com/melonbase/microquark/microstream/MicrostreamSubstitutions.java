package com.melonbase.microquark.microstream;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.RecomputeFieldValue.Kind;
import com.oracle.svm.core.annotate.TargetClass;
import one.microstream.memory.sun.JdkInternals;

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

public class MicrostreamSubstitutions {
}
