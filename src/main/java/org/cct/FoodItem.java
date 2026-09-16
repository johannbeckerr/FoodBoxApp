package org.cct;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FoodItem {
/***********************************************************************************************/
    // The fields are private to prevent other parts of the code
    // from modifying these attributes without authorization.
    private String name;
    private int weightInGrams;
    private LocalDate bestBeforeDate;
    private LocalDateTime timeBoxPlaced;
/***********************************************************************************************/
    // This is a CONSTRUCTOR. It executes when a new object is created with new.
    // This constructor receives external parameters for three class attributes; however,
    // timeBoxPlaced receives the system time directly at the moment the object is created.
    // The creation time serves as the storage time because the object is saved
    // immediately after being created.
    // The user does not need to enter a value for timeBoxPlaced,
    // as it is populated automatically.
    public FoodItem(String name, int weightInGrams, LocalDate bestBeforeDate) {
        this.name = name;
        this.weightInGrams = weightInGrams;
        this.bestBeforeDate = bestBeforeDate;
        this.timeBoxPlaced = LocalDateTime.now();
    }
/***********************************************************************************************/
    // Here are the GETTERS. They allow other parts of the
    // program to read this class's attributes.
    // No system operation (add, remove, peek, display, search)
    // modifies a box, so the attributes do not need to change.
    // That is why I am not using SETTERS making the class immutable.
    public String getName() {
        return name;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public LocalDate getBestBeforeDate() {
        return bestBeforeDate;
    }

    public LocalDateTime getTimeBoxPlaced() {
        return timeBoxPlaced;
    }

}
