import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ErenErdogan_HW3_Question2_Part2 {

    public static void main(String[] args) {
        boolean cont = true;

        while(cont) {

            Scanner inpt = new Scanner(System.in);

            System.out.print("Please enter the size of the randomly generated array you'd like to sort: ");

            int size = inpt.nextInt();

            int[] a = new int[size];
            int[] b = new int[size];


            for (int i = 0; i < a.length; i++) {
                Random rand = new Random();


                int r = rand.nextInt(a.length);

                a[i] = r;

                b[i] = r;

            }


            // Begin Quick Sort Median-Of-3 Test

            Stopwatch timer = new Stopwatch();


            timer.startTimer();

            quick_sort(a);


            double time = timer.elapsedTime();

            System.out.println("Quick Sort median-of-3 with insertion sort cutoff of 10 took " + time + " seconds on an array of size " + size);


            // Begin Mergesort Test

            timer.startTimer();

            sort_top(b);


            time = timer.elapsedTime();

            System.out.println();

            System.out.println("Top Down Mergesort with insertion sort cutoff of 10 took " + time + " seconds on an array of size " + size);



            System.out.println("\nDo you want to do another test? Enter 'y' for yes and 'n' for no");

            String c = inpt.next();

            if(c.toUpperCase().equals("N")) {
                System.out.println("Program exiting. Good Bye!");
                cont = false;
            } else{

                System.out.println();
            }
        }


    }

    // Used to set the begining pivot value with the median of three randomly selected values
    private static void swapMedian(int [] a){

        Random rand = new Random();

        int  k = rand.nextInt(a.length);

        int i = k;
        int j = k;

        while (i == k){
            i = rand.nextInt(a.length);
        }

        while (j == k || j == i){

            j = rand.nextInt(a.length);
        }

        System.out.println("\nThree randomly chosen elements:");

        System.out.println("a[i]: " + a[i]);
        System.out.println("a[j]: " + a[j]);
        System.out.println("a[k]: " + a[k]);

        int [] med = {a[i], a[j], a[k]};

        Arrays.sort(med);

        int l = med[1];

        if (l == a[i]) {
            System.out.println("Median is " + a[i]);
            swap(a, 0, i);
            System.out.println(a[0] + " swapped and now first element of array, will be used as pivot");
        }

        else if(l == a[j]) {
            System.out.println("Median is " + a[j]);
            swap(a, 0, j);
            System.out.println(a[0] + " swapped and now first element of array, will be used as pivot");
        }

        else {
            System.out.println("Median is " + a[k]);
            swap(a, 0, k);
            System.out.println(a[0] + " swapped and now first element of array, will be used as pivot");
        }






    }


    private static int partition(int [] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            while (a[++i] < a[lo])
                if (i == hi)
                    break;

            while (a[lo] <  a[--j])
                if (j == lo)
                    break;

            if (i >= j)
                break;

            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private static void swap(int [] array, int i, int j){


        int temp = array[i];

        array[i] = array[j];

        array[j] = temp;

    }

    public static void quick_sort(int [] a) {

        swapMedian(a);
        System.out.println();
        sort(a, 0, a.length - 1);
    }

    private static void sort(int [] a, int lo, int hi)
    {
        int n = hi - lo + 1;
        if (n <= 10) {
            insertionSort(a, lo, hi);
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);


    }

    public static void print(int [] a ){


        for (int i : a){

            System.out.print(i + " ");;
        }

        System.out.println();


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

        }
    }

    private static void sort_top(int [] a, int [] aux, int lo, int hi)
    {
        int n = hi - lo + 1;
        if (n <= 10) {
            insertionSort(a, lo, hi);
            return;
        }
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
    public static void insertionSort(int [] a, int lo, int hi)
    {
        for (int i = lo; i < hi; i++)
            for (int j = i; j > lo; j--)
                if (a[j] < a[j-1] )
                    swap(a, j, j-1);
                else break;
    }


}
