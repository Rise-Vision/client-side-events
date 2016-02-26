package tejohnso.bigquery;

public interface JobsApi {
  void insertJob(JobSchedulerConfig configuration,
                 JobSchedulerConfig.IndividualQueryConfiguration queryConfiguration);
}
