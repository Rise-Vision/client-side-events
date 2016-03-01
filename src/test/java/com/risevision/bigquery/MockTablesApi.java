package com.risevision.bigquery;

class MockTablesApi implements TablesApi {
  int insertTableCallCount = 0;
  String insertedTableName;

  public void insertTable(TableInserterConfig.TableInfo tableInfo) {
    insertTableCallCount += 1;
    insertedTableName = tableInfo.name;
    return;
  }
}
