package org.cct;

public class StackStorage {

    private int capacity = 8;
    private FoodItem[] foodItems = new FoodItem[capacity];
    private int top = -1;

    public void push(FoodItem foodItem) {

        if(top == capacity - 1){
            System.out.println("Storage is Full!");
            return;
        }
        top = top + 1;
        foodItems[top] = foodItem;
    }

}
