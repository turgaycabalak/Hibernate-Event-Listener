package com.orderservice.hibernateevent;

import com.orderservice.rabbitmq.RabbitMQProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
@RequiredArgsConstructor
public class CustomPostCommitInsertListener implements PostCommitInsertEventListener {
  private final RabbitMQProducer rabbitMQProducer;

  @Override
  public void onPostInsertCommitFailed(PostInsertEvent event) {
    log.error("Persisting failed!");
  }

  @Override
  public void onPostInsert(PostInsertEvent event) {
    log.info("Persisting is ok. Publishing the object.");

    rabbitMQProducer.publish(event.getEntity());
  }

  @Override
  public boolean requiresPostCommitHandling(EntityPersister persister) {
    return true;
  }
}
