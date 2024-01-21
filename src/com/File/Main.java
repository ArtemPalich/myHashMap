package com.File;

import java.util.Collection;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, Integer> myMap1 = new MyHashMap<String, Integer>() {
            @Override
            public Collection values() {
                return null;
            }
        };


    }
}
