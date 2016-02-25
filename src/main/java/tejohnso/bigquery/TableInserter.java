package tejohnso.bigquery;

import java.util.*;

class TableInserter {
  private final TablesApi api;

  public TableInserter(TablesApi api) {
    this.api = api;
  }

  public void insertTables() {
    Config config = ConfigLoader.getConfig();
    int dateOffset = config.includeCurrentDay ? 0 : 1;
    int numDaysToInsert = config.numberOfDays;

    for (int i = 0; i < numDaysToInsert; i += 1) {
      for (Config.TableInfo info : config.tables) {
        info.name = info.tableNamePrefix + getDate(dateOffset);

        api.insertTable(info);
      }

      dateOffset += 1;
    }
  }

  private String getDate(int offset) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, offset);

    String year = "" + cal.get(Calendar.YEAR);
    String month = (cal.get(Calendar.MONTH) + 1 < 10 ? "0" : "") +
    (cal.get(Calendar.MONTH) + 1);
    String day = (cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") +
    cal.get(Calendar.DAY_OF_MONTH);

    return year + month + day;
  }
}
