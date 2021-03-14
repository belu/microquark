#!/bin/sh

java -agentlib:native-image-agent=config-merge-dir=src/main/resources/META-INF/native-image -jar target/quarkus-app/quarkus-run.jar
