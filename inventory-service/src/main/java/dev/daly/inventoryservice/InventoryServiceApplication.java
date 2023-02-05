package dev.daly.inventoryservice;

import dev.daly.inventoryservice.model.Inventory;
import dev.daly.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("iphone_13");
            inventory1.setQuantity(10);
            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("iphone_14");
            inventory2.setQuantity(5);
            Inventory inventory3 = new Inventory();
            inventory3.setSkuCode("iphone_14_pro");
            inventory3.setQuantity(0);

            inventoryRepository.saveAll(List.of(inventory1, inventory2, inventory3));
        };
    }
}

