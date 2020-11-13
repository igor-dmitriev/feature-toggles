package com.igor.feature.config;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.EventRepository;
import org.ff4j.audit.repository.JdbcEventRepository;
import org.ff4j.cache.FF4JCacheManager;
import org.ff4j.cache.FF4jCacheManagerRedis;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.store.JdbcPropertyStore;
import org.ff4j.property.store.PropertyStore;
import org.ff4j.security.AuthorizationsManager;
import org.ff4j.store.JdbcFeatureStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Ff4jConfig {

  private final DataSource dataSource;
  @Value("${redis.host}")
  private String redisHost;

  @Bean
  public FF4j getFF4j(AuthorizationsManager authorizationsManager) {
    FF4j ff4j = new FF4j();
    ff4j.setFeatureStore(featureStore());
    ff4j.setEventRepository(eventRepository());
    ff4j.setPropertiesStore(propertyStore());
    ff4j.audit(true);
    ff4j.cache(ff4jCacheManager());
    ff4j.setAuthorizationsManager(authorizationsManager);
    return ff4j;
  }

  @Bean
  public FF4JCacheManager ff4jCacheManager() {
    return new FF4jCacheManagerRedis(redisHost, 6379);
  }

  @Bean
  public FeatureStore featureStore() {
    return new JdbcFeatureStore(dataSource);
  }

  @Bean
  public EventRepository eventRepository() {
    return new CustomEventRepository(new JdbcEventRepository(dataSource));
  }

  @Bean
  public PropertyStore propertyStore() {
    return new JdbcPropertyStore(dataSource);
  }
}