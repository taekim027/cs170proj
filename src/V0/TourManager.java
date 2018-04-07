/*
* TourManager.java
* Holds the cities of a tour
*/
package V0;

import java.util.ArrayList;

public class TourManager {

    // Holds our cities
    private static ArrayList destinationCities = new ArrayList<Integer>();

    // Adds a destination city
    public static void addCity(int city) {
        destinationCities.add(city);
    }
    
    // Get a city
    public static int getCity(int index){
        return (int) destinationCities.get(index);
    }
    
    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    }
    
}