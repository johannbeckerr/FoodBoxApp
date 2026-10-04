package org.cct;

public class StackStorage {
/***********************************************************************************************/
    // 1 - Capacity comes first by design. Java initializes fields from top to bottom,
    // and the array line uses that value. The number 8 appears only once.
    // 2 - Top is the index for the top of the stack. It starts at -1 because there is no index -1.
    // That is the code for an empty stack. The first push results in top = 0.
    private int capacity = 8;
    private FoodItem[] foodItems = new FoodItem[capacity];
    private int top = -1;
/***********************************************************************************************/
    public void push(FoodItem foodItem) {
        // 1 - The check comes first. When top == capacity - 1, the cabinet is full.
        // Without if statement, top would reach 8 and the program would crash with
        // an ArrayIndexOutOfBoundsException, resulting in data loss.
        // 2 - The return; statement in a void method means exit the method immediately,
        // protecting the next two lines.
        if(isFull()){
            System.out.println("Storage is Full!");
            return;
        }
        top = top + 1;
        foodItems[top] = foodItem;
    }
/***********************************************************************************************/
    // 1 - Returns the removed box (of type FoodItem) so that Main can display what was removed.
    // 2 - Empty stack == null: Means "no object."
    // 3 - The order: Store in a variable → clear the position → move the top pointer back → return myFood.
    public FoodItem pop(){
        if (isEmpty()){
            System.out.println("Storage is Empty");
            return null;
        }
        FoodItem myFood = foodItems[top];
        foodItems[top] = null;
        top = top -1;
        return myFood;
    }
/***********************************************************************************************/
    // Returns the top box (the last one added) without removing it.
    // Does not modify the top or the array. Returns null if the stack is empty.
    public FoodItem peek(){
        if (isEmpty()){
            System.out.println("Storage is Empty!");
            return null;
        }
        return foodItems[top];
    }
/***********************************************************************************************/
    // Displays all boxes from the top to the bottom (from top to 0), in LIFO order.
    // Only reads the array; does not modify anything.
    public void display(){
        if(isEmpty()){
            System.out.println("Storage is Empty!!");
            return;
        }
        for(int i = top; i > -1; i --){
            System.out.println("Box - "+foodItems[i]);
        }
    }
/***********************************************************************************************/
    // Searches for a box by name from top to bottom case insensitively.
    // Returns the first box found or null if not found or if the stack is empty.
    // Linear search: O(n) because in the worst case it checks all boxes.
    public FoodItem search(String nameFoodSearch){
        if(isEmpty()){
            System.out.println("Storage is Empty!!");
            return null;
        }

        for(int i = top; i > -1; i --){
            String nameFoodStorage = foodItems[i].getName();
            if (nameFoodSearch.equalsIgnoreCase(nameFoodStorage)){
                return foodItems[i];
            }
        }
        System.out.println("Food not found");
        return null;
    }
/***********************************************************************************************/
    public boolean isFull(){
        return top == capacity -1;
    }
    public boolean isEmpty(){
        return top == -1;
    }
}
