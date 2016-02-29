package tejohnso.bigquery;

import com.google.api.services.bigquery.model.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class BigqueryJobsApiTest {

  @Test
  public void itExists() {
    assertNotNull(BigqueryTablesApi.class);
  }

  @Test
  public void itAssemblesJobs() throws IOException {
    BigqueryJobsApi bqApi = new BigqueryJobsApi(null);
    JobInserterConfig schedulerConfiguration = ConfigLoader.getJobInserterConfig();
    JobInserterConfig.IndividualQueryConfiguration schedulerQueryConfiguration = schedulerConfiguration.every4Hours.get(0);

    Job job = bqApi.assembleJob(schedulerConfiguration, schedulerQueryConfiguration);
    JobReference jobReference = job.getJobReference();
    JobConfiguration jobConfiguration = job.getConfiguration();
    JobConfigurationQuery jobConfigurationQuery = jobConfiguration.getQuery();
    TableReference destinationTable = jobConfigurationQuery.getDestinationTable();

    assertThat(jobReference.getProjectId(), is("test-job-scheduler"));
    assertNotNull(jobConfigurationQuery);
    assertThat(jobConfigurationQuery.getCreateDisposition(), is("WRITE_TRUNCATE"));
    assertThat(destinationTable.getProjectId(), is("destination-table-project"));
    assertThat(destinationTable.getDatasetId(), is("dataset-id"));

    assertThat(jobConfigurationQuery.getQuery(), is("SELECT 4 FROM test_table\n"));
  }
}
