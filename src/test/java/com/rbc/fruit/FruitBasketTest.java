package com.rbc.fruit;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/fruit-basket-context.xml"})
public class FruitBasketTest {

    private static Logger log = LoggerFactory.getLogger(FruitBasketTest.class);

    @Autowired
    ItemGenerator itemGenerator;
    @Autowired
    FruitBasket fruitBasket;

    int quantity = 20;
    List<Fruit> fruitList;

    @Test
    public void emptyBasketTest() {
        fruitList = itemGenerator.generateRandomFruitList(0);
        BigDecimal total = fruitBasket.calculateTotal(fruitList);
        Assert.assertTrue(total.compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void basketTotalTest() {
        fruitList = itemGenerator.generateRandomFruitList(quantity);
        BigDecimal total = fruitBasket.calculateTotal(fruitList);
        Assert.assertTrue(total.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    public void basketSubTotalTest() {
        fruitList = itemGenerator.generateRandomFruitList(quantity);
        Map<Pair<String, Integer>, BigDecimal> subTotals = fruitBasket.calculateSubTotals(fruitList);

        for(Map.Entry<Pair<String, Integer>, BigDecimal> subTotal : subTotals.entrySet()){
            Pair fruitPair = subTotal.getKey();
            Fruit fruit = Fruit.valueOf((String)fruitPair.getKey());
            log.info("{} {} @ {} each, SubTotal = {}",
                    new Object[] { fruitPair.getValue(), fruitPair.getKey(),
                            fruit.getPrice(), subTotal.getValue() });
            Assert.assertTrue(subTotal.getValue().compareTo(BigDecimal.ZERO) > 0);
        }
    }
}
