package com.oliveira.willy.starwarsresistance.service;

import com.oliveira.willy.starwarsresistance.dto.AdminReport;
import com.oliveira.willy.starwarsresistance.model.Inventory;
import com.oliveira.willy.starwarsresistance.model.Location;
import com.oliveira.willy.starwarsresistance.model.Rebel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AdminService {
    @PreAuthorize("hasAuthority('ADMIN')")
    Rebel saveRebel(Rebel rebel);

    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteRebel(Long id);

    @PreAuthorize("hasAuthority('ADMIN')")
    Rebel updateRebel(Rebel requestRebel, Long id);

    @PreAuthorize("hasAuthority('ADMIN')")
    Location updateRebelLocation(Location location, Long rebelId);

    @PreAuthorize("hasAuthority('ADMIN')")
    List<Rebel> findAllRebels();

    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Rebel> findAllRebelsWithPagination(Pageable pageable);

    @PreAuthorize("hasAuthority('ADMIN')")
    Inventory inventory(Long id);

    @PreAuthorize("hasAuthority('ADMIN')")
    AdminReport report();

    @PreAuthorize("hasAuthority('ADMIN')")
    Rebel findRebelById(Long id);
}
