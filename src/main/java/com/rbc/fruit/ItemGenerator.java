package com.rbc.fruit;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ItemGenerator {

    /**
     * Generate a random list of Fruit based on Fruit.FruitEnum
     * @param totalItems
     * @return
     */
    public List<Fruit> generateRandomFruitList(int totalItems){

        List<Fruit> fruitList = new ArrayList<>();

        Random random = new Random();
        IntStream randomStream = random.ints(0, Fruit.values().length);

        List<Integer> randomFruitStream = randomStream
                .limit(totalItems)
                .boxed()
                .collect(Collectors.toList());

        randomFruitStream.forEach(entry -> {
            fruitList.add(Fruit.values()[entry.intValue()]);
        });

        return fruitList;
    }
}
