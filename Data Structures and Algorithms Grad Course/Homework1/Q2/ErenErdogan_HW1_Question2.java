// Eren Erdogan
// Data Structures & Algorithms
// Homework # 1
// Question-2 Implementation

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class ErenErdogan_HW1_Question2 {

    public static void main(String[] args) throws IOException {


        System.out.println("Welcome to Eren Erdogan's Union Find / Testing Program!!\n");

        // Used to read input
        Scanner inpt = new Scanner(System.in);

        // Used to store file name
        String file_name = "";

        // Used to maintain running of the while loop
        boolean test = true;

        // Used to store number of elements program will be using
        // 9999 picked since all the test files contain at most 4 digit integers
        int max = 10000;


        while(test) {

            int p;
            int q;

            System.out.println("Please enter the name of the file you'd like to open");
            file_name = inpt.next();

            System.out.println();

            File file = new File(file_name);
            Scanner scanner = new Scanner(file);

            // Initialize and create objects

            QuickFindUF q_f = new QuickFindUF(max);

            QuickUnionUF q_u = new QuickUnionUF(max);

            WeightedQuickUnionUF w_qu = new WeightedQuickUnionUF(max);

            QuickUnionPathCompressionUF w_qu_pac = new QuickUnionPathCompressionUF(max);

            //
            // Start testing on Quick Find Algorithm
            //

            // creater Stopwatch object to be used to keep track of the time of each process
            Stopwatch timer = new Stopwatch();

            // Start timer
            timer.startTimer();

            // Read file until the end
            while (scanner.hasNextInt()) {
                p = scanner.nextInt();
                q = scanner.nextInt();

                q_f.union(p, q);
            }

            // End timer
            double time = timer.elapsedTime();

            System.out.println(file_name + " took " + time + " seconds to run with Quick Find Algorithm" );

            scanner.close();

            //
            // Now Quick Union Algorithm
            //

            // Create new scanner to start from the begining of the file
            scanner = new Scanner(file);

            timer.startTimer();

            // Read file until the end
            while (scanner.hasNextInt()) {
                p = scanner.nextInt();
                q = scanner.nextInt();

                q_u.union(p, q);
            }

            time = timer.elapsedTime();

            System.out.println(file_name + " took " + time + " seconds to run with Quick Union Algorithm" );

            scanner.close();

            //
            // Now Weighted Quick Union
            //

            scanner = new Scanner(file);

            timer.startTimer();

            // Read file until the end
            while (scanner.hasNextInt()) {
                p = scanner.nextInt();
                q = scanner.nextInt();

                w_qu.union(p, q);
            }

            time = timer.elapsedTime();

            System.out.println(file_name + " took " + time + " seconds to run with Weight Quick Union Algorithm" );

            scanner.close();

            //
            // Lastly, we'll do weighted quick-union with path compression
            //

            scanner = new Scanner(file);

            timer.startTimer();

            while (scanner.hasNextInt()) {
                p = scanner.nextInt();
                q = scanner.nextInt();

                if(w_qu_pac.connected(p,q))
                    continue;
                w_qu_pac.union(p, q);

            }

            time = timer.elapsedTime();

            System.out.println(file_name + " took " + time + " seconds to run with Weight Quick Union With Path Compression Algorithm" );

            scanner.close();


            // Check if user wants to do another test
            System.out.println("\nDo you want to do another test? y for yes, n for no");

            String cont = inpt.next();

            if (cont.equals("n")) {
                test = false;
                System.out.println("\nSystem quitting. Good bye!");
            }
            else
                System.out.println();
        }



    }

    // Create stop watch class to keep track of elapsed time
    public static class Stopwatch {


        private long start;

        Stopwatch() {
            start = System.currentTimeMillis();

        }

        public void startTimer() {
            start = System.currentTimeMillis();
        }

        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;

        }


    }

    //
    // NOTE: All Union-Find classes taken from text book since it was just asked to implement them and test
    //

    public static class QuickFindUF
    {
        private int[] id;

        public QuickFindUF(int N)
        {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }
        public int find(int p)
        { return id[p]; }

        public void union(int p, int q)
        {
            int pid = id[p];
            int qid = id[q];
            for (int i = 0; i < id.length; i++)
                if (id[i] == pid) id[i] = qid;
        }
    }


    public static class QuickUnionUF
    {
        private int[] id;
        public QuickUnionUF(int N)
        {
            id = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;
        }
        public int find(int i)
        {
            while (i != id[i]) i = id[i];
            return i;
        }
        public void union(int p, int q)
        {
            int i = find(p);
            int j = find(q);
            id[i] = j;
        }
    }

    public static class WeightedQuickUnionUF
    {
        private int[] id; // parent link (site indexed)
        private int[] sz; // size of component for roots (site indexed)
        private int count; // number of components


        public WeightedQuickUnionUF(int N)
        {
            count = N;
            id = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;
            sz = new int[N];
            for (int i = 0; i < N; i++) sz[i] = 1;
        }

        public int count()
        { return count; }
        public boolean connected(int p, int q)
        { return find(p) == find(q); }
        private int find(int p)
        { // Follow links to find a root.
            while (p != id[p]) p = id[p];
            return p;
        }
        public void union(int p, int q)
        {
            int i = find(p);
            int j = find(q);
            if (i == j) return;
            // Make smaller root point to larger one.
            if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
            else { id[j] = i; sz[i] += sz[j]; }
            count--;
        }
    }

    public static class QuickUnionPathCompressionUF {
        private int[] id;    // id[i] = parent of i
        private int count;   // number of components


        public QuickUnionPathCompressionUF(int n) {
            count = n;
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }


        public int count() {
            return count;
        }


        public int find(int p) {
            int root = p;
            while (root != id[root])
                root = id[root];
            while (p != root) {
                int newp = id[p];
                id[p] = root;
                p = newp;
            }
            return root;
        }


        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }


        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            id[rootP] = rootQ;
            count--;
        }

    }



}
