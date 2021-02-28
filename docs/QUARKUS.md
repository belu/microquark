# Quarkus integration

The goal was to integrate MicroStream so that it almost feels like a _normal_
[Quarkus extension](https://quarkus.io/guides/building-my-first-extension).

### Quarkus?! Why not Helidon?

[Helidon](https://helidon.io) is for sure an interesting microservice-framework. Especially with the possibility to
create microservices with a very small footprint (Helidon SE). However, as I knew Quarkus quite well (using it
professionally for almost a year now) I decided to go with Quarkus for this Hackathon. Therefore, I could concentrate on
MicroStream and GraalVM, which were both new technologies to me. Furthermore, I am very interested to see how well
MicroStream can be integrated with Quarkus. This gives me valuable insight if MicroStream could be a good alternative to
Hibernate/ORM.

### Live-Reload & Hot-Deployment

Full support for Live-Reload and Hot-Deployment. Clean shutdown and re-opening of the MicroStream storage.

[![asciicast](https://asciinema.org/a/N4onVbB9yCtXuF58RzV5umAnB.svg)](https://asciinema.org/a/N4onVbB9yCtXuF58RzV5umAnB)

### MicroStream Lazy Checker

The `duration` and `memory quota` of the Lazy Checker can be configured
via [application.properties](../src/main/resources/application.properties).

### MicroStream storage targets

Easily configurable via [application.properties](../src/main/resources/application.properties).

* in-memory
* filesystem
* JDBC (PostgreSQL, MariaDB)
* MongoDB

### MicroStream health status

* readiness
* liveness

![Health UI](images/health-ui.png "Health UI")

### Testing

For testing, you can use the supported in-memory filesystem. Every `QuarkusTest` will use the in-memory filesystem by
default. This is configured in the file
[application.properties](../src/test/resources/application.properties) in the test resources.

The in-memory filesystem is provided by [JimFS](https://github.com/google/jimfs/).