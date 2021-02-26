# Native Image

Quick guide on how to build the native image. This has been tested
for Linux and macOS only.

1. Setup GraalVM: https://www.graalvm.org/docs/getting-started/

2. Install the `native-image` add-on: `gu install native-image`

3. Build: `mvn clean package -Pnative`

4. Run: `./target/microquark-1.0.0-SNAPSHOT-runner`