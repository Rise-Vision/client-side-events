package tejohnso.bigquery;

import java.util.List;

class Config {
  public Config(){}
  boolean includeCurrentDay;
  int numberOfDays;
  List<TableInfo> tables;

  static class TableInfo {
    public TableInfo() {}
    String projectId;
    String dataset;
    String tableNamePrefix;
    String name;
    List<TableField> fields;
  }

  static class TableField {
    public TableField(){}
    String name;
    String type;
    boolean nullable;
  }
}
