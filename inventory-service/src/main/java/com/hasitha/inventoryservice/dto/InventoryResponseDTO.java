package com.hasitha.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponseDTO {

    private Long id;
    private String skuCode;
    private int quentity;
    boolean isInInventory;
}
