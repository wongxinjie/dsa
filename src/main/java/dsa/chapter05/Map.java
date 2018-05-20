package dsa.chapter05;

public class Map<K, V> {

    private IHashTable<Entry<K, V>> items;

    public Map() {
        // items = new SeparateChainingHashTable<>();
        items = new QuadraticProbingHashTable<>();
    }

    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        items.insert(entry);
    }

    public V get(K key) {
        Entry<K, V> keyEntry = new Entry<>(key);
        Entry<K, V> result = items.get(keyEntry);

        return result == null ? null : result.getValue();
    }

    public boolean contains(K key) {
        Entry<K, V> keyEntry = new Entry<>(key);
        return items.contains(keyEntry);
    }

    public void delete(K key) {
        Entry<K, V> keyEntry = new Entry<>(key);
        items.remove(keyEntry);
    }

    public void clear() {
        items.makeEmpty();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void makeEmpty() {
        items.makeEmpty();
    }

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry() {
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(K key) {
            this.key = key;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if(key.equals(((Entry)o).getKey())) return true;
            return false;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            return result;
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> rating = new Map<>();
        rating.put("apple", 10);
        rating.put("facebook", 20);
        rating.put("twitter", 15);
        rating.put("google", 50);

        System.out.println(rating.contains("facebook"));
        System.out.println(rating.contains("ZTE"));

        rating.delete("facebook");
        System.out.println(rating.get("facebook"));

        rating.clear();
        System.out.println(rating.isEmpty());
    }
}
