// Eren Erdogan
// Homework 5
// Question 3


import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.File;
import java.util.*;

public class ErenErdogan_HW5_Question3 {

    public static void main(String[] args) throws IOException {

        Scanner inpt = new Scanner(System.in);

        System.out.println("Please enter the file name you'd like to read");

        String f = inpt.next();

        File file = new File(f);

        Scanner scan = new Scanner(file);

        int origin = scan.nextInt();

        int paths = scan.nextInt();

        Graph g = new Graph(origin);


        // Read inputs from file
        while ( scan.hasNext() ) {

            int v = scan.nextInt();

            int w = scan.nextInt();

            double weight = scan.nextDouble();

            g.addE(v, w);
        }

        System.out.println("DFS from Vertex 1:");
        g.DFS(1);

        System.out.println("\nBFS from Vertex 1:");
        g.BFS(1);



    }

    static class Graph {

        // Used to stop the DFS BFS algorithms are 50 each
        int countDFS = 0;
        int countBFS = 0;

        private int V;

        private LinkedList<Integer> adj[];

        Graph(int v) {

            V = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList();
        }


        void addE(int v, int w) {

            adj[v].add(w);

        }


        void DFS_Utilility(int v,boolean visited[]) {
            // Mark the current node as visited and print it
            visited[v] = true;

            if(countDFS <= 50) {
                System.out.println(v);
                countDFS++;
            }
            else{
                return;
            }

            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {

                int n = i.next();
                if (!visited[n])
                    DFS_Utilility(n, visited);
            }
        }

        // Helper function
        void DFS(int v) {

            boolean visited[] = new boolean[V];


            DFS_Utilility(v, visited);
        }

        // BFS from origin given
        void BFS(int origin) {


            // Initialize visted array
            boolean visited[] = new boolean[V];



            // Queue to be used for BFS
            LinkedList<Integer> q = new LinkedList<Integer>();

            // mark node visited
            visited[origin] = true;
            q.add(origin);

            while (q.size() != 0) {


                origin = q.poll();

                // Check to make sure we haven't surpassed 50 vists
                if(countBFS <= 50) {
                    System.out.println(origin);
                    countBFS++;
                }
                else{
                    return;
                }

                Iterator<Integer> i = adj[origin].listIterator();


                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {

                        visited[n] = true;
                        q.add(n);
                    }
                }


            }
        }


    }



}
