package com.inventive.helpers;

import com.google.gson.Gson;
import com.looker.sdk.WriteQuery;
import org.jetbrains.annotations.Nullable;

/**
 * Value object meant for use with {@link com.inventive.clients.LookerClient#runInlineQuery}.
 */
public class RunInlineQueryParams {
  public final WriteQuery writeQuery;
  public final String resultFormat;

  @Nullable public final Long limit;
  @Nullable public final Boolean applyFormatting;
  @Nullable public final Boolean applyVis;
  @Nullable public final Boolean cache;

  private RunInlineQueryParams(Builder builder) {
    this.writeQuery = builder.writeQuery;
    this.resultFormat = builder.resultFormat;

    this.limit = builder.limit;
    this.applyFormatting = builder.applyFormatting;
    this.applyVis = builder.applyVis;
    this.cache = builder.cache;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {
    public WriteQuery writeQuery;
    public String resultFormat;

    public long limit;
    public boolean applyFormatting;
    public boolean applyVis;
    public boolean cache;

    public Builder setWriteQuery(WriteQuery writeQuery) {
      this.writeQuery = writeQuery;
      return this;
    }

    public Builder setWriteQuery(WriteQueryBuilder writeQueryBuilder) {
      WriteQuery query = writeQueryBuilder.build();
      return setWriteQuery(query);
    }

    public Builder setResultFormat(String resultFormat) {
      this.resultFormat = resultFormat;
      return this;
    }

    public Builder setCache(boolean cache) {
      this.cache = cache;
      return this;
    }

    public Builder setLimit(long limit) {
      this.limit = limit;
      return this;
    }

    public RunInlineQueryParams build() {
      return new RunInlineQueryParams(this);
    }
  }
}
