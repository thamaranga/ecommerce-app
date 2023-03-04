package com.hasitha.inventoryservice.repository;

import com.hasitha.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository  extends JpaRepository<Inventory, Long> {
    @Query("SELECT quentity FROM Inventory i  WHERE i.skuCode=?1")
    int findQuentityBySkuCode(String skuCode);


    Optional<Inventory> findBySkuCode(String skuCode);
}
