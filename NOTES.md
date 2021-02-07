## Beobachtungen

Wenn mit `native-image-agent` die Configs erzeugt werden, sind die Resultate
je Szenario unterschiedlich.

1. Existiert initial ein `data`-Verzeichnis ja/nein
2. Werden REST-Calls gemacht ja/nein

Es zeigen sich folgende Resultate:

* alle gleich: `jni-config.json`, `proxy-config.json`, `serialization-config.json`

* Abhängig davon ob REST-Calls gemacht werden: `resource-config.json` enthält
  `META-INF/services/io.vertx.core.spi.BufferFactory` bei REST-Calls 

* alle unterschiedlich: `reflect-config.json`
