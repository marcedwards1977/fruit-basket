package com.rbc.fruit;

import java.math.BigDecimal;

public enum Fruit {

        BANANAS(0.50),
        ORANGES(0.45),
        APPLES(0.12),
        LEMONS(0.17),
        PEACHES(1.00);

    BigDecimal price;

    Fruit(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public String getName() {
        return this.name();
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}
