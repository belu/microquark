# Getting started

## How to start the application

Use the following command to run **microquark** in development mode:

```shell script
mvn quarkus:dev
```

The application has fully started when you see an entry: `Listening on: http://localhost:8080`

## Getting data in

You have two possibilities to load data: automatically with the DataImporter or manually via REST.

### Using the DataImporter

microquark comes with a **DataImporter** that allows you to load referendums from a CSV file
(see `data.csv` in the root folder).
This creates the given referendums and performs a (random) election on each submission.

In total, this generates around 2.5 GB on your disk (depending on how many people show up to vote!). 

### Adding data via REST

microquark comes with several REST endpoints to interact with. You can e. g. add referendums, list all
referendums, perform an election on a referendum and get the voting results.

For details, see the [REST Guide](REST.md).