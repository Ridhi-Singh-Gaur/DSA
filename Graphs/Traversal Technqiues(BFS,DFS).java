// ==========================================================
// BFS (Breadth First Search)
// DFS (Depth First Search)
// ==========================================================

/*

##################################################
1. BFS (Breadth First Search)
##################################################

BFS traverses graph level by level.

It first visits all neighbors of current node,
then moves to next level.

BFS uses:
QUEUE (FIFO)


==================================================
EXAMPLE
==================================================

Graph:

        1
      /   \
     2     3
    / \
   4   5


BFS Traversal from node 1:

1 2 3 4 5


==================================================
STEPS OF BFS
==================================================

1. Put starting node into queue.
2. Mark it visited.
3. Remove node from queue.
4. Visit all unvisited neighbors.
5. Repeat until queue becomes empty.


==================================================
TIME COMPLEXITY
==================================================

O(V + 2* E)
OUTER FOR LOOP RUNS FOR NO OF VERTICES, AS EVERY NODE IS ADDED ONCE IN THE QUEUE,
INNER FOR LOOP RUNS FOR NEIGHBOURS OF A PARTICULAR VERTEX, SO IT RUNS FOR DEGREE OF GRAPH
SO V DENOTES NO OF VERTICES (OUTER WHILE LOOP),        2*E IS SUMMATION OF ALL DEGREES IN CASE OF UNDIRECTED GRAPH AND ITS FOR INNER FOR LOOP
where:
V = number of vertices
E = number of edges

Reason:
- Every node is visited once.
- Every edge is traversed once.


==================================================
SPACE COMPLEXITY
==================================================

O(V)

where:
V = number of vertices

Reason:
BFS uses:

1. Queue
   -> In worst case,
      queue can contain all nodes.

2. Visited Array
   -> Stores visited status for every node.

Therefore:

Space Complexity = O(V)


==================================================
CODE : BFS
==================================================
*/

import java.util.*;

public class basics {

    public static void bfs(int start,
                           ArrayList<ArrayList<Integer>> adj,
                           boolean[] visited) {

        Queue<Integer> q = new LinkedList<>();

        q.offer(start);

        visited[start] = true;

        while(!q.isEmpty()) {//OUTER WHILE LOOP

            int node = q.poll();

            System.out.print(node + " ");

            for(int neighbor : adj.get(node)) {//INNER FOR LOOP

                if(!visited[neighbor]) {

                    visited[neighbor] = true;

                    q.offer(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {

        int n = 5;

        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<>();


        for(int i = 0; i <= n; i++) {

            adj.add(new ArrayList<>());
        }


        // Edges
        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(1).add(3);
        adj.get(3).add(1);

        adj.get(2).add(4);
        adj.get(4).add(2);

        adj.get(2).add(5);
        adj.get(5).add(2);


        boolean[] visited = new boolean[n + 1];

        System.out.println("BFS Traversal:");

        bfs(1, adj, visited);
    }
}


/*

==================================================
OUTPUT
==================================================

1 2 3 4 5


==================================================
IMPORTANT POINTS
==================================================

1. BFS uses QUEUE.

2. Traversal happens level-wise.

3. BFS gives shortest path
   in unweighted graph.


##################################################
##################################################
2. DFS (Depth First Search)
##################################################
##################################################

DFS goes deep first.

It visits one path completely,
then backtracks.

DFS uses:
RECURSION or STACK


==================================================
EXAMPLE
==================================================

Graph:

        1
      /   \
     2     3
    / \
   4   5


Possible DFS Traversal:

1 2 4 5 3


==================================================
STEPS OF DFS
==================================================

1. Visit node.
2. Mark visited.
3. Visit all unvisited neighbors recursively.


==================================================
TIME COMPLEXITY
==================================================

O(V + 2*E)
DFS FUNC RUNS FOR NO OF VERTICES, AS EVERY NODE IS ADDED PASSED AS PARAMETER IN DFS FUNC,
INNER FOR LOOP RUNS FOR NEIGHBOURS OF A PARTICULAR VERTEX, SO IT RUNS FOR DEGREE OF GRAPH
SO V DENOTES NO OF VERTICES (OUTER WHILE LOOP),        2*E IS SUMMATION OF ALL DEGREES IN CASE OF UNDIRECTED GRAPH AND ITS FOR INNER FOR LOOP
where:
V = number of vertices
E = number of edges

Reason:
- Every node is visited once.
- Every edge is traversed once.


==================================================
SPACE COMPLEXITY
==================================================

O(V)

where:
V = number of vertices

Reason:
DFS uses:

1. Recursion Stack
   -> In worst case,
      recursion can go till all nodes.

2. Visited Array
   -> Stores visited status for every node.

Therefore:

Space Complexity = O(V)


==================================================
CODE : DFS
==================================================
*/

import java.util.*;

public class basics {

    public static void dfs(int node,
                           ArrayList<ArrayList<Integer>> adj,
                           boolean[] visited) {

        visited[node] = true;

        System.out.print(node + " ");

        for(int neighbor : adj.get(node)) {//INNER FOR LOOP

            if(!visited[neighbor]) {

                dfs(neighbor, adj, visited);
            }
        }
    }

    public static void main(String[] args) {

        int n = 5;

        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<>();


        for(int i = 0; i <= n; i++) {

            adj.add(new ArrayList<>());
        }


        // Edges
        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(1).add(3);
        adj.get(3).add(1);

        adj.get(2).add(4);
        adj.get(4).add(2);

        adj.get(2).add(5);
        adj.get(5).add(2);


        boolean[] visited = new boolean[n + 1];

        System.out.println("DFS Traversal:");

        dfs(1, adj, visited);
    }
}


/*

==================================================
OUTPUT
==================================================

1 2 4 5 3


==================================================
IMPORTANT POINTS
==================================================

1. DFS uses RECURSION/STACK.

2. DFS goes deep first.

3. Used in:
   - Connected Components
   - Cycle Detection
   - Topological Sort
   - Bridges
   - Graph Traversal


==================================================
OVERALL GRAPH STORAGE SPACE
==================================================

Adjacency List Space:

O(2E)

Reason:
In undirected graph,
every edge is stored twice.

Example:

1 ---- 2

Stored as:

1 -> 2
2 -> 1

So,
1 edge contributes 2 stored entries.

Therefore,
for E edges:

Total storage = 2E


==================================================
TOTAL SPACE INCLUDING GRAPH + BFS/DFS
==================================================

Adjacency List:
O(2E)

Visited Array:
O(V)

Queue / Recursion Stack:
O(V)

Overall:

O(V + 2E)

which is commonly written as:

O(V + E)


==================================================
BFS vs DFS
==================================================

BFS:
-----
- Uses Queue
- Level-wise traversal
- Finds shortest path
- More memory sometimes

DFS:
-----
- Uses Recursion/Stack
- Goes deep first
- Good for backtracking
- Simpler recursive code

*/
