package dsa.chapter05;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<T> implements IHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<T>[] chainList;

    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        chainList = new LinkedList[nextPrime(size)];
        for(int i=0; i < chainList.length; i++) {
            chainList[i] = new LinkedList<T>();
        }
    }

    public void insert(T x) {
        List<T> targetList = chainList[hashFunc(x)];
        if (!targetList.contains(x)) {
            targetList.add(x);

            if (++currentSize > chainList.length) {
                rehash();
            }
        }
    }

    public T get(T x) {
        if (contains(x)) {
            int hashValue = hashFunc(x);

            List<T> targetList = chainList[hashValue];
            for (T item : targetList) {
                if(item.equals(x)) {
                    return item;
                }
            }
        }

        return null;
    }

    public void remove(T x) {
        List<T> targetList = chainList[hashFunc(x)];
        if (targetList.contains(x)) {
            targetList.remove(x);
            currentSize--;
        }
    }

    public boolean contains(T x) {
        List<T> targetList = chainList[hashFunc(x)];
        return targetList.contains(x);
    }

    public void makeEmpty() {
        for(int i=0; i < chainList.length; i++) {
            chainList[i].clear();
        }
        currentSize = 0;
    }

    public int size() {
        return currentSize;
    }

    private void rehash() {
        List<T>[] oldList = chainList;

        chainList = new LinkedList[nextPrime(2 * chainList.length)];
        for(int i = 0; i < chainList.length; i ++) {
            chainList[i] = new LinkedList<>();
        }

        currentSize = 0;
        for(int i=0; i < oldList.length; i++) {
            for (T item : oldList[i]) {
                insert(item);
            }
        }
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    private int hashFunc(T x) {
        int hashValue = x.hashCode();

        hashValue %= chainList.length;
        if(hashValue < 0)
            hashValue += chainList.length;
        return hashValue;
    }

    private static int nextPrime(int n) {
        if(n % 2 == 0)
            n++;

        for(; !isPrime(n); n+= 2)
            ;
        return n;
    }

    private static boolean isPrime(int n) {
        if(n == 2 || n == 3)
            return true;

        if(n == 1 || n % 2 == 0)
            return false;

        for(int i=3; i * i <= n; i += 2) {
            if(n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SeparateChainingHashTable<String> tables = new SeparateChainingHashTable<>();
        tables.insert("apple");
        tables.insert("facebook");
        tables.insert("google");

        System.out.println(tables.contains("google"));
        tables.remove("facebook");
        System.out.println(tables.contains("facebook"));
    }
}
