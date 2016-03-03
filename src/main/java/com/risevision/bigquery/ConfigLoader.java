package com.risevision.bigquery;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

class ConfigLoader {

  private final static Logger log = Logger.getAnonymousLogger();

  private final static TableInserterConfig TABLE_INSERTER_CONFIG = loadConfig
          ("table-inserter-config.json", TableInserterConfig.class);

  private final static JobInserterConfig JOB_INSERTER_CONFIG = loadConfig
          ("job-scheduler-config.json", JobInserterConfig.class);

  private final static Map<String, String> QUERIES = readQueries();

  static Map<String, String> readQueries() {
    Map<String, String> queries = new HashMap<>();
    List<JobInserterConfig.IndividualQueryConfiguration> queryConfigurations = new ArrayList<>();

    queryConfigurations.addAll(JOB_INSERTER_CONFIG.every4Hours);
    queryConfigurations.addAll(JOB_INSERTER_CONFIG.every24Hours);

    for (JobInserterConfig.IndividualQueryConfiguration queryConfiguration : queryConfigurations) {
      String fileName = queryConfiguration.fileName;
      try {
        queries.put(fileName, Utils.readResource(fileName));
      } catch (IOException e) {
        String message = String.format("Couldn't read query %s. %s", fileName, e.getMessage());
        log.severe(message);
      }
    }

    return queries;
  }

  static <E> E loadConfig(String fileName, Class<E> eClass) {
    InputStream configFileStream = ConfigLoader.class.getClassLoader().getResourceAsStream
            (fileName);
    InputStreamReader reader = new InputStreamReader(configFileStream);
    return new Gson().fromJson(reader, eClass);
  }

  public static String getQuery(String fileName) {
    return QUERIES.get(fileName);
  }

  public static TableInserterConfig getTableInserterConfig() {
    return TABLE_INSERTER_CONFIG;
  }

  public static JobInserterConfig getJobInserterConfig() {
    return JOB_INSERTER_CONFIG;
  }
}
