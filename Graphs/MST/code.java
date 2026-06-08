/*
====================================================
MINIMUM SPANNING TREE (MST)
====================================================

Definition:
A Minimum Spanning Tree is a subset of edges of a
connected, undirected, weighted graph such that:

1. All vertices are connected.
2. No cycle exists.
3. Total edge weight is minimum.

Properties:
1. MST contains exactly (V - 1) edges.
2. MST has no cycles.
3. A graph can have multiple MSTs.
4. Applicable only to connected graphs.

Applications:
1. Network Design
   - Computer Networks
   - Telephone Networks
   - Road Networks

2. Wiring/Cabling Problems

3. Water Supply Networks

4. Cluster Analysis

----------------------------------------------------
Methods to Find MST
----------------------------------------------------

1. Prim's Algorithm
   - Greedy Algorithm
   - Starts from any vertex.
   - Expands MST vertex by vertex.
   - Uses Priority Queue (Min Heap).

Time Complexity:
O(E log V)

----------------------------------------------------

2. Kruskal's Algorithm
   - Greedy Algorithm
   - Sort edges by weight.
   - Add smallest edge that does not form cycle.
   - Uses Disjoint Set Union (DSU).

Time Complexity:
O(E log E)

----------------------------------------------------
Difference Between Prim and Kruskal
----------------------------------------------------

Prim:
- Vertex Based
- Grows one tree
- Uses Priority Queue

Kruskal:
- Edge Based
- Selects edges globally
- Uses DSU (Union Find)

----------------------------------------------------
MST Conditions
----------------------------------------------------

Graph must be:
1. Connected
2. Undirected
3. Weighted

If graph is disconnected:
=> Minimum Spanning Forest is formed.
====================================================

TC for prim's algo E*log E
SC for prim's algo E
*/
import java.util.*;

public class PrimsAlgorithm {

    static class Pair {
        int node;
        int weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int primMST(int V, ArrayList<ArrayList<Pair>> adj) {

        // Min Heap based on edge weight
        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> a.weight - b.weight);

        boolean[] visited = new boolean[V];

        // Start from node 0
        pq.add(new Pair(0, 0));

        int mstWeight = 0;

        while (!pq.isEmpty()) {

            Pair current = pq.poll();

            int node = current.node;
            int wt = current.weight;

            // Skip if already included
            if (visited[node])
                continue;

            visited[node] = true;

            mstWeight += wt;

            // Explore neighbours
            for (Pair neighbour : adj.get(node)) {

                if (!visited[neighbour.node]) {
                    pq.add(new Pair(neighbour.node,
                                    neighbour.weight));
                }
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {

        int V = 4;

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1, 10));
        adj.get(1).add(new Pair(0, 10));

        adj.get(0).add(new Pair(2, 6));
        adj.get(2).add(new Pair(0, 6));

        adj.get(0).add(new Pair(3, 5));
        adj.get(3).add(new Pair(0, 5));

        adj.get(1).add(new Pair(3, 15));
        adj.get(3).add(new Pair(1, 15));

        adj.get(2).add(new Pair(3, 4));
        adj.get(3).add(new Pair(2, 4));

        System.out.println("MST Weight = "
                + primMST(V, adj));
    }
}
import java.util.*;

public class KruskalAlgorithm {

    static class Edge {
        int u, v, wt;

        Edge(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }
    }

    static int[] parent;
    static int[] rank;

    // Find Parent
    static int find(int node) {

        if (parent[node] == node)
            return node;

        return parent[node] =
                find(parent[node]);
    }

    // Union By Rank
    static void union(int u, int v) {

        int pu = find(u);
        int pv = find(v);

        if (pu == pv)
            return;

        if (rank[pu] < rank[pv]) {
            parent[pu] = pv;
        }
        else if (rank[pv] < rank[pu]) {
            parent[pv] = pu;
        }
        else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }

    static int kruskalMST(int V,
                          ArrayList<Edge> edges) {

        Collections.sort(edges,
                (a, b) -> a.wt - b.wt);

        parent = new int[V];
        rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int mstWeight = 0;

        for (Edge edge : edges) {

            int u = edge.u;
            int v = edge.v;
            int wt = edge.wt;

            // If cycle is not formed
            if (find(u) != find(v)) {

                mstWeight += wt;

                union(u, v);
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {

        int V = 4;

        ArrayList<Edge> edges =
                new ArrayList<>();

        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        System.out.println("MST Weight = "
                + kruskalMST(V, edges));
    }
}
