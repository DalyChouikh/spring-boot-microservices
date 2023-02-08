package dev.daly.inventoryservice.service;

import dev.daly.inventoryservice.dto.InventoryResponse;
import dev.daly.inventoryservice.model.Inventory;
import dev.daly.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Fetching inventory for skuCode: {}", skuCode);
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                               .skuCode(inventory.getSkuCode())
                               .isInStock(inventory.getQuantity() > 0)
                               .build()
                ).toList();
    }

}
