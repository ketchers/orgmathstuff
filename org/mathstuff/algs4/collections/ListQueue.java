package org.mathstuff.algs4.collections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @{inheritDoc
 */
public class ListQueue<T> implements Queue<T> {

    private int size = 0;

    private class Node {
        T data = null;
        Node next = null;

        Node() {
            data = null;
            next = this;
        }

        Node (T data) {
            this.data = data;
            next = this;
        }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node first;

    ListQueue() {
        first = new Node();
        size = 0;
    }

    /**
     * Queue up an array of items
     *
     * @param items
     */
    ListQueue(T[] items) {
        this();
        for (T item : items)
            enqueue(item);
    }

    /**
     * Queue up any iterable
     *
     * @param items
     */
    ListQueue(Iterable<T> items) {
        for (T item : items)
            enqueue(item);
    }

    @Override
    public void enqueue(T item) {
        Node nd;
        if (size == 0)
            nd = new Node(item);
        else
            nd = new Node(item, first.next.next);
        first.next.next = nd;
        first.next = nd;
        size++;
    }

    /**
     * @throws IndexOutOfBoundsException
     *             if queue is empty
     */
    @Override
    public T dequeue(){ // Remove from the head
        
        T item = first.next.next.data;
        
        if (first.next == first.next.next)
            first.next = first; // Deleted last item.
        else
            first.next.next = first.next.next.next;
        
        if (size > 0)
            size--;
        
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return !(current == null);
        }

        @Override
        public T next() {

            if (hasNext()) {
                T value = current.data;
                current = current.next;
                return value;

            } else
                throw new NoSuchElementException("The queue is empty.");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "Method \"remove\" is not implemented.");
        }

        @Override
        public String toString() {
            return queueToString();
        }

    }

    public static void main(String[] args) {
        Queue<String> q = new ListQueue<>();
        q.enqueue("a1");
        q.enqueue("a2");
        q.enqueue("a3");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
    }
}
