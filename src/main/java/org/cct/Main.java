package org.cct;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        FoodItem foodItem = new FoodItem(
                "Arroz",
                3000,
                LocalDate.of(2026, 12, 25));

        System.out.println(foodItem);

    }

}
