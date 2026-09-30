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

    // Nao conseguimos utilizar o front e o rear para verificar se a fila circular esta
    // cheia ou vazia. Precisamos de um count.
    public boolean isFull(){
        return count == capacity;
    }

    public boolean isEmpty(){
        return count == 0;
    }
}
