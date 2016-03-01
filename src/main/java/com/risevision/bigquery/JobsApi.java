package com.risevision.bigquery;

public interface JobsApi {
  void insertJob(JobInserterConfig configuration,
                 JobInserterConfig.IndividualQueryConfiguration queryConfiguration);
}
