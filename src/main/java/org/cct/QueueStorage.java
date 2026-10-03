package org.cct;

public class QueueStorage {
    // 1 - Queue size
    // 2 - Where the items are stored
    // 3 - front (position of the item to be removed).
    //     Reads the item at the current position and then advances; hence, it starts at 0.
    // 4 - count (number of items present: empty = 0, full = capacity).
    // 5 - rear (position of the last item added)
    //     Advances one position before storing the value; hence, it starts at -1,
    //     so that the first item goes to position 0.
    private int capacity = 8;
    private FoodItem[] foodItems = new FoodItem[capacity];
    private int front = 0;
    private int count = 0;
    private int rear = -1;
/***********************************************************************************************/
    // Here is the method to place a box with items into the queue and
    // when the rear passes the last position, the % operation wraps it back to 0.
    public void enqueue(FoodItem foodItem){
        if(isFull()){
            System.out.println("Queue is Full!");
            return;
        }
        rear = (rear + 1) % capacity;
        foodItems[rear] = foodItem;
        count++;
    }

    // The front is one position after the rear in both the full and empty queue.
    // That is why we use count, which stores the exact number of items.
    public boolean isFull(){
        return count == capacity;
    }
    public boolean isEmpty(){
        return count == 0;
    }
}
