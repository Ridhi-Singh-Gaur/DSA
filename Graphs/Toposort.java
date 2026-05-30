import java.util.*;

/*
===============================================================================
TOPOLOGICAL SORT
===============================================================================

Definition:
------------
Topological Sorting is a linear ordering of vertices in a Directed Acyclic Graph
(DAG) such that for every directed edge u -> v, u appears before v in the order.

Example:

0 -> 1
|    |
v    v
2 -> 3

Valid Topological Orders:
0 1 2 3
0 2 1 3

Invalid:
1 0 2 3

because 0 must come before 1.

===============================================================================
APPLICATIONS
===============================================================================

1. Course Schedule Problems
2. Task Scheduling
3. Build Systems
4. Dependency Resolution
5. Alien Dictionary
6. Package Installation Order

===============================================================================
IMPORTANT
===============================================================================

Topological Sort is possible ONLY for Directed Acyclic Graphs (DAG).

If a cycle exists:
0 -> 1 -> 2 -> 0

Then Topological Ordering is impossible.

===============================================================================
TIME COMPLEXITY
===============================================================================

BFS (Kahn's Algorithm):
O(V + E)

DFS Topological Sort:
O(V + E)

where:
V = Number of Vertices
E = Number of Edges

===============================================================================
SPACE COMPLEXITY
===============================================================================

BFS:
O(V)

DFS:
O(V)

===============================================================================
METHOD 1 : BFS (KAHN'S ALGORITHM)
===============================================================================

IDEA:
-----

Step 1:
Calculate indegree of every node.

indegree[node] =
number of incoming edges.

Example:

0 -> 1
0 -> 2
1 -> 3
2 -> 3

indegree:

0 = 0
1 = 1
2 = 1
3 = 2

Step 2:
Put all nodes having indegree = 0 in queue.

Queue:
[0]

Step 3:
Remove node from queue.

Step 4:
Reduce indegree of all neighbors.

Step 5:
If neighbor indegree becomes 0,
push it into queue.

Continue until queue becomes empty.

===============================================================================
CYCLE DETECTION USING BFS
===============================================================================

If number of processed nodes != V

then cycle exists.

Reason:
Nodes inside cycle never get indegree = 0.

===============================================================================
*/

class TopologicalSortBFS {

    public static List<Integer> topoSort(int V, List<List<Integer>> adj) {

        // Stores incoming edges count for every node
        int[] indegree = new int[V];

        /*
         * Calculate indegree of each node
         *
         * Example:
         * 0 -> 1
         * 0 -> 2
         *
         * indegree[1]++
         * indegree[2]++
         */
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }

        // Queue will contain nodes having indegree 0
        Queue<Integer> q = new LinkedList<>();

        // Add all nodes with indegree 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // Stores final topological order
        List<Integer> topo = new ArrayList<>();

        // Counts processed nodes
        int count = 0;

        while (!q.isEmpty()) {

            int node = q.poll();

            topo.add(node);
            count++;

            /*
             * Remove current node logically.
             *
             * For every outgoing edge:
             * node -> neighbor
             *
             * decrease indegree.
             */
            for (int neighbor : adj.get(node)) {

                indegree[neighbor]--;

                // New source node found
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        /*
         * Cycle Detection
         *
         * If all nodes are not processed,
         * graph contains cycle.
         */
        if (count != V) {
            System.out.println("Cycle Detected");
        }

        return topo;
    }
}

/*
===============================================================================
METHOD 2 : DFS TOPOLOGICAL SORT
===============================================================================

IDEA:
-----

"Process dependencies first, then current node."

Instead of storing node immediately,
store it AFTER visiting all neighbors.

Example:

0 -> 1 -> 3
|
v
2 -> 3

DFS:

0
|
+---1
|    |
|    3
|
+---2

Push order:

3
1
2
0

Stack:

TOP
0
2
1
3

Pop:

0 2 1 3

This becomes Topological Order.

===============================================================================
WHY PUSH AFTER DFS?
===============================================================================

Suppose:

0 -> 1

1 depends on 0.

DFS reaches 1 first.

When DFS finishes 1,
push 1.

Then return to 0 and push 0.

Stack:

1
0

Pop:

0 1

Correct order.

===============================================================================
*/

class TopologicalSortDFS {

    private static void dfs(
            int node,
            List<List<Integer>> adj,
            boolean[] visited,
            Stack<Integer> stack) {

        // Mark current node visited
        visited[node] = true;

        // Visit all neighbors
        for (int neighbor : adj.get(node)) {

            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, stack);
            }
        }

        /*
         * IMPORTANT:
         *
         * Push after processing all neighbors.
         *
         * This ensures dependencies appear first.
         */
        stack.push(node);
    }

    public static List<Integer> topoSort(int V, List<List<Integer>> adj) {

        boolean[] visited = new boolean[V];

        // Stores reverse topological order
        Stack<Integer> stack = new Stack<>();

        // Run DFS from every unvisited node
        for (int i = 0; i < V; i++) {

            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        // Final topological order
        List<Integer> topo = new ArrayList<>();

        // Reverse stack order
        while (!stack.isEmpty()) {
            topo.add(stack.pop());
        }

        return topo;
    }
}

/*
===============================================================================
DFS CYCLE DETECTION
===============================================================================

Use 3 states:

0 = Unvisited
1 = Visiting (currently in recursion stack)
2 = Visited

If during DFS:

current node ---> neighbor

and neighbor state = 1

then cycle exists.

Example:

0 -> 1
^    |
|    v
3 <- 2

DFS reaches node already present in recursion stack.

This forms a Back Edge.

Back Edge => Cycle.

===============================================================================
DFS CYCLE DETECTION CODE
===============================================================================

boolean dfs(int node){

    state[node] = 1;

    for(int neighbor : adj.get(node)){

        if(state[neighbor] == 1)
            return true;

        if(state[neighbor] == 0 &&
           dfs(neighbor))
            return true;
    }

    state[node] = 2;

    return false;
}

===============================================================================
INTERVIEW QUICK REVISION
===============================================================================

Topological Sort:
-----------------
Ordering of vertices such that
for every edge u -> v,
u appears before v.

Works only on DAG.

BFS (Kahn's):
-------------
1. Calculate indegree.
2. Push indegree 0 nodes.
3. Pop node.
4. Reduce neighbor indegree.
5. If indegree becomes 0, push.

Cycle:
topo.size() != V

TC: O(V + E)
SC: O(V)

DFS:
----
1. DFS all nodes.
2. Push node after visiting neighbors.
3. Reverse stack.

TC: O(V + E)
SC: O(V)

===============================================================================
*/
