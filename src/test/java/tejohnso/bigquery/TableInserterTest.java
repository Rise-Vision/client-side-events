package tejohnso.bigquery;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.regex.Pattern;

public class TableInserterTest {
  @Test
  public void itExists() {
    assertThat(TableInserter.class, notNullValue());
  }

  @Test
  public void itCreatesTables() {
    MockTablesApi  mockApi = new MockTablesApi();
    TableInserter inserter = new TableInserter(mockApi);
    inserter.insertTables();
    int numDays = ConfigLoader.getConfig().numberOfDays;
    int numTables = ConfigLoader.getConfig().tables.size();

    assertThat(mockApi.insertTableCallCount, is(numDays * numTables));
    assertThat(Pattern.matches("prefix[0-9]{8}", mockApi.insertedTableName), is(true));
  }
}
