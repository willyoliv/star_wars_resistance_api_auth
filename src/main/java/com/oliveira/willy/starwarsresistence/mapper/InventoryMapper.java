package com.oliveira.willy.starwarsresistence.mapper;

import com.oliveira.willy.starwarsresistence.dto.InventoryDto;
import com.oliveira.willy.starwarsresistence.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDto inventoryToInventoryDTO(Inventory inventory);
}
