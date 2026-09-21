package org.cct;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        StackStorage stackStorage = new StackStorage();

        FoodItem rice = new FoodItem("Rice", 1000, LocalDate.of(2027, 1, 01));
        stackStorage.push(rice);

        FoodItem pasta = new FoodItem("Pasta", 1000, LocalDate.of(2027, 1, 01));
        stackStorage.push(pasta);

        FoodItem oats = new FoodItem("Oats", 1000, LocalDate.of(2027, 1, 01));
        stackStorage.push(oats);

        System.out.println(rice);
        System.out.println(pasta);
        System.out.println(oats);



        stackStorage.search("pasta");

        //FoodItem removed = stackStorage.pop();
        //System.out.println("Removed: " + removed);

    }

}
