package com.oliveira.willy.starwarsresistence.model.enums;

public enum ItemInventory {
    WEAPON(4),
    BULLET(3),
    WATER(2),
    FOOD(1);

    public final int value;

    ItemInventory(int value) {
        this.value = value;
    }
}
