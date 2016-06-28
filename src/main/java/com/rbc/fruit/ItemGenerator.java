package com.rbc.fruit;


import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

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

        List<Fruit> fruitList = Lists.newArrayList();

        Random random = new Random();
        IntStream randomStream = random.ints(0, Fruit.values().length);

        List<Integer> randomFruitStream = randomStream
                .limit(totalItems)
                .boxed()
                .collect(Collectors.toList());

        for(Integer i : randomFruitStream){
            Fruit fruit = Fruit.values()[i];
            fruitList.add(fruit);
        }
        return fruitList;
    }
}
