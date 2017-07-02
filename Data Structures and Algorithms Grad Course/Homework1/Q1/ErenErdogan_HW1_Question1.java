// Eren Erdogan
// Data Structures & Algorithms
// Homework # 1
// Question-1 Implementation

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class ErenErdogan_HW1_Question1 {

    public static void main(String[] args) throws IOException {


        // Create file object to read data then read integer data and store in array

        //

        System.out.println("Welcome to Eren Erdogan's Three Sum Testing Program!!\n");

        // Used to read input
        Scanner inpt = new Scanner(System.in);

        // Used to store file name
        String file_name = "";

        // Used to maintain running of the while loop
        boolean test = true;

        // Used to store number of elements program will be using
        int size = 0;

        // Used to keep track of successfull triples
        int count = 0;


        while(test) {

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

            // Create stopwatch object to keep track of how long each operation takes
            // Class for this is below
            Stopwatch timer = new Stopwatch();

            // Execture Three Sum
            count = ThreeSum(nums);

            double time = timer.elapsedTime();

            System.out.println(count + " triples found in " + file_name + " " + time + " seconds using Brute-Force N^3 three sum algorithm");

            // Reset timer for binary search method
            timer.startTimer();

            // Execute Three Sum Fast with using binary search
            count = ThreeSumFast(nums);

            time = timer.elapsedTime();

            System.out.println(count + " triples found in " + file_name + " " + time + " seconds using Fast N^2*lg(N) three sum algorithm");

            System.out.println();

            // Check if user wants to do another test
            System.out.println("Do you want to do another test? y for yes, n for no");

            String cont = inpt.next();

            if (cont.equals("n")) {
                test = false;
                System.out.println("System quitting. Good bye!");
            }


        }

    }




    public static int ThreeSum(int[] num) {
        int N = num.length;
        int count = 0;

        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++)
                    if (num[i] + num[j] + num[k] == 0)
                        count++;
        return count;

    }

    public static int ThreeSumFast(int [] num){
        // First sort array
        Arrays.sort(num);
        int N = num.length;
        int count = 0;

        for (int i = 0; i < N; i++){
            for (int j = i+1; j < N; j++)
                if (binary_search(num, (-num[i] -num[j]) ) > j)
                    count++;
        }

        return count;



    }

    public static int binary_search(int [] a, int key){

        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.

            int mid = lo + (hi - lo) / 2;

            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;

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



