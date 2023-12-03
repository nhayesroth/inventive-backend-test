package com.inventive.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.inventive.clients.LookerClient;
import com.inventive.helpers.RunInlineQueryParams;


/**
 * Service class for interacting with Looker backend.
 *
 * <p>Manages some additional things like object conversions.
 */
public class LookerService {

  private final LookerClient lookerClient;

  public LookerService() {
    this.lookerClient = new LookerClient();

    // Verify the user is logged in.
    this.lookerClient.runCallMe();
  }

  public JsonArray runQueryAndGetJsonArray(RunInlineQueryParams params) {
    String response = lookerClient.runInlineQuery(params);
    return JsonParser.parseString(response).getAsJsonArray();
  }

  public void logout() {
    lookerClient.logout();
  }
}
