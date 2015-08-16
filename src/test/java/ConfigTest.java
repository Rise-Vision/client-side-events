package tejohnso.bigquery;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ConfigTest {
  @Test
  public void itExists() {
    assertThat(Config.class, notNullValue());
  }

  @Test
  public void itLoadsConfig() {
    assertThat(Config.config, notNullValue());
    assertThat(Config.config.containsKey("tableSchemas"), is(true));
  }
}
