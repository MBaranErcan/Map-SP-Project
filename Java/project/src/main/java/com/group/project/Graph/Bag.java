package com.group.project.Graph;

import java.util.*;

public class Bag<Item> implements Iterable<Item> {  // Bag class
    private Node<Item> first;                       // Beginning of bag
    private int n;                                  // Number of elements in bag

    private static class Node<Item> {               // Helper linked list class.
        private Item item;
        private Node<Item> next;
    }

    public Bag() {                                  // Constructor that initializes an empty bag.
        first = null;
        n = 0;
    }

    public boolean isEmpty() {                      // Returns true if this bag is empty.
        return first == null;
    }

    public int size() {                             // Returns the size of the bag.
        return n;
    }

    public void add(Item item) {                    // Adds the item to this bag.
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator()  {             // Returns an iterator that iterates over the items in this bag.
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {// Helper class for the iterator.
        private Node<Item> current;


        public LinkedIterator(Node<Item> first) {    // Constructor that initializes the iterator with the first node.
            current = first;
        }

        public boolean hasNext()  {                 // Returns true if there is a next item in the bag.
            return current != null;
        }

        public Item next() {                         // Returns the next item in the bag.
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}