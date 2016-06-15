# Client side events Job and Table Inserter

This project is able to do two things:

- create daily tables for client side events project.

- run scheduled queries (currently 4- and 24-hour intervals)

## Table Inserter Configuration

The configuration file `(src/main/resources/table-inserter-config.json)` specifies one or more table schemas ,each having one or more fields:

```json
{
  "includeCurrentDay": true,
  "numberOfDays": 3,
  "tables": [
    {
      "projectId": "project-id",
      "dataset": "dataset",
      "tableNamePrefix": "prefix",
      "fields": [
        {
          "name": "field1",
          "type": "string",
          "nullable": false
        }
      ]
    },
  ]
}
```

Table names will be of the format [tableNamePrefix]YYYYMMDD for use in table date range queries.

## Job Inserter Configuration

To add a query, you need to modify the configuration file `(src/main/resources/job-inserter-config.json)`
and add a query under `src/main/resources/queries/`

The configuration file follows the following schema:

```json
{
  "projectId": "billing-project",
  "configuration": {
    "query": {
      "writeDisposition": "WRITE_TRUNCATE",
      "destinationTable": {
        "projectId": "destination-project",
        "datasetId": "destination_dataset"
      }
    }
  },
  "every4Hours": [
    {
      "destinationTableId": "destination_table_name",
      "fileName": "queries/query-file-name.sql"
    }
  ],
  "every24Hours": [
    ...
  ]
}
```

The query result will be stored in `[destination-project:destination_dataset.destination_table_name]`.

N.B. 1. the billing project, destination project, and destination dataset are currently global configuration options
that are identical for all jobs.

N.B. 2. the results will overwrite the previous contents of the table

## Usage

 - Set up the configuration files as required
 - Deploy to an appengine project having service account permissions to the target table project

```bash
mvn appengine:update -Dappengine.appId=project-app-id -Ddeploy.version=[version-num] -Ddeploy.module=[default | module-name]
```

## Troubleshooting job scheduler

To test the job scheduler, make a request for `/insertJobs?interval=x` where x
is the interval that includes the new jobs. If the job does not cause the target
table to be updated, use `bq ls -a -j client-side-events` and look for failures.
Then use `bq show -j <job_id>` and look for the error message.

## Unit Tests

```bash
mvn test
```

## Integration Tests

```bash
mvn verify
```
Note: Requires a json service account file.  The file path should be specified by the environment variable *GOOGLE_APPLICATION_CREDENTIALS*
