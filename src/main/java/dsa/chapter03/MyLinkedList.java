package dsa.chapter03;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    private int listSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    private static class Node<T> {
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList(){
        clear();
    }

    public void clear() {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;

        listSize = 0;
        modCount += 1;
    }

    public int size() {
        return  listSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, T x) {
        addBefore(getNode(idx), x);
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public T set(int idx, T value) {
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = value;
        return oldVal;
    }

    public T remove(int idx){
        return remove(getNode(idx));
    }

    private void addBefore(Node<T> p, T x) {
        Node<T> node = new Node<T>(x, p.prev, p);
        node.prev.next = node;
        p.prev = node;

        listSize++;
        modCount++;
    }

    private T remove(Node<T> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;

        listSize--;
        modCount++;
        return p.data;
    }

    private Node<T> getNode(int idx) {
        Node<T> p;

        if(idx < 0 || idx > size())
            throw new IndexOutOfBoundsException();

        if(idx < size() / 2){
            p = beginMarker.next;
            for(int i=0; i < idx; i++){
                p = p.next;
            }
        } else {
            p = endMarker;
            for(int i=size(); i > idx; i--){
                p = p.prev;
            }
        }
        return p;
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public T next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove(){
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }

    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        for(int i=0; i < 12; i++){
            list.add(i);
        }

        for(Integer x: list){
            System.out.println(x);
        }
    }
}
