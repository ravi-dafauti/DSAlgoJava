package graph;

import disjointset.DisjointSet;

import javax.print.DocFlavor;
import java.nio.file.LinkOption;
import java.util.*;

public class Problems {

// 1. TOPIC TOPOLOGICAL SORT :

    //kahn algorithm, can handle cycles as well
    public static void topologicalSort(Graph graph) {
        int[] inDegree = new int[graph.getVertices()];
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < graph.getVertices(); i++) {
            for(int j : graph.getEdgesMapping()[i]) {
                inDegree[j]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < graph.getVertices(); i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            result.add(v);
            count++;
            for(int k : graph.getEdgesMapping()[v]) {
                if(--inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }

        if(count != graph.getVertices()) {
            System.out.println("Not a DAG, there exists a cyclic");
        } else {
            for(int i : result) {
                System.out.print(i + " ");
            }
        }
    }


    //topogical sort using DFS, cannot handle cycle

    public static void topologicalSortDfs(Graph graph) {
        Stack<Integer> s = new Stack<>();
        int[] visited = new int[graph.getVertices()];

        for(int i = 0; i < graph.getVertices(); i++) {
            if(visited[i] == 0) {
                topologicalSortDfsUtil(graph, s, i, visited);
            }
        }

        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }



    private static void topologicalSortDfsUtil(Graph graph, Stack<Integer> stack, int u, int[] visited) {
        visited[u] = 1;
        for(int i : graph.getEdgesMapping()[u]) {
            if(visited[i] == 0) {
                topologicalSortDfsUtil(graph,stack,i,visited);
            }
        }
        stack.push(u);
    }

    // to find all topogical sort orders

    public static void findAllTopologicalSorts(Graph graph) {
        int[] inDegree = new int[graph.getVertices()];
        for(int i = 0; i < graph.getVertices(); i++) {
            for(int k : graph.getEdgesMapping()[i]) {
                inDegree[k]++;
            }
        }
        boolean[] visited = new boolean[graph.getVertices()];
        List<Integer> res = new ArrayList<>();
        findAllTopologicalSortsUtil(graph, inDegree, res, visited);
    }

    private static void findAllTopologicalSortsUtil(Graph graph, int[] inDegree, List<Integer> res, boolean[] visited) {


        for(int i = 0; i < graph.getVertices(); i++) {
            if(visited[i] == false && inDegree[i] == 0) {
                visited[i] = true;
                res.add(i);
                for(int k : graph.getEdgesMapping()[i]) {
                    inDegree[k]--;
                }
                findAllTopologicalSortsUtil(graph,inDegree,res,visited);
                visited[i] = false;
                res.remove(res.size() - 1);
                for(int k : graph.getEdgesMapping()[i]) {
                    inDegree[k]++;
                }
            }
        }

        if(res.size() == graph.getVertices()) {
            for(int i : res) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }

    }

    // do leetcode Alien dictonary.


    // TOPIC : SINGLE source shortest path Algo

    //shortest path in unweighted graph for both directed and undirected

    public static void shortestPathUnweighted(Graph graph, int u) {
        int[] distance = new int[graph.getVertices()];
        int[] parent = new int[graph.getVertices()];
        for(int i = 0; i < distance.length; i++) {
            distance[i] = -1;
        }
        distance[u] = 0;
        parent[u] = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for(int k : graph.getEdgesMapping()[v]) {
                if(distance[k] == -1) {
                    distance[k] = distance[v] + 1;
                    parent[k] = v;
                    queue.add(k);
                }
            }
        }

        for(int i = 0; i < distance.length; i++) {
            System.out.println(u + "->" + i +" : " +  distance[i]);
        }

        for(int i = 0; i < distance.length; i++) {
            System.out.println(i +" : " +  parent[i]);
        }
    }

    // Now for weighted graph we have 2 algo dijktra and bellman ford.
    // They work for both directed and undirected
    // dijktras does not works for negative weight and bellman ford does works for negative weights
    // in case of negative weight undirected graph always result in negative weight cycle.
    //shortest path bellman ford O(EV)

    public static void bellmanFord(GraphWeighted graph, int source) {


        int[] distance = new int[graph.getVertices()];
        int[] parent = new int[graph.getVertices()];
        for(int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[source] = 0;

        class Edge {
            int u;
            int v;
            int w;

            Edge(int u, int v, int w) {
                this.u = u;
                this.v = v;
                this.w = w;
            }
        }
        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i < graph.getVertices(); i++) {
            for(GraphWeighted.Edge k : graph.getEdgesMapping()[i]) {
                edges.add(new Edge(i, k.v, k.w));
            }
        }

        for(int i = 0; i< graph.getVertices() - 1; i++) {
            for(Edge edge : edges) {
                if(distance[edge.u] != Integer.MAX_VALUE) {
                    if(distance[edge.u] + edge.w < distance[edge.v]) {
                        distance[edge.v] = distance[edge.u] + edge.w;
                        parent[edge.v] = edge.u;
                    }
                }
            }
        }

        boolean isCycle = false;
        for(Edge edge : edges) {
            if(distance[edge.u] != Integer.MAX_VALUE) {
                if(distance[edge.u] + edge.w < distance[edge.v]) {
                    isCycle = true;
                    break;
                }
            }
        }

        if(isCycle) {
            System.out.println("There is a negative weight cycle");
        } else {
            for(int i = 0; i < distance.length; i++) {
                System.out.println(i + " " + distance[i]);
            }
        }


    }


    //dijkstra algo V^2 (Better for dense graph else go for ElogV , ElogV is not good for dense graph as E ~ V ^2 and it will be V^2logV )

    public static void dijkstra(int graph[][], int src, int V) {
        int[] distance = new int[V];
        int[] visited = new int[V];
        int[] parent = new int[V];
        for(int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        distance[src] = 0;

        for(int i = 0; i < V; i++) {
            int u = getMinimum(distance, visited);
            visited[u] = 1;
            for(int v = 0; v < V; v++) {
                if(visited[v] == 0 && graph[u][v] != 0 && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        for(int i = 0; i < V; i++) {
            System.out.println(i + " " + distance[i]);
        }

    }

    //gets the vertex with minimum distance
    private static int getMinimum(int[] dis, int[] vis) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0; i < dis.length; i++ ) {
            if(vis[i] == 0 && dis[i] != Integer.MAX_VALUE && dis[i] < min) {
                min = dis[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    //dijkstra algo ElogV (if graph is dense it will be V^2logV as E ~V^2)
    public static void dijkstra(GraphWeighted graph, int source) {
        int[] distance = new int[graph.getVertices()];
        int[] parent = new int[graph.getVertices()];
        int[] visited = new int[graph.getVertices()];
        for(int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        class Node {
            int key;
            int vertex;
            Node(int k, int v) {
                this.key = k;
                this.vertex = v;
            }
        }
        TreeSet<Node> map = new TreeSet<Node>((a,b) -> {
           return a.key != b.key ? a.key - b.key : a.vertex - b.vertex;
        });

        map.add(new Node(0, source));
        distance[source] = 0;

        while (!map.isEmpty()) {
            Node node = map.pollFirst();
            visited[node.vertex] = 1;
            for(GraphWeighted.Edge v : graph.getEdgesMapping()[node.vertex]) {
                if(visited[v.v] == 0 && distance[node.vertex] + v.w < distance[v.v]) {
                    distance[v.v] = distance[node.vertex] + v.w;
                    parent[v.v] = node.vertex;
                    map.add(new Node(distance[node.vertex] + v.w, v.v));
                }
            }
        }
        for(int i = 0; i < distance.length; i++) {
            System.out.println(i + " " + distance[i]);
        }

    }

        /*
    The Floyd Warshall Algorithm is for solving all pairs shortest path problems. The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed Graph.
     */

        // both directed and undireted with negative weights too but will not worl for negative weight cycle.

    //O(V^3)

    public static void floydWarshall(int graph[][]) {

        int[][] dp = new int[graph.length][graph.length];
        for(int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                dp[i][j] = graph[i][j];
            }
        }
        for(int k = 0; k < graph.length; k++) {
            for(int i = 0; i < graph.length; i++) {
                for(int j = 0; j < graph.length; j++){
                    if(dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE && dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        printSolution(dp);
    }

    private static void printSolution(int dist[][])
    {
        System.out.println(
                "The following matrix shows the shortest "
                        + "distances between every pair of vertices");
        for (int i = 0; i < dist.length; ++i) {
            for (int j = 0; j < dist.length; ++j) {
                if (dist[i][j] == Integer.MAX_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }


    // TOPIC MINIMUM SPANNING TREE
    /*
    A minimum spanning tree (MST) or minimum weight spanning tree is a subset of the edges of a connected,
    edge-weighted undirected graph that connects all the vertices together,
    without any cycles and with the minimum possible total edge weight.
     */
    // only for undirected graphs
    /*
    Both Prim's and Kruskal's algorithm work with negative edge weights.
    It is because the correctness of the algorithm does not depend on the weights being positive.
     */


    // O(ELogV)
    public static void prims(GraphWeighted graph) {

        int[] distance = new int[graph.getVertices()];
        int[] parent = new int[graph.getVertices()];
        int[] visited = new int[graph.getVertices()];
        for(int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        class Node {
            int key;
            int vertex;
            Node(int k, int v) {
                this.key = k;
                this.vertex = v;
            }
        }
        TreeSet<Node> map = new TreeSet<Node>((a,b) -> {
            return a.key != b.key ? a.key - b.key : a.vertex - b.vertex;
        });

        map.add(new Node(0, 0));
        distance[0] = 0;

        while (!map.isEmpty()) {
            Node node = map.pollFirst();
            visited[node.vertex] = 1;
            for(GraphWeighted.Edge v : graph.getEdgesMapping()[node.vertex]) {
                if(visited[v.v] == 0 && v.w < distance[v.v]) {
                    distance[v.v] =  v.w;
                    parent[v.v] = node.vertex;
                    map.add(new Node(v.w, v.v));
                }
            }
        }

        for (int o = 1; o < graph.getVertices(); o++)
            System.out.println(parent[o] + " "
                    + "-"
                    + " " + o);

    }

//O(ElogE + EV) --> we are considoring disjoint set find to take V as can be skew tree.
    public static void kruskal(GraphWeighted graph) throws Exception {

        DisjointSet disjointSet = new DisjointSet(graph.getVertices());
        class Edge {
            int u;
            int v;
            int w;

            Edge(int u, int v, int w) {
                this.u = u;
                this.v = v;
                this.w = w;
            }
        }
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i< graph.getVertices(); i++) {
            for(GraphWeighted.Edge edge : graph.getEdgesMapping()[i]) {
                edges.add(new Edge(i, edge.v, edge.w));
            }
        }

        Collections.sort(edges, (a,b) -> {
            return a.w - b.w;
        });

        for(Edge edge : edges) {
            if(disjointSet.find(edge.u) != disjointSet.find(edge.v)) {
                System.out.println(edge.u + "->"  + edge.v);
                disjointSet.union(edge.u, edge.v);
            }
        }
    }

    static int  count = 0;
    public static int countSimplePathInGraph(int[][] graph, int v, int s, int d) {
        boolean[] visited = new boolean[v];
        countSimplePathInGraphUtil(graph, v, s, d, visited);
        return count;
    }

    public static void countSimplePathInGraphUtil(int[][] graph, int v, int s, int d, boolean[] visited) {
        visited[s] = true;

        if(s == d) {
            count++;
            visited[s] = false;
            return;
        }

        for(int i = 0; i < v; i++) {
            if(graph[s][i] == 1 && visited[i] == false) {
                countSimplePathInGraphUtil(graph, v, i, d, visited);
            }
        }
        visited[s] = false;
    }


    // Articulation points
    static int time = 0;

    public static void articulationPoints(Graph graph) {

        int[] parent = new int[graph.getVertices()];
        int[] visited = new int[graph.getVertices()];
        int[] discoverTime = new int [graph.getVertices()];
        int[] low = new int[graph.getVertices()];
        for(int i : parent) {
            parent[i] = -1;
        }

        for(int i = 0; i< graph.getVertices(); i++) {
            if(visited[i] == 0) {
                printArticulationPoints(graph, parent, visited, discoverTime, low, i);
            }
        }

    }

    public static void printArticulationPoints(Graph graph,int[] parent,int[] visited,int[] discoverTime, int[] low, int u) {
        visited[u] = 1;
        discoverTime[u] = time;
        low[u] = time;
        time++;
        int childCount = 0;
        boolean isArticulationPoint = false;
        for(Integer v : graph.getEdgesMapping()[u]) {
            if(parent[u] == v)
                continue;
            if(visited[v] == 0) {
                parent[v] = u;
                childCount++;
                printArticulationPoints(graph, parent, visited, discoverTime, low, v);
                if(low[v] >= discoverTime[u]) {
                    isArticulationPoint = true;
                }
                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], discoverTime[v]);
            }
        }

        if((parent[u] == -1 && childCount >= 2) || (parent[u] != -1 && isArticulationPoint)) {
            System.out.println(u);
        }
    }



    // cut bridges


    public static void bridges(Graph graph) {

        int[] visited = new int[graph.getVertices()];
        int[] discoverTime = new int [graph.getVertices()];
        int[] low = new int[graph.getVertices()];

        for(int i = 0; i< graph.getVertices(); i++) {
            if(visited[i] == 0) {
                bridgesUtils(graph, visited, discoverTime, low, i, -1);
            }
        }

    }

    public static void bridgesUtils(Graph graph,int[] visited,int[] discoverTime, int[] low, int u, int parent) {
        visited[u] = 1;
        discoverTime[u] = time;
        low[u] = time;
        time++;
        for(Integer v : graph.getEdgesMapping()[u]) {
            if(parent == v)
                continue;
            if(visited[v] == 0) {
                bridgesUtils(graph, visited, discoverTime, low, v, u);
                if(low[v] > discoverTime[u]) {
                    System.out.println(u + "->"  + v);
                }
                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], discoverTime[v]);
            }
        }

    }


    //TOPIC  : strongly connected directed graph, an undirected graph is always strongly connected.


    // check DG for Strongly connected
    public static boolean checkIfGraphIsStronglyConnected(Graph graph) {

        int[] visited = new int[graph.getVertices()];
        dfs(graph, 0, visited);

        for(int i : visited) {
            if(i == 0)
                return false;
        }

        for(int i = 0; i < visited.length; i++)
            visited[i] = 0;
        Graph reverse = reverseGraph(graph);
        dfs(reverse, 0, visited);
        for(int i : visited) {
            if(i == 0) {
                return false;
            }
        }
        return true;
    }

    public static Graph reverseGraph(Graph graph) {
        Graph reverseGraph = new Graph(graph.getVertices());
        for(int u = 0; u < graph.getVertices(); u++) {
            for(Integer v : graph.getEdgesMapping()[u]) {
                reverseGraph.addEdge(v, u);
            }
        }
        return reverseGraph;
    }

    private static void dfs(Graph graph, int v, int[] visited) {
        visited[v] = 1;
        for(int i : graph.getEdgesMapping()[v]) {
            if(visited[i] == 0) {
                dfs(graph, i, visited);
            }
        }
    }

    private static void dfsPrint(Graph graph, int v, int[] visited) {
        visited[v] = 1;
        System.out.print(v + " ");
        for(int i : graph.getEdgesMapping()[v]) {
            if(visited[i] == 0) {
                dfsPrint(graph, i, visited);
            }
        }
    }

    private static void dfs(Graph graph, int u, int[] visited, Stack<Integer> stack) {
        visited[u] = 1;
        for(int i : graph.getEdgesMapping()[u]) {
            if(visited[i] == 0) {
                dfs(graph, i, visited, stack);
            }
        }
        stack.push(u);
    }

    // Find all strongly connected components in DG

    public static void findStronglyConnectedComponentsInConnectedDG(Graph graph) {

        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[graph.getVertices()];
        dfs(graph, 0, visited, stack);
        Graph reverse = reverseGraph(graph);

        for(int i = 0; i < visited.length; i++)
            visited[i] = 0;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if(visited[u] == 0) {
                dfsPrint(reverse, u, visited);
                System.out.println();
            }
        }
    }


    //TOPIC DETECT CYCLE in directed an undirected graph


    // detect cycle in undirected graph

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] visited = new int[V];
        for(int i = 0; i < V; i++) {
            if(visited[i] == 0 && isCycleUtil(V, adj, i, -1, visited))
                return true;
        }

        return false;
    }

    public boolean isCycleUtil(int V, ArrayList<ArrayList<Integer>> adj, int u, int parent, int[] visited) {

        visited[u] = 1;
        for(int v : adj.get(u)) {
            if(visited[v] == 0) {
                if(isCycleUtil(V, adj, v, u, visited))
                    return true;
            }
            else {
                if(parent != v)
                    return true;
            }
        }
        return false;
    }



    // detect cycle in directed graph

    public static boolean isDgCyclic(Graph graph) {

        int[] visited = new int[graph.getVertices()];
        int[] recursionStack = new int[graph.getVertices()];

        for(int i = 0; i < graph.getVertices(); i++) {
            if(visited[i] == 0) {
                return isDgCyclicUtil(graph, visited, recursionStack, i);
            }
        }
        return false;
    }

    private static boolean isDgCyclicUtil(Graph graph, int[] visited, int[] recursionStack, int u) {
        recursionStack[u] = 1;
        visited[u] = 1;
        for(Integer v : graph.getEdgesMapping()[u]) {
            if(visited[v] == 0) {
                if(isDgCyclicUtil(graph, visited, recursionStack, v) == true)
                    return true;
            } else {
                if(recursionStack[v] == 1)
                    return true;
            }
        }
        recursionStack[u] = 0;
        return false;
    }





    //TOPIC : hamitonian path and cycle, see the code in outer circle.
    // it is NP complete, for UG and DG use n! method and for DG we can use approximation algo that is topolocical sort
    // and check if hamil path exists, do topological using dfs.


    //TOPIC  : eular path and circuit. NOTE this problem is not NP complete and can be solved in polynomial time.
    //TO CHECK IF graph is eularian :
    // undiretced : connected and all vertex have even degree or exactly 2 vertex with odd degree.
    // directed : strongly connected and all vertex have same in and out degree.


    // TOPIC bipartite.


    // Find maximum bipartite matching M * N

    public static int maxMatching(boolean bpGraph[][], int M, int N) {

        int[] match = new int[N];
        for(int i = 0; i < N; i++) {
            match[i] = -1;
        }

        int matchCount = 0;
        for(int u = 0; u < M; u++) {
            boolean[] seen = new boolean[N];
            if(bipartiteMatching(bpGraph, M, N, match, seen, u)) {
                matchCount++;
            }

        }
        return matchCount;
    }

    private static boolean bipartiteMatching(boolean bpGraph[][], int M, int N, int[] match, boolean[] seen, int u) {
        for(int v = 0; v < N; v++) {
            if(bpGraph[u][v] && seen[v] == false) {
                seen[v] = true;
                if(match[v] == -1 || bipartiteMatching(bpGraph, M, N, match, seen, match[v])) {
                    match[v] = u;
                    return true;
                }
                seen[v] = false;
            }
        }
        return false;
    }


    // check if Graph is Bipartite

    public static boolean isBipartite(Graph graph) {

        int[] color = new int[graph.getVertices()];
        for(int i = 0; i< color.length; i++)
            color[i] = -1;

        for(int u = 0; u < graph.getVertices(); u++) {
            if(color[u] == -1) {
                if(isBipartiteUtil(graph, color, u) == false)
                return false;
            }
        }
        return true;
    }

    private static boolean isBipartiteUtil(Graph graph, int[] color, int u) {

        color[u] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for(Integer i : graph.getEdgesMapping()[v]) {
                if(color[i] == -1) {
                    color[i] = 1 - color[v];
                    queue.add(i);
                } else {
                    if(color[i] == color[v])
                        return false;
                }
            }
        }
        return true;
    }



    // check bipartite DFS

    static boolean colorGraph(int G[][],
                              int V,
                              int color[],
                              int pos, int c)
    {
        if (color[pos] != -1 && color[pos] != c)
            return false;

        // color this pos as c and
        // all its neighbours as 1-c
        color[pos] = c;
        for (int i = 0; i < V; i++)
        {
            if (G[pos][i] == 1)
            {
                if (color[i] == -1)
                    if( colorGraph(G, V, color, i, 1 - c) == false) {
                        return false;
                    }

                if (color[i] != -1 && color[i] != 1 - c)
                    return false;
            }
        }
        return true;
    }

    public static boolean isBipartite(int G[][], int V)
    {
        int[] color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = -1;
        // two colors 1 and 0

        for(int i = 0; i <  V; i++) {
            if(color[i] == -1) {
                if(colorGraph(G, V, color, i, 1) == false)
                    return false;
            }
        }
        return true;
    }



    // M coloring problem

    //Given an undirected graph and a number m, determine if the graph can be coloured with at most m colours such that no two adjacent vertices of the graph are colored with the same color. Here coloring of a graph means the assignment of colors to all vertices.

    public static boolean graphColoring(int graph[][], int V, int m) {

        int[] color = new int[V];
        for(int i = 0; i < V; i++)
            color[i] = -1;

        if(graphColoringM(graph, V, m,0, color))
            return true;

        return false;
    }

    private static boolean graphColoringM(int graph[][], int V, int m, int u, int[] color) {
        if(u == V)
            return true;
        for(int i = 0; i < m; i++) {
            if(isSafe(graph, V, u, color, i)) {
                color[u] = i;
                if(graphColoringM(graph, V, m, u + 1, color)) {
                    return true;
                }
                color[u] = -1;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] graph, int V, int u, int[] color, int c) {

        for(int i = 0; i < V; i++) {
            if(graph[u][i] == 1 && color[i] == c)
                return false;
        }
        return true;
    }

    // time complexity(O(E + V) best solution

    public static boolean mColorBFS(Graph graph, int m) {
        int[] color = new int[graph.getVertices()];
        int[] visited = new int[graph.getVertices()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited[u] = 1;
            for (Integer v : graph.getEdgesMapping()[u]) {
                if(color[v] == color[u]) {
                    color[v]++;
                    if(color[v] >= m)
                        return false;
                }
                if(visited[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return true;
    }



    /*

    there is no efficient algorithm available for coloring a graph with minimum number of colors as the problem is a known NP Complete problem. There are approximate algorithms to solve the problem though. Following is the basic Greedy Algorithm to assign colors. It doesn’t guarantee to use minimum colors, but it guarantees an upper bound on the number of colors. The basic algorithm never uses more than d+1 colors where d is the maximum degree of a vertex in the given graph.

        Basic Greedy Coloring Algorithm:

        1. Color first vertex with first color.
        2. Do following for remaining V-1 vertices.
        ….. a) Consider the currently picked vertex and color it with the
        lowest numbered color that has not been used on any previously
        colored vertices adjacent to it. If all previously used colors
        appear on vertices adjacent to v, assign a new color to it.


        the order in which the vertices are picked is important. Many people have suggested different ways to find an ordering that work better than the basic algorithm on average. The most common is Welsh–Powell Algorithm which considers vertices in descending order of degrees.

        algorithm guarantee an upper bound of d+1, d is max degree of a vertex in graph
     */


    public static void colorGraphMinimum(Graph graph) {
        int[] color = new int[graph.getVertices()];
        for(int i = 0; i < graph.getVertices(); i++)
            color[i] = -1;
        boolean[] availableColor = new boolean[graph.getVertices()];
        for(int i = 0; i< availableColor.length; i++)
            availableColor[i] = true;
        color[0] = 0;
        for(int u = 1; u < graph.getVertices(); u++) {

            for(Integer v : graph.getEdgesMapping()[u]) {
                if(color[v] != -1)
                    availableColor[color[v]] = false;
            }

            int cr;
            for(cr = 0; cr < graph.getVertices(); cr++) {
                if(availableColor[cr] == true)
                    break;
            }
            color[u] = cr;
            for(int i = 0; i < availableColor.length; i++)
                availableColor[i] = true;

        }

        // print the result
        for (int u = 0; u < graph.getVertices(); u++)
            System.out.println("Vertex " + u + " --->  Color "
                    + color[u]);

    }



    public static void main(String[] args) throws Exception {
/*        GraphWeighted graph = new GraphWeighted(9);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);

        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(1, 0, 7);

        graph.addEdge(2, 1, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);


        graph.addEdge(3, 2, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);


        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 5, 10);


        graph.addEdge(5,4,10);
        graph.addEdge(5,6,2);


        graph.addEdge(6,5,2);
        graph.addEdge(6,7,1);
        graph.addEdge(6,8,6);


        graph.addEdge(7,0,8);
        graph.addEdge(7,1,11);
        graph.addEdge(7,6,1);
        graph.addEdge(7,8,7);

        graph.addEdge(8,2,2);
        graph.addEdge(8,6,6);
        graph.addEdge(8,7,1);*/


        //prims(graph);


        //kruskal(graph);



/*        int graph[][] = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 },
        };
        int m = 3; // Number of colors
        //System.out.println(graphColoring(graph, 4, 4));

        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        System.out.println(mColorBFS(g, 2));*/



/*        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        dijkstra(graph,0, 9);*/

/*
5 5
1 0
0 2
2 1
0 3
3 4
 */
    Problems p = new Problems();
    }
}
