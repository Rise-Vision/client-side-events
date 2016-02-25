package tejohnso.bigquery;

import com.google.gson.Gson;
import java.util.*;
import java.io.*;
import java.util.logging.Logger;

class ConfigLoader {
  static final InputStream configFileStream = ConfigLoader.class.getClassLoader().getResourceAsStream
  ("config.json");

  static final InputStreamReader reader = new InputStreamReader(configFileStream);

  private static final Config config = new Gson().fromJson(reader, Config.class);
  private static final Logger log = Logger.getAnonymousLogger();

  public static Config getConfig() {return config;}
}
