// Eren Erdogan
// Data Structures and Algorithms
// Homework 2
// Question 5

import java.util.Random;
import java.util.*;

public class ErenErdogan_HW2_Question5 {

    public static void main(String[] args) {

        boolean test = true;

        while(test) {

            Scanner inpt = new Scanner(System.in);

            System.out.println("Enter the size of the randomly generated array you want to quick sort");


            // Generate two randomly arrays of given input size
            int length = inpt.nextInt();

            int[] a = new int[length];
            int[] b = new int[length];

            Random rand = new Random();

            int n;


            // Fill in two arrays with random inputs
            for (int i = 0; i < length; i++) {

                n = rand.nextInt(length) + 1;

                a[i] = n;
                b[i] = n;
            }


            //
            // Begin Iterative Quick Sort
            //


            Stopwatch timer = new Stopwatch();

            timer.startTimer();

            quick_sort_iterative(a, 0 , a.length-1);


            double time = timer.elapsedTime();


            System.out.println();

            System.out.println("Iterative Quicksort on a randomly generated array of size " + length + " took " + time + " seconds");

            //
            // Begin Recursive Quick Sort
            //


            timer.startTimer();

            quick_sort_recursive(b);


            time = timer.elapsedTime();


            System.out.println("\nRecursive Quicksort on a randomly generated array of size " + length + " took " + time + " seconds");


            // Check if user wants to do another test
            System.out.println("\nDo you want to do another test? y for yes, n for no");

            String cont = inpt.next();

            if (cont.equals("n")) {
                test = false;
                System.out.println("System quitting. Good bye!");
            } else{

                System.out.println();
            }

        }

    }

    private static int partition(int [] a, int lo, int hi) {
        int i = lo, j = hi+1;
        while (true) {
            while (a[++i] < a[lo])
                if (i == hi)
                    break;


            while (a[lo] < a[--j])
                if (j == lo)
                    break;

            if (i >= j) break;
            swap(a, i, j);
        }

        swap(a, lo, j);

        return j;
    }

    private static void swap(int [] a, int i,int j){

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;


    }

    private static void quick_sort_recursive(int [] a) {

        sort(a, 0, a.length - 1);
    }

    private static void sort(int [] a, int lo, int hi)
    {
        if (hi <= lo)
            return;

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static class Stopwatch {


        private long start;

        Stopwatch(){
            start = System.currentTimeMillis();

        }

        private void startTimer(){
            start = System.currentTimeMillis();
        }

        private double elapsedTime(){
            long now = System.currentTimeMillis();
            return (now-start)/ 1000.0;

        }

    }

    private static void quick_sort_iterative(int arr[], int lo, int hi) {

        int stack_aux[] = new int[hi-lo+1];

        int top = -1;


        stack_aux[++top] = lo;
        stack_aux[++top] = hi;


        while (top >= 0) {


            // Pop hi
            hi = stack_aux[top--];

            // Pop lo
            lo = stack_aux[top--];

            // Set Pivot
            int pivot = partition(arr, lo, hi);


            // Push to the left side of the stack if there are elements on the left side of the pivot
            if ( (pivot-1) > lo ) {
                stack_aux[ ++top ] = lo;
                stack_aux[ ++top ] = pivot - 1;
            }


            // Push to the right side of the stack if there are elements on the right side of the pivot
            if ( (pivot+1) < hi ) {
                stack_aux[ ++top ] = pivot + 1;
                stack_aux[ ++top ] = hi;
            }


        } // End While Loop

    }



}
