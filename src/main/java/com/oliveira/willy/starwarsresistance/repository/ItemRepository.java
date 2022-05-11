package com.oliveira.willy.starwarsresistance.repository;

import com.oliveira.willy.starwarsresistance.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
