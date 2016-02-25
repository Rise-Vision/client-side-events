package tejohnso.bigquery;

import java.util.*;
import java.io.IOException;
import com.google.api.services.bigquery.*;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.bigquery.model.*;
import java.util.logging.Logger;
import com.google.api.client.http.HttpTransport;

class BigqueryTablesApi implements TablesApi {
  private GoogleCredential credential;
  private Bigquery bqClient;
  private static final Logger log = Logger.getAnonymousLogger();

  public BigqueryTablesApi(HttpTransport transport) {
    try {
      credential = GoogleCredential.getApplicationDefault();
    } catch (IOException e) {
      log.severe(e.getMessage());
    }

    if (credential == null || transport == null) {
      log.severe("Could not initiate credential");
      return;
    }

    credential = credential.createScoped(Arrays.asList(BigqueryScopes.BIGQUERY));

    bqClient = new Bigquery.Builder
    (transport, JacksonFactory.getDefaultInstance(), credential)
    .build();
  }

  public void insertTable(Config.TableInfo tableInfo) {
    String projectId = tableInfo.projectId;
    String dataset = tableInfo.dataset;
    Table table = assembleTable(tableInfo);

    try {
      bqClient.tables().insert(projectId, dataset, table).execute();
    } catch (IOException e) {
      log.severe("Could not create table " + e.getMessage());
    }
  }

  Table assembleTable(Config.TableInfo tableInfo) {
    TableReference ref = new TableReference();
    ref.setTableId(tableInfo.name);
    ref.setProjectId(tableInfo.projectId);
    ref.setDatasetId(tableInfo.dataset);

    TableSchema schema = new TableSchema();
    List<TableFieldSchema> fields = new ArrayList<>();
    for (Config.TableField field : tableInfo.fields) {
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

