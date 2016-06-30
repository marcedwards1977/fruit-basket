package com.rbc.fruit;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ItemGeneratorTest {

    ItemGenerator itemGenerator = new ItemGenerator();

    int quantity = 20;

    @Test
    public void fruitListQuantityTest() {
        List<Fruit> fruit = itemGenerator.generateRandomFruitList(quantity);
        Assert.assertEquals(fruit.size(), quantity);
    }
}
