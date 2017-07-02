import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ErenErdogan_HW3_Question3 {

    public static void main(String[] args) throws IOException {


        BST bst = new BST();

        // Used to maintain running of the while loop
        boolean test = true;

        // Used to read input
        Scanner inpt = new Scanner(System.in);

        // Used to store file name
        String file_name = "";

        System.out.println("Please enter the name of the file you'd like to use");
        file_name = inpt.next();


        System.out.println("Constructing Binary Search Tree with data from text file. Please wait.");

        File file = new File(file_name);

        Scanner scanner = new Scanner(file);


        int num = 0;

        while(scanner.hasNext()){

            bst.put(scanner.nextInt());
            num++;
        }

        System.out.println("BST Constructed!");

        while(test) {

            System.out.println("\nPlease enter the number you'd like to search the rank of");

            int rank = inpt.nextInt();

            System.out.println("Rank of " + rank + " is " + bst.rank(rank));


            System.out.println("\nPlease enter the number you'd like to search with the select method");

            int select = inpt.nextInt();

            System.out.println("Select of " + select + " is " + bst.select(select));


            //Check if user wants to do another test
            System.out.println("Do you want to do another test? y for yes, n for no");

            String cont = inpt.next();

            if (cont.equals("n")) {
                test = false;
                System.out.println("System quitting. Good bye!");
            } else {
                System.out.println();
            }
        }

    }

    private static class BST {

        private Node root;

        private void put (int key)
        {

            root = put(root, key);
        }

        private Node put(Node x, int key) {

            if (x == null)
                return new Node(key, 1);

            if (key < x.key)
                x.left = put(x.left, key);

            else if (key >= x.key)
                x.right = put(x.right, key);


            x.count = 1 + size(x.left) + size(x.right);

            return x;
        }

        private void printInOrder(Node root) {

            if(root == null)
                return;

            printInOrder(root.left);
            System.out.print(root.key + " ");
            printInOrder(root.right);

        }

        private int size() {

            return size(root);
        }

        private int size(Node x)
        {
            if (x == null)
                return 0;
            else
                return x.count;
        }

        private int rank(int key) {

            return rank(key, root);
        }

        private int rank(int key, Node x) {

            if (x == null)
                return 0;

            if (key < x.key)
                return rank(key, x.left);

            else if (key > x.key)
                return 1 + size(x.left) + rank(key, x.right);

            else
                return size(x.left);


        }

        private int select(int k) {

            if (k < 0 || k >= size()) {
                throw new IllegalArgumentException("called select() with invalid argument: " + k);
            }

            Node x = select(root, k);

            return x.key;
        }

        // Return key of rank k.
        private Node select(Node x, int k) {

            if (x == null)
                return null;

            int t = size(x.left);

            if (t > k)
                return select(x.left,  k);

            else if (t < k)
                return select(x.right, k-t-1);

            else
                return x;
        }


        private class Node {

            private int key;
            private Node left;
            private Node right;
            private int count;


            private Node(int key, int count) {
                this.key = key;
                this.count = count;

            }

        }





    }
}
