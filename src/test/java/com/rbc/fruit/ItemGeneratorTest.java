package com.rbc.fruit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/fruit-basket-context.xml"})
public class ItemGeneratorTest {

    private static Logger log = LoggerFactory.getLogger(ItemGeneratorTest.class);

    @Autowired
    ItemGenerator itemGenerator;

    int quantity = 20;

    @Test
    public void generateRandomFruitListTest() {
        log.info("generateRandomFruitListTest() : start");

        List<Fruit> fruit = itemGenerator.generateRandomFruitList(quantity);
        Assert.assertEquals(fruit.size(), quantity);

        log.info("generateRandomFruitListTest() : end");
    }
}
