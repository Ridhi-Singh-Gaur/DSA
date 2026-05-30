/*
=========================================================
                    DIJKSTRA ALGORITHM
=========================================================

Definition:
------------
Dijkstra Algorithm is used to find the Shortest Distance
from a source node to all other nodes in a weighted graph.

Condition:
----------
All edge weights must be NON-NEGATIVE.

If graph contains negative weights,
Dijkstra may give incorrect answers.

Use Bellman Ford instead.

---------------------------------------------------------
What does it find?
---------------------------------------------------------

Shortest distance from Source -> Every Vertex

Example:

        4
    0 ------> 1
    |         |
  1 |         | 2
    v         v
    2 ------> 3
         5

Source = 0

Shortest Distances:

0 -> 0 = 0
0 -> 1 = 4
0 -> 2 = 1
0 -> 3 = 6

---------------------------------------------------------
Core Idea
---------------------------------------------------------

Always process the node having the
minimum known distance from source.

Once a smaller distance is found,
update the neighbor.

This process is called RELAXATION.

---------------------------------------------------------
Relaxation
---------------------------------------------------------

Suppose:

dist[u] = 5

Edge:

u ----(3)----> v

Current:

dist[v] = 20

New Possible Distance:

dist[u] + weight
= 5 + 3
= 8

Since:

8 < 20

Update:

dist[v] = 8

---------------------------------------------------------
Data Structure Used
---------------------------------------------------------

Priority Queue (Min Heap)

Stores:

(distance, node)

Reason:
-------

Always need the node having
minimum distance first.

Priority Queue gives:

Insertion:
O(log V)

Removal:
O(log V)

=========================================================
STEP BY STEP WORKING
=========================================================

Example:

0 --4--> 1
|
1
|
v
2 --2--> 1

Source = 0

---------------------------------------------------------
Step 1
---------------------------------------------------------

Distance Array:

[0, INF, INF]

Push:

(0,0)

PQ:

[(0,0)]

---------------------------------------------------------
Step 2
---------------------------------------------------------

Remove minimum

Node = 0

Process neighbors

For node 1:

0 + 4 = 4

dist[1] = 4

For node 2:

0 + 1 = 1

dist[2] = 1

PQ:

[(1,2),(4,1)]

---------------------------------------------------------
Step 3
---------------------------------------------------------

Remove minimum

Node = 2

Process neighbor 1

1 + 2 = 3

Current dist[1] = 4

3 < 4

Update:

dist[1] = 3

PQ:

[(3,1),(4,1)]

---------------------------------------------------------
Step 4
---------------------------------------------------------

Remove minimum

Node = 1

Done.

Final Distance:

[0,3,1]

=========================================================
TIME COMPLEXITY
=========================================================

Every edge may cause a PQ insertion.

Priority Queue Operations:

O(log V)

For E edges:

O(E log V)

More commonly written as:

O((V + E) log V)

=========================================================
SPACE COMPLEXITY
=========================================================

Distance Array:
O(V)

Priority Queue:
O(V)

Adjacency List:
O(V + E)

Extra Space:

O(V)

=========================================================
DIJKSTRA CODE
=========================================================
*/

import java.util.*;

class Dijkstra {

    static class Pair {

        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public int[] dijkstra(int V,
                          ArrayList<ArrayList<ArrayList<Integer>>> adj,
                          int src) {

        // Distance array
        int[] dist = new int[V];

        // Initially all distances are infinite
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Distance of source from itself is 0
        dist[src] = 0;

        // Min Heap based on distance
        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> a.dist - b.dist);

        // Push source
        pq.offer(new Pair(src, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            int node = curr.node;
            int currDist = curr.dist;

            /*
             Important Optimization

             If this distance is not the latest
             shortest distance, skip it.

             Prevents unnecessary processing.
            */
            if (currDist > dist[node]) {
                continue;
            }

            // Process all neighbors
            for (ArrayList<Integer> edge : adj.get(node)) {

                int neighbor = edge.get(0);
                int weight = edge.get(1);

                /*
                 Relaxation

                 If shorter path found,
                 update distance.
                */
                if (currDist + weight < dist[neighbor]) {

                    dist[neighbor] =
                            currDist + weight;

                    pq.offer(
                            new Pair(
                                    neighbor,
                                    dist[neighbor]
                            )
                    );
                }
            }
        }

        return dist;
    }
}

/*
=========================================================
INTERVIEW QUESTIONS
=========================================================

Q1. Why Priority Queue?

Because we always need the node with
minimum distance first.

---------------------------------------------------------

Q2. Why doesn't Dijkstra work for
negative weights?

Because once a node is processed,
Dijkstra assumes its shortest distance
is finalized.

Negative edges can later produce
a smaller distance.

Hence incorrect answer.

---------------------------------------------------------

Q3. Difference Between BFS and Dijkstra?

BFS:
----
Used for unweighted graphs.

Every edge weight = 1.

TC = O(V + E)

Dijkstra:
---------
Used for weighted graphs.

TC = O((V + E) log V)

---------------------------------------------------------

Q4. Can Dijkstra detect cycle?

No.

It is only a shortest path algorithm.

---------------------------------------------------------

Q5. Single Source or Multi Source?

Dijkstra is a:

Single Source Shortest Path Algorithm

=========================================================
REVISION
=========================================================

Purpose:
--------
Find shortest distance from source
to all vertices.

Works On:
---------
Weighted Graph

Condition:
----------
No Negative Edge

Data Structure:
---------------
Priority Queue (Min Heap)

Relaxation:
-----------
if(dist[u] + wt < dist[v])

    dist[v] = dist[u] + wt

Complexity:
-----------
TC = O((V + E) log V)

SC = O(V)

Applications:
-------------
1. Google Maps
2. Network Routing
3. Flight Paths
4. GPS Navigation
5. Shortest Path Problems

=========================================================
*/
