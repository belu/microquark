# Quarkus integration

The goal was to integrate, configure and use MicroStream as it would be a _normal_ Quarkus extension.
The work done in this project could even be a good starting point for that!

## Features

* Configuration via `application.properties`
  * e.g. basic configuration of the LazyChecker
* Support for different storage targets
  * in-memory
  * filesystem
  * JDBC (PostgreSQL, MariaDB)
  * MongoDB
* MicroStream health status
  * [readiness](http://127.0.0.1:8080/q/health/ready)
  * [liveness](http://127.0.0.1:8080/q/health/live)

## Live-Reload & Hot-Deployment

Full support for Live-Reload and Hot-Deployment. Clean shutdown and re-opening of the
MicroStream storage.

## Testing

For testing, you can use the supported in-memory filesystem. Every `QuarkusTest` will use
the in-memory filesystem by default. This is configured in the file
`application.properties` under test resources.

The in-memory filesystem is provided by [JimFS](https://github.com/google/jimfs/).