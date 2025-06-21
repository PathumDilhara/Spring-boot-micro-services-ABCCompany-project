package com.example.inventory.services;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.entity.Inventory;
import com.example.inventory.repo.InventoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventoryItems(){
       List<Inventory> inventories = inventoryRepo.findAll();
       return modelMapper.map(inventories, new TypeToken<List<InventoryDTO>>(){}.getType());
    }
    public InventoryDTO getInventoryItemByItemId(Integer itemId){
        Inventory inventoryItem =  inventoryRepo.findInventoryItemByItemId(itemId);
        return modelMapper.map(inventoryItem, InventoryDTO.class);
    }

    public InventoryDTO saveInventory(InventoryDTO inventoryDTO){
        Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);
        inventoryRepo.save(inventory);
        return inventoryDTO;
    }

    public InventoryDTO updateInventory(InventoryDTO inventoryDTO){
        Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);
        inventoryRepo.save(inventory);
        return inventoryDTO;
    }

    public boolean deleteInventory(InventoryDTO inventoryDTO){
        inventoryRepo.delete(modelMapper.map(inventoryDTO, Inventory.class));
        return true;
    }
}
