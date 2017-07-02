// Eren Erdogan
// Data Structures and Algorithms
// Homework 2
// Question 3

import java.io.IOException;
import java.util.*;
import java.io.File;


public class ErenErdogan_HW2_Question3 {

    public static int comparisons = 0;

    public static void main(String[] args) throws IOException {

        // Used to read input
        Scanner inpt = new Scanner(System.in);

        // Used to store file name
        String file_name = "";

        // Used to maintain running of the while loop
        boolean test = true;

        // Used to store number of elements program will be using
        int size = 0;


        while(test) {

            comparisons = 0;

            System.out.println("Please enter the name of the file you'd like to use");
            file_name = inpt.next();

            System.out.println("Please enter the number of integers to use");
            size = inpt.nextInt();

            File file = new File(file_name);

            Scanner scanner = new Scanner(file);


            // Create array size of numbers to be read
            int[] nums = new int[size];

            // Read numbers for text file named
            for (int i = 0; i < nums.length; i++)
                nums[i] = scanner.nextInt();


            scanner.close();

            Stopwatch timer = new Stopwatch();

            timer.startTimer();

            sort_top(nums);

            double time = timer.elapsedTime();

            System.out.println("\nComparisons: " + comparisons );

            System.out.println("Top Down Mergesort operation took " + time + " seconds\n");


            scanner = new Scanner(file);


            // Read numbers for text file named
            for (int i = 0; i < nums.length; i++)
                nums[i] = scanner.nextInt();

            scanner.close();

            timer.startTimer();

            comparisons = 0;

            sort_top(nums);

            time = timer.elapsedTime();

            System.out.println("Comparisons: " + comparisons );

            System.out.println("Bottom Up Mergesourt operation took " + time + " seconds");

            System.out.println();

            // Check if user wants to do another test
            System.out.println("Do you want to do another test? y for yes, n for no");

            String cont = inpt.next();

            if (cont.equals("n")) {
                test = false;
                System.out.println("System quitting. Good bye!");
            }else{
                System.out.println();
            }

        }


    }


    private static void merge(int [] a, int [] aux, int lo, int mid, int hi)
    {
        // keep track of the number of comparisons

        // Copy to auxilary array
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;
        int j = mid+1;

        for (int k = lo; k <= hi; k++) {

            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (aux[j] < aux[i])
                a[k] = aux[j++];
            else
                a[k] = aux[i++];

            comparisons++;
        }


    }


    private static void sort_top(int [] a, int [] aux, int lo, int hi)
    {

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort_top(a, aux, lo, mid);
        sort_top(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);

    }

    private static void sort_top(int [] a)
    {

        int [] aux = new int [a.length];
        sort_top(a, aux, 0, a.length - 1);

    }

    public static void sort_bottom(int [] a)
    {
        int N = a.length;

        int [] aux = new int [N];
        for (int sz = 1; sz < N; sz = sz+sz)
            for (int lo = 0; lo < N-sz; lo += sz+sz)
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));

    }

    public static class Stopwatch {


        private long start;

        Stopwatch(){
            start = System.currentTimeMillis();

        }

        public void startTimer(){
            start = System.currentTimeMillis();
        }

        public double elapsedTime(){
            long now = System.currentTimeMillis();
            return (now-start)/ 1000.0;

        }

    }

}
