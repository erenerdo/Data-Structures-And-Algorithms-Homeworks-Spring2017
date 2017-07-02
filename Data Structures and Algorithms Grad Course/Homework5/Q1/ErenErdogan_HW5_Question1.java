// Eren Erdogan
// Homework 5
// Question 1

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ErenErdogan_HW5_Question1 {

    static ArrayList<Integer> visted = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Scanner inpt = new Scanner(System.in);

        System.out.println("Please enter the file name you'd like to read");

        String f = inpt.next();

        File file = new File(f);

        Scanner scan = new Scanner(file);

        int origin = scan.nextInt();

        int paths = scan.nextInt();

        Graph g = new Graph(paths);



        // Read inputs from file
        while ( scan.hasNext() ) {

            int v = scan.nextInt();

            int w = scan.nextInt();

            double weight = scan.nextDouble();

            g.addEdge(v, w);
        }

        if(g.isCyclic())
            System.out.println("Cycle Exists");
        else
            System.out.println("Cycle Does Not Exist");


    }

    // Graph class
    static class Graph {

        // Used to store number of vertices
        private int V;

        // Used to store adjacent vertexed
        private LinkedList<Integer> adj[];


        Graph(int v) {

            V = v;

            adj = new LinkedList[v];

            for (int i = 0; i < v; ++i)

                adj[i] = new LinkedList();
        }


        void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }

        Boolean isCyclicUtil(int v, Boolean visited[], int parent) {

            visited[v] = true;

            Integer i;

            Iterator<Integer> it = adj[v].iterator();

            while (it.hasNext()) {

                i = it.next();


                if (!visited[i]) {
                    if (isCyclicUtil(i, visited, v))
                        return true;
                }

                else if (i != parent)
                    return true;
            }
            return false;
        }


        Boolean isCyclic() {

            Boolean visited[] = new Boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;


            for (int u = 0; u < V; u++)
                if (!visited[u])
                    if (isCyclicUtil(u, visited, -1))
                        return true;

            return false;
        }
    }

}


