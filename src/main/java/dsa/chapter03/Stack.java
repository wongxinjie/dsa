package dsa.chapter03;

import java.util.EmptyStackException;

public class Stack<T> {

    private static final int DEFAULT_CAPACITY = 100;
    private int stackSize = 0;
    private int topOfStack = -1;
    private T[] items;

    public Stack() {
        clear();
    }

    public void clear(){
        stackSize = 0;
        topOfStack = -1;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < size())
            return;

        T[] old = items;
        items = (T[]) new Object[newCapacity];
        for(int i=0; i < size(); i++) {
            items[i] = old[i];
        }
    }

    public int size(){
        return stackSize;
    }

    public boolean isEmpty() {
        return stackSize == 0;
    }

    public void push(T x) {
        add(size(), x);
    }

    private void add(int idx, T x) {
        if(items.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }

        items[idx] = x;
        stackSize++;
        topOfStack = idx;
    }

    public T pop() {
        if(stackSize == 0){
            throw new EmptyStackException();
        }

        stackSize -= 1;
        topOfStack -= 1;
        T removeItem = items[stackSize];
        return removeItem;
    }
    public T peek() {
        T item = items[topOfStack];
        return item;
    }

    public static void main(String[] args){
        Stack<String> stack = new Stack<String>();
        for(Integer i=0; i < 10; i++){
            stack.push(i.toString());
        }

        for(Integer i=10; i < 200; i++){
            stack.push(i.toString());
        }

        System.out.println(stack.peek());
        for(int i=0; i < 10; i++){
            System.out.println(stack.pop());
        }
    }

}
