package com.risevision.bigquery;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class BigqueryCommonApi {
  private GoogleCredential credential;
  protected Bigquery bqClient;
  private static final Logger log = Logger.getAnonymousLogger();

   public BigqueryCommonApi(HttpTransport transport) {
    try {
      credential = GoogleCredential.getApplicationDefault();
    } catch (IOException e) {
      log.severe(e.getMessage());
    }

    if (credential == null || transport == null) {
      log.severe("Could not initiate credential");
      return;
    }

    credential = credential.createScoped(Arrays.asList(BigqueryScopes.BIGQUERY));

    bqClient = new Bigquery.Builder
    (transport, JacksonFactory.getDefaultInstance(), credential)
    .build();
  }
}
