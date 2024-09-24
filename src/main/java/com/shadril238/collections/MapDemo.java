package com.shadril238.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapDemo {
    public static void show() {
        // Map is a collection of key-value pairs
        // HashMap, LinkedHashMap, TreeMap
        List<Customer> customers = new ArrayList<>();

        // O(n) complexity to find an item
        for (var customer : customers)
            if (customer.getEmail() == "e1")
                System.out.println("Found");


        var c1 = new Customer("a", "e1");
        var c2 = new Customer("b", "e2");

        Map<String, Customer> map = new HashMap<>();
        map.put(c1.getEmail(), c1);
        map.put(c2.getEmail(), c2);

        // O(1) complexity to find an item
        var customer = map.get("e1");
        System.out.println(customer);

        var customer2 = map.get("e10");
        System.out.println(customer2);

        var customer3 = map.getOrDefault("e10", new Customer("anonymous", "nan"));
        System.out.println(customer3);

        var exists = map.containsKey("e10");
        System.out.println(exists);

        map.replace("e1", new Customer("a++", "e1"));
        System.out.println(map);

        // Iterating over a map
        for (var key : map.keySet())
            System.out.println(key);

        for (var entry : map.entrySet())
            System.out.println(entry.getValue());

        for (var customer1 : map.values())
            System.out.println(customer1);
    }
}
