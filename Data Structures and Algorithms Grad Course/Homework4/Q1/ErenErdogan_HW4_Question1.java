// Eren Erdogan
// Data Structures and Algorithms
// Homework 4
// Question 1

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ErenErdogan_HW4_Question1 {

    public static void main(String[] args)throws IOException {

        Scanner inpt = new Scanner(System.in);

        TwoThreeTree tw = new TwoThreeTree();

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
                tw.put(t);
                System.out.print(t + " ");

            }

        System.out.println("\n\nValues Put Into 2-3 Search Tree");

            // Output integers in 2-3 tree in order

        System.out.println("\nIn Order Traversal in 2-3 Tree Result:");
            tw.printInOrder(tw.root);


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

    public static class TwoThreeTree  {


        private Node root;


        public class Node
        {
            protected int key1 = -1;
            protected int key2 = -1;
            protected Node parent;
            protected Node left;
            protected Node middle;
            protected Node right;

            public  Node(int key1)
            {
                this.key1 = key1;
            }

            private Node(int key1, int key2)
            {
                this.key1 = key1;
                this.key2 = key2;
            }
        }

        public class FourNode extends Node {
            protected int key3 = -1;
            protected Node middle2;

            public FourNode (int key1, int key2, int key3) {
                super(key1, key2);
                this.key3 = key3;
            }
        }

        private Node getNode(int key, Node start, boolean nullIfMissing) {

            if (nullIfMissing) {
                if(start == null)
                    return null;
            }

            else {

                if (start.left == null)
                    return start;
            }

            if (key == start.key1)
                return start;

            if (key < start.key1)
                return getNode(key, start.left, nullIfMissing);

            // Two Node
            if (start.key2 == -1) {

                return getNode(key, start.middle, nullIfMissing);
            }


            //Three Node
            else {

                if (key == start.key2)
                    return start;


                if (key > start.key1 && key < start.key2 )
                    return getNode(key, start.middle, nullIfMissing);

                else
                    return getNode(key, start.right, nullIfMissing);
            }
        }

        public int get(int key) {

            Node search = getNode(key, root, true);

            if (search != null) {

                if (search.key1 == key)
                    return search.key1;
                else
                    return search.key2;
            }
            else
                return -1;
        }

        public void put(int key) {


            if (root == null) {
                root = new Node(key);
                return;
            }

            Node found = getNode(key, root, false);

            if (found.key2 == -1) {
                if (key < found.key1)	{
                    found.key2 = found.key1;
                    found.key1 = key;
                }

                else if (key == found.key1)
                    found.key1 = key;

                else if (key > found.key1)
                    found.key2 = key;
            }

            else {
                if (found.key1 == key) {
                    return;
                }

                if (found.key2 == key) {
                    return;
                }

                FourNode tempFourNode = null;


                if (key < found.key1)
                    tempFourNode = new FourNode(key, found.key1, found.key2);

                else if (key > found.key1 && key < found.key2)
                    tempFourNode = new FourNode(found.key1, key, found.key2);

                else if (key > found.key2)
                    tempFourNode = new FourNode(found.key1, found.key2, key);

                put4NodeInTree(found, tempFourNode);
            }
        }

        private void put4NodeInTree(Node current, FourNode tmpFour) {

            Node split = Convert4to2(tmpFour);

            if (current == root) {
                root = split;
            }
            else {
                Node parent = current.parent;


                FourNode merge = MergeNodes (parent, split);

                if (merge != null)
                    put4NodeInTree(parent, merge);
            }
        }

        private Node Convert4to2(FourNode four) {

            Node newRoot = new Node (four.key2);


            Node newLeft = new Node (four.key1);
            Node newRight = new Node (four.key3);

            // New children now new root
            newRoot.left = newLeft;
            newRoot.middle = newRight;


            // Link root to children
            newLeft.parent = newRoot;
            newRight.parent = newRoot;


            // Move and relink parent
            newLeft.left = four.left;

            if (newLeft.left != null)
                newLeft.left.parent = newLeft;


            // Move and relink parent
            newLeft.middle = four.middle;

            if (newLeft.middle != null)
                newLeft.middle.parent = newLeft;

            // Move and relink parent
            newRight.left = four.middle2;

            if (newRight.left != null)
                newRight.left.parent = newRight;

            // Move and relink parent
            newRight.middle = four.right;

            if(newRight.middle != null)
                newRight.middle.parent = newRight;

            return newRoot;
        }

        private FourNode MergeNodes(Node tree, Node separate) {

            FourNode newFour = null;

            // If 2 Node
            if (tree.key2 == -1) {

                if(separate.key1 < tree.key1) {

                    tree.key2 = tree.key1;

                    tree.key1 = separate.key1;

                    tree.right = tree.middle;

                    tree.middle = separate.middle;

                    tree.left = separate.left;

                } else if (separate.key1 > tree.key1) {

                    tree.key2 = separate.key1;

                    tree.right = separate.middle;

                    tree.middle = separate.left;

                }

                separate.middle.parent = tree;

                separate.left.parent = tree;
            }
            else {

                if (separate.key1 < tree.key1) {
                    newFour = new FourNode(separate.key1, tree.key1, tree.key2);

                    newFour.left = separate.left;

                    newFour.middle = separate.middle;

                    newFour.middle2 = tree.middle;

                    newFour.right = tree.right;
                }

                else if (separate.key1 > tree.key1 && separate.key1 < tree.key2) {

                    newFour = new FourNode(tree.key1, separate.key1, tree.key2);

                    newFour.left = tree.left;

                    newFour.middle = separate.left;

                    newFour.middle2 = separate.middle;

                    newFour.right = tree.right;
                }
                else {

                    newFour = new FourNode(tree.key1, tree.key2, separate.key1);

                    newFour.left = tree.left;

                    newFour.middle = tree.middle;

                    newFour.middle2 = separate.left;

                    newFour.right = separate.middle;
                }

                // re-link
                newFour.left.parent = newFour;

                newFour.middle.parent = newFour;

                newFour.middle2.parent = newFour;

                newFour.right.parent = newFour;
            }

            return newFour;
        }

        private void printInOrder (Node current) {

            if (current.left == null) {
                System.out.print(current.key1 + " ");

                if (current.key2 != -1)
                    System.out.print(current.key2 + " ");
            }

            // 2 Node
            else if (current.key2 == -1) {
                printInOrder(current.left);
                System.out.print(current.key1 + " ");
                printInOrder(current.middle);
            }

            //If 3 Node
            else {
                printInOrder(current.left);
                System.out.print(current.key1 + " ");

                printInOrder(current.middle);
                System.out.print(current.key2 + " ");

                printInOrder(current.right);
            }
        }




    }


}
