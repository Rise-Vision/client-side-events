package com.risevision.bigquery;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class InsertJobsHandler extends HttpServlet {
  JobInserter inserter;
  private Logger log = Logger.getAnonymousLogger();
  private static UrlFetchTransport transport = new UrlFetchTransport();

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {

    JobInserterConfig config = ConfigLoader.getJobInserterConfig();
    String interval = req.getParameter("interval");
    List<JobInserterConfig.IndividualQueryConfiguration> jobConfigs;

    if(interval == null) {
      resp.sendError(400, "no interval parameter received");
      return;

    } else if (interval.equals("4")) {
      jobConfigs = config.every4Hours;

    } else if (interval.equals("24")) {
      jobConfigs = config.every24Hours;

    } else {
      resp.sendError(400, "interval parameter set incorrectly");
      return;
    }

    log.info("Inserting " + jobConfigs.size() + " jobs");
    inserter = new JobInserter(new BigqueryJobsApi(transport));
    inserter.insertJobs(jobConfigs);
  }
}

