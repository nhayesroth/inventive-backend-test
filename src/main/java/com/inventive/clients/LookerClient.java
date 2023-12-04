package com.inventive.clients;

import com.inventive.helpers.RunInlineQueryParams;
import com.inventive.helpers.RunLookParams;
import com.looker.rtl.AuthSession;
import com.looker.rtl.ConfigurationProvider;
import com.looker.rtl.SDKResponse;
import com.looker.rtl.Transport;
import com.looker.sdk.ApiSettings;
import com.looker.sdk.LookerSDK;
import com.looker.sdk.User;
import com.looker.sdk.WriteQuery;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.HashMap;


/** Thin client class for interacting with Looker. */
public class LookerClient {
  private final LookerSDK sdk;

  public LookerClient() {
    // Load system properties from .env
    Dotenv.load()
        .entries()
        .forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    ConfigurationProvider settings = ApiSettings.fromMap(new HashMap<>());
    settings.readConfig();

    this.sdk = new LookerSDK(new AuthSession(settings, new Transport(settings)));
  }

  /** Checks that the configured credentials are valid. */
  public User runCallMe() {
    System.out.println("Checking login credentials...");
    User user = sdk.ok(sdk.me());
    System.out.println("User name is " + user.getDisplay_name());
    return user;
  }

  /**
   * Runs an inline query based on the provided params.
   */
  public String runInlineQuery(RunInlineQueryParams params) {
    System.out.println("run_inline_query request: " + params);
    SDKResponse response =
        sdk.run_inline_query(
            params.resultFormat,
            params.writeQuery,
            params.limit,
            params.applyFormatting,
            params.applyVis,
            params.cache);
    System.out.println("run_inline_query response:" + response);
    return sdk.ok(response);
  }

  /**
   * Runs a "runLook" query based on the provided params
   *
   * @apiNote This method is unused as of yet, but it's included since
   * it was present in the sample code.
   */
  public String runLook(RunLookParams params) {
    System.out.println("run_look request: " + params);
    SDKResponse response =
        sdk.run_look(
            params.lookId,
            params.resultFormat,
            params.limit,
            params.applyFormatting,
            params.applyVis,
            params.cache);
    System.out.println("run_look response: " + response);
    return sdk.ok(response);
  }

  /** Logs the current user out of looker. */
  public SDKResponse logout() {
    System.out.println("logging out...");
    SDKResponse response = sdk.logout();
    System.out.println("logoutResponse: " + response);
    return response;
  }
}
