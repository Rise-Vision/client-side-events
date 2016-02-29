package tejohnso.bigquery;

import com.google.gson.Gson;

import java.io.*;

class ConfigLoader {

  static <E> E loadConfig(String fileName, Class<E> eClass) {

    InputStream configFileStream = ConfigLoader.class.getClassLoader().getResourceAsStream
            (fileName);

    InputStreamReader reader = new InputStreamReader(configFileStream);

    return new Gson().fromJson(reader, eClass);
  }

  private final static TableInserterConfig tableInserterConfig = loadConfig
          ("table-inserter-config.json", TableInserterConfig.class);

  private final static JobInserterConfig JOB_INSERTER_CONFIG = loadConfig
          ("job-scheduler-config.json", JobInserterConfig.class);

  public static TableInserterConfig getTableInserterConfig() {return tableInserterConfig;}

  public static JobInserterConfig getJobInserterConfig() {return JOB_INSERTER_CONFIG;}
}
