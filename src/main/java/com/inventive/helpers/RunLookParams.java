package com.inventive.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.looker.sdk.WriteQuery;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Value object meant for use with {@link com.inventive.clients.LookerClient#runLook}.
 */
public class RunLookParams {
  public final String lookId;
  public final String resultFormat;

  @Nullable public final Long limit;
  @Nullable public final Boolean applyFormatting;
  @Nullable public final Boolean applyVis;
  @Nullable public final Boolean cache;

  private RunLookParams(Builder builder) {
    this.lookId = builder.lookId;
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
    public String lookId;
    public String resultFormat;

    public long limit;
    public boolean applyFormatting;
    public boolean applyVis;
    public boolean cache;

    public Builder setLookId(String lookId) {
      this.lookId = lookId;
      return this;
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

    public RunLookParams build() {
      return new RunLookParams(this);
    }
  }
}
