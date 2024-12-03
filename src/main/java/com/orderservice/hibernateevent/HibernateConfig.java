package com.orderservice.hibernateevent;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;

import com.orderservice.rabbitmq.RabbitMQProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HibernateConfig {
  private final EntityManagerFactory entityManagerFactory;
  private final RabbitMQProducer rabbitMQProducer;

  @PostConstruct
  public void registerListeners() {
    log.info("Registering CustomPostCommitInsertListener...");

    SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
    if (sessionFactory == null) {
      log.error("SessionFactory is null, cannot register listeners.");
      return;
    }

    EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
    if (registry == null) {
      log.error("EventListenerRegistry is null, cannot register listeners.");
      return;
    }

    registry.getEventListenerGroup(EventType.POST_COMMIT_INSERT)
        .appendListener(new CustomPostCommitInsertListener(rabbitMQProducer));
  }
}
