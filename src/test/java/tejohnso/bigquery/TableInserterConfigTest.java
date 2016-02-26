package tejohnso.bigquery;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TableInserterConfigTest {
  @Test
  public void itExists() {
    assertThat(TableInserterConfig.class, notNullValue());
  }

  @Test
  public void itLoadsConfig() {
    assertThat(ConfigLoader.getTableInserterConfig().tables.size() > 0, is(true));
  }
}
