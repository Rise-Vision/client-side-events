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

    JobSchedulerConfig config = ConfigLoader.getJobSchedulerConfig();
    String interval = req.getParameter("interval");
    List<JobSchedulerConfig.IndividualQueryConfiguration> jobConfigs;

    if (interval.equals("4")) {
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

