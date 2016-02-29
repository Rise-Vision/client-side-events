package tejohnso.bigquery;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class JobInserterTest {
  @Test
  public void itExists() {
    assertNotNull(JobInserter.class);
  }

  @Test
  public void itCreatesJobs() {
    MockJobsApi mockApi = new MockJobsApi();
    JobInserter inserter = new JobInserter(mockApi);
    inserter.insertJobs(ConfigLoader.getJobInserterConfig().every4Hours);
    assertThat(mockApi.jobs.size(), is(1));
  }
}
