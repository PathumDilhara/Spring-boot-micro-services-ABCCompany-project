package com.example.inventory.controller;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getAllInventories")
    public List<InventoryDTO> getAllInventories(){
        return inventoryService.getAllInventoryItems();
    }

    @PostMapping("/saveInventory")
    public InventoryDTO saveInventory(@RequestBody InventoryDTO inventoryDTO){
        return  inventoryService.saveInventory(inventoryDTO);
    }

    @PutMapping("/updatesInventory")
    public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO){
        return  inventoryService.updateInventory(inventoryDTO);
    }

    @DeleteMapping("/deleteInventory")
    public boolean deleteInventory(@RequestBody InventoryDTO inventoryDTO){
       return inventoryService.deleteInventory(inventoryDTO);
    }
}
