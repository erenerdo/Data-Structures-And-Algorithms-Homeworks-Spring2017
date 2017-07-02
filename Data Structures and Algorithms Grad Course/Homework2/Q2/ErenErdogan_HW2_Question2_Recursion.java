// Eren Erdogan
// Data Structures and Algorithms
// Homework 2
// Question 2
// Recursive Method

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ErenErdogan_HW2_Question2_Recursion {

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

        System.out.println("Destination Could Not Be Reached!");


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

        // IF C is the destination
        // terminate, destination is reached

        if(origin.airport.equals(destination.airport)){
            System.out.println("Destination Reached!");
            System.exit(0);
        }

        if(origin.destinations.size() == 0){
            System.out.println("Dead End Airport!");
            return;
        }


        // Check if we are already visited the current origin airport
        if(visitedAirports.indexOf(origin.airport) == - 1)
            visitedAirports.add(origin.airport);
        else{
            System.out.println("Airport Already Visited!");
            return;
        }


        // Select a city C adjacent to to the origin
        for( int i = 0 ; i < origin.destinations.size(); i++){

            System.out.println("Flying from " + origin.airport + " to " +  origin.destinations.get(i) );

            // Recursive call to with new origin

            flightMap(getAirport(origin.destinations.get(i) ), destination);



        }

    }


}
