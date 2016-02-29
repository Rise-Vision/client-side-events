package tejohnso.bigquery;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InsertJobsHandler extends HttpServlet {
  private static UrlFetchTransport transport = new UrlFetchTransport();

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {

    JobInserterConfig config = ConfigLoader.getJobInserterConfig();
    String interval = req.getParameter("interval");
    List<JobInserterConfig.IndividualQueryConfiguration> jobConfigs;

    if(interval == null) {
      resp.sendError(404);
      return;

    } else if (interval.equals("4")) {
      jobConfigs = config.every4Hours;

    } else if (interval.equals("24")) {
      jobConfigs = config.every24Hours;

    } else {
      resp.sendError(404);
      return;
    }
    JobInserter inserter = new JobInserter(new BigqueryJobsApi(transport));

    inserter.insertJobs(jobConfigs);
  }
}

