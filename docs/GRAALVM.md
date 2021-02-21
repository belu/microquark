# GraalVM integration

## Substitutions

Necessary because of Unsafe memory access.
TODO Show WARNING log.

## How to update the native build configs

If you change code in the application, you probably have to update the configs for
the native build.

For this to work you need GraalVM installed locally on your machine. For instructions have
a look at the GraalVM documentation:
https://www.graalvm.org/docs/getting-started/#install-graalvm

1. Build the application JAR:

```shell script
mvn clean compile package
```

2. Run the application JAR and let Graal do the heavy work to create the native build configs:

```shell script
java -agentlib:native-image-agent=config-merge-dir=src/main/resources/META-INF/native-image -jar target/*-runner.jar
```

Note that whilst your application is running, you should enter every possible code path that
could occure. This assures that Graal catches all configs that are required.
Ideally, this is done with automated tests.
