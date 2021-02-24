# TODO

* Lazy Loading der Wahlresultate: das pgAdmin zeigt, das ALLE Daten initial geladen.
  Das Lazy.get() wird jedoch noch nicht aufgerufen.
  Werden die Daten Off-Heap (binär) gespeichert?! Und erst bei Zugriff deserialisiert?
  Ist das irgendwo dokumentiert?
  
* Doc: MicroStream integration
* Doc: GraalVM integration

* GitHub Actions: Maven Cache to speed up build

* ScreenCast mit asciinema

* Quarkus @NativeImageTest

* Storage-Laden erst nachdem Quarkus vollständig geladen ist? Und dann Health-Ready melden wenn vollständig geladen.
  Erst danach werden Requests akzeptiert.

* weitere MicroStream Konfigurationen in `application.properties` aufnehmen (Channels, Housekeeping, Backup, etc.)

* Quarkus-Extension für MicroStream (Thema für einen weiteren Hackathon)

* Does MicroStream support java.util.EnumMap? A first test did not work
