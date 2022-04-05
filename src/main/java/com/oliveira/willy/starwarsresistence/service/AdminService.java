package com.oliveira.willy.starwarsresistence.service;

import com.oliveira.willy.starwarsresistence.dto.AdminReport;
import com.oliveira.willy.starwarsresistence.model.Inventory;
import com.oliveira.willy.starwarsresistence.model.Location;
import com.oliveira.willy.starwarsresistence.model.Rebel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    Rebel saveRebel(Rebel rebel);

    void deleteRebel(Long id);

    Rebel updateRebel(Rebel requestRebel, Long id);

    Location updateRebelLocation(Location location, Long rebelId);

    List<Rebel> findAllRebels();

    Page<Rebel> findAllRebelsWithPagination(Pageable pageable);

    Inventory getRebelInventory(Long id);

    AdminReport report();

    Rebel findRebelById(Long id);
}
