# Native Image

Quick guide on how to build the native image. This has been tested
for Linux and macOS only.

1. Setup GraalVM: https://www.graalvm.org/docs/getting-started/

2. Install the `native-image` add-on: `gu install native-image`

3. Build: `mvn clean package -Pnative`

4. Run: `./target/microquark-1.0.0-SNAPSHOT-runner`

**NOTE**: The generated image could be made much smaller. However, for convenience Swagger is also
included in PROD mode. Furthermore, all the dependencies for the different storage targets are also
added to the native-image. This makes it easier for you to try out the different storages.

Go back to [Getting started](HOWTO.md) on how to add some data!