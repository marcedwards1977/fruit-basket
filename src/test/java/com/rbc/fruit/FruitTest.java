package com.rbc.fruit;


import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FruitTest {

    private static Logger log = LoggerFactory.getLogger(FruitTest.class);

    @Test
    public void distinctFruitTypeTest() {
        int distinctFruitCount = 0;

        for (Fruit fruit : Fruit.values()) {
            log.info("fruit={}", fruit.toString());
            distinctFruitCount++;
        }
        Assert.assertEquals(distinctFruitCount, Fruit.values().length);
    }
}
