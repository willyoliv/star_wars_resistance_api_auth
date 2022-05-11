package com.oliveira.willy.starwarsresistance.dto;

import com.oliveira.willy.starwarsresistance.model.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponseDto {

    private Long id;

    private String galaxyName;

    private Long latitude;

    private Long longitude;

    private Roles lastUpdatedUserRole;
}
