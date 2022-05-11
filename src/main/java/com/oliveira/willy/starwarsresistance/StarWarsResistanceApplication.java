package com.oliveira.willy.starwarsresistance;

import com.oliveira.willy.starwarsresistance.model.Inventory;
import com.oliveira.willy.starwarsresistance.model.Item;
import com.oliveira.willy.starwarsresistance.model.Location;
import com.oliveira.willy.starwarsresistance.model.Rebel;
import com.oliveira.willy.starwarsresistance.model.enums.Genre;
import com.oliveira.willy.starwarsresistance.model.enums.ItemInventory;
import com.oliveira.willy.starwarsresistance.model.enums.Roles;
import com.oliveira.willy.starwarsresistance.repository.ItemRepository;
import com.oliveira.willy.starwarsresistance.repository.RebelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StarWarsResistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsResistanceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RebelRepository rebelRepository, ItemRepository itemRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            Rebel rebel = rebelRepository.save(Rebel.builder()
                    .name("Rebel")
                    .age(20)
                    .password(passwordEncoder.encode("password"))
                    .username("rebel")
                    .role(Roles.ADMIN)
                    .genre(Genre.MALE)
                    .isTraitor(false)
                    .location(Location.builder()
                            .galaxyName("Galaxy name")
                            .latitude(123123L)
                            .longitude(123123L)
                            .lastUpdatedUserRole(Roles.ADMIN)
                            .build())
                    .inventory(Inventory.builder()
                            .items(List.of(Item.builder().name(ItemInventory.WEAPON).quantity(1).build(),
                                    Item.builder().name(ItemInventory.WATER).quantity(1).build(),
                                    Item.builder().name(ItemInventory.FOOD).quantity(1).build(),
                                    Item.builder().name(ItemInventory.BULLET).quantity(1).build()
                            ))
                            .createdAt(LocalDateTime.now())
                            .updatedAt(null)
                            .build())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(null)
                    .report(new ArrayList<>())
                    .build());

            for (Item item : rebel.getInventory().getItems()) {
                item.setInventory(rebel.getInventory());
                itemRepository.save(item);
            }
        };
    }

}
