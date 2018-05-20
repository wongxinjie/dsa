package dsa.chapter05;

public interface IHashTable<T> {
    void insert(T x);

    T get(T x);

    void remove(T x);

    boolean contains(T x);

    int size();

    boolean isEmpty();

    void makeEmpty();
}
