package com.orderservice;

import java.util.List;
import java.util.UUID;

import com.orderservice.entity.Customer;
import com.orderservice.entity.Item;
import com.orderservice.repository.CustomerRepository;
import com.orderservice.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityItemInitializer implements ApplicationRunner {
  private final ItemRepository itemRepository;
  private final CustomerRepository customerRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    List<Item> toSaveAll = List.of(
        Item.builder()
            .id(UUID.fromString("6a0e0f49-46da-4414-b472-528ab019670f"))
            .productName("Kalem")
            .quantity(100)
            .build(),
        Item.builder()
            .id(UUID.fromString("de6e39d6-d56e-482d-8a60-58deb71b484d"))
            .productName("Kağıt")
            .quantity(5000)
            .build(),
        Item.builder()
            .id(UUID.fromString("b5b06e4f-8c54-4a78-bbde-472c8702fba3"))
            .productName("Defter")
            .quantity(250)
            .build(),
        Item.builder()
            .id(UUID.fromString("3ad45c89-914c-47f0-94ea-83608597da13"))
            .productName("Silgi")
            .quantity(300)
            .build(),
        Item.builder()
            .id(UUID.fromString("65c1983e-1f65-4d8b-9640-34569ee04b7f"))
            .productName("Tablet")
            .quantity(12)
            .build()
    );
    for (Item item : toSaveAll) {
      if (!itemRepository.existsById(item.getId())) {
        itemRepository.save(item);
      }
    }

    Customer toSave = Customer.builder()
        .id(UUID.fromString("0193877a-9be2-7df4-aa9b-fca92ca83385"))
        .firstName("Turgay")
        .lastName("ÇABALAK")
        .address("İstanbul")
        .build();
    if (!customerRepository.existsById(toSave.getId())) {
      customerRepository.save(toSave);
    }
  }
}
