package com.oliveira.willy.starwarsresistence.service;

import com.oliveira.willy.starwarsresistence.dto.AdminReport;
import com.oliveira.willy.starwarsresistence.exception.DuplicateItemsInventoryException;
import com.oliveira.willy.starwarsresistence.exception.RebelNotFoundException;
import com.oliveira.willy.starwarsresistence.model.Inventory;
import com.oliveira.willy.starwarsresistence.model.Item;
import com.oliveira.willy.starwarsresistence.model.Location;
import com.oliveira.willy.starwarsresistence.model.Rebel;
import com.oliveira.willy.starwarsresistence.model.enums.ItemInventory;
import com.oliveira.willy.starwarsresistence.model.enums.Roles;
import com.oliveira.willy.starwarsresistence.repository.LocationRepository;
import com.oliveira.willy.starwarsresistence.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final RebelRepository rebelRepository;

    private final LocationRepository locationRepository;

    private final PasswordEncoder passwordEncoder;

    private final int inventorySize = ItemInventory.values().length;

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Transactional
    @Override
    public Rebel saveRebel(Rebel rebel) {
        int itemsInRebelInventorySize = countDistinctItemsInInventory(rebel.getInventory().getItems());

        if (itemsInRebelInventorySize != inventorySize) {
            logger.error("There are duplicate items in the inventory");
            throw new DuplicateItemsInventoryException("There are duplicate items in the inventory");
        }
        rebel.getInventory().setInventoryToItem();
        rebel.setPassword(passwordEncoder.encode(rebel.getPassword()));
        return rebelRepository.save(rebel);
    }

    @Transactional
    @Override
    public void deleteRebel(Long id) {
        Rebel rebel = findRebelById(id);
        rebelRepository.delete(rebel);
    }

    @Transactional
    @Override
    public Rebel updateRebel(Rebel requestRebel, Long id) {
        Rebel rebelToBeSaved = findRebelById(id);
        BeanUtils.copyProperties(requestRebel, rebelToBeSaved);
        return rebelRepository.save(rebelToBeSaved);
    }

    @Transactional
    @Override
    public Location updateRebelLocation(Location location, Long rebelId) {
        Rebel rebel = findRebelById(rebelId);
        location.setId(rebel.getLocation().getId());
        location.setLastUpdatedUserRole(Roles.ADMIN);
        return locationRepository.save(location);
    }

    @Transactional
    @Override
    public List<Rebel> findAllRebels() {
        return rebelRepository.findAll();
    }

    @Transactional
    @Override
    public Page<Rebel> findAllRebelsWithPagination(Pageable pageable) {
        return rebelRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Inventory getRebelInventory(Long id) {
        Rebel rebel = findRebelById(id);
        return rebel.getInventory();
    }

    @Transactional
    @Override
    public AdminReport report() {
        List<Rebel> rebels = rebelRepository.findAll();
        int quantityTotal = rebels.size();
        int quantityOfTraitors = countTraitor(rebels);
        int quantityOfRebels = quantityTotal - quantityOfTraitors;

        Map<ItemInventory, Double> mapAverage = calculateAverageOfItemsPerRebel(rebels);

        int sumLostPoints = countLostPointsBecauseOfTraitors(rebels);
        Double percentageOfRebels = (double) quantityOfRebels / quantityTotal;
        Double percentageOfTraitors = (double) quantityOfTraitors / quantityTotal;

        return AdminReport.builder()
                .averageOfItems(mapAverage)
                .percentageOfRebels(Double.isNaN(percentageOfRebels) ? 0.0 : percentageOfRebels)
                .percentageOfTraitors(Double.isNaN(percentageOfTraitors) ? 0.0 : percentageOfTraitors)
                .lostPoints(sumLostPoints)
                .build();
    }

    private int countTraitor(List<Rebel> rebels) {
        return rebels.stream().filter(Rebel::isTraitor).collect(Collectors.toList()).size();
    }

    private Map<ItemInventory, Double> calculateAverageOfItemsPerRebel(List<Rebel> rebels) {

        List<Item> items = rebels.stream().filter((rebel -> !rebel.isTraitor()))
                .map(Rebel::getInventory)
                .map(Inventory::getItems)
                .flatMap(List::stream).collect(Collectors.toList());

        Map<ItemInventory, Double> itemInventoryMap = items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.averagingInt(Item::getQuantity)));

        return itemInventoryMap;
    }

    private int countLostPointsBecauseOfTraitors(List<Rebel> rebels) {
        int sum = rebels.stream().filter(Rebel::isTraitor)
                .map(Rebel::getInventory)
                .map(Inventory::getItems)
                .flatMap(List::stream).collect(Collectors.toList())
                .stream().mapToInt(Item::getQuantity).sum();
        return sum;
    }

    private int countDistinctItemsInInventory(List<Item> items) {
        return (int) items.stream()
                .map(Item::getName)
                .distinct().count();
    }
    @Override
    public Rebel findRebelById(Long id) {
        return rebelRepository.findById(id).orElseThrow(() -> new RebelNotFoundException("Rebel ID " + id + " not found!"));
    }
}
