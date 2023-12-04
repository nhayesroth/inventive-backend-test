package com.inventive.helpers;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Constant values used in various places.
 *
 * @apiNote In real life, these would likely be stored on disk and differ for different customers.
 */
public class Constants {
  public static final Long LIMIT = 10L;
  public static final boolean CACHE = true;
  public static final String RESULT_FORMAT_JSON = "json";
  public static final Map<String, String> FILTERS =
      ImmutableMap.of(
          "order_items.sale_price", ">200",
          "products.category", "Pants");
  public static final String[] FIELDS =
      new String[] {"order_items.sale_price", "products.category", "products.item_name"};
  public static final String MODEL = "thelook_partner";
  public static final String VIEW = "order_items";
}
