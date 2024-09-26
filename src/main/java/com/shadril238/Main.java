package com.shadril238;

import com.shadril238.collections.*;
import com.shadril238.exceptions.ExceptionsDemo;
import com.shadril238.generics.GenericList;
import com.shadril238.generics.Instructor;
import com.shadril238.generics.User;
import com.shadril238.generics.Utils;
import com.shadril238.lambdas.LambdasDemo;
import com.shadril238.streams.StreamsDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("---Ultimate Java Advanced Topics---");

        // Exceptions
        ExceptionsDemo.show();

        // Generics
        GenericList<Instructor> instructors = new GenericList<>();
        GenericList<User> users = new GenericList<>();
        Utils.printUsers(new GenericList<Instructor>());

        // Collections
        var list = new GenericList<String>();
        list.add("a");
        list.add("b");
        for (var item : list)
            System.out.println(item);

        CollectionsDemo.show();

        // List
        ListDemo.show();

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("a", "e3"));
        customers.add(new Customer("b", "e2"));
        customers.add(new Customer("c", "e1"));

        // Sorting with Comparable
        Collections.sort(customers);
        System.out.println(customers);

        // Sorting with Comparator
        Collections.sort(customers, new EmailComparator());
        System.out.println(customers);

        // Queue
        QueueDemo.show();

        // Set
        SetDemo.show();

        // Map
        MapDemo.show();

        // Lambdas and Functional Interfaces
        LambdasDemo.show();


        // Streams
        StreamsDemo.show();
    }
}