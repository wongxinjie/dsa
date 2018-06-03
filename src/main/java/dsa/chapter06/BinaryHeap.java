package dsa.chapter06;

import java.util.Arrays;

public class BinaryHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] array;

    private int currentSize;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(T[] items) {
        currentSize = items.length;

        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
        int i=1;
        for (T item : items) {
            array[i++] = item;
        }

        buildHeap();
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity+1];
    }

    public void insert(T x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }

        int hole = ++currentSize;
        bubbleUp(hole, x);
    }

    public T findMin() {
        if (isEmpty()) {
            return null;
        }
        return array[1];
    }

    public T deleteMin() {
        if(isEmpty())
            return null;

        T x = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return x;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    private void percolateDown(int hole) {
        T x = array[hole];
        int child;

        while (hole * 2 <= currentSize) {
            child = hole * 2;
            if(child != currentSize && array[child+1].compareTo(array[child]) < 0)
                child++;

            if(array[child].compareTo(x) < 0)
                array[hole] = array[child];
            else
                break;

            hole = child;
        }
        array[hole] = x;
    }

    private void bubbleUp(int hole, T x) {
        for(; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2){
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    private void buildHeap() {
        for(int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    @SuppressWarnings("unchecked")
    private void enlargeArray(int size) {
        T[] oldArray = array;
        array = (T[]) new Comparable[size];

        for(int i=1; i <= currentSize; i++) {
            array[i] = oldArray[i];
        }
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> ranges = new BinaryHeap<>();

        Integer[] list = new Integer[10];
        for(int i=0; i < 10; i++) {
            list[i] = (int) (Math.random() * 9 + 1);
        }
        System.out.println(Arrays.asList(list));

        for (Integer x : list) {
            ranges.insert(x);
        }

        System.out.println(ranges.findMin());
        System.out.println(ranges.deleteMin());
        System.out.println(ranges.findMin());
    }
}
