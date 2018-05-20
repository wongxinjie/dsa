package dsa.chapter05;


public class QuadraticProbingHashTable<T> implements IHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 11;

    private static class HashEntry<T> {
        public T element;
        public  boolean isActive;

        public HashEntry(T element) {
            this(element, true);
        }

        public HashEntry(T element, boolean isActive) {
            this.element = element;
            this.isActive = isActive;
        }
    }

    private HashEntry<T>[] array;
    private int currentSize;

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    public void insert(T x) {
        int pos = findPos(x);

        if(isActive(pos))  {
            array[pos].element = x;
        } else {
            array[pos] = new HashEntry<T>(x, true);
            if(++currentSize > array.length / 2)
                rehash();
        }
    }

    public void remove(T x) {
        int pos = findPos(x);
        if (isActive(pos)) {
            array[pos].isActive = false;
            currentSize--;
        }
    }

    public T get(T x) {
        int pos = findPos(x);
        HashEntry<T> entry = array[pos];

        return entry != null && entry.isActive ? entry.element : null;
    }

    public void makeEmpty(){
        currentSize = 0;
        for(int i=0; i < array.length; i++) {
            array[i] = null;
        }
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean contains(T x) {
        int pos = findPos(x);
        return isActive(pos);
    }

    public int size() {
        return currentSize;
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    private int findPos(T x){
        int offset = 1;
        int currentPos = hashFunc(x);

        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if(currentPos >= array.length)
                currentPos -= array.length;
        }
        return currentPos;
    }

    private void rehash() {
        HashEntry<T> [] oldArray = array;
        allocateArray(nextPrime(2 * oldArray.length));

        currentSize = 0;
        for(int i=0; i < oldArray.length; i++) {
            if(oldArray[i] != null && oldArray[i].isActive){
                insert(oldArray[i].element);
            }
        }
    }

    private int hashFunc(T x) {
        int hashValue = x.hashCode();

        hashValue %= array.length;
        if(hashValue < 0)
            hashValue += array.length;
        return hashValue;
    }

    private int nextPrime(int n) {
        if(n % 2 == 0)
            n++;
        for(; !isPrime(n); n+= 2)
            ;

        return n;
    }

    private boolean isPrime(int n) {
        if(n == 2 || n == 3)
            return true;
        if(n == 1 || n % 2 == 0)
            return false;

        for(int i=3; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        QuadraticProbingHashTable<String> companies = new QuadraticProbingHashTable<>();
        companies.insert("apple");
        companies.insert("facebook");
        companies.insert("google");
        companies.insert("amazon");
        System.out.println(companies.size());
        System.out.println(companies.contains("facebook"));

        companies.remove("facebook");
        System.out.println(companies.size());
        System.out.println(companies.contains("facebook"));
    }
}
