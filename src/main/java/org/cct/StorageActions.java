package org.cct;

// An interface is a contract. It defines what every storage must do, not how.
// StackStorage (LIFO) and QueueStorage (FIFO) implement these operations in different ways.

public interface StorageActions {
    void add(FoodItem foodItem);
    FoodItem remove();
    FoodItem peek();
    void display();
    FoodItem search(String name);
    boolean isFull();
    boolean isEmpty();
}
