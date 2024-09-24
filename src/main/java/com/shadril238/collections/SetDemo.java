package com.shadril238.collections;

import java.util.*;

public class SetDemo {
    public static void show(){
        // Set is a collection that does not contain duplicate elements
        // HashSet, LinkedHashSet, TreeSet
        Set<String> set = new HashSet<>();
        set.add("sky");
        set.add("is");
        set.add("blue");
        set.add("blue");
        System.out.println(set);

        Collection<String> collection = new ArrayList<>();
        Collections.addAll(collection, "a", "b", "c", "c");
        Set<String> removeDuplicate = new HashSet<>(collection);
        System.out.println(removeDuplicate);

        Set<String> set1 = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> set2 = new HashSet<>(Arrays.asList("b", "c", "d"));
        // Union
        set1.addAll(set2);
        System.out.println(set1);

        // Intersection
        set1.retainAll(set2);
        System.out.println(set1);

        // Difference
        set1.removeAll(set2);
        System.out.println(set1);
    }
}
