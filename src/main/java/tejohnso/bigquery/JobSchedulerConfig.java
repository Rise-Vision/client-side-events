package tejohnso.bigquery;

import java.util.List;

class JobSchedulerConfig {

  String projectId;

  JobConfiguration configuration;

  List<IndividualQueryConfiguration> every4Hours;

  List<IndividualQueryConfiguration> every24Hours;

  static class JobConfiguration {
    QueryConfiguration query;
  }

  static class QueryConfiguration {
    String createDisposition;
    TableInfo destinationTable;
  }

  static class TableInfo {
    String datasetId;
    String projectId;
  }

  static class IndividualQueryConfiguration {
    String destinationTableId;
    String fileName;
  }
}
