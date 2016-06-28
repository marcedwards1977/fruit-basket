package com.rbc.fruit;


import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class FruitBasketApp {

    private static Logger log = LoggerFactory.getLogger(FruitBasketApp.class);

    public static void main(String[] args) {

        int quantity = 0;
        if (0 == args.length) {
            quantity = 50;
            log.warn("using default quantity to create basket. User can input integer value on cmd line e.g. java FruitBasketApp <number>");
        } else {
            quantity = Integer.parseInt(args[0]);
        }

        log.info("creating basket of {} items ...", quantity);

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring/fruit-basket-context.xml");

        ItemGenerator itemGenerator = (ItemGenerator) ctx.getBean("itemGenerator");
        FruitBasket fruitBasket = (FruitBasket) ctx.getBean("fruitBasket");

        List<Fruit> fruits = itemGenerator.generateRandomFruitList(quantity);

        printSubTotal(fruitBasket, fruits);

        BigDecimal total = getTotal(fruitBasket, fruits);
        log.info("The total price of your basket of fruit is {}", total);
    }

    static BigDecimal getTotal(FruitBasket fruitBasket, List<Fruit> fruits){
        return fruitBasket.calculateTotal(fruits);
    }

    static void printSubTotal(FruitBasket fruitBasket, List<Fruit> fruits){
        for (Map.Entry<Pair<String, Integer>, BigDecimal> subTotal : fruitBasket.calculateSubTotals(fruits).entrySet()) {
            Pair fruitKey = subTotal.getKey();
            Fruit fruit = Fruit.valueOf((String) fruitKey.getKey());
            log.info("{} {} @ {} each, SubTotal = {}",
                    new Object[]{fruitKey.getValue(), fruitKey.getKey(),
                            fruit.getPrice(),
                            subTotal.getValue()});
        }
    }
}