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

  final static TableInserterConfig tableInserterConfig = loadConfig
          ("table-inserter-config.json", TableInserterConfig.class);

  final static JobSchedulerConfig jobSchedulerConfig = loadConfig
          ("job-scheduler-config.json", JobSchedulerConfig.class);

  public static TableInserterConfig getTableInserterConfig() {return tableInserterConfig;}

  public static JobSchedulerConfig getJobSchedulerConfig() {return jobSchedulerConfig;}
}
