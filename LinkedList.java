/*
 * Author: Choudhry, Ahmed
 * CS 231 Section A
 * Date: 04/02/2024 (Updated on 04/22/2024)
 * Class Name and Purpose: LinkedList - Represents a generic LinkedList that implements a Stack and Queue and the Iterable interface
 */

 import java.util.Comparator;
 import java.util.Iterator; // defines the Iterator interface
 
 public class LinkedList<T> implements Stack<T>, Queue<T>, Iterable<T> {
 
     // Represents a node in the LinkedList holding a value and a reference to the
     // next node
     private class Node<T> {
         private Node<T> next;
         private T value;
 
         // Constructor to create a node with a specified value
         public Node(T item) {
             next = null;
             value = item;
         }
 
         // Getter method to retrieve the data value of the node
         public T getData() {
             return value;
         }
 
         // Setter method to set the next node
         public void setNext(Node<T> n) {
             next = n;
         }
 
         // Getter method to retrieve the next node
         public Node<T> getNext() {
             return next;
         }
     }
 
     // Private fields for the LinkedList to store the head node and size
     Node<T> head;
     Node<T> tail;
     int size;
 
     // Constructor to initialize an empty LinkedList
     public LinkedList() {
         head = null;
         tail = null;
         size = 0;
     }
 
     // Getter method to retrieve the size of the LinkedList
     public int size() {
         // Returns the size of the LinkedList.
         return size;
     }
 
     // Method to clear the LinkedList
     public void clear() {
         // Clears the LinkedList.
         head = null;
         tail = null;
         size = 0;
     }
 
     // Method to check if the LinkedList is empty
     public boolean isEmpty() {
         // Checks if the LinkedList is empty.
         return size == 0;
     }
 
     // toString method to provide a string representation of the LinkedList
     public String toString() {
         // Returns a string representation of the LinkedList.
         StringBuilder output = new StringBuilder();
         output.append("[");
         Node<T> walker = head;
         for (int i = 0; i < size; i ++) {
             output.append(walker.value.toString());
             if (i != size - 1) {
                 output.append(", ");
             }
             walker = walker.next;
         }
         output.append("]");
         return output.toString();
     }
 
     // Method to add a new item to the LinkedList
     public void add(T item) {
         // Adds a new item to the beginning of the LinkedList.
         Node<T> newItem = new Node<T>(item);
         if (size == 0) {
             tail = newItem;
         }
         newItem.next = head;
         head = newItem;
         size++;
     }
 
     // Method to check if the LinkedList contains a specified object
     public boolean contains(Object o) {
         // Checks if the LinkedList contains a specified object.
         Node<T> walker = head;
         for (int i = 0; i < size; i++) {
             if (walker.value.equals(o)) {
                 return true;
             } else {
                 walker = walker.getNext();
             }
         }
         return false;
     }
 
     // Method to retrieve an item from the LinkedList at a specified index
     public T get(int index) {
         // Retrieves an item from the LinkedList at a specified index.
         if (index == size - 1) {
             return getLast();
         } else {
             Node<T> walker = head;
             for (int i = 0; i < index; i++) {
                 walker = walker.getNext();
             }
             return walker.getData();
         }
     }
 
     // Method to remove and return the first item from the LinkedList
     public T remove() {
         // Removes and returns the first item from the LinkedList.
         Node<T> walker = head;
         head = head.getNext();
         T copy = walker.getData();
         walker = null;
         if (size - 1 == 0) {
             tail = null;
         }
         size--;
         return copy;
     }
 
     // Method to add a new item at a specified index in the LinkedList
     public void add(int index, T item) {
         // Adds a new item at a specified index in the LinkedList.
         if (index == 0) {
             add(item);
         } else if (index == size) {
             addLast(item);
         } else {
             Node<T> walker = head;
             for (int i = 0; i < index - 1; i++) {
                 walker = walker.getNext();
             }
             Node<T> newItem = new Node<T>(item);
             newItem.next = walker.next;
             walker.next = newItem;
             size++;
         }
     }
 
     // Method to remove and return an item from the LinkedList at a specified index
     public T remove(int index) {
         // Removes and returns an item from the LinkedList at a specified index.
         if (index == 0) {
             T copy = head.getData();
             head = head.getNext();
             size--;
             return copy;
         } else if (index == size) {
             size--;
             return removeLast();
         } else {
             Node<T> walker = head;
             for (int i = 0; i < index - 1; i++) {
                 walker = walker.getNext();
             }
             T copy = walker.next.getData();
             walker.next = walker.getNext().getNext();
             size--;
             return copy;
         }
     }
 
     // Method to check if the LinkedList is equal to another object
     public boolean equals(Object o) {
         // Checks if the LinkedList is equal to another object.
         if (!(o instanceof LinkedList)) {
             return false;
         }
         LinkedList<T> oAsALinkedList = (LinkedList<T>) o;
         Node<T> walker = head;
         for (int i = 0; i < size; i++) {
             if (!walker.getData().equals(oAsALinkedList.get(i))) {
                 return false;
             }
             walker = walker.next;
         }
         return true;
     }
 
     public void addLast(T item) {
         // Adds a new item to the end of the LinkedList.
         Node<T> newItem = new Node<T>(item);
         if (size == 0) {
             head = newItem;
             tail = newItem;
         } else {
             tail.next = newItem;
             tail = newItem;
         }
         size++;
     }
 
     public T removeLast() {
         // Removes and returns the last item from the LinkedList.
         T copy = tail.value;
         Node<T> walker = head;
         for (int i = 0; i < size - 2; i++) {
             walker = walker.next;
         }
         tail = walker;
         tail.next = null;
         size--;
         return copy;
     }
 
     public T getLast() {
         // Returns the last item in the LinkedList.
         return tail.value;
     }

    // Method to return a new LLIterator pointing to the head of the list
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

    // Private inner class representing an iterator for the LinkedList
    private class LLIterator implements Iterator<T> {
        private Node<T> nextNode;

        // Constructor to initialize the iterator with the head node
        public LLIterator(Node<T> head) {
            this.nextNode = head;
        }

        // Method to check if there is a next node in the LinkedList
        public boolean hasNext() {
            return nextNode != null;
        }

        // Method to retrieve the data value of the next node and move the iterator to
        // the next node
        public T next() {
            T nodeCopy = nextNode.value;
            nextNode = nextNode.getNext();
            return nodeCopy;
        }

        // Method to remove a node (not supported in this implementation)
        public void remove() {
            // Not implemented
        }
    }

    public void offer(T item) {
        // Adds the specified item to the end of the LinkedList (Queue operation).
        addLast(item);
    }

    public T peek() {
        // Retrieves, but does not remove, the head of the LinkedList (Queue operation).
        return head == null ? null : head.value;
    }

    public T poll() {
        // Retrieves and removes the head of the LinkedList, or returns null if the LinkedList is empty (Queue operation).
        return remove();
    }

    public T pop() {
        // Retrieves and removes the first element (head) of the LinkedList.
        return remove();
    }

    public void push(T item) {
        // Pushes an item in the end (tail) of the LinkedList.
        add(item);
    }

    // Retrieves the minimal item in the LinkedList.
    public T findMin(Comparator<T> comparator) {
        if (this.size == 0) {
            return null;
        }

        T min = this.head.value;

        for (T item : this) {
            if (comparator.compare(item, min) < 0) {
                min = item;
            }
        }

        return min;
    }



    /**
     * this method removes the minimal item from the list.
     * 
     * @param comparator the comparator object
     * @return the minimum value
     */
    public T removeMin(Comparator<T> comparator) {
        if (this.size == 0) {
            return null;
        }

        if (this.size == 1) {
            final T min = this.head.value;
            this.head = null;
            this.tail = null;
            size--;
            return min;
        }

        Node<T> current = this.head;
        T min = this.head.value;
        int minIndex = 0;

        for (int i = 0; i < this.size - 1; i++) {
            Node<T> next = current.next;

            if (comparator.compare(next.value, min) < 0) {
                min = next.value;
                minIndex = i + 1;
            }
            current = next;
        }

        current = this.head;

        for (int i = 0; i < minIndex - 1; i++) {
            current = current.next;
        }

        if (minIndex == this.size - 1) {
            this.tail = current;
            current.next = null;
        } else if (minIndex == 0) {
            this.head = current.next;
        } else {
            current.next = current.next.next;
        }

        size--;
        return min;
    }


    // Main method (empty for now)
    public static void main(String[] args) {

    }
}