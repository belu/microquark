package com.melonbase.microquark.substitutions;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.RecomputeFieldValue.Kind;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "one.microstream.memory.sun.JdkInternals")
final class Target_one_microstream_memory_sun_JdkInternals0 {
  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.nio.Buffer",
      name = "address"
  )
  static long FIELD_OFFSET_Buffer_address;
}

@TargetClass(className = "one.microstream.memory.sun.JdkInternals")
final class Target_one_microstream_memory_sun_JdkInternals1 {
  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "java.nio.DirectByteBuffer",
      name = "cleaner"
  )
  static long FIELD_OFFSET_DirectByteBuffer_cleaner;
}

@TargetClass(className = "one.microstream.memory.sun.JdkInternals")
final class Target_one_microstream_memory_sun_JdkInternals2 {
  @Alias
  @RecomputeFieldValue(
      kind = Kind.FieldOffset,
      declClassName = "jdk.internal.ref.Cleaner", // "sun.misc.Cleaner"
      name = "thunk"
  )
  static long FIELD_OFFSET_Cleaner_thunk;
}

public class MicrostreamSubstitutions {
}
