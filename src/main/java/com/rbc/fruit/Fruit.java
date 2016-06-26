package com.rbc.fruit;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Fruit {

    enum FruitEnum {

        BANANAS(0.50),
        ORANGES(0.45),
        APPLES(0.12),
        LEMONS(0.17),
        PEACHES(1.00);

        double value;

        FruitEnum(double value) {
            this.value = value;
        }
    }

    private final String name;
    BigDecimal price;

    Fruit(String name, double value) {
        this.name = name;
        price = BigDecimal.valueOf(value);
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", price=" + NumberFormat.getCurrencyInstance().format(price) +
                '}';
    }
}
