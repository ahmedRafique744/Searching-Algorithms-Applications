/*
 * Name: Ahmed Rafique Choudhry
 * CS 231 Section A
 * Date: 05/01/2024
 * Class Purpose: Heap - The Heap data structures utilizes a Priority Queue an is array-based allowing efficient methods and quick runtime.
 */

import java.util.Comparator;
import java.util.ArrayList;

public class Heap<T> implements PriorityQueue<T> {

    private Comparator<T> comparator;
    private ArrayList<T> heap;
    private boolean heapChecker;

    public Heap(Comparator<T> comparator, boolean maxHeap) {
        if (comparator == null) {
            this.comparator = new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return ( (Comparable<T>) o1 ).compareTo(o2);
                }
            };
        } else {
            this.comparator = comparator;
        } if (maxHeap == true) {
            ArrayList<T> heapMax = new ArrayList<T>();
            this.heap = heapMax;
            this.comparator = this.comparator.reversed();
            heapChecker = true;
        } else if (maxHeap == false) {
            ArrayList<T> heapMin = new ArrayList<T>();
            this.heap = heapMin;
            heapChecker = false;
        }
    }

    public Heap(Comparator<T> comparator) {
        this(comparator, false);
    }

    private void swap(int idx1, int idx2) {
        T original = heap.get(idx1);
        heap.set(idx1, heap.get(idx2));
        heap.set(idx2, original);
    }

    private int getParentIdx(int idx) {
        if (idx == 0) {
            return -1;
        } else {
            return (idx - 1) / 2;
        }
    }

    private int getLeftChildIdx(int idx) {
        return (idx * 2) + 1;
    }

    private int getRightChildIdx(int idx) {
        return (idx * 2) + 2;
    }

    private void bubbleUp( int idx ) {
        int parIdx = getParentIdx(idx);
        if (heapChecker == false) {
            if (getParentIdx(idx) != -1 && comparator.compare(this.heap.get(parIdx), this.heap.get(idx)) > 0) {
                swap(idx, parIdx);
                bubbleUp(parIdx);
            }
        } else if (heapChecker == true) {
            if (getParentIdx(idx) != -1 && comparator.compare(this.heap.get(parIdx), this.heap.get(idx)) < 0) {
                swap(idx, parIdx);
                bubbleUp(parIdx);
            }
        }
        
    }

    private void bubbleDown(int idx) {
        int leftIdx = getLeftChildIdx(idx);
        int rightIdx = getRightChildIdx(idx);
        int targetIdx = idx;

        if (leftIdx < heap.size() && (heapChecker ? comparator.compare(heap.get(leftIdx), heap.get(targetIdx)) > 0 : comparator.compare(heap.get(leftIdx), heap.get(targetIdx)) < 0)) {
            targetIdx = leftIdx;
        }

        if (rightIdx < heap.size() && (heapChecker ? comparator.compare(heap.get(rightIdx), heap.get(targetIdx)) > 0 : comparator.compare(heap.get(rightIdx), heap.get(targetIdx)) < 0)) {
            targetIdx = rightIdx;
        }

        if (targetIdx != idx) {
            swap(idx, targetIdx);
            bubbleDown(targetIdx);
        }
    }

    public String toString() {
        int depth = 0 ;
        return toString( 0 , depth );
    }
    
    private String toString( int idx , int depth ) {
        if (idx >= this.size() ) {
            return "";
        }
        String left = toString(getLeftChildIdx( idx ) , depth + 1 );
        String right = toString(getRightChildIdx( idx ) , depth + 1 );

        String myself = "\t".repeat(depth) + this.heap.get( idx ) + "\n";
        return right + myself + left;
    }

    public int size() {
        return heap.size();
    }

    public T peek() {
        return heap.get(0);
    }

    public void offer(T item) {
        heap.add(item);
        bubbleUp(heap.size() - 1);
    }

    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        T root = heap.get(0);
        T lastItem = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastItem);
            bubbleDown(0);
        }
        return root;
    }

    public void updatePriority(T item) {
        int index = heap.indexOf(item);
        if (index == -1) return;
        bubbleUp(index);
        bubbleDown(index);
    }

    public static void main(String[] args) {
        // Test case 1: Testing min heap with integers
        Heap<Integer> minHeap = new Heap<Integer>(Comparator.naturalOrder());
        minHeap.offer(10);
        minHeap.offer(5);
        minHeap.offer(15);
        minHeap.offer(3);
        System.out.println("Min Heap:");
        System.out.println(minHeap);

        // Test case 2: Testing max heap with strings
        Heap<String> maxHeap = new Heap<String>(Comparator.reverseOrder(), true);
        maxHeap.offer("apple");
        maxHeap.offer("banana");
        maxHeap.offer("orange");
        maxHeap.offer("kiwi");
        System.out.println("\nMax Heap:");
        System.out.println(maxHeap);

        // Test case 3: Testing poll operation on min heap
        Integer min = minHeap.poll();
        System.out.println("\nMin Heap after polling: " + min);
        System.out.println(minHeap);

        // Test case 4: Testing poll operation on max heap
        String max = maxHeap.poll();
        System.out.println("\nMax Heap after polling: " + max);
        System.out.println(maxHeap);
    }
}
