package com.example.inventory.controller;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/inventoryItem")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getAll")
    public List<InventoryDTO> getAllInventories(){
        return inventoryService.getAllInventoryItems();
    }

    @GetMapping("/{itemId}")
    public InventoryDTO getInventoryItemByItemId (@PathVariable Integer itemId){

        System.out.println("%%%%%%%%%%%%%%%%%%%%%% inventory controller "+itemId);
        return inventoryService.getInventoryItemByItemId(itemId);
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
