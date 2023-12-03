package com.inventive.helpers;

import com.looker.sdk.WriteQuery;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Builder interface for constructing {@link WriteQuery} objects.
 */
public class WriteQueryBuilder {
  @NotNull private String model;
  @NotNull private String view;

  @Nullable private String[] fields;
  @Nullable private String[] pivots;
  @Nullable private String[] fillFields;
  @Nullable private Map<String, ? extends Object> filters;
  @Nullable private String filterExpression;
  @Nullable private String[] sorts;
  @Nullable private String limit;
  @Nullable private String columnLimit;
  @Nullable private Boolean total;
  @Nullable private String rowTotal;
  @Nullable private String[] subtotals;
  @Nullable private Map<String, ? extends Object> visConfig;
  @Nullable private Map<String, ? extends Object> filterConfig;
  @Nullable private String visibleUiSections;
  @Nullable private String dynamicFields;
  @Nullable private String clientId;
  @Nullable private String queryTimezone;

  public WriteQueryBuilder setModel(String model) {
    this.model = model;
    return this;
  }

  public WriteQueryBuilder setView(String view) {
    this.view = view;
    return this;
  }

  public WriteQueryBuilder setFields(String[] fields) {
    this.fields = fields;
    return this;
  }

  public WriteQueryBuilder setFilters(Map<String, ? extends Object> filters) {
    this.filters = filters;
    return this;
  }

  public WriteQueryBuilder setLimit(long limit) {
    this.limit = String.valueOf(limit);
    return this;
  }

  public WriteQuery build() {
    return new WriteQuery(
        model,
        view,
        fields,
        pivots,
        fillFields,
        filters,
        filterExpression,
        sorts,
        limit,
        columnLimit,
        total,
        rowTotal,
        subtotals,
        visConfig,
        filterConfig,
        visibleUiSections,
        dynamicFields,
        clientId,
        queryTimezone);
  }
}
