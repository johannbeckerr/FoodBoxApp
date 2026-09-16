package org.cct;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FoodItem {
    //The fields are private to prevent other parts of the code
    //from modifying these attributes without authorization.
    private String name;
    private int weightInGrams;
    private LocalDate bestBeforeDate;
    private LocalDateTime timeBoxPlaced;

}
