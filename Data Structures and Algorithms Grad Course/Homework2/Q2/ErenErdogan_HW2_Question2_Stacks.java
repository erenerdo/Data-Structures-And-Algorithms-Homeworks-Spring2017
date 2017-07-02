// Eren Erdogan
// Data Structures and Algorithms
// Homework 2
// Question 2
// Stack Method

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ErenErdogan_HW2_Question2_Stacks {

    static ArrayList<String> visitedAirports = new ArrayList<>();

    // Create Airports
    static AirportNode P = new AirportNode("P");

    static AirportNode Q = new AirportNode("Q");

    static AirportNode R = new AirportNode("R");

    static AirportNode S = new AirportNode("S");

    static AirportNode T = new AirportNode("T");

    static AirportNode W = new AirportNode("W");

    static AirportNode X = new AirportNode("X");

    static AirportNode Y = new AirportNode("Y");

    static AirportNode Z = new AirportNode("Z");

    // Begin Main Method
    public static void main(String[] args) throws IOException {

        File file = new File("Flight_Links.txt");

        Scanner scan = new Scanner(file);

        Scanner inpt = new Scanner(System.in);

        ArrayList<String> test = new ArrayList<String>();

        while(scan.hasNext()){

            String origin = scan.next();

            String destination = scan.next();

            linkDestinations(origin, destination);
        }

        System.out.print("Please enter the origin airport: ");
        String o = inpt.next().toUpperCase();

        System.out.print("Please enter the destination airport: ");
        String d = inpt.next().toUpperCase();

        System.out.println("Attempting to fly from airport " + o + " to airport " + d);

        flightMap(getAirport(o), getAirport(d));


    }

    // Airport Node Class
    public static class AirportNode{

        AirportNode(String airport){
            this.airport = airport;
        }

        public String airport;

        public void addDestination(String c){
            destinations.add(c);
        }

        public boolean isDestination(String d){
            return destinations.indexOf(d) != -1;
        }

        public ArrayList<String> destinations = new ArrayList<>();


    }

    // Used to return Airport Node from passed in airport name
    public static AirportNode getAirport(String s){

        if(s.equals("P"))
            return P;

        else if(s.equals("Q"))
            return Q;

        else if(s.equals("R"))
            return R;

        else if(s.equals("S"))
            return S;

        else if(s.equals("T"))
            return T;

        else if(s.equals("W"))
            return W;

        else if(s.equals("X"))
            return X;

        else if(s.equals("Y"))
            return Y;
        else
            return Z;

    }

    // Used to flight paths destinatjons
    public static void linkDestinations(String origin, String destination){

        if (origin.equals("P"))
            P.addDestination(destination);

        else if (origin.equals("Q"))
            Q.addDestination(destination);

        else if (origin.equals("R"))
            R.addDestination(destination);

        else if (origin.equals("S"))
            S.addDestination(destination);

        else if (origin.equals("T"))
            T.addDestination(destination);

        else if (origin.equals("W"))
            W.addDestination(destination);

        else if (origin.equals("X"))
            X.addDestination(destination);

        else if (origin.equals("Y"))
            Y.addDestination(destination);

        else if (origin.equals("Z"))
            Z.addDestination(destination);

    }

    public static void flightMap(AirportNode origin, AirportNode destination){

        // To Fly From Origin to Destination


        // Stacks used to link possible paths
        Stack<AirportNode> stackA = new Stack<>();
        Stack<AirportNode> stackB = new Stack<>();
        Stack<AirportNode> stackC = new Stack<>();
        Stack<AirportNode> stackD = new Stack<>();
        Stack<AirportNode> stackE = new Stack<>();



        if(origin.destinations.isEmpty()) {
            System.out.println("Dead End Airport " + origin.airport + "!");
            return;
        }

        // Push possible destinations to stack A
        for (int i = 0 ; i < origin.destinations.size(); i++){

            stackA.push( getAirport(origin.destinations.get(i)) );

        }


        while (!stackA.isEmpty() ){

            AirportNode current1 = stackA.pop();

            visitedAirports.add(current1.airport);

            System.out.println("Flying from " + origin.airport + " to " + current1.airport);

            if(current1.equals(destination)){
                System.out.println("Destination Reached!");
                return;
            }

            if(current1.destinations.isEmpty()) {
                System.out.println("Dead End Airport " + current1.airport + "!");
                continue;
            }


            for (int i = 0; i < current1.destinations.size(); i++){

                stackB.push( getAirport(current1.destinations.get(i)) );

            }

            while (!stackB.isEmpty()) {
                AirportNode current2 = stackB.pop();


                // Check if we are already visited the current origin airport
                if(visitedAirports.indexOf(current2.airport) == - 1)
                    visitedAirports.add(current2.airport);
                else{
                    System.out.println("Airport Already Visited!");
                    continue;
                }

                System.out.println("Flying from " + current1.airport + " to " + current2.airport);

                if(current2.equals(destination)){
                    System.out.println("Destination Reached!");
                    return;
                }


                if(current2.destinations.isEmpty()) {
                    System.out.println("Dead End Airport " + current2.airport + "!");
                    continue;
                }


                for (int i = 0; i < current2.destinations.size(); i++) {

                    stackC.push(getAirport(current2.destinations.get(i)));

                }


                while (!stackC.isEmpty()){

                    AirportNode current3 = stackC.pop();


                    // Check if we are already visited the current origin airport
                    if(visitedAirports.indexOf(current3.airport) == - 1)
                        visitedAirports.add(current3.airport);
                    else{
                        System.out.println("Airport Already Visited!");
                        continue;
                    }

                    System.out.println("Flying from " + current2.airport + " to " + current3.airport);

                    if(current3.equals(destination)){
                        System.out.println("Destination Reached!");
                        return;
                    }


                    if(current3.destinations.isEmpty()) {
                        System.out.println("Dead End Airport " + current3.airport + "!");
                        continue;
                    }


                    for (int i = 0; i < current3.destinations.size(); i++) {

                        stackD.push(getAirport(current3.destinations.get(i)));

                    }

                    while (!stackD.isEmpty()) {

                        AirportNode current4 = stackD.pop();


                        // Check if we are already visited the current origin airport
                        if (visitedAirports.indexOf(current4.airport) == -1)
                            visitedAirports.add(current4.airport);
                        else {
                            System.out.println("Airport Already Visited!");
                            continue;
                        }

                        System.out.println("Flying from " + current3.airport + " to " + current4.airport);

                        if (current4.equals(destination)) {
                            System.out.println("Destination Reached!");
                            return;
                        }


                        if (current4.destinations.isEmpty()) {
                            System.out.println("Dead End Airport " + current4.airport + "!");
                            continue;
                        }


                        for (int i = 0; i < current4.destinations.size(); i++) {

                            stackE.push(getAirport(current4.destinations.get(i)));

                        }

                        while (!stackE.isEmpty()) {

                            AirportNode current5 = stackE.pop();


                            // Check if we are already visited the current origin airport
                            if (visitedAirports.indexOf(current5.airport) == -1)
                                visitedAirports.add(current5.airport);
                            else {
                                System.out.println("Airport Already Visited!");
                                continue;
                            }

                            System.out.println("Flying from " + current4.airport + " to " + current5.airport);

                            if (current5.equals(destination)) {
                                System.out.println("Destination Reached!");
                                return;
                            }


                            if (current5.destinations.isEmpty()) {
                                System.out.println("Dead End Airport " + current5.airport + "!");
                                continue;
                            }



                        }


                    }
                }


            }


        }


        System.out.println("Destination Could Not Be Reached!");
    }


}
