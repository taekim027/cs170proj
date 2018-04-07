/**
 * Stores a valid tour
 */
package V0;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{

    public static double[][] cost;

    // Holds our tour of cities
    //private ArrayList tour = new ArrayList<City>();
    private ArrayList tour = new ArrayList<Integer>();
    // Cache
    private double distance = 0;
    
    // Constructs a blank tour
    /*public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }*/

    public Tour(double[][] cost) {
        /*for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }*/
        this.cost = cost;
    }
    
    // Constructs a tour from another tour
    public Tour(ArrayList tour){
        this.tour = (ArrayList) tour.clone();
    }
    
    // Returns tour information
    public ArrayList getTour(){
        return tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
            tour.add(TourManager.getCity(cityIndex));
            //setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public int getCity(int tourPosition) {
        return (int) tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, int city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        distance = 0;
    }
    
    // Gets the total distance of the tour
    public double getDistance(){
        if (distance == 0) {
            double tourDistance = 0;
            // Loop through our tour's cities
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {

                // Get city we're traveling from
                //City fromCity = getCity(cityIndex);
                int fromCity = getCity(cityIndex);
                // City we're traveling to
                //City destinationCity;
                int destinationCity;
                // Check we're not on our tour's last city, if we are set our 
                // tour's final destination city to our starting city
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }

                // Get the distance between the two cities
                double distCost = cost[fromCity][destinationCity];
                //if(distCost > 0) {
                    tourDistance += distCost;
                //} else {
                //    tourDistance = Double.POSITIVE_INFINITY;
                //}

                //tourDistance += fromCity.distanceTo(destinationCity);

            }
            distance = tourDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }


    @Override
    public String toString() {

        String geneString = "";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i) + " ";
        }
        return geneString;
    }
}