package com.risevision.bigquery;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

  static String readResource(String fileName) throws IOException {
    InputStream is = InsertJobsHandler.class.getClassLoader().getResourceAsStream(fileName);
    return IOUtils.toString(is, "UTF-8");
  }

}
