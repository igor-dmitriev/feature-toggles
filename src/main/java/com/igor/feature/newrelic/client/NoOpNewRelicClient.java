package com.igor.feature.newrelic.client;

import com.igor.feature.newrelic.dto.Deployment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class NoOpNewRelicClient implements NewRelicClient {
  @Override
  public void createDeployment(Deployment deployment) {

  }
}
