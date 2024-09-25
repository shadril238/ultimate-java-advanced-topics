package com.shadril238.lambdas;

import java.util.List;
import java.util.function.*;

public class LambdasDemo {
    // In Java, there are 4 types of functional interfaces: Consumer, Supplier, Function, Predicate
    public LambdasDemo(String message) {
        System.out.println("--" + message);
    }

    public static void show() {
        // Anonymous Inner Class
        greet(new Printer() {
            @Override
            public void print(String message) {
                System.out.println(message);
            }
        });

        String prefix = "-";
        // Lambda Expression
        greet(message -> System.out.println(prefix + message));
        Printer printer = message -> System.out.println(message); // Lambda Expression is basically an object of a functional interface.

        // Method Reference
        // Classname/Objectname::methodname
        greet(System.out::println);
        greet(LambdasDemo::new);


        // The consumer interface
        List<Integer> list = List.of(1, 2, 3);
        // Imperative Programming (for, if/else, switch/case)
        for (var item : list)
            System.out.println(item);

        // Declarative Programming (Functional Programming)
        list.forEach(item -> System.out.println(item));

        // Chaining Consumers
        List<String> list2 = List.of("a", "b", "c");
        Consumer<String> print = item -> System.out.println(item);
        Consumer<String> printUpperCase = item -> System.out.println(item.toUpperCase());
        list2.forEach(print.andThen(printUpperCase).andThen(print)); // andThen() method is used to chain consumers, accept() method is used to accept the value from the consumer

        // The Supplier Interface
        Supplier<Double> getRandom = () -> Math.random();
        var random = getRandom.get(); // get() method is used to get the value from the supplier
        System.out.println(random);

        // The Function Interface
        Function<String, Integer> map = str -> str.length(); // Function interface is used to map a value to another value
        var length = map.apply("sky");
        System.out.println(length);

        // Composing Functions
        Function<String, String> replaceColon = str -> str.replace(":", "=");
        Function<String, String> addBraces = str -> "{" + str + "}";

        var result = replaceColon.andThen(addBraces).apply("key:value"); // andThen() method is used to compose functions, apply() method is used to apply the function
        System.out.println(result);

        // Composing Functions in reverse order
        var result2 = replaceColon.compose(addBraces).apply("key:value"); // compose() method is used to compose functions in reverse order, apply() method is used to apply the function
        System.out.println(result2);

        // The Predicate Interface
        // Predicate is a functional interface that takes a value and returns a boolean, it is used to filter values
        Predicate<String> isLongerThan5 = str -> str.length() > 5;
        var result3 = isLongerThan5.test("sky"); // test() method is used to test the value
        System.out.println(result3);

        // Combining Predicates
        Predicate<String> hasLeftBrace = str -> str.startsWith("{");
        Predicate<String> hasRightBrace = str -> str.endsWith("}");

        Predicate<String> hasLeftAndRightBraces = hasLeftBrace.and(hasRightBrace); // and() method is used to combine predicates
        var result4 = hasLeftAndRightBraces.test("{key:value}");
        System.out.println(result4);

        Predicate<String> hasLeftOrRightBraces = hasLeftBrace.or(hasRightBrace); // or() method is used to combine predicates
        var result5 = hasLeftOrRightBraces.test("key:value}");
        System.out.println(result5);

        Predicate<String> hasNoBraces = hasLeftBrace.negate(); // negate() method is used to negate the predicate
        var result6 = hasNoBraces.test("{key:value}");
        System.out.println(result6);


        // The BinaryOperator Interface
        BinaryOperator<Integer> add = (a, b) -> a + b; // BinaryOperator is a functional interface that takes two values and returns a value
        Function<Integer, Integer> square = a -> a * a;
        var result7 = add.andThen(square).apply(1, 2);
        System.out.println(result7);

        // The UnaryOperator Interface
        UnaryOperator<Integer> square2 = a -> a * a; // UnaryOperator is a functional interface that takes a value and returns a value
        UnaryOperator<Integer> increment = a -> a + 1;
        var result8 = increment.andThen(square2).apply(1);
        System.out.println(result8);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World");
    }
}
