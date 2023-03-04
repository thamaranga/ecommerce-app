package com.hasitha.inventoryservice.controller;

import com.hasitha.inventoryservice.dto.InventoryRequestDTO;
import com.hasitha.inventoryservice.dto.InventoryResponseDTO;
import com.hasitha.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class inventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/checkInventory")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDTO> isInStock(@RequestBody List<InventoryRequestDTO> inventoryRequestDTOS){
        return inventoryService.isInStock(inventoryRequestDTOS);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponseDTO addInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        return inventoryService.saveInventory(inventoryRequestDTO);
    }

    @PutMapping("/{skuCode}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponseDTO updateInventory(String skuCode, int quantity){
        return inventoryService.updateInventory(skuCode,quantity);
    }
}
