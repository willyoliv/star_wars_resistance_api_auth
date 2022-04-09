package com.oliveira.willy.starwarsresistence.repository;

import com.oliveira.willy.starwarsresistence.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
