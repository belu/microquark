## REST API

Some example REST calls with [cURL](https://curl.se/).

#### Show all Volksabstimmungen

```shell script
curl "http://127.0.0.1:8080/volksabstimmungen"
```

This lists all the Volksabstimmungen and show their date and the Vorlagen (topics to vote on).

#### Create a new Volksabstimmung

```shell script
curl "http://127.0.0.1:8080/volksabstimmungen" -H "Content-Type: application/json" -d '{"datum":"2021-02-28","vorlagen":["Soll MicroStream als zuverlässige und schnelle Persistenz für alle Behörden eingeführt werden?"]}'
```

This inserts a new Volksabstimmung. You can define the date when the election takes place. And also a list
of topics that the population can vote on (Vorlagen).

#### Show a single Volksabstimmung

```shell script
curl "http://127.0.0.1:8080/volksabstimmungen/2021-02-28"
```

#### Delete a Volksabstimmung

```shell script
curl -X DELETE "http://127.0.0.1:8080/volksabstimmungen/2021-02-28"
```

Removes a Volksabstimmung including all the results if available.

#### Perform (random) election for a Volksabstimmung

```shell script
curl -X POST "http://127.0.0.1:8080/volksabstimmungen/2021-02-28/abstimmen" -H "Content-type: application/json"
```

Note that an election can only be performed once per Volksabstimmung. 

#### Get election results for a Volksabstimmung

```shell script
curl "http://127.0.0.1:8080/volksabstimmungen/2021-02-28/result"
```

Returns the detailed results of the election. Note that all the results
are calculated on-the-fly directly from MicroStream storage data root!

## cURL tips

* For verbose output add the parameter `-v`,
  e. g. `curl -v "http://127.0.0.1:8080/volksabstimmungen"`
  
* To time the requests, add `time` at the beginning. E. g. `time curl "http://127.0.0.1:8080/volksabstimmungen"`