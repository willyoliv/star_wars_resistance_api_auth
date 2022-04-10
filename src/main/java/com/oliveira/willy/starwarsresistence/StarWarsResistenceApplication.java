package com.oliveira.willy.starwarsresistence;

import com.oliveira.willy.starwarsresistence.model.Inventory;
import com.oliveira.willy.starwarsresistence.model.Item;
import com.oliveira.willy.starwarsresistence.model.Location;
import com.oliveira.willy.starwarsresistence.model.Rebel;
import com.oliveira.willy.starwarsresistence.model.enums.Genre;
import com.oliveira.willy.starwarsresistence.model.enums.ItemInventory;
import com.oliveira.willy.starwarsresistence.model.enums.Roles;
import com.oliveira.willy.starwarsresistence.repository.ItemRepository;
import com.oliveira.willy.starwarsresistence.repository.RebelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StarWarsResistenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsResistenceApplication.class, args);
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
