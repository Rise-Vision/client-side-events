package tejohnso.bigquery;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JobInserterConfigTest {

  @Test
  public void itExists() {
    assertNotNull(JobInserterConfig.class);
  }

  @Test
  public void itLoadsConfig() {
    assertThat((ConfigLoader.getJobInserterConfig().every4Hours.size() > 0), is(true));
    assertThat((ConfigLoader.getJobInserterConfig().every24Hours.size() > 0), is(true));
  }
}
