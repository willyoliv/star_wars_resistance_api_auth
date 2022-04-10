package com.oliveira.willy.starwarsresistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oliveira.willy.starwarsresistence.model.enums.Roles;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
