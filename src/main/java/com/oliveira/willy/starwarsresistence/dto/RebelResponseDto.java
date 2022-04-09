package com.oliveira.willy.starwarsresistence.dto;

import com.oliveira.willy.starwarsresistence.model.enums.Genre;
import com.oliveira.willy.starwarsresistence.model.enums.Roles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RebelResponseDto {

    private Long id;

    private String name;

    private String username;

    private int age;

    private Genre genre;

    private LocationResponseDto location;

    private InventoryResponseDto inventory;

    private List<ReportResponseDto> report;

    private Roles role;

    private boolean traitor;
}
