package dsa.chapter03;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 100;

    private int arraySize;
    private T[] items;

    public MyArrayList() {
        clear();
    }

    public void clear(){
        arraySize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
       return arraySize;
    }

    public void trimToSize(){
        ensureCapacity(arraySize);
    }

    public T get(int idx){
        checkIndexRange(idx);

        return items[idx];
    }

    public T set(int idx, T val){
        checkIndexRange(idx);

        T old = items[idx];
        items[idx] = val;
        return old;
    }

    public boolean contains(T x) {
        for(int i=0; i < arraySize; i++){
            if(x.equals(items[i]))
                return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return arraySize == 0;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < arraySize)
            return;

        T[] old = items;
        items = (T[]) new Object[newCapacity];
        for(int i=0; i < size(); i++){
            items[i] = old[i];
        }
    }

    public boolean add(T x){
        add(size(), x);
        return true;
    }

    public void add(int idx, T x){
        if(items.length == size()){
            ensureCapacity(size() * 2 + 1);
        }
        for(int i=arraySize; i > idx; i--){
            items[i] = items[i-1];
        }

        items[idx] = x;
        arraySize += 1;
    }

    private void checkIndexRange(int i) throws ArrayIndexOutOfBoundsException {
        if(i < 0 || i >= size())
            throw new ArrayIndexOutOfBoundsException();
    }

    public T remove(int idx){
        checkIndexRange(idx);

        T removeItem = items[idx];
        for(int i=idx; i < size() -1; i++){
            items[i] = items[i+1];
        }
        arraySize -= 1;
        return removeItem;
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T>{
        private int current = 0;

        public boolean hasNext(){
            return current < size();
        }

        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return items[current++];
        }

        public void remove(){
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        for(int i=0; i < 12; i ++){
            list.add(i);
        }

        for(Integer x: list){
            System.out.println(x);
        }

        System.out.println(list.contains(5));
        list.remove(5);
        System.out.println(list.contains(5));
        for(Integer x: list) {
            System.out.println(x);
        }
    }
}
