package com.shadril238.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsDemo {
    // Streams - To process a collection of data in a declarative way
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10, Genre.THRILLER),
                new Movie("b", 15, Genre.ACTION),
                new Movie("c", 20, Genre.ACTION)
        );
        System.out.println("-------------------Streams-------------------");

        var count = movies.stream()
                .filter(movie -> movie.getLikes() > 10)
                .count();
        System.out.println(count);

        var result = movies.stream()
                .collect(Collectors.summarizingInt(Movie::getLikes));
        System.out.println(result);

        // Generate Infinite Stream
        var stream = Stream.generate(() -> Math.random());
        stream
                .limit(5) // Limit the number of elements
                .forEach(n -> System.out.println(n));

        Stream.iterate(1, n -> n + 1) // iterate method takes an initial value and a lambda expression to generate the next value in the sequence based on the previous value
                .limit(10)
                .forEach(n -> System.out.println(n));

        // Map
        movies.stream()
                .map(Movie::getTitle) // map method is used to transform each object in the stream
                .forEach(System.out::println);

        movies.stream()
                .mapToInt(Movie::getLikes)
                .forEach(System.out::println);

        // FlatMap (Stream<List<T>> -> Stream<T>)
        var stream1 = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));
        stream1
                .flatMap(list -> list.stream()) // flatMap method is used to flatten a stream of streams into a single stream
                .forEach(System.out::println);

        movies.stream()
                .filter(movie -> movie.getLikes() > 10)
                .map(Movie::getTitle)
                .forEach(System.out::println);

        // Slicing Streams
        // Limit
        movies.stream()
                .limit(2) // limit method is used to limit the number of elements in the stream, we can use it to get the first n elements
                .forEach(m -> System.out.println(m.getTitle()));


        // Skip
        movies.stream()
                .skip(2) // skip method is used to skip the first n elements in the stream
                .forEach(m -> System.out.println(m.getTitle()));

        // Usecase : Pagination
        // 1000 movies, 10 movies per page, 3rd page, skip 20 -> skip((page - 1) * pageSize), limit 10 -> limit(pageSize)
        movies.stream()
                .skip(20)
                .limit(10)
                .forEach(m -> System.out.println(m.getTitle()));

        // takeWhile
        movies.stream()
                .takeWhile(m -> m.getLikes() < 30) // takeWhile method is used to take elements from the stream while the condition is true, once the condition is false, it stops taking elements
                .forEach(m -> System.out.println(m.getTitle()));


        // dropWhile
        movies.stream()
                .dropWhile(m -> m.getLikes() < 30) // dropWhile method is used to drop elements from the stream while the condition is true, once the condition is false, it starts taking elements
                .forEach(m -> System.out.println(m.getTitle()));

        // Sorting streams
        movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle).reversed()) // sorted method is used to sort the elements in the stream, it takes a comparator, if no comparator is provided, it uses the natural order
                .forEach(m -> System.out.println(m.getTitle()));

        // Getting the unique elements of a stream
        movies.stream()
                .map(Movie::getLikes)
                .distinct() // distinct method is used to get the unique elements in the stream
                .forEach(System.out::println);

        // Peeking elements
        movies.stream()
                .filter(m -> m.getLikes() > 10)
                .peek(m -> System.out.println("Filtered: " + m.getTitle())) // peek method is used to perform an action on each element in the stream without consuming it
                .map(Movie::getTitle)
                .peek(t -> System.out.println("Mapped: " + t))
                .forEach(System.out::println);


        // Reducers -> Perform a reduction on the elements of the stream, resulting in a single value, like sum, min, max, average
        var result1 = movies.stream()
                .count();

        var result2 = movies.stream()
                .anyMatch(m -> m.getTitle().contains("a"));

        System.out.println(result2);

        // Reducing a stream
        var result3 = movies.stream()
                .map(Movie::getLikes)
                .reduce(0, Integer::sum); // reduce method is used to reduce the elements in the stream to a single value, it takes a BinaryOperator

        System.out.println(result3);

        // Collectors
        var result4 = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .collect(Collectors.toMap(Movie::getTitle, Movie::getLikes)); // collect method is used to collect the elements in the stream to a collection

        System.out.println(result4);

        var result5 = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .collect(Collectors.summarizingInt(Movie::getLikes)); // summarizingInt method is used to get the summary statistics of the elements in the stream

        System.out.println(result5);

        var result6 = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .map(Movie::getTitle)
                .collect(Collectors.joining(", ")); // joining method is used to join the elements in the stream to a single string

        System.out.println(result6);

        // Grouping elements
        var result7 = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGenre, Collectors.toSet()
                )); // groupingBy method is used to group the elements in the stream by a classifier, it takes a classifier and a downstream collector, in this case, we are using a set collector, so we get a set of movies for each genre, if we want a list, we can use Collectors.toList()

        System.out.println(result7);

        var result8 = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGenre, Collectors.mapping(
                                Movie::getTitle, Collectors.joining(", ")
                        )
                )); // mapping method is used to transform the elements in the stream before collecting them, in this case, we are transforming the movies to their titles before joining them

        System.out.println(result8);

        // Partitioning elements
        var result9 = movies.stream()
                .collect(Collectors.partitioningBy(
                        m -> m.getLikes() > 15
                )); // partitioningBy method is used to partition the elements in the stream into two groups based on a predicate, in this case, we are partitioning the movies into two groups based on the number of likes

        System.out.println(result9);

        var result10 = movies.stream()
                .collect(Collectors.partitioningBy(
                        m -> m.getLikes() > 15,
                        Collectors.mapping(
                                Movie::getTitle, Collectors.joining(", ")
                        )
                )); // partitioningBy method is used to partition the elements in the stream into two groups based on a predicate, in this case, we are partitioning the movies into two groups based on the number of likes and then transforming the movies to their titles before joining them

        System.out.println(result10);

        // Primitive Type Streams
        IntStream.of(1, 2, 3, 4, 5)
                .max()
                .ifPresent(System.out::println);

        IntStream.rangeClosed(1, 5)  // rangeClosed method is used to create a stream of integers from a starting value to an ending value, inclusive
                .forEach(System.out::println);

        IntStream.range(1, 5) // range method is used to create a stream of integers from a starting value to an ending value, exclusive
                .forEach(System.out::println);

        // Summary
        // Streams allow us to process a collection of data in a declarative way, we can use streams to filter, transform, slice, sort, reduce, and collect elements in a collection
    }
}
