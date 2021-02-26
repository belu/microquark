# MicroStream integration

### Lazy Checker

The **duration** and the **memory quota** of the Lazy Checker can be configured in the
`application.properties` file.

### Storage targets

The default storage target is `filesystem`.

You can easily switch to PostgreSQL, MariaDB, MongoDB or even an in-memory storage. See the comments in
`application.properties` for guidance.

The in-memory storage is especially useful if you run automated tests. For
[QuarkusTest's](https://quarkus.io/guides/getting-started-testing) this is the default setting.

For your convenience, a **docker-compose** file is included in the root directory. You can start
all the databases with the following command:

```shell script
docker-compose up
```
