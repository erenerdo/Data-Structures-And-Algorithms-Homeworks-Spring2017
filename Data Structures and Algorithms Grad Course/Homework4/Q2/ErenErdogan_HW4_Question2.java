// Eren Erdogan
// Data Structures and Algorithms
// Homework 4
// Question 2

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ErenErdogan_HW4_Question2 {

    public static void main(String[] args) throws IOException {

        RedBlackBST rb = new RedBlackBST();

        Scanner inpt = new Scanner(System.in);

        boolean cont = true;

        while(cont) {

            System.out.println("Please enter the name of the file you want to use");

            String file_name = inpt.next();

            File file = new File(file_name);

            Scanner scanner = new Scanner(file);

            System.out.println("Numbers in '" + file_name + "':");

            // Scan file and put into 2-3 Tree
            while(scanner.hasNext()){

                int t = scanner.nextInt();
                rb.put(t);
                System.out.print(t + " ");

            }

            System.out.println("\n\nValues Put Into Red-Black Tree");

            // Output integers in 2-3 tree in order

            System.out.println("\nIn Order Traversal in Red-Black Tree Result:");
            
            rb.printInOrder();

            //

            System.out.println("\n\nDo you want to do another test? Enter 'y' for yes and 'n' for no");

            String c = inpt.next();

            if(c.toUpperCase().equals("N")) {
                System.out.println("Program exiting. Good Bye!");
                cont = false;
            } else{

                System.out.println();
            }
        }

    }


    public static class RedBlackBST {

        // Used for color links
        private static final boolean RED   = true;
        private static final boolean BLACK = false;
        private Node root;

        // Node Class
        private class Node {

            private int key;
            private Node left;
            private Node right;
            private boolean color;
            private int size;


            public Node(int key, boolean color) {

                this.key = key;
                this.color = color;

            }
        }

        public RedBlackBST() {
        }

        private boolean isRed(Node x) {

            if (x == null)
                return false;

            return x.color == RED;
        }

        public boolean isEmpty() {

            return root == null;
        }

        private int size(Node x) {

            if (x == null)
                return 0;

            return x.size;
        }


        public int size() {

            return size(root);
        }

        public void put(int key) {
            root = put(root, key);
            root.color = BLACK;
        }


        private Node put(Node h, int key) {

            if (h == null)
                return new Node(key, RED);

            if (key < h.key)
                h.left  = put(h.left,  key);

            else if (key > h.key)
                h.right = put(h.right, key);

            else
                h.key = key;

            /////

            if (isRed(h.right) && !isRed(h.left))
                h = rotateLeft(h);

            if (isRed(h.left)  &&  isRed(h.left.left))
                h = rotateRight(h);

            if (isRed(h.left)  &&  isRed(h.right))
                flipColors(h);

            h.size = size(h.left) + size(h.right) + 1;

            return h;
        }


        private Node rotateRight(Node h) {

            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = x.right.color;
            x.right.color = RED;
            x.size = h.size;
            h.size = size(h.left) + size(h.right) + 1;
            return x;
        }


        private Node rotateLeft(Node h) {

            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = x.left.color;
            x.left.color = RED;
            x.size = h.size;
            h.size = size(h.left) + size(h.right) + 1;
            return x;
        }


        private void flipColors(Node h) {

            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;

        }


        private Node moveRedLeft(Node h) {

            flipColors(h);

            if ( isRed(h.right.left) ) {
                h.right = rotateRight(h.right);
                h = rotateLeft(h);
                flipColors(h);
            }

            return h;
        }

        private Node moveRedRight(Node h) {

            flipColors(h);

            if (isRed(h.left.left)) {
                h = rotateRight(h);
                flipColors(h);
            }

            return h;
        }


        private Node balance(Node h) {

            if (isRed(h.right))
                h = rotateLeft(h);

            if (isRed(h.left) && isRed(h.left.left))
                h = rotateRight(h);

            if (isRed(h.left) && isRed(h.right))
                flipColors(h);

            h.size = size(h.left) + size(h.right) + 1;
            return h;
        }


        public int min() {
            return min(root).key;
        }


        private Node min(Node x) {

            if (x.left == null)
                return x;
            else
                return min(x.left);

        }


        public int max() {

            return max(root).key;
        }

        private Node max(Node x) {

            if (x.right == null)
                return x;

            else
                return max(x.right);

        }


        public void printInOrder() {

            keys(root, min(), max());
        }


        private void keys(Node x, int lo, int hi) {

            if (x == null)
                return;

            keys(x.left, lo, hi);
            System.out.print(x.key + " ");
            keys(x.right, lo, hi);

        }

    }

}
