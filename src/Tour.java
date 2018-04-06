import java.util.ArrayList;

public class Tour {
	
	public static double[][] cost = new double[][]{
	  { 1, 2, -1, -1, 3, 3},
	  { 2, 2, 3, -1, 3, -1},
	  { -1, 3, 3, 3, 2, -1},
	  { -1, -1, 3, 4, 2, -1},
	  { 3, 3, 2, 2, 5, 2},
	  { 3, -1, -1, -1, 2, 6}
	};

	//ordering of the tour
	public ArrayList<Integer> ordering = new ArrayList<Integer>();
	//list of Kingdoms that are explicitly conquered. Deduce from 'ordering'
	public ArrayList<Integer> conquered = new ArrayList<Integer>();

	//idk if needed
	public ArrayList<Kingdom> kingdoms = new ArrayList<Kingdom>();
	//number of kingdoms
	public int numKingdoms;

	public Tour() {
		ordering = new ArrayList<Integer>();
		conquered = new ArrayList<Integer>();
		kingdoms = new ArrayList<Kingdom>();
		numKingdoms = 0;
    }

	public Tour(Tour another) {
		//copy another.ordering into this.ordering
	}

	public Tour getTour() {
		//return a copy of this tour
		return this;
	}

	public void generateIndividual() {
		// make a random tour
        ordering.add(1);
        ordering.add(2);
        ordering.add(3);
        ordering.add(3);
        ordering.add(5);
        ordering.add(6);
        ordering.add(6);
        ordering.add(1);

        numKingdoms = cost.length;
		for(int i = 0; i < ordering.size() - 1; i++) {
			//if ith is the same as i+1th, it's conquered
			if(ordering.get(i) == ordering.get(i+1)) {
				conquered.add(ordering.get(i));
			}
		}

	}

	public Tour neighbor() {
		return this;
	}

	//COMPLETE
	//returns 'energy' of current state
	public double getDistance() {
		double sum = 0;
		for(int i = 0; i < ordering.size() - 1; i++) {
			sum += cost[ordering.get(i) - 1][ordering.get(i+1) - 1];
		}
		return sum;
	}

	//COMPLETE
	// Valid if every kingdom has a conquered neighbor
	public boolean isValid() {
		//for every city
		for(int i = 0; i < numKingdoms; i++) {
			//check its adjacency list
			for(int j = 0; j < numKingdoms; j++) {

				//if reachable from i and if j has been conquered
				if(cost[i][j] > 0 && conquered.contains(j)) {
					//yes, this city has been conquered
					break;
				}
			}
			return false;
		}
		return true;
	}

	public String toString() {
		String result = "";
		for(Integer i : ordering) {
			result += i + " ";
		}
		return result;
	}


}