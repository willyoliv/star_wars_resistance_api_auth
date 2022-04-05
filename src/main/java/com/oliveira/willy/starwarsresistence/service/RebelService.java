package com.oliveira.willy.starwarsresistence.service;

import com.oliveira.willy.starwarsresistence.model.Item;
import com.oliveira.willy.starwarsresistence.model.Location;
import com.oliveira.willy.starwarsresistence.model.Rebel;

import java.util.List;

public interface RebelService {
    Rebel findRebelById(Long id);

    Location updateRebelLocation(Location location, Long rebelId);

    void reportRebelTraitor(Rebel accuser, Rebel accused, String reason);

    void trade(Rebel fromRebel, Rebel toRebel, List<Item> fromRebelItems, List<Item> toRebelItems);
}
