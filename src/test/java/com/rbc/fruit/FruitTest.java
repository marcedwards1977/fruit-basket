package com.rbc.fruit;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/fruit-basket-context.xml"})
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
