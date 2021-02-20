# TODO

* Quarkus @NativeImageTest
* Lazy Loading of results -> warum dauert Storage-Start so lange (5 Sekunden)? Evtl. besser Wahlergebnis
  Lazy machen anstatt die Map<>?
* Storage-Laden erst nachdem Quarkus vollständig geladen ist? Und dann Health-Ready melden wenn
  vollständig geladen. Erst danach werden Requests akzeptiert.
* Wahlresultate in JSON-Format zurückgeben
* Swagger Doku verbessern
* MicroStream Properties nicht 'gelb' (werden vom System erkannt)

## Questions

- Support for java.util.EnumMap? A first test did not work

## Open Source contributions

Issues, feature- and pull-requests submitted during this project.

### MicroStream

- [Typo in RuntimeException for memory quota outside valid range](https://github.com/microstream-one/microstream/issues/3)

### Quarkus

- [Fix typos in Kotlin guide](https://github.com/quarkusio/quarkus/pull/15134)
- [Jackson - Disable WRITE_DATES_AS_TIMESTAMPS by default](https://github.com/quarkusio/quarkus/pull/15139)
- [Dev UI config editor with filtering capabilities](https://github.com/quarkusio/quarkus/issues/15196)

### GraalVM

- [native-image-agent does not merge reflect-config.json as expected](https://github.com/oracle/graal/issues/3192)
