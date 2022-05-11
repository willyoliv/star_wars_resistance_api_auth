package com.oliveira.willy.starwarsresistance.controller;

import com.oliveira.willy.starwarsresistance.dto.*;
import com.oliveira.willy.starwarsresistance.mapper.ItemMapper;
import com.oliveira.willy.starwarsresistance.mapper.LocationMapper;
import com.oliveira.willy.starwarsresistance.mapper.LocationResponseDtoMapper;
import com.oliveira.willy.starwarsresistance.mapper.RebelResponseDtoMapper;
import com.oliveira.willy.starwarsresistance.model.Item;
import com.oliveira.willy.starwarsresistance.model.Location;
import com.oliveira.willy.starwarsresistance.model.Rebel;
import com.oliveira.willy.starwarsresistance.service.RebelService;
import com.oliveira.willy.starwarsresistance.utils.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("rebels")
public class RebelController {
    private final RebelService rebelService;

    private final LocationMapper locationMapper;

    private final LocationResponseDtoMapper locationResponseDtoMapper;

    private final ItemMapper itemMapper;

    private final RebelResponseDtoMapper rebelResponseDtoMapper;

    @GetMapping(path = "/find")
    private ResponseEntity<RebelResponseDto> findRebelByName() {
        RebelResponseDto rebelResponseDto = rebelResponseDtoMapper.rebelToRebelResponseDto(rebelService.findRebel());
        return new ResponseEntity<>(rebelResponseDto, HttpStatus.OK);
    }

    @PutMapping(path = "/update-location")
    private ResponseEntity<LocationResponseDto> updateRebelLocation(@Valid @RequestBody LocationDto locationDto) {
        Location location = rebelService.updateRebelLocation(locationMapper.locationDTOToLocation(locationDto));
        LocationResponseDto locationResponseDto = locationResponseDtoMapper.locationToLocationResponseDto(location);
        return new ResponseEntity<>(locationResponseDto, HttpStatus.OK);
    }

    @PostMapping(path = "/report-traitor")
    private ResponseEntity<SuccessMessage> reportRebelTraitor(@Valid @RequestBody ReportCreateDto reportCreateDto) {
        Rebel accuser = this.rebelService.findRebel();
        Rebel accused = this.rebelService.findRebelById(reportCreateDto.getAccusedId());
        rebelService.reportRebelTraitor(accuser, accused, reportCreateDto.getReason());
        return new ResponseEntity<>(new SuccessMessage("Report made successfully."), HttpStatus.OK);
    }

    @PostMapping(path = "/inventory/trade")
    public ResponseEntity<SuccessMessage> tradeItemsBetweemRebels(@Valid @RequestBody TradeDto tradeDto) {
        Rebel fromRebel = rebelService.findRebel();
        Rebel toRebel = rebelService.findRebelById(tradeDto.getToRebel().getRebelId());
        List<Item> fromRebelItems = tradeDto.getFromRebel().getItems().stream()
                .map(itemMapper::itemDTOToItem).collect(Collectors.toList());
        List<Item> toRebelItems = tradeDto.getToRebel().getItems().stream()
                .map(itemMapper::itemDTOToItem).collect(Collectors.toList());
        rebelService.trade(fromRebel, toRebel, fromRebelItems, toRebelItems);
        return new ResponseEntity<>(new SuccessMessage("Trade made successfully."), HttpStatus.OK);
    }
}
