package org.cct;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        /*
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


        FoodItem foodSearch = stackStorage.search("pasta");
        if(foodSearch != null){
            System.out.println("Food Found: "+foodSearch);
        }
        */


        /****TEST TEST TEST ****/
        QueueStorage queueStorage = new QueueStorage();

        for (int i = 1; i <= 8; i++) {
            FoodItem box = new FoodItem("Box " + i, 1000, LocalDate.of(2026, 12, 25));
            queueStorage.enqueue(box);
        }

        FoodItem box9 = new FoodItem("Box 9", 1000, LocalDate.of(2026, 12, 25));
        queueStorage.enqueue(box9);

        System.out.println("Removed: " + queueStorage.dequeue());  // esperado: Box 1
        queueStorage.enqueue(box9);


        for (int i = 1; i <= 9; i++) {
            System.out.println("Removed: " + queueStorage.dequeue());
        }
        /****TEST TEST TEST ****/
    }

}
