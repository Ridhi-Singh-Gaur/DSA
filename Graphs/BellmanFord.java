import java.util.*;

// Bellman-Ford Algorithm
// Used to find shortest path from a source vertex to all other vertices
// Works even when graph contains negative weight edges
// Can also detect negative weight cycles

public class BellmanFord {

    // Edge class to store source, destination and weight
    static class Edge {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    // Bellman-Ford Function
    static void bellmanFord(List<Edge> edges, int V, int source) {

        // Distance array
        int[] dist = new int[V];

        // Initialize all distances as infinity
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Distance from source to itself = 0
        dist[source] = 0;

        /*
         * Step 1:
         * Relax all edges V-1 times
         * Shortest path can contain at most V-1 edges
         */
        for (int i = 1; i < V; i++) {

            for (Edge edge : edges) {

                int u = edge.src;
                int v = edge.dest;
                int wt = edge.weight;

                // Relaxation Condition
                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + wt < dist[v]) {

                    dist[v] = dist[u] + wt;
                }
            }
        }

        /*
         * Step 2:
         * Check for Negative Weight Cycle
         * If we can still relax an edge,
         * then a negative cycle exists.
         */
        for (Edge edge : edges) {

            int u = edge.src;
            int v = edge.dest;
            int wt = edge.weight;

            if (dist[u] != Integer.MAX_VALUE &&
                    dist[u] + wt < dist[v]) {

                System.out.println("Negative Weight Cycle Detected");
                return;
            }
        }

        // Print shortest distances
        System.out.println("Vertex\tDistance from Source");

        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {

        int V = 5; // Number of vertices

        List<Edge> edges = new ArrayList<>();

        // Add edges: (source, destination, weight)
        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        int source = 0;

        bellmanFord(edges, V, source);
    }
}
