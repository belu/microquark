# Native Image

Quick guide on how to build a native image. This has been tested for Linux and macOS only.

## Build without GraalVM installed locally

If you run on Linux x64 build the native image with the following command inside a Docker container:
```shell script
mvn clean package -Pnative -Dquarkus.native.container-build=true
```

If you do not run on Linux x64, you can build a Docker image that contains microquark:
```shell script
mvn clean package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

For more details see the Quarkus guide on
[Building Native Images](https://quarkus.io/guides/building-native-image#container-runtime).

## Build with GraalVM installed locally

**NOTE**: Tested only with Linux x64 and macOS.

The Quarkus guide on
[Building Native Images](https://quarkus.io/guides/building-native-image#prerequisites-for-oracle-graalvm-ceee)
describes how to install and setup GraalVM.

Then build the native image with the following command:
```shell script
mvn clean package -Pnative
```

## Run the native image

The native image can be started with the following command:
```shell script
./target/microquark-1.0.0-SNAPSHOT-runner
```

Or if you built a Docker image, use:
```shell script
docker run -i --rm -p 8080:8080 <username>/microquark:1.0.0-SNAPSHOT
```

**NOTE**: The generated image could be made much smaller. However, for convenience Swagger is also included in PROD
mode. Furthermore, all the dependencies for the different storage targets are also added to the native-image. This makes
it easier for you to try out the different storages.

Go back to [Getting started](HOWTO.md) on how to add some data!