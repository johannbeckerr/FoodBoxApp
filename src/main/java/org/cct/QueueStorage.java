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
/***********************************************************************************************/
    // Here, we always remove the first box that arrived the oldest one.
    // Setting it to null removes the box from the array and the garbage collector clears it from memory.
    // Without null, the array would retain a "ghost" box.
    public FoodItem dequeue(){
        if(isEmpty()){
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
    public FoodItem peek(){
        if(isEmpty()){
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
    public void display(){
        if(isEmpty()){
            System.out.println("Queue is Empty!");
            return;
        }
        for (int i=0; i<count; i++){
            int index = (front + i) % capacity;
            System.out.println("Box - " + foodItems[index]);
        }
    }
/***********************************************************************************************/
    // The front is one position after the rear in both the full and empty queue.
    // That is why we use count, which stores the exact number of items.
    public boolean isFull(){
        return count == capacity;
    }
    public boolean isEmpty(){
        return count == 0;
    }
}
