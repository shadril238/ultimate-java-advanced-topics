package com.shadril238.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo {
    public static void show() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c");
        list.add(1, "!");
        // Replace an element
        list.set(1, "a++");
        // Remove an element
        list.remove(0);
        System.out.println(list);
        System.out.println(list.get(1));

        // Index of an element
        System.out.println(list.indexOf("a++"));
        // Last index of an element
        System.out.println(list.lastIndexOf("a++"));

        // Sublist
        List<String> subList = list.subList(0, 2);
        System.out.println(subList);
    }
}
