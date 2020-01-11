import java.util.Arrays;

public class MyHashTable<K, V> {
    private MyHashTable.Entry<?, ?>[] table;
    private int size;
    private int capacity;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private static final int STARTED_MAX_SIZE = 8;
    private static final int CAPACITY_INCREASE = 2;

    MyHashTable() {
        size = 0;
        capacity = STARTED_MAX_SIZE;
        table = new MyHashTable.Entry[0];
    }

    private static int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }

    public V put(K key, V value) {
        if (size < MAX_ARRAY_SIZE) {
            increaseCapacity();
        }
        int hash = hash(key);
        int index = (capacity - 1) & hash;


        if (table[index] == null) {
            table[index] = new MyHashTable.Entry(hash, key, value, null);
            return value;
        }

        for (Entry e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && e.getKey() == key) {
                V oldValue = (V) e.value;
                e.value = value;
                return oldValue;
            }
        }

        for (Entry e = table[index]; e != null; e = e.next) {
            if (e.next == null) {
                e.next = new MyHashTable.Entry<K, V>(hash, key, value, null);
                break;
            }
        }

        return value;
    }

    private void increaseCapacity() {
        if (size == 0) {
            table = Arrays.copyOf(table, capacity);
        }
        if (size * CAPACITY_INCREASE >= capacity) {
            if (size < MAX_ARRAY_SIZE / CAPACITY_INCREASE) {
                capacity *= CAPACITY_INCREASE;
            } else {
                capacity = MAX_ARRAY_SIZE;
            }
            table = Arrays.copyOf(table, capacity);
        }
        size++;
    }

    public V get(Object key) {

        int hash = hash(key);
        int index = (capacity - 1) & hash;

        for (Entry e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && e.getKey() == key) {
                return (V) e.getValue();
            }
        }
        return null;
    }

    boolean isEmpty() {
        return table.length == 0;
    }

    boolean containsKey(Object key) {

        int hash = hash(key);
        int index = (capacity - 1) & hash;

        for (Entry e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && e.getKey() == key ) {
                return true;
            }
        }
        return false;
    }

    boolean containsValue(Object value) {
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class Entry<K, V> implements java.util.Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        MyHashTable.Entry<K, V> next;

        protected Entry(int hash, K key, V value, MyHashTable.Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            } else {
                V oldValue = this.value;
                this.value = value;
                return oldValue;
            }
        }
    }
}