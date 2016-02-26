package tejohnso.bigquery;

import java.util.*;
import java.io.IOException;
import com.google.api.services.bigquery.model.*;
import java.util.logging.Logger;
import com.google.api.client.http.HttpTransport;

class BigqueryTablesApi extends BigqueryCommonApi implements TablesApi{
  private static final Logger log = Logger.getAnonymousLogger();

  BigqueryTablesApi(HttpTransport transport) {
    super(transport);
  }

  public void insertTable(TableInserterConfig.TableInfo tableInfo) {
    String projectId = tableInfo.projectId;
    String dataset = tableInfo.dataset;
    Table table = assembleTable(tableInfo);

    try {
      bqClient.tables().insert(projectId, dataset, table).execute();
    } catch (IOException e) {
      log.severe("Could not create table " + e.getMessage());
    }
  }

  Table assembleTable(TableInserterConfig.TableInfo tableInfo) {
    TableReference ref = new TableReference();
    ref.setTableId(tableInfo.name);
    ref.setProjectId(tableInfo.projectId);
    ref.setDatasetId(tableInfo.dataset);

    TableSchema schema = new TableSchema();
    List<TableFieldSchema> fields = new ArrayList<>();
    for (TableInserterConfig.TableField field : tableInfo.fields) {
      TableFieldSchema fieldSchema = new TableFieldSchema();
      fieldSchema.setMode(field.nullable ? "NULLABLE" : "REQUIRED");
      fieldSchema.setName(field.name);
      fieldSchema.setType(field.type);
      fields.add(fieldSchema);
    }
    schema.setFields(fields);

    Table table = new Table();
    table.setTableReference(ref);
    table.setSchema(schema);
    return table;
  }
}

