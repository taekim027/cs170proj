/**
 * The list of cities to conquer, analogous to a set cover
 * 'States' to the SimAnnMaster.java
 */

package V0;

public class SetCover {

	//public static ArrayList<Integer> kingdoms;
	public static double[][] shortestPath;
	public static int num;

	public ArrayList<Integer> conquered;		//the set cover

	// the number of kingdoms is implicit
	public SetCover(double[][] shortestPath) {
		this.shortestPath = shortestPath;
		num = shortestPath.length;
		conquered = new ArrayList<Integer>();
	}

	public static SetCover generateRandomSC() {
		//TODO: return a random initial state
		//conquered = 

		return null;
	}

	@Override
	public SetCover clone() {
		//TODO: return a copy of this
        //maybe don't need to override? idk how the native clone() works
        SetCover cloned = new SetCover(shortestPath);
        cloned.conquered = this.conquered;
		return cloned;
	}

	public SetCover neighbor() {
		//TODO: return a valid neighboring state
		return null;
	}

	public double getCost() {
		//TODO: return the time it takes to tour this set cover.
		// use the traveling salesman solver, TSPSolver to find this value

		//
		TSPSolver.shortestPath = shortestPath;
        TSPSolver.solve(conquered);

        return TSPSolver.finalCost();
        //System.out.println("Tour: " + TSPSolver.finalOrdering);
	}

}