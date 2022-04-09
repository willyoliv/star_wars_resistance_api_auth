package com.oliveira.willy.starwarsresistence.repository;

import com.oliveira.willy.starwarsresistence.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
