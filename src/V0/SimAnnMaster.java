/**
 * The highest level simulated annealing deciding which cities to conquer
 * A 'state' in this case is a set cover of cities to conquer
 */

package V0;

import java.util.ArrayList;

public class SimAnnMaster {
    
    public static double[][] cost;

    // Calculate the acceptance probability
    public static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }


    public static SetCover doYourStuff(double startingTemperature, int numberOfIterations, double coolingRate) {
        double t = startingTemperature;
        SetCover x = SetCover.generateRandomSC();
        double ti = t;
        /*
        for (int i = 0; i < numberOfIterations; i++) {
            double f = calculateFitness(x);
            Solution mutatedX = mutate(x);
            double newF = calculateFitness(mutatedX);
            if (newF < f) {
                double p = PR(); // no idea what you're talking about here
                if (p > UR(0, 1)) { // likewise
                    // then do nothing
                } else {
                    x = mutatedX;
                }
                ti = t * coolingRate;
            }
        }
        */
        return x;
    }


    public static SetCover solve(double temp, double coolingRate) {
        //temp and cooling rate hard coded for now
        // Set initial temp
        temp = 10000;
        // Cooling rate
        coolingRate = 0.003;

        // Initialize intial solution
        SetCover currentSolution = SetCover.generateRandomSC();

        System.out.println("Initial solution distance: " + currentSolution.getCost());
        System.out.println("Tour: " + currentSolution);

        // Set as current best
        SetCover best = currentSolution.clone();

        // Loop until system has cooled
        while (temp > 1) {
            
            //TODO: generate neighboring set cover
            SetCover newSolution = currentSolution.neighbor();
            
            // Get energy of solutions
            double currentEnergy = currentSolution.getCost();
            double neighbourEnergy = newSolution.getCost();

            // Decide if we should accept the neighbour
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = newSolution.clone();
            }

            // Keep track of the best solution found
            if (currentSolution.getCost() < best.getCost()) {
                best = currentSolution.clone();
            }

            // Cool system
            temp *= 1 - coolingRate;
        }

        return best;
    }

    public static void main(String[] args) {

        //from parser
        this.cost = new double[][]{
                {1, 2, -1, -1, 3, 3},
                {2, 2, 3, -1, 3, -1},
                {-1, 3, 3, 3, 2, -1},
                {-1, -1, 3, 4, 2, -1},
                {3, 3, 2, 2, 5, 2},
                {3, -1, -1, -1, 2, 6}
        };


        //Call winston's method to find shortest path between all cities
        double [][] shortestPath = (new Dijkstraproj(cost, 0, 5)).dijkstra();

        //Generate an initial set cover, then convert into ArrayList<Integer>
        SetCover

        ArrayList<Integer> conquered = new ArrayList<>();
        for (int i = 0; i < shortestPath.length; i++) {
            conquered.add(i);
        }

        //find its

        TSPSolver.shortestPath = shortestPath;
        TSPSolver.conquered = conquered;
        TSPSolver.solve(conquered);

        System.out.println("Final solution distance: " + TSPSolver.finalCost);
        //System.out.println("Tour: " + finalOrdering);


    }

}