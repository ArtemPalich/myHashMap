package com.File;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class MyHashMapTest {
    @Test
    public void testPutAndGet() {
        MyHashMap<Integer, String> map = new MyHashMap<Integer, String>() {
            @Override
            public Collection<String> values() {
                return null;
            }
        };
        map.put(1, "One");
        assertEquals("One", map.get(1));
    }
    @Test
    public void testIsEmpty() {
        MyHashMap<Integer, String> map = new MyHashMap<Integer, String>() {
            @Override
            public Collection<String> values() {
                return null;
            }
        };
        assertTrue(map.isEmpty()); // Проверяем, что картa пустая
        map.put(1, "One");
        assertFalse(map.isEmpty()); // Проверяем, что картa больше не пустая
        map.remove(1);
        assertTrue(map.isEmpty()); // Проверяем, что картa снова пустая после удаления элемента
    }

    }

