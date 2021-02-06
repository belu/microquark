# microquark

## REST API

Get current message:

```shell script
curl http://0.0.0.0:8080/hello
```

Post new message:

```shell script
curl -X POST -H "Content-Type: text/plain" -d "Hello @ `date`" http://0.0.0.0:8080/hello
```


## How to update the native build configs

If you change the application, e. g. the storage root you probably have to update the configs for
the native build.

1. Build the application JAR:

```shell script
mvn clean compile package
```

2. Run the application JAR and let Graal do the heavy work to create the native build configs: 

```shell script
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar target/*-runner.jar
```

Note that whilst your application is running, you should enter every possible code path that could occure.
This assures that Graal catches all configs that are required.
Ideally, this is done with automated tests.