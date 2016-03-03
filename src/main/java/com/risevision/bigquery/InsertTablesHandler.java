package com.risevision.bigquery;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;

public class InsertTablesHandler extends HttpServlet {
  private static UrlFetchTransport transport = new UrlFetchTransport();

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    TableInserter inserter = new TableInserter(new BigqueryTablesApi(transport));
    inserter.insertTables();
  }
}

