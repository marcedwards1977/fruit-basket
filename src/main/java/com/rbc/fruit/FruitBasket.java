package com.rbc.fruit;

import com.google.common.collect.Maps;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FruitBasket {

    private static Logger log = LoggerFactory.getLogger(FruitBasket.class);

    /**
     * Calculate 'Total' based on List of <Fruit> using getPrice
     *
     * @param fruitList
     * @return
     */
    BigDecimal calculateTotal(List<Fruit> fruitList) {
        log.debug("calculateTotal( {} )", fruitList);

        return fruitList.stream()
                .map(Fruit::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Group list by fruit 'type' and calculate subtotal
     *
     * @param fruitList
     * @return
     */
    Map<Pair<String, Integer>, BigDecimal> calculateSubTotals(List<Fruit> fruitList) {
        log.debug("calculateSubTotals( {} ) : start", fruitList);

        Map<Pair<String, Integer>, BigDecimal> subTotalByFruitType = Maps.newHashMap();

        Map<String, List<Fruit>> index = fruitList.stream().collect(Collectors.groupingBy(Fruit::getName));

        for (String fruit : index.keySet()) {
            List<BigDecimal> fruits = index.get(fruit).stream().map(f -> f.getPrice()).collect(Collectors.toList());
            BigDecimal subTotal = fruits.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            subTotalByFruitType.put(new Pair(fruit, fruits.size()), subTotal);
        }

        log.debug("calculateSubTotals() : end : [subTotalByFruitType={}]", subTotalByFruitType);
        return subTotalByFruitType;
    }
}
