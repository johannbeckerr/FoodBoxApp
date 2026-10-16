package org.cct;

// Abstract class is a base that cannot be instantiated using *new*
// It tells what StackStorage and QueueStorage have in common, avoiding code duplication
// It implements StorageActions, but leaves the methods to the subclasses

public abstract class FoodStorage implements StorageActions {
    // protected == visible to this class and to the subclasses
    protected int capacity = 8;
    protected FoodItem[] foodItems = new FoodItem[capacity];
}