/**
 * Solves the traveling salesman problem using simulated annealing
 * Requires as an input the n x n shortest distance matrix and the list of cities to conquer
 * shortestPath should not have any negative entries
 * Finds shortest tour between the conquered cities
 * Example usage in main()
 */

package V0;

import java.util.ArrayList;

public class TSPSolver {

    //shortest distance from one city to another
    public static double[][] shortestPath;

    public static ArrayList<Integer> conquered;

    public static double finalCost;
    public static ArrayList<Integer> finalOrdering;

    public TSPSolver(double[][] shortestPath) {
        TSPSolver.shortestPath = shortestPath;
    }

    // Calculate the acceptance probability
    public static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }

    //Could probably be optimized much more for speed
    public static void solve(ArrayList<Integer> conquered) {

        //initialize conquered list
        TSPSolver.conquered = conquered;
        for(int i = 0; i < conquered.size(); i++) {
            TourManager.addCity(conquered.get(i));
        }

        //temp and cooling rate hard coded for now
        double temp = 10000;
        double coolingRate = 0.003;

        // Initialize intial solution
        Tour currentSolution = new Tour(shortestPath);
        currentSolution.generateIndividual();
        
        System.out.println("Initial solution distance: " + currentSolution.getDistance());
        System.out.println("Tour: " + currentSolution);

        // Set as current best
        Tour best = new Tour(currentSolution.getTour());
        
        // Loop until system has cooled
        while (temp > 1) {
            // Create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            // TODO: might want to switch consecutive cities instead of random ones
            // Get random positions in the tour
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());

            // Get the cities at selected positions in the tour
            //int citySwap1 = newSolution.getCity(tourPos1);
            //int citySwap2 = newSolution.getCity(tourPos2);

            // Swap them
            newSolution.setCity(tourPos2, newSolution.getCity(tourPos1));
            newSolution.setCity(tourPos1, newSolution.getCity(tourPos2));
            
            // Get energy of solutions
            double currentEnergy = currentSolution.getDistance();
            double neighbourEnergy = newSolution.getDistance();

            // Decide if we should accept the neighbour
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            // Keep track of the best solution found
            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
            }
            
            // Cool system
            temp *= 1-coolingRate;
        }

        finalCost = best.getDistance();
        finalOrdering = best.getTour();
    }


    //Example usage
    public static void main(String[] args) {
        //shortestPath n x n matrix
        double[][] shortestPath = new double[][]{
            { 0, 1, 2, 5},
            { 1, 0, 4, 3},
            { 2, 4, 0, 6},
            { 5, 3, 6, 0},
        };

        //list of cities to be conquered
        ArrayList<Integer> conquered = new ArrayList<>();
        for(int i = 0; i < shortestPath.length; i++) {
            conquered.add(i);
        }
        
        //Usage example
        TSPSolver.shortestPath = shortestPath;
        TSPSolver.solve(conquered);

        System.out.println("Final solution distance: " + TSPSolver.finalCost);
        System.out.println("Tour: " + TSPSolver.finalOrdering);
    }

}