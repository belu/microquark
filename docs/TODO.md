# TODO

* Lazy Loading of results -> warum dauert Storage-Start so lange (5 Sekunden)? Evtl. besser Wahlergebnis Lazy machen
  anstatt die Map<>?

* Doc: Data model
* Doc: MicroStream integration
* Doc: GraalVM integration
* Doc: Movation (README)

* GitHub Actions: Maven Cache to speed up build

* ScreenCast mit asciinema

* Swagger Doku verbessern

* Quarkus @NativeImageTest

* Storage-Laden erst nachdem Quarkus vollständig geladen ist? Und dann Health-Ready melden wenn vollständig geladen.
  Erst danach werden Requests akzeptiert.

* MicroStream Properties nicht 'gelb' (werden vom System erkannt)

* weitere MicroStream Konfigurationen in `application.properties` aufnehmen (Channels, Housekeeping, Backup, etc.)

* Quarkus-Extension für MicroStream (Thema für einen weiteren Hackathon)

* Does MicroStream support java.util.EnumMap? A first test did not work
