## REST API

Get current Volksabstimmungen:

```shell script
curl http://0.0.0.0:8080/volksabstimmungen
```

Create a new Volksabstimmung:

```shell script
curl -X POST -H "Content-Type: text/plain" -d "TODO" http://0.0.0.0:8080/volksabstimmungen
```

...