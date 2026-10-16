package org.cct;

public class QueueStorage extends FoodStorage {
    // 1 - front (position of the item to be removed).
    //     Reads the item at the current position and then advances; hence, it starts at 0.
    // 2 - count (number of items present: empty = 0, full = capacity).
    // 3 - rear (position of the last item added)
    //     Advances one position before storing the value; hence, it starts at -1,
    //     so that the first item goes to position 0.
    private int front = 0;
    private int count = 0;
    private int rear = -1;
/***********************************************************************************************/
    // add and remove come from the StorageActions interface.
    // In a queue they simply call enqueue and dequeue (FIFO).
    @Override
    public void add(FoodItem foodItem) {
        enqueue(foodItem);
    }
    @Override
    public FoodItem remove() {
        return dequeue();
    }
/***********************************************************************************************/
    // Here is the method to place a box with items into the queue and
    // when the rear passes the last position, the % operation wraps it back to 0.
    public void enqueue(FoodItem foodItem) {
        if (isFull()) {
            System.out.println("Queue is Full!");
            return;
        }
        rear = (rear + 1) % capacity;
        foodItems[rear] = foodItem;
        count++;
    }
/***********************************************************************************************/
    // Here, we always remove the first box that arrived the oldest one.
    // Setting it to null removes the box from the array and the garbage collector clears it from memory.
    // Without null, the array would retain a "ghost" box.
    public FoodItem dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return null;
        }
        FoodItem myFood = foodItems[front];
        foodItems[front] = null;
        front = (front + 1) % capacity;
        count--;
        return myFood;
    }
/***********************************************************************************************/
    // Here we check which item is next to be removed. The difference compared to dequeue is that
    // here we only look at the item and do not modify anything.
    @Override
    public FoodItem peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return null;
        }
        return foodItems[front];
    }
/***********************************************************************************************/
    // Displays the boxes in the order of arrival (from the first to leave to the last to leave).
    // We do not iterate through the array from 0 to 7, because the queue might have wrapped around:
    // the order would be incorrect, and empty positions (null) would appear.
    // We start at 'front' and iterate through 'count' boxes. The % capacity operator causes the position to wrap back to 0.
    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return;
        }
        for (int i = 0; i < count; i++) {
            int index = (front + i) % capacity;
            System.out.println("Box - " + foodItems[index]);
        }
    }
/***********************************************************************************************/
    //This search looks for an item starting at the front and moving to the rear, using the % operator
    //to continue advancing from position to position if the array has wrapped around.
    // Returns the item if found, or null if it is not in the queue.
    @Override
    public FoodItem search(String nameFoodSearch) {
        if (isEmpty()) {
            System.out.println("Storage is Empty!!");
            return null;
        }
        for (int i = 0; i < count; i++) {
            int index = (front + i) % capacity;
            String nameFoodStorage = foodItems[index].getName();
            if (nameFoodSearch.equalsIgnoreCase(nameFoodStorage)) {
                return foodItems[index];
            }
        }
        System.out.println("Food in QueueStorage not found");
        return null;
    }
/***********************************************************************************************/
    // The front is one position after the rear in both the full and empty queue.
    // That is why we use count, which stores the exact number of items.
    @Override
    public boolean isFull() {
        return count == capacity;
    }
    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
