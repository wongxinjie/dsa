package dsa.chapter03;

public class Queue<T>{
    private static final int DEFAULT_CAPACITY = 100;
    private int front;
    private int back;
    private int currentSize;
    private int capacity;
    private T[] items;

    public Queue(){
        clear();
    }

    public void clear() {
        front = 0;
        back = 0;
        currentSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void enqueue(T x) {
        if(currentSize >= capacity) {
            ensureCapacity(currentSize * 2 + 1);
        }
        currentSize += 1;
        if(back >= capacity - 1){
            back = 0;
        }
        items[back] = x;
        back += 1;
    }

    public T dequeue() {
        if(currentSize == 0) {
            throw new IndexOutOfBoundsException();
        }
        T item = items[front];
        front += 1;
        currentSize -= 1;
        if(front == capacity - 1) {
            front = 0;
        }
        return item;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < currentSize){
            return;
        }

        T[] old = items;
        capacity = newCapacity;
        items = (T[]) new Object[capacity];
        for(int i=0; i < currentSize; i++) {
            items[i] = old[i];
        }
    }

    public String toString() {
        String str = "Quque(";
        for(int i = 0; i < capacity; i++) {
            str += items[i] + ", ";
        }
        str += ")";
        return str;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        for(int i=0; i < 10; i++) {
            queue.enqueue(i);
        }

        for(int i=0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }

        for(int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }

        System.out.println(queue);
        System.out.println(queue.dequeue());
    }
}
