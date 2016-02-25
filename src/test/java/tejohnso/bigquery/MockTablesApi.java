package tejohnso.bigquery;

import java.util.Map;

class MockTablesApi implements TablesApi {
  int insertTableCallCount = 0;
  String insertedTableName;

  public void insertTable(Config.TableInfo tableInfo) {
    insertTableCallCount += 1;
    insertedTableName = tableInfo.name;
    return;
  }
}
