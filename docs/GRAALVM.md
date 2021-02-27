# GraalVM integration

DISCLAIMER: This is my first real project that uses GraalVM. Therefore, probably not everything is solved in the best
way. If you see something that could be done better please let me know!

## Substitutions

MicroStream uses `sun.misc.Unsafe` in several places, for example in the class
`one.microstream.memory.sun.JdkInternals`. `sun.misc.Unsafe` is used to compute the offsets of fields for some classes.
This leads to the following warnings during the native build:

```
Warning: RecomputeFieldValue.FieldOffset automatic substitution failed. The automatic substitution
registration was attempted because a call to jdk.internal.misc.Unsafe.objectFieldOffset(Field) was
detected in the static initializer of one.microstream.memory.sun.JdkInternals.
Detailed failure reason(s): The argument of Unsafe.objectFieldOffset(Field) is not a constant field.,
Could not determine the field where the value produced by the call to
jdk.internal.misc.Unsafe.objectFieldOffset(Field) for the field offset computation is stored.
The call is not directly followed by a field store or by a sign extend node followed directly by
a field store.
```

Because **MicroStream** does not use a standard-pattern to calculate the offsets with `sun.misc.Unsafe`, this could not
be handled automatically by `native-image`. As a consequence, the generated executable crashes with a segmentation
fault.

In the blog of the GraalVM team I found a solution for this problem:
https://medium.com/graalvm/instant-netty-startup-using-graalvm-native-image-generation-ed6f14ff7692

With this hint I was able to implement the correct Substitutions (see
[MicrostreamSubstitutions.java](../src/main/kotlin/com/melonbase/microquark/microstream/graal/MicrostreamSubstitutions.java))
.

The native executable was able to start now.

## Reflection

The joy only lasted for a short time. When the storage was started, the application crashed with an exception:

```
java.lang.IllegalArgumentException: Class one.microstream.afs.nio.NioReadableFile$Default[] is
instantiated reflectively but was never registered. Register the class by using
org.graalvm.nativeimage.hosted.RuntimeReflection
```

Seems some classes are instantiated via Reflection but have not been registered. First, I tried to register the classes
manually (via `reflect-config.json`). This took no end, therefore I was searching for a better solution.

I found the solution in another blog article by the GraalVM team...

## The Tracing Agent

One can use the Tracing Agent to generate all the necessary configs, including the
[reflect-config.json](../src/main/resources/META-INF/native-image/reflect-config.json).

This is explained in the following article:
https://medium.com/graalvm/introducing-the-tracing-agent-simplifying-graalvm-native-image-configuration-c3b56c486271

Also in the Native Image Manual:
https://www.graalvm.org/reference-manual/native-image/BuildConfiguration/#assisted-configuration-of-native-image-builds

For this to work you need [GraalVM installed locally](https://www.graalvm.org/docs/getting-started/)
on your machine. Then you just package your application:

```shell script
mvn clean package
```

Run the application with the agent (`java` is the one from GraalVM):

```shell script
java -agentlib:native-image-agent=config-merge-dir=src/main/resources/META-INF/native-image -jar target/quarkus-app/quarkus-run.jar
```

All the configs will be generated for you.

Note that whilst your application is running, you should enter every possible code path that could occur. This assures
that the agent catches all required things. Ideally, this is done with automated tests.

You can stop and start the application JAR several times. The configs will be merged automatically for you.

When trying the agent with the merge feature (`config-merge-dir`), it sometimes did not behave as expected.
For `java.util.Optional` the `allowUnsafeAccess` for the field `value` was not merged correctly.

You find details in the following bug report:
https://github.com/oracle/graal/issues/3192

## Success

Finally, MicroStream on Quarkus was running fine (and fast!) with GraalVM.

After this first success, I started adding different storage targets (PostgreSQL, MariaDB, MongoDB, etc.). All of them
could be integrated with the help of the tracing agent.