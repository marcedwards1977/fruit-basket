Objective:

Write a program that takes a basket of items and outputs its total cost.

The basket can contain one or more of the following items: Bananas, Oranges, Apples, Lemons, Peaches

Requirements:
Generate fruit basket of items
Total the contents of the basket

Extra:
Added sub total facility


To run:

In the root directory execute,

mvn clean install
mvn exec:java -Dexec.mainClass="com.rbc.fruit.FruitBasketApp" -Dexec.args="20"

The exec.args parameter specifies how many pieces of fruit will be in the basket.




