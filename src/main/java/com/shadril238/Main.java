package com.shadril238;

import com.shadril238.exceptions.ExceptionsDemo;
import com.shadril238.generics.GenericList;
import com.shadril238.generics.Instructor;
import com.shadril238.generics.User;
import com.shadril238.generics.Utils;

public class Main {
    public static void main(String[] args) {
        System.out.println("---Ultimate Java Advanced Topics---");

        // Exceptions
        ExceptionsDemo.show();

        // Generics
        GenericList<Instructor> instructors = new GenericList<>();
        GenericList<User> users = new GenericList<>();
        Utils.printUsers(new GenericList<Instructor>());
    }
}