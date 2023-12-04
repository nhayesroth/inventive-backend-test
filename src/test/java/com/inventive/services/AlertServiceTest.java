package com.inventive.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.inventive.clients.LookerClient;
import org.mockito.Mockito;
import org.testng.annotations.Test;

public class AlertServiceTest {

  @Test(description = "General happy path test. We get results from Looker,"
      + " we decide they should alert, and we send the email.")
  public void testQueryAndNotify_results_shouldAlert_emailSent() {
    Mocks mocks = new Mocks();

    Mockito.when(mocks.lookerClient.runInlineQuery(Mockito.any()))
        .thenReturn(MockData.QUERY_RESULTS);

    mocks.alertService.queryAndNotify();

    Mockito.verify(mocks.emailService).sendEmail(MockData.QUERY_RESULTS_JSON_ARRAY);
  }

  @Test(description = "Empty results from Looker => no email.")
  public void testQueryAndNotify_emptyResults_noAlert() {
    Mocks mocks = new Mocks();

    Mockito.when(mocks.lookerClient.runInlineQuery(Mockito.any())).thenReturn("");

    mocks.alertService.queryAndNotify();

    Mockito.verifyNoInteractions(mocks.emailService);
  }

  @Test(description = "Mock the shouldAlert() function to return false => no email.")
  public void testQueryAndNotify_results_shouldNotAlert_noAlert() {
    Mocks mocks = new Mocks();

    Mockito.when(mocks.lookerClient.runInlineQuery(Mockito.any()))
        .thenReturn(MockData.QUERY_RESULTS);
    Mockito.doReturn(false)
        .when(mocks.alertService)
        .shouldNotify(MockData.QUERY_RESULTS_JSON_ARRAY);

    mocks.alertService.queryAndNotify();


    Mockito.verifyNoInteractions(mocks.emailService);
  }

  static class Mocks {
    EmailService emailService = Mockito.mock(EmailService.class);
    LookerClient lookerClient = Mockito.mock(LookerClient.class);

    AlertService alertService;

    Mocks() {
      alertService = Mockito.spy(new AlertService(emailService, new LookerService(lookerClient)));
    }
  }

  static class MockData {
    static final String QUERY_RESULTS =
        "["
            + "{\"order_items.sale_price\":11,\"products.category\":\"Pants\",\"products.item_name\":\"foo\"},"
            + "{\"order_items.sale_price\":22,\"products.category\":\"Pants\",\"products.item_name\":\"bar\"}"
            + "]";
    static final JsonArray QUERY_RESULTS_JSON_ARRAY = JsonParser.parseString(QUERY_RESULTS).getAsJsonArray();
  }
}
