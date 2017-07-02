// Eren Erdogan
// Homework 5
// Question 2 Part 2
// Prims

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class ErenErdogan_HW5_Question2_Part2 {

    public static void main(String[] args) throws IOException {

        Scanner inpt = new Scanner(System.in);

        System.out.println("Please enter the file name you'd like to read");

        String f = inpt.next();

        File file = new File(f);

        Scanner scan = new Scanner(file);

        int origin = scan.nextInt();

        int paths = scan.nextInt();

        Graph g = new Graph(origin, paths);

        int index = 0;

        // Read inputs from file
        while (scan.hasNext()) {

            int v = scan.nextInt();

            int w = scan.nextInt();

            double weight = scan.nextDouble() * 100000;

            g.edge[index].origin = v;
            g.edge[index].destination = w;
            g.edge[index].weight = (int) weight;

            index++;


        }

        g.Kruskal();

    }


    public static class Graph {

        int V;
        int E;
        Edge edge[];

        // Used for union-find
        class subset {
            int parent, rank;
        }

        class Edge implements Comparable<Edge> {
            int origin;
            int destination;
            int weight;


            public int compareTo(Edge compareEdge) {

                return this.weight - compareEdge.weight;
            }
        }


        Graph(int v, int e) {

            this.V = v;
            this.E = e;
            edge = new Edge[E];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();

        }


        int find(subset sub[], int i) {


            if (sub[i].parent != i) {
                sub[i].parent = find(sub, sub[i].parent);
            }

            return sub[i].parent;
        }


        void Union(subset sub[], int x, int y) {


            int xrt = find(sub, x);
            int yrt = find(sub, y);


            if (sub[xrt].rank < sub[yrt].rank) {
                sub[xrt].parent = yrt;
            } else if (sub[xrt].rank > sub[yrt].rank) {
                sub[yrt].parent = xrt;
            } else {
                sub[yrt].parent = xrt;
                sub[xrt].rank++;
            }
        }


        void Kruskal() {


            Edge result[] = new Edge[V];

            int e = 0;
            int i = 0;

            for (i = 0; i < V; i++) {
                result[i] = new Edge();
            }

            Arrays.sort(edge);

            subset sub[] = new subset[V];


            for (i = 0; i < V; i++) {
                sub[i] = new subset();
            }


            for (int v = 0; v < V; v++) {

                sub[v].parent = v;
                sub[v].rank = 0;
            }

            // Index
            i = 0;


            while (e < V - 1) {

                Edge next = new Edge();
                next = edge[i++];

                int x = find(sub, next.origin);
                int y = find(sub, next.destination);


                if (x != y) {

                    result[e++] = next;
                    Union(sub, x, y);
                }

            }

            // Print
            System.out.println("\nEdges in MST");
            for (i = 0; i < e; i++) {

                System.out.println(result[i].origin + " -- " + result[i].destination + " == " + (double) result[i].weight / 100000);
            }
        }
    }
}