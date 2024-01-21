package com.File;

import java.util.*;

/**
 * Реализует основные методы интерфейса Map.
 *
 * @param <K> Тип ключа
 * @param <V> Тип значения
 */
public abstract class MyHashMap<K, V> implements Map<K, V> {
    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    private List<Entry<K, V>>[] buckets;
    private int size;
    private static final int INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }
    /**
     * Возвращает количество элементов в таблице.
     *
     * @return Количество элементов в таблице
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Проверяет, пуста ли таблица.
     *
     * @return true, если таблица пуста, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Проверяет, содержит ли таблица указанный ключ.
     *
     * @param key Ключ для поиска
     * @return true, если таблица содержит ключ, иначе false
     */
    @Override
    public boolean containsKey(Object key) {
        int bucketIndex = getBucketIndex(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Проверяет, содержит ли таблица указанное значение.
     *
     * @param value Значение для поиска
     * @return true, если таблица содержит значение, иначе false
     */
    @Override
    public boolean containsValue(Object value) {
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int bucketIndex = getBucketIndex(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        bucket.add(new Entry<K, V>(key, value));
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int bucketIndex = getBucketIndex(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];
        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                iterator.remove();
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(buckets, new LinkedList<Object>());
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                keySet.add(entry.getKey());
            }
        }
        return keySet;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<Map.Entry<K, V>>();
        for (List<Entry<K, V>> bucket : buckets) {
            entrySet.addAll(bucket);
        }
        return entrySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashMap<?, ?> myHashMap = (MyHashMap<?, ?>) o;
        return Arrays.equals(buckets, myHashMap.buckets);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(buckets);
    }

    private int getBucketIndex(Object key) {
        return Math.abs(key.hashCode()) % INITIAL_CAPACITY;
    }
}




