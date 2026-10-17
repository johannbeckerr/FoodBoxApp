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
        String[] foods = {"Burger","Pizza","Fries","Sandwich","Hotdog"};
/***********************************************************************************************/
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
        // Polymorphism: the variable type is the abstract class FoodStorage,
        // but the object created is a StackStorage or a QueueStorage, depending on the mode.
        // Here the Main class calls storage.add(), storage.remove(), etc... without knowing which one it actually is.
        FoodStorage storage;
        if (mode == 1) {
            storage = new StackStorage();
        } else {
            storage = new QueueStorage();
        }
/***********************************************************************************************/
        int choose;
        do{
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
//                                          ADD
/***********************************************************************************************/
                //NAME FOOD//
                case 1:
                    // Checks if the storage is full before asking for food.
                    // break exits the switch and goes back to the menu.
                    if (storage.isFull()) {
                        System.out.println("Storage is Full, Chef! Remove a box before add.");
                        break;
                    }
                    // Repeat until chooses a number from 1 to 5.
                    int selectionChef = 0;
                    do{
                        try{
                            System.out.println("MENU | 1-Burger | 2-Pizza | 3-Fries | 4-Sandwich | 5-Hotdog");
                            selectionChef = scanner.nextInt();
                            if (selectionChef < 1 || selectionChef > 5) {
                                System.out.println("Only 1 to 5, please!");
                            }
                        }catch (InputMismatchException e) {
                            System.out.println("Only 1 to 5, please!");
                            scanner.nextLine();
                        }
                    } while (selectionChef < 1 || selectionChef > 5);
                    // Arrays start at 0, so option 1 (Burger) is index 0: index = selectionChef - 1.
                    String name = foods[selectionChef - 1];
/***********************************************************************************************/
                    //WEIGHT FOOD//
                    // Repeat until the chef enters a number greater than 0.
                    int weightInGrams = 0;
                    do{
                        try{
                            System.out.println("Enter food weight in Grams: ");
                            weightInGrams = scanner.nextInt();
                            if (weightInGrams <= 0) {
                                System.out.println("Weight must be greater than 0!");
                            }
                        }catch (InputMismatchException e) {
                            System.out.println("Weight must be a valid number!");
                            scanner.nextLine();
                        }
                    } while (weightInGrams <= 0);
/***********************************************************************************************/
                    //BEST BEFORE DATE FOOD//
                    System.out.println("Enter date (dd/MM/yyyy): ");
                    // bestBeforeDate starts as null "no date yet".
                    // The actual date is only assigned inside the try block, which might fail.
                    // Since the compiler cannot be certain that this line will execute,
                    // it requires the local variable to have an initial value before it is used.
                    LocalDate bestBeforeDate = null;
                    // check controls the loop. false = we havenot received a valid date yet.
                    // It only becomes true when the format is valid and the date is between today and 14 days from today.
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
/***********************************************************************************************/
                    // Creates the box with the entered data.
                    // In the call, we pass only the variables without types.
                    // The time it was stored (timeBoxPlaced) is populated automatically in the constructor.
                    FoodItem foodItem = new FoodItem(name, weightInGrams, bestBeforeDate);
                    // Java calls the add() of the real object: push in the stack, enqueue in the queue.
                    storage.add(foodItem);
                    break;
/***********************************************************************************************/
//                                          PEEK
/***********************************************************************************************/
                case 2:
                    // peek returns the next item to leave (top of the stack or front of the queue), or null if empty.
                    FoodItem foodPeek = storage.peek();
                    // Only prints if an item was received, to avoid displaying null on the screen.
                    if (foodPeek != null) {
                        System.out.println("Next food: " + foodPeek);
                    }
                    break;
/***********************************************************************************************/
//                                          DISPLAY
/***********************************************************************************************/
                case 3:
                    // display returns nothing (void) and it prints and handles the empty case.
                    storage.display();
                    break;
/***********************************************************************************************/
//                                          SEARCH
/***********************************************************************************************/
                case 4:
                    System.out.println("Type the food: ");
                    scanner.nextLine(); // discards the \n left in the buffer by nextInt
                    String foodSearch = scanner.nextLine();
                    // search returns the found item, or null if not found.
                    FoodItem foodSearchReturn = storage.search(foodSearch);
                    if (foodSearchReturn != null) {
                        System.out.println("Food Searched: " + foodSearchReturn);
                    }
                    break;
/***********************************************************************************************/
//                                          REMOVE
/***********************************************************************************************/
                case 5:
                    // remove calls pop in the stack (LIFO: the last added leaves)
                    // or dequeue in the queue (FIFO: the first added leaves).
                    FoodItem foodRemoved = storage.remove();
                    if (foodRemoved != null) {
                        System.out.println("Removed food: " + foodRemoved);
                    }
                    break;
/***********************************************************************************************/
//                                          EXIT
/***********************************************************************************************/
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
