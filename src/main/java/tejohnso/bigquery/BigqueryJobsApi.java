package tejohnso.bigquery;

import com.google.api.client.http.HttpTransport;
import com.google.api.services.bigquery.model.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class BigqueryJobsApi extends BigqueryCommonApi implements JobsApi{

  BigqueryJobsApi(HttpTransport transport) {
    super(transport);
  }

  static final Logger log = Logger.getAnonymousLogger();

  @Override
  public void insertJob(JobInserterConfig configuration,
                        JobInserterConfig.IndividualQueryConfiguration queryConfiguration) {
    try {
      Job job = assembleJob(configuration, queryConfiguration);
      bqClient.jobs().insert(configuration.projectId, job).execute();
    } catch (IOException e) {
      log.severe("Could not insert job " + e.getMessage());
    }
  }

  protected Job assembleJob(JobInserterConfig configuration,
                  JobInserterConfig.IndividualQueryConfiguration queryConfiguration) throws IOException {

    Job job = new Job();
    JobConfiguration jobConfiguration = new JobConfiguration();
    JobConfigurationQuery jobConfigurationQuery = new JobConfigurationQuery();
    JobReference jobReference = new JobReference();
    String sqlQuery = readResource(queryConfiguration.fileName);
    TableReference destinationTable = new TableReference();

    // Prepare BigQuery request objects
    job.setJobReference(jobReference);
    job.setConfiguration(jobConfiguration);
    jobConfiguration.setQuery(jobConfigurationQuery);
    jobConfigurationQuery.setDestinationTable(destinationTable);

    // Configuration common to all jobs
    jobReference.setProjectId(configuration.projectId);
    jobConfigurationQuery.setCreateDisposition(configuration.configuration.query.createDisposition);
    destinationTable.setProjectId(configuration.configuration.query.destinationTable.projectId);
    destinationTable.setDatasetId(configuration.configuration.query.destinationTable.datasetId);

    // Job-specific configuration
    jobConfigurationQuery.setQuery(sqlQuery);
    destinationTable.setTableId(queryConfiguration.destinationTableId);

    return job;
  }

  String readResource(String fileName) throws IOException {
    InputStream is = InsertJobsHandler.class.getClassLoader().getResourceAsStream(fileName);
    return IOUtils.toString(is, "UTF-8");
  }
}
