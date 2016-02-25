package tejohnso.bigquery;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import com.google.api.services.bigquery.model.*;

public class BigqueryTablesApiTest {
  @Test
  public void itExists() {
    assertThat(BigqueryTablesApi.class, notNullValue());
  }

  @Test
  public void itAssemblesTables() {
    BigqueryTablesApi bqApi = new BigqueryTablesApi(null);

    Table table = bqApi.assembleTable(ConfigLoader.getConfig().tables.get(0));
    assertThat(table.getTableReference().getTableId(), not(""));
    assertThat(table.getTableReference().getDatasetId(), is("dataset"));
    assertThat(table.getSchema().getFields().get(0).getName(), is("field1"));
    assertThat(table.getSchema().getFields().get(0).getMode(), is("REQUIRED"));

    table = bqApi.assembleTable(ConfigLoader.getConfig().tables.get(1));
    assertThat(table.getTableReference().getDatasetId(), is("dataset"));
    assertThat(table.getSchema().getFields().get(1).getName(), is("field3"));
    assertThat(table.getSchema().getFields().get(1).getMode(), is("NULLABLE"));
  }
}

