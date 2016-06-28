package com.rbc.fruit;

import com.google.common.collect.Maps;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FruitBasket {

    /**
     * Calculate 'Total' based on List of <Fruit> using getPrice
     * @param fruitList
     * @return
     */
    BigDecimal calculateTotal(List<Fruit> fruitList) {

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

        Map<Pair<String, Integer>, BigDecimal> subTotalByFruitType = Maps.newHashMap();
        Map<String, List<Fruit>> index = fruitList.stream().collect(Collectors.groupingBy(Fruit::getName));

        index.entrySet().forEach(entry -> {
            List<BigDecimal> fruits = index.get(entry.getKey()).stream().map(f -> f.getPrice()).collect(Collectors.toList());
            BigDecimal subTotal = fruits.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            subTotalByFruitType.put(new Pair(entry.getKey(), fruits.size()), subTotal);
        });

        return subTotalByFruitType;
    }
}
