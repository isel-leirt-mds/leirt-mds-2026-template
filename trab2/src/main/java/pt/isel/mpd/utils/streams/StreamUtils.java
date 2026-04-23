package pt.isel.mpd.utils.streams;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static pt.isel.mpd.utils.resources.BuildUtils.TODO;

public class StreamUtils {
    public static <T> Optional<T> findLast(Stream<T> str) {
        return str.reduce((a, e) -> e);
    }

    public static <T> Supplier<Stream<T>> cache(Stream<T> src) {
        TODO("cache");
        return null;
    }


    // the intersection operation
    public  static <T,U,V> Stream<V> matchAndCombine(
        Stream<T> seq1,
        Stream<U> seq2,
        BiPredicate<T,U> matched,
        BiFunction<T,U, V> combiner) {

        TODO("matchAndCombine");
        return null;
    }


}
