package tejohnso.bigquery;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import com.google.api.client.http.javanet.NetHttpTransport;

public class BigqueryTablesApiIT {
  @Test
  public void itCreatesTables() {
    BigqueryTablesApi api = new BigqueryTablesApi(new NetHttpTransport());
    assertThat(api, isA(BigqueryTablesApi.class));

    Config.TableInfo tableInfo = ConfigLoader.getConfig().tables.get(0);
    tableInfo.name = "test_table";
    api.insertTable(tableInfo);
  }
}
