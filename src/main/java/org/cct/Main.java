package org.cct;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Chef, escolha uma opcao: 1-Stack 2-Queue");
        int mode = scanner.nextInt();

        StackStorage stackStorage = new StackStorage();
        QueueStorage queueStorage = new QueueStorage();

        int choose;
        do{
            System.out.println("1-add, 2-peek, 3-display, 4-search, 5-remove, 6-exit");
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    System.out.println("Enter food name: ");
                        String name = scanner.next();
                    System.out.println("Enter food weight: ");
                        int weightInGrams = scanner.nextInt();
                    System.out.println("Enter date: ");
                        String date = scanner.next();
                        LocalDate bestBeforeDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        FoodItem foodItem = new FoodItem(name, weightInGrams,bestBeforeDate);

                        if(mode == 1){      //STACK
                            stackStorage.push(foodItem);
                        }
                        else{               //QUEUE
                            queueStorage.enqueue(foodItem);
                        }



                    break;
                case 2:
                    break;
                case 3:
                    if(mode ==1){
                        stackStorage.display();
                    }
                    else{
                        queueStorage.display();
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Tchau Chefe!");
                    break;
                default:
                    System.out.println("Invalid option, try again");
                    break;
            }

        }
        while(choose != 6);

    }
}


