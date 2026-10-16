package org.cct;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
/***********************************************************************************************/
        Scanner scanner = new Scanner(System.in);
        int mode = 0;
        // Repeat until the mode equals 1 or 2.
        do{
            try{
                System.out.println("1-Stack 2-Queue");
                mode = scanner.nextInt();
                if(mode != 1 && mode != 2){
                    System.out.println("Only options 1 and 2, please.");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Only options 1 and 2, please.");
                scanner.nextLine();
            }
        }
        while(mode != 1 && mode != 2);

/***********************************************************************************************/
        // Both structures are created before the loop, within the main block,
        // so that they exist throughout the main function.
        // Only one will be used, depending on the mode. The other remains empty.
        StackStorage stackStorage = new StackStorage();
        QueueStorage queueStorage = new QueueStorage();
/***********************************************************************************************/
        int choose;
        do {
            try{
                System.out.println("1-add, 2-peek, 3-display, 4-search, 5-remove, 6-exit");
                choose = scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                choose = -1;
            }
            // The switch statement selects which block to execute based on the value of choose.
            // Each case ends with a break; otherwise, Java continues executing the next case.
            switch (choose) {
/***********************************************************************************************/
                // -----ADD-----
                case 1:
                    System.out.println("Enter food name: ");
                    // next() reads 1 word and skips the \n left behind by nextInt.
                    // It works because the 5 foods consist of a single word.
                    String name = scanner.next();
                    System.out.println("Enter food weight: ");
                    int weightInGrams;
                    try{
                        weightInGrams = scanner.nextInt();
                    }catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Weight must be a valid number!");
                        break;
                    }
                    System.out.println("Enter date (dd/MM/yyyy): ");
                    // bestBeforeDate starts as null "no date yet".
                    // The actual date is only assigned inside the try block, which might fail.
                    // Since the compiler cannot be certain that this line will execute,
                    // it requires the local variable to have an initial value before it is used.
                    LocalDate bestBeforeDate = null;
                    // check controls the loop. false = we havenot received a valid date yet.
                    // It only becomes true once the parse succeeds without errors.
                    boolean check = false;
                    do{
                        try{
                            String date = scanner.next();
                    // parse converts the text into a LocalDate using the dd/MM/yyyy format.
                    // Without the formatter, parse would only understand the ISO format (2026-10-20).
                            bestBeforeDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            LocalDate todayDate = LocalDate.now();
                    //Date must be between today and 14 days from today!
                            LocalDate days14Rules = todayDate.plusDays(14);
                            if(bestBeforeDate.isBefore(todayDate) || bestBeforeDate.isAfter(days14Rules)){
                                System.out.println("You entered a date that is either in the past or more than 14 days from today.");
                            }
                    //Just happens if date is ok
                            else{
                                check = true;
                            }
                        }catch (DateTimeParseException e){
                    // Discards the remainder of the typed line like "20 10 2026" next() only read 20).
                    // Without this, the next attempt would read the leftovers "10" "2026" instead of waiting for the user.
                            scanner.nextLine();
                            System.out.println("Date must be a valid date! Must be (dd/MM/yyyy)");
                        }
                    // !check to repeat the date entry question if it is incorrect.
                    }while(!check);
                    // Creates the box with the entered data.
                    // In the call, we pass only the variables without types.
                    // The time it was stored (timeBoxPlaced) is populated automatically in the constructor.
                    FoodItem foodItem = new FoodItem(name, weightInGrams, bestBeforeDate);
                    // case 1 specifies what to do (add); mode specifies where (stack or queue).
                    if (mode == 1) {      //STACK
                        stackStorage.push(foodItem);
                    } else {               //QUEUE
                        queueStorage.enqueue(foodItem);
                    }
                    break;
/***********************************************************************************************/
                // -----PEEK-----
                case 2:
                    if (mode == 1) {      //STACK
                        // peek returns the top item, or null if empty.
                        FoodItem foodPeek = stackStorage.peek();
                        // Only prints if an item was received, to avoid displaying null on the screen.
                        if (foodPeek != null) {
                            System.out.println("Next food: " + foodPeek);
                        }
                    } else {               //QUEUE
                        // In the queue, the next one to leave is the one at the front.
                        FoodItem foodPeek = queueStorage.peek();
                        if (foodPeek != null) {
                            System.out.println("Next food: " + foodPeek);
                        }
                    }
                    break;
/***********************************************************************************************/
                // -----DISPLAY-----
                case 3:
                    // display returns nothing (void) and it prints and handles the empty case.
                    if (mode == 1) {       //STACK
                        stackStorage.display();
                    } else {              //QUEUE
                        queueStorage.display();
                    }
                    break;
/***********************************************************************************************/
                // -----SEARCH-----
                case 4:
                    if(mode == 1){
                        System.out.println("Type the food: ");
                        scanner.nextLine(); // discards the \n left in the buffer by nextInt
                        String foodSearch = scanner.nextLine();
                        // search returns the found item, or null if not found.
                        FoodItem foodSearchReturn =  stackStorage.search(foodSearch);
                        if(foodSearchReturn != null){
                            System.out.println("Food Searched: "+foodSearchReturn);
                        }
                    }
                    else{
                        // Same 3 reading lines as the block above (duplicates).
                        System.out.println("Type the food: ");
                        scanner.nextLine();
                        String foodSearch = scanner.nextLine();
                        FoodItem foodSearchReturn =  queueStorage.search(foodSearch);
                        if(foodSearchReturn != null){
                            System.out.println("Food Searched: "+foodSearchReturn);
                        }
                    }
                    break;
/***********************************************************************************************/
                // -----REMOVE-----
                case 5:
                    if (mode == 1) {      //STACK
                        // pop removes and returns the item from the top - the last one that was added.
                        FoodItem foodPop = stackStorage.pop();
                        if (foodPop != null) {
                            System.out.println("Removed food: " + foodPop);
                        }
                    } else {
                        // dequeue removes and returns the item from the front - the first one that entered.
                        FoodItem foodDequeue = queueStorage.dequeue();
                        if (foodDequeue != null) {
                            System.out.println("Removed food: " + foodDequeue);
                        }
                    }
                    break;
/***********************************************************************************************/
                // -----EXIT-----
                case 6:
                    // Only displays the bye message. The while condition terminates the loop.
                    System.out.println("Tchau Chefe!");
                    break;
/***********************************************************************************************/
                // -----ANOTHER NUMBER-----
                // Catches invalid options 0, 7, 9.... The loop continues and the menu repeats again.
                default:
                    System.out.println("Invalid option, try again");
                    break;
            }
        }
/***********************************************************************************************/
        // Repeat until the chef chooses 6.
        while (choose != 6);
    }
}
