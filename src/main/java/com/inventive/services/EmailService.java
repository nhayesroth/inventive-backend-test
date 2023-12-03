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
public class EmailService {

  public EmailService() {}


  public void sendEmail(JsonArray results) {
    System.out.println("Email sent for: " + results);
  }
}
