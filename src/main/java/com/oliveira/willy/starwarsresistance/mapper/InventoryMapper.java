package com.oliveira.willy.starwarsresistance.mapper;

import com.oliveira.willy.starwarsresistance.dto.InventoryDto;
import com.oliveira.willy.starwarsresistance.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDto inventoryToInventoryDTO(Inventory inventory);
}
