/**
 * The list of cities to conquer, analogous to a set cover
 * 'States' to the SimAnnMaster.java
 */

package V0;

public class SetCover {

	public static SetCover generateRandomSC() {
		//TODO: return a random initial state
		return null;
	}

	@Override
	public SetCover clone() {
		//TODO: return a copy of this
        //maybe don't need to override? idk how the native clone() works
		return null;
	}

	public SetCover neighbor() {
		//TODO: return a valid neighboring state
		return null;
	}

	public double getCost() {
		//TODO: return the time it takes to tour this set cover.
		// use the traveling salesman solver, TSPSolver to find this value
		return 0;
	}

}