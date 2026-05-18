```java
// ==========================================================
// GRAPH BASICS
// Source: Striver Graph Series - First Video
// File Name: basics.java
// ==========================================================

/*

==============================
WHAT IS A GRAPH?
==============================

A graph is a data structure used to represent
connections between nodes.

A graph contains:
1. Nodes (Vertices)
2. Edges

Examples:
- Facebook Friends Network
- Google Maps
- Flight Routes
- Computer Networks
- Instagram Followers


==================================================
TERMINOLOGIES
==================================================

1. NODE / VERTEX
----------------
Individual point in graph.

Example:
1, 2, 3, 4


2. EDGE
--------
Connection between two nodes.

Example:
1 ---- 2


==================================================
TYPES OF GRAPHS
==================================================

1. UNDIRECTED GRAPH
-------------------

Connection works both ways.

u ---- v

Meaning:
u -> v
v -> u


2. DIRECTED GRAPH
-----------------

Connection works in one direction.

u ---> v

Only:
u -> v


3. WEIGHTED GRAPH
-----------------

Edges contain weight/cost.

Example:

1 ---(5)---> 2

Here,
5 = weight/cost


==================================================
DEGREE OF A NODE
==================================================

Degree of node =
Number of edges connected to node.

Example:

1 ---- 2 ---- 3

Degree(1) = 1
Degree(2) = 2
Degree(3) = 1


==================================================
IMPORTANT FORMULA FOR UNDIRECTED GRAPH
==================================================

Total Degree = 2 * Number of Edges

i.e.

Total Degree = 2E

Reason:
Each edge contributes to degree of 2 nodes.


Example:

Edges:
(1,2)
(2,3)

Degrees:
deg(1)=1
deg(2)=2
deg(3)=1

Total Degree = 1+2+1 = 4

2E = 2*2 = 4


==================================================
DIRECTED GRAPH TERMS
==================================================

In directed graph:

u ---> v

1. INDEGREE
------------
Number of incoming edges.

2. OUTDEGREE
-------------
Number of outgoing edges.


Example:

1 ---> 2 ---> 3
 \
  ---> 3


Indegree(1) = 0
Outdegree(1) = 2

Indegree(2) = 1
Outdegree(2) = 1

Indegree(3) = 2
Outdegree(3) = 0


==================================================
IMPORTANT FORMULA FOR DIRECTED GRAPH
==================================================

Total Degree =
Total Indegree + Total Outdegree

Also,

Total Indegree = E
Total Outdegree = E

Therefore,

Total Degree = 2E


==================================================
GRAPH REPRESENTATION
==================================================

Graphs are mainly represented in 2 ways:

1. Adjacency Matrix
2. Adjacency List


##################################################
1. ADJACENCY MATRIX
##################################################

- Represented using 2D matrix.
- matrix[i][j] = 1 means edge exists.
- matrix[i][j] = 0 means no edge.


==================================================
SPACE COMPLEXITY
==================================================

O(N^2)

where,
N = number of nodes


==================================================
EXAMPLE
==================================================

Nodes = 3
Edges:
(1,2)
(2,3)

Matrix:

    1 2 3

1 [ 0 1 0 ]
2 [ 1 0 1 ]
3 [ 0 1 0 ]


==================================================
CODE : ADJACENCY MATRIX
==================================================
*/

import java.util.*;

public class basics {

    public static void main(String[] args) {

        int n = 3;

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

##################################################
2. ADJACENCY LIST
##################################################

- Most commonly used representation.
- Stores neighbors of every node.


==================================================
SPACE COMPLEXITY
==================================================

O(2E)

where,
E = number of edges

Reason:
In an undirected graph,
every edge connects 2 nodes.

So both nodes store each other
in the adjacency list.

Example:

1 ---- 2

Stored as:

1 -> 2
2 -> 1

Thus,
1 edge contributes 2 stored entries.

Therefore,
for E edges,
total stored entries = 2E.

Hence,
Space Complexity = O(2E)


==================================================
EXAMPLE
==================================================

Edges:
(1,2)
(2,3)

Adjacency List:

1 -> 2
2 -> 1,3
3 -> 2


==================================================
CODE : ADJACENCY LIST
==================================================
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
TIME COMPLEXITIES
==================================================

1. ADJACENCY MATRIX
-------------------

Add Edge:
O(1)

Check Edge:
O(1)

Space:
O(N^2)


2. ADJACENCY LIST
-----------------

Add Edge:
O(1)

Space:
O(2E)


==================================================
STRIVER IMPORTANT NOTES
==================================================

1. In Competitive Programming,
   mostly use Adjacency List.

2. Adjacency Matrix is useful for:
   - Dense Graphs
   - Quick edge checking

3. Adjacency List is useful for:
   - Sparse Graphs
   - BFS
   - DFS
   - Most graph problems


==================================================
SUMMARY
==================================================

GRAPH:
Collection of nodes and edges.

UNDIRECTED GRAPH:
Edges work both ways.

DIRECTED GRAPH:
Edges work in one direction.

WEIGHTED GRAPH:
Edges contain weights.

ADJACENCY MATRIX:
2D Array Representation.

ADJACENCY LIST:
ArrayList Representation.

IMPORTANT FORMULAS:

Undirected Graph:
Total Degree = 2E

Directed Graph:
Total Degree =
Indegree + Outdegree = 2E

*/
```java
// ==========================================================
// CONNECTED COMPONENTS
// ==========================================================

/*

A connected component is a group of nodes
where every node is reachable from every other node.

In simple words:
All nodes in the component are connected directly
or indirectly.


==================================================
EXAMPLE
==================================================

Graph:

1 ---- 2

3 ---- 4 ---- 5

6

Connected Components:

Component 1:
1, 2

Component 2:
3, 4, 5

Component 3:
6

So,
Number of Connected Components = 3


==================================================
IMPORTANT POINT
==================================================

Connected Components are mainly used in
UNDIRECTED GRAPHS.


==================================================
HOW TO FIND NUMBER OF CONNECTED COMPONENTS?
==================================================

1. Create a visited array.
2. Traverse all nodes.
3. If node is not visited:
      - Start DFS/BFS
      - Mark all reachable nodes
      - Increase component count by 1

Each DFS/BFS traversal gives
one connected component.


==================================================
TIME COMPLEXITY
==================================================

Using DFS/BFS with adjacency list:

O(V + E)

where:
V = number of vertices
E = number of edges


==================================================
CODE : FIND NUMBER OF CONNECTED COMPONENTS
==================================================
*/

import java.util.*;

public class basics {

    static void dfs(int node,
                    ArrayList<ArrayList<Integer>> adj,
                    boolean[] visited) {

        visited[node] = true;

        for(int neighbor : adj.get(node)) {

            if(!visited[neighbor]) {

                dfs(neighbor, adj, visited);
            }
        }
    }

    public static void main(String[] args) {

        int n = 6;

        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<>();


        for(int i = 0; i <= n; i++) {

            adj.add(new ArrayList<>());
        }


        // Component 1
        adj.get(1).add(2);
        adj.get(2).add(1);

        // Component 2
        adj.get(3).add(4);
        adj.get(4).add(3);

        adj.get(4).add(5);
        adj.get(5).add(4);

        // Node 6 is isolated


        boolean[] visited = new boolean[n + 1];

        int components = 0;


        for(int i = 1; i <= n; i++) {

            if(!visited[i]) {

                components++;

                dfs(i, adj, visited);
            }
        }

        System.out.println(
                "Number of Connected Components = "
                + components);
    }
}


/*

==================================================
DRY RUN
==================================================

Start from node 1:
DFS visits 1,2
components = 1

Node 2 already visited.

Start from node 3:
DFS visits 3,4,5
components = 2

Node 4 visited
Node 5 visited

Start from node 6:
DFS visits 6
components = 3

Final Answer:
3


==================================================
IMPORTANT NOTES
==================================================

1. If graph has only 1 connected component,
   graph is called CONNECTED GRAPH.

2. If graph has multiple connected components,
   graph is called DISCONNECTED GRAPH.

3. DFS and BFS both can be used.

*/
```

```
