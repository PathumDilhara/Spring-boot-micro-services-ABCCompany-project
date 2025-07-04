package com.example.inventory.repo;

import com.example.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

    @Query(value = "SELECT * FROM inventory WHERE item_id=?1", nativeQuery = true)
    Inventory findInventoryItemByItemId(int itemId);
}
