package com.oliveira.willy.starwarsresistence.service;

import com.oliveira.willy.starwarsresistence.model.Item;
import com.oliveira.willy.starwarsresistence.model.Location;
import com.oliveira.willy.starwarsresistence.model.Rebel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RebelService {
    @PreAuthorize("hasAuthority('REBEL')")
    Rebel findRebelById(Long id);

    @PreAuthorize("hasAuthority('REBEL')")
    Location updateRebelLocation(Location location);

    @PreAuthorize("hasAuthority('REBEL')")
    void reportRebelTraitor(Rebel accuser, Rebel accused, String reason);

    @PreAuthorize("hasAuthority('REBEL')")
    void trade(Rebel fromRebel, Rebel toRebel, List<Item> fromRebelItems, List<Item> toRebelItems);

    @PreAuthorize("hasAuthority('REBEL')")
    Rebel findRebel();
}
