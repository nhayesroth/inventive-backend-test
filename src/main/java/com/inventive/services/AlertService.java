package com.inventive.services;

import com.google.gson.JsonArray;
import com.inventive.helpers.Constants;
import com.inventive.helpers.RunInlineQueryParams;
import com.inventive.helpers.WriteQueryBuilder;

/**
 * Executes a looker query and (optionally) sends an email communication.
 *
 * <p>The conditions, content, and frequency with which this service runs are
 * largely faked here. In real life, this service would likely be deployed and run
 * as a cron job and/or as a stream processor.
 */
public class AlertService {

  private final EmailService emailService;
  private final LookerService lookerService;

  public AlertService() {
    this.emailService = new EmailService();
    this.lookerService = new LookerService();
  }

  public static void main(String[] args) {
    // Initialize connections/services.
    AlertService alertService = new AlertService();
    LookerService lookerService = alertService.lookerService;
    EmailService emailService = alertService.emailService;
    try {
      // Retrieve results from looker.
      JsonArray results = alertService.queryLooker();

      // Optionally sent an email.
      if (alertService.shouldNotify(results)) {
        emailService.sendEmail(results);
      }

      // Logout and exit.
      lookerService.logout();
    } catch(Error e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

  /**
   * Retrieves a list of results from Looker.
   *
   * @apiNote This currently uses constant values. In real life, we'd need to
   * record some preferences for each customer in order to retrieve the appropriate
   * information.
   */
  public JsonArray queryLooker() {
    return lookerService.runQueryAndGetJsonArray(
        RunInlineQueryParams.newBuilder()
            .setWriteQuery(
                new WriteQueryBuilder()
                    .setModel(Constants.MODEL)
                    .setView(Constants.VIEW)
                    .setFields(Constants.FIELDS)
                    .setFilters(Constants.FILTERS)
                    .setLimit(Constants.LIMIT))
            .setResultFormat(Constants.RESULT_FORMAT_JSON)
            .setCache(Constants.CACHE)
            .setLimit(Constants.LIMIT)
            .build());
  }

  /**
   * Returns a boolean indicating whether the specified results warrant
   * an alert/email.
   *
   * @apiNote this method is stubbed out. Currently, our query returns the same
   * information for each query because data doesn't change. In real life,
   * the response would change and we'd need some mechanism to tell if we should
   * notify the customer for any given update (e.g. we probably don't want to
   * notify the same customer about the same pair of pants several times).
   */
  public boolean shouldNotify(JsonArray results) {
    return true;
  }
}
