package com.rbc.fruit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/fruit-basket-context.xml"})
public class ItemGeneratorTest {

    @Autowired
    ItemGenerator itemGenerator;

    int quantity = 20;

    @Test
    public void fruitListQuantityTest() {
        List<Fruit> fruit = itemGenerator.generateRandomFruitList(quantity);
        Assert.assertEquals(fruit.size(), quantity);
    }
}
