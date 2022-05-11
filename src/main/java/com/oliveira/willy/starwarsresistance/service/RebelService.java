package com.oliveira.willy.starwarsresistance.service;

import com.oliveira.willy.starwarsresistance.model.Item;
import com.oliveira.willy.starwarsresistance.model.Location;
import com.oliveira.willy.starwarsresistance.model.Rebel;
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
