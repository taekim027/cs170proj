import edu.princeton.cs.algs4.MinPQ;

public class Dijkstraproj {

    public static City[][] cities; //matrix of cities [starting point][end point]
    public double[][] cost; //cost[i][j] is price to go from ith city to jth city
    public int start; //starting city
    public int end; //end city
    public int size; //number of cities

    public Dijkstraproj(double[][] cost, int start, int end) {
        this.cost = cost;
        this.start = start;
        this.end = end;
        size = cost[0].length;
    }

    public double[][] dijkstra() {
        int N = cost[0].length; // N is number of cities
        double[][] dist = new double[N][N]; //initiates a large value for distances between cities
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                dist[i][j] = 10000;
            }
        }
        boolean[][] adj = adj(cost); //gets the adjacency matrix as true/false
        toString(adj);
        boolean[][] visited = new boolean[N][N]; //visited matrix for Dijkstra purposes
        cities = new City[N][N];//Creates matrix of cities [start][end]
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visited[i][j] = false; //none of the cities have been visited
                cities[i][j] = new City(j, 1000, false, null); //creates new City with large distance from initial node
            }
        }
        //Does Dijkstra from every point
        for (start = 0; start < size; start++) { //increment over starting cities

            MinPQ<City> pq = new MinPQ<>();//PQ for Dijkstra
            pq.insert(cities[start][start]);
            dist[start][start] = 0; //dist to self is 0
            cities[start][start].dist = 0;
            cities[start][start].prev = null; //There is no previous node
            //implement dijkstra
            while (!pq.isEmpty()) {
                City currcity = pq.delMin();
                int curr = currcity.num;
                visited[start][curr] = true;
                cities[start][curr].visited = true;
                for (int k = 0; k < N; k++) {
                    //if an edge
                    if (adj[curr][k] && curr != k && !visited[start][k] &&  k != start) {
                        //update distances from starting city as necessary
                        if (dist[start][k] > dist[start][curr] + cost[curr][k]) {
                            dist[start][k] = dist[start][curr] + cost[curr][k];
                            cities[start][k].dist = dist[start][k];
                            cities[start][k].setPrev(currcity);
                            System.out.println(cities[start][k].num + " "+ cities[start][curr]);
                        }
                        //add updated cities to PQ
                        pq.insert(cities[start][k]);
                    }

                }
            }
        }
        toString(cities);
        return dist; //returns the shortest distances
    }
    //creates adjacency matrix
    private static boolean[][] adj(double[][] cost) {
        int N = cost[0].length;
        boolean[][] adj = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean val;
                if (cost[i][j] > 0 && i != j) {
                    val = true;
                } else {
                    val = false;
                }
                adj[i][j] = val;
                adj[j][i] = val;
            }
        }
        return adj;
    }

    public static void main(String[] args) {
        double[][] cost = new double[][]{
                {1, 2, -1, -1, 3, 3},
                {2, 2, 3, -1, 3, -1},
                {-1, 3, 3, 3, 2, -1},
                {-1, -1, 3, 4, 2, -1},
                {3, 3, 2, 2, 5, 2},
                {3, -1, -1, -1, 2, 6}
        };
        toString(cost);
        toString(new Dijkstraproj(cost, 0, 5).dijkstra());
    }

    public static void toString(double[][] obj) {
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj.length; j++) {
                System.out.print(obj[i][j] + " \t");
            }
            System.out.println("\n");
        }
    }
    public static void toString(Object[][] obj) {
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj.length; j++) {
                System.out.print(obj[i][j] + " \t");
            }
            System.out.println("\n");
        }
    }
    public static void toString(boolean[][] obj) {
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj.length; j++) {
                System.out.print(obj[i][j] + " \t");
            }
            System.out.println("\n");
        }
    }

    private class City implements Comparable<City> {
        public int num;
        public double dist;
        public boolean visited;
        public City prev;

        public City(int num, double dist, boolean visited, City prev) {
            this.num = num;
            this.dist = dist;
            this.visited = visited;
            this.prev = prev;
        }
        public void setPrev(City prev) {
            this.prev = prev;
        }
        public String toString(){
            if(prev == null){
                //"num: "+num + " dist: " + dist + " visited: " + visited +
                return " prev: null";

            }
            return " prev: " + prev.num;
        }
        public int compareTo(City c) {
            return (int) Math.round(dist - c.dist);
        }
    }
}