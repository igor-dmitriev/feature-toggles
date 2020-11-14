package com.igor.feature.newrelic.client;

import com.igor.feature.newrelic.dto.Deployment;

public interface NewRelicClient {
  void createDeployment(Deployment deployment);
}