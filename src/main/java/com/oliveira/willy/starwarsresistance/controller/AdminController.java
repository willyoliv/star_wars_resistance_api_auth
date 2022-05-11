package com.oliveira.willy.starwarsresistance.controller;

import com.oliveira.willy.starwarsresistance.dto.*;
import com.oliveira.willy.starwarsresistance.mapper.*;
import com.oliveira.willy.starwarsresistance.model.Location;
import com.oliveira.willy.starwarsresistance.model.Rebel;
import com.oliveira.willy.starwarsresistance.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    private final RebelMapper rebelMapper;

    private final RebelResponseDtoMapper rebelResponseDtoMapper;

    private final LocationMapper locationMapper;

    private final LocationResponseDtoMapper locationResponseDtoMapper;

    private final InventoryMapper inventoryMapper;

    @GetMapping("/list")
    private ResponseEntity<List<RebelResponseDto>> findAllRebels() {
        List<Rebel> rebels = adminService.findAllRebels();
        return new ResponseEntity<>(rebels.stream().map(rebelResponseDtoMapper::rebelToRebelResponseDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<Page<RebelResponseDto>> findAllRebelsWithPagination(@SortDefault.SortDefaults({
            @SortDefault(sort = "name", direction = Sort.Direction.ASC)}) Pageable pageable) {
        Page<Rebel> rebels = adminService.findAllRebelsWithPagination(pageable);
        return new ResponseEntity<>(rebels.map(rebelResponseDtoMapper::rebelToRebelResponseDto), HttpStatus.OK);
    }

    @GetMapping(path = "/rebel/find/{rebelId}")
    private ResponseEntity<RebelResponseDto> findRebelById(@PathVariable("rebelId") Long rebelId) {
        RebelResponseDto rebelResponseDto = rebelResponseDtoMapper.rebelToRebelResponseDto(adminService.findRebelById(rebelId));
        return new ResponseEntity<>(rebelResponseDto, HttpStatus.OK);
    }

    @GetMapping(path = "/rebel/{rebelId}/inventory")
    private ResponseEntity<InventoryDto> getInvetoryRebel(@PathVariable("rebelId") Long rebelId) {
        return new ResponseEntity<>(inventoryMapper.inventoryToInventoryDTO(adminService.inventory(rebelId)), HttpStatus.OK);
    }

    @PostMapping(path = "/rebel/save")
    private ResponseEntity<RebelResponseDto> saveRebel(@Valid @RequestBody RebelCreateDto rebelCreateDto) {
        Rebel rebel = rebelMapper.rebelDTOToRebel(rebelCreateDto);
        return new ResponseEntity<>(rebelResponseDtoMapper.rebelToRebelResponseDto(adminService.saveRebel(rebel)), HttpStatus.CREATED);
    }

    @DeleteMapping("/rebel/delete/{rebelId}")
    private ResponseEntity<Void> deleteRebel(@PathVariable(("rebelId")) Long rebelId) {
        adminService.deleteRebel(rebelId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/rebel/{rebelId}/update-location")
    private ResponseEntity<LocationResponseDto> updateRebelLocation(@PathVariable("rebelId") Long rebelId,
                                                                    @Valid @RequestBody LocationDto locationDto) {
        Location location = adminService.updateRebelLocation(locationMapper.locationDTOToLocation(locationDto), rebelId);
        LocationResponseDto locationResponseDto = locationResponseDtoMapper.locationToLocationResponseDto(location);
        return new ResponseEntity<>(locationResponseDto, HttpStatus.OK);
    }

    @GetMapping(path = "/report")
    public ResponseEntity<AdminReport> report() {
        return new ResponseEntity<>(adminService.report(), HttpStatus.OK);
    }
}
