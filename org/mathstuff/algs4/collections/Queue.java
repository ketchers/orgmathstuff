package org.mathstuff.algs4.collections;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Create a FIFO datastructure
 *
 * @author ketchers
 *
 * @param <T>
 */

public interface Queue<T> extends Iterable<T> {

    void enqueue(T item);

    T dequeue();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    Iterator<T> iterator();

    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    default String queueToString() {
        //@formatter:off
		return this.stream().map(s -> s.toString())
							.collect(Collectors.joining(",", "[", "]"));
		//.reduce("", (s, t) -> s + " " + t.toString());
		//@formatter:on
    }

}
