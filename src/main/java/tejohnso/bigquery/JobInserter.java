package tejohnso.bigquery;

import java.util.List;

public class JobInserter {

  private final JobsApi api;
  private final static JobInserterConfig schedulerConfig = ConfigLoader.getJobInserterConfig();

  public JobInserter(JobsApi api) {
    this.api = api;
  }

  public void insertJobs(List<JobInserterConfig.IndividualQueryConfiguration> jobConfigs) {
    for (JobInserterConfig.IndividualQueryConfiguration config : jobConfigs) {
      api.insertJob(schedulerConfig, config);
    }
  }
}
