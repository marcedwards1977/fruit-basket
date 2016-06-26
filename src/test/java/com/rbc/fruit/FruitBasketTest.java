package com.rbc.fruit;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.NumberFormat;
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
    NumberFormat fmt;

    @Test
    public void basketTotalTest() {
        log.info("basketTotalTest() : start");

        BigDecimal total = fruitBasket.calculateTotal(fruitList);
        Assert.assertTrue(total.compareTo(BigDecimal.ZERO) > 0);

        log.info("basketTotalTest() : end : [total={}]", total);
    }

    @Test
    public void basketSubTotalTest() {
        log.info("basketSubTotalTest() : start");

        Map<Pair<String, Integer>, BigDecimal> subTotals = fruitBasket.calculateSubTotals(fruitList);

        for(Map.Entry<Pair<String, Integer>, BigDecimal> subTotal : subTotals.entrySet()){
            Pair fruitKey = subTotal.getKey();
            Fruit.FruitEnum fruit = Fruit.FruitEnum.valueOf((String)fruitKey.getKey());

            log.info("{} {} @ {} each, SubTotal = {}",
                    new Object[] { fruitKey.getValue(), fruitKey.getKey(),
                            fmt.format(fruit.value), fmt.format(subTotal.getValue()) });

            Assert.assertTrue(subTotal.getValue().compareTo(BigDecimal.ZERO) > 0);
        }
        log.info("basketSubTotalTest() : end");
    }

    @Before
    public void setUp(){
        fruitList = itemGenerator.generateRandomFruitList(quantity);
        fmt = NumberFormat.getCurrencyInstance();
    }
}
