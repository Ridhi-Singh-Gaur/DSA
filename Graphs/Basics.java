```java
// ===============================
// GRAPHS BASICS
// Source: Striver Graph Series - First Video
// File Name: basics.java
// ===============================

/*
WHAT IS A GRAPH?
----------------
A graph is a data structure used to represent connections
between different nodes (vertices).

Examples:
- Social Networks
- Google Maps
- Flight Routes
- Computer Networks

TERMINOLOGY
-----------
1. Vertex (Node)
   -> Individual point in graph

2. Edge
   -> Connection between two nodes

3. Undirected Graph
   -> Connection works both ways
   Example: 1 -- 2

4. Directed Graph
   -> Connection works in one direction
   Example: 1 -> 2

5. Weighted Graph
   -> Edges have weights/costs

6. Degree of Node
   -> Number of edges connected to node

--------------------------------------------------
GRAPH REPRESENTATION
--------------------------------------------------

We mainly represent graphs in 2 ways:

1. Adjacency Matrix
2. Adjacency List

==================================================
1. ADJACENCY MATRIX
==================================================

- 2D matrix of size n x n
- matrix[i][j] = 1 means edge exists
- matrix[i][j] = 0 means no edge

SPACE COMPLEXITY:
O(N^2)

GOOD FOR:
- Dense graphs

Example:
Nodes = 3
Edges = (1,2), (2,3)

Matrix:

    1 2 3
1 [ 0 1 0 ]
2 [ 1 0 1 ]
3 [ 0 1 0 ]

--------------------------------------------------
CODE: ADJACENCY MATRIX
--------------------------------------------------
*/

import java.util.*;

public class basics {

    public static void main(String[] args) {

        int n = 3; // number of nodes
        int[][] matrix = new int[n + 1][n + 1];

        // Edge 1-2
        matrix[1][2] = 1;
        matrix[2][1] = 1;

        // Edge 2-3
        matrix[2][3] = 1;
        matrix[3][2] = 1;

        System.out.println("Adjacency Matrix:");

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        /*
        ==================================================
        2. ADJACENCY LIST
        ==================================================

        - Most commonly used representation
        - Stores neighbors of every node

        SPACE COMPLEXITY:
        O(2E) for undirected graph

        GOOD FOR:
        - Sparse graphs
        - Competitive Programming

        Example:
        1 -> 2
        2 -> 1,3
        3 -> 2

        --------------------------------------------------
        CODE: ADJACENCY LIST
        --------------------------------------------------
        */

        int nodes = 3;

        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<>();

        // Create empty lists
        for(int i = 0; i <= nodes; i++) {
            adj.add(new ArrayList<>());
        }

        // Edge 1-2
        adj.get(1).add(2);
        adj.get(2).add(1);

        // Edge 2-3
        adj.get(2).add(3);
        adj.get(3).add(2);

        System.out.println("\nAdjacency List:");

        for(int i = 1; i <= nodes; i++) {
            System.out.print(i + " -> ");

            for(int neighbor : adj.get(i)) {
                System.out.print(neighbor + " ");
            }

            System.out.println();
        }
    }
}

/*
==================================================
IMPORTANT POINTS
==================================================

1. In Undirected Graph:
   u-v means:
   u -> v and v -> u

2. In Directed Graph:
   u -> v only one direction

3. Weighted Graph:
   Store pair(node, weight)

4. Adjacency List is preferred in DSA.

==================================================
TIME COMPLEXITIES
==================================================

Adjacency Matrix:
- Add Edge: O(1)
- Check Edge: O(1)
- Space: O(N^2)

Adjacency List:
- Add Edge: O(1)
- Space: O(2E)

==================================================
STRIVER IMPORTANT NOTE
==================================================

For Competitive Programming,
mostly use Adjacency List.
*/
```
