package com.inventive.services;

import com.google.common.collect.ImmutableMap;
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
import java.util.Map;


public class AlertService {

  private static final Long LIMIT = 10L;
  private static final boolean APPLY_FORMATTING = false;
  private static final boolean APPLY_VIS = false;
  private static final boolean CACHE = true;
  private static final String LOOK_ID = "3";
  private static final String RESULT_FORMAT = "json";
  private static final String NULL_CLIENT_ID = null;
  private static final String NULL_DYNAMIC_FIELDS = null;
  private static final Map<String, String> FILTERS =
      ImmutableMap.of(
          "order_items.sale_price", ">200",
          "products.category", "Pants" );
  private static final String[] FIELDS = new String[]{"order_items.sale_price", "products.category", "products.item_name"};
  private static final String MODEL = "thelook_partner";
  private static final String VIEW = "order_items";

  private LookerSDK sdk;
  private static final String QUERY_TIME_ZONE = null;

  public static void main(String[] args) {
    try {
      new AlertService()
          .configure()
          .runCallMe();
    } catch(Error e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

  public AlertService configure() {
    // Load settings from .env file into system properties.
    // Java does not allow ENV variables to be overridden so
    // system properties are used instead. System properties
    // can also be passed in using -Dkey=value.
    // Settings can also be passwed in using ini format.
    Dotenv dotenv = Dotenv.load();
    dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    // Set up the settings from system properties
    ConfigurationProvider settings = ApiSettings.fromMap(new HashMap<>());
    settings.readConfig();
    AuthSession session = new AuthSession(settings, new Transport(settings));
    sdk = new LookerSDK(session);
    return this;
  }

  public AlertService runCallMe() {
    User user = sdk.ok(sdk.me());
    System.out.println("User name is " + user.getDisplay_name());

    SDKResponse response =
        sdk.run_inline_query("json", getWriteQuery(), LIMIT, APPLY_FORMATTING, APPLY_VIS, CACHE);
    System.out.println("run_inline_query: " + response);
    System.out.println("ok(run_inline_query): " + sdk.ok(response).toString());

    SDKResponse runLookResponse = sdk.run_look(LOOK_ID, RESULT_FORMAT, LIMIT, APPLY_FORMATTING, APPLY_VIS, CACHE);
    System.out.println("runLookResponse: " + runLookResponse);
    System.out.println("ok(runLookResponse): " + sdk.ok(runLookResponse).toString());

    SDKResponse logoutResponse = sdk.logout();
    System.out.println("logoutResponse: " + logoutResponse);
    return this;
  }

  private WriteQuery getWriteQuery() {
    return new WriteQuery(
        MODEL,
        VIEW,
        FIELDS,
        /* pivots= */ null,
        /* fillFields= */ null,
        FILTERS,
        /* filterExpressions= */ null,
        /* sorts= */ null,
        String.valueOf(LIMIT),
        /* columnLimit= */ null,
        /* total= */ null,
        /* rowTotal= */ null,
        /* subtotals= */ null,
        /* visConfig= */ null,
        /* filterConfig= */ null,
        /* visibleUiSelections= */ null,
        NULL_DYNAMIC_FIELDS,
        NULL_CLIENT_ID,
        QUERY_TIME_ZONE);
  }
}
