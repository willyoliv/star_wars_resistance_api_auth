package com.oliveira.willy.starwarsresistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oliveira.willy.starwarsresistance.model.enums.Roles;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String galaxyName;

    private Long latitude;

    private Long longitude;

    @OneToOne(mappedBy = "location")
    @JsonBackReference
    private Rebel rebel;

    @Enumerated(EnumType.STRING)
    private Roles lastUpdatedUserRole;

}
