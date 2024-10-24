/*
 * Name: Ahmed Rafique Choudhry
 * CS 231 Section A
 * Date: 05/01/2024
 * Class Purpose: Given tester that tests the heap class
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;

public class HeapTest {
    public static void test(int n) {
        PriorityQueue<Double> test = new Heap<Double>(new Comparator<Double>() { // defining the required comparator.
                @Override
                public int compare(Double o1, Double o2) {
                    return o1.compareTo(o2);
                }
            });
        double[] control = new double[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) { // using a for loop to add items into the heap.
            control[i] = rand.nextDouble();
            test.offer(control[i]);
        }
        Arrays.sort(control);

        for(int i = 0; i < control.length ; i++) { // testing here and checking for errors.
            if (test.size() == 0 || !test.peek().equals(control[i]) || !test.poll().equals(control[i]))
                System.out.println("\tERROR for n == " + n + " after removing " + (n - i) + " items.");
        }
    }

    public static void main(String[] args){ // the main method to be run implementing test on three heaps of different sizes.
        int [] heapSizes = { 3, 20, 1000000};
        for(int heapSize : heapSizes ) {
            System.out.println( "Testing heap with " + heapSize + " items:" );
            test( heapSize );
        }
    }
}