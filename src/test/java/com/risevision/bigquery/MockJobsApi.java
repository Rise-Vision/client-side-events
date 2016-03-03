package com.risevision.bigquery;

import java.util.ArrayList;
import java.util.List;

public class MockJobsApi implements JobsApi {

  protected List<JobInserterConfig.IndividualQueryConfiguration> jobs = new ArrayList<>();

  @Override
  public void insertJob(JobInserterConfig configuration, JobInserterConfig.IndividualQueryConfiguration queryConfiguration) {
    jobs.add(queryConfiguration);
  }
}
