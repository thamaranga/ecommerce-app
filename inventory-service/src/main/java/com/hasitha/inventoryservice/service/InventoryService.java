package com.hasitha.inventoryservice.service;

import com.hasitha.inventoryservice.dto.InventoryRequestDTO;
import com.hasitha.inventoryservice.dto.InventoryResponseDTO;
import com.hasitha.inventoryservice.model.Inventory;
import com.hasitha.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryResponseDTO> isInStock(List<InventoryRequestDTO> inventoryRequestDTOS){
        //Below is for demonstrating  resiliance4j timeouts
        /*System.out.println("Start calling isInStock");
        try {
            Thread.sleep(10000);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Endeded calling isInStock");
*/
        List<InventoryResponseDTO>  inventoryResponseDTOS = inventoryRequestDTOS.stream().map(inventoryRequestDTO -> {
            int inventoryCount = inventoryRepository.findQuentityBySkuCode(inventoryRequestDTO.getSkuCode());
            System.out.println("Count is "+inventoryRequestDTO.getSkuCode()+"|"+inventoryCount);
            if (inventoryCount >= inventoryRequestDTO.getQuentity()) {
                return InventoryResponseDTO.builder().isInInventory(true).quentity(inventoryCount).skuCode(inventoryRequestDTO.getSkuCode()).
                        build();
            } else {
                return InventoryResponseDTO.builder().isInInventory(false).
                        quentity(inventoryCount).skuCode(inventoryRequestDTO.getSkuCode()).build();
            }
        }).collect(Collectors.toList());
        return inventoryResponseDTOS;

    }

    public InventoryResponseDTO saveInventory(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory=new Inventory();
        Inventory savedInventory=null;
        InventoryResponseDTO inventoryResponseDTO= null;
        inventory=this.getInventory(inventoryRequestDTO);
        savedInventory=inventoryRepository.save(inventory);
        if(savedInventory!=null){
            inventoryResponseDTO=this.getInventoryResponseDTO(savedInventory);
        }
        return inventoryResponseDTO;
    }

    private  InventoryResponseDTO getInventoryResponseDTO(Inventory inventory){
        InventoryResponseDTO inventoryResponseDTO= new InventoryResponseDTO();
        inventoryResponseDTO.setId(inventory.getId());
        inventoryResponseDTO.setQuentity(inventory.getQuentity());
        inventoryResponseDTO.setSkuCode(inventory.getSkuCode());
        return inventoryResponseDTO;
    }


    private Inventory getInventory(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory=new Inventory();
        inventory.setSkuCode(inventoryRequestDTO.getSkuCode());
        inventory.setQuentity(inventoryRequestDTO.getQuentity());
        return inventory;
    }

    public InventoryResponseDTO updateInventory(String skuCode , int quantity){
        Optional<Inventory> optionalInventory=inventoryRepository.findBySkuCode(skuCode);
        InventoryResponseDTO inventoryResponseDTO=null;
        Inventory existingInventory=null;
        Inventory updatedInventory=null;
        if(optionalInventory.isPresent()){
            existingInventory=optionalInventory.get();
            existingInventory.setQuentity(quantity);
            updatedInventory=inventoryRepository.save(existingInventory);
            if(updatedInventory!=null){
                inventoryResponseDTO=this.getInventoryResponseDTO(updatedInventory);
            }
        }
        return inventoryResponseDTO;
    }

}
