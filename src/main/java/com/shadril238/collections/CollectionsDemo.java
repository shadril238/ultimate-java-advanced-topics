package com.shadril238.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CollectionsDemo {
    public static void show() {
        Collection<String> collection = new ArrayList<>();
        // Add elements
        Collections.addAll(collection, "a", "b", "c");
        for (var item : collection)
            System.out.println(item);

        // Print as a whole
        System.out.println(collection);
        // Size of the collection
        System.out.println(collection.size());
        // Remove an element
        collection.remove("b");
        System.out.println(collection);

        // Check existence of an item
        collection.contains("a");

        // Convert to an object array
        Object[] objectArray = collection.toArray();
        // Convert to a string array
        String[] stringArray = collection.toArray(new String[0]);
        stringArray[0].toUpperCase();
        // Remove all elements
//        collection.clear();
        System.out.println(collection.isEmpty());

        Collection<String> other = new ArrayList<>();
        other.addAll(collection);

//        System.out.println(collection == other);
        System.out.println(collection.equals(other));
    }
}
