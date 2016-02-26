package tejohnso.bigquery;

import java.util.List;

public class JobInserter {

  private final JobsApi api;
  private final static JobSchedulerConfig schedulerConfig = ConfigLoader.getJobSchedulerConfig();

  public JobInserter(JobsApi api) {
    this.api = api;
  }

  public void insertJobs(List<JobSchedulerConfig.IndividualQueryConfiguration> jobConfigs) {
    for (JobSchedulerConfig.IndividualQueryConfiguration config : jobConfigs) {
      api.insertJob(schedulerConfig, config);
    }
  }
}
