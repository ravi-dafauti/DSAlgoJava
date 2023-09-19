package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private int V;
    private ArrayList<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
        //uncomment for directed graph
    }

    public int getVertices() {
        return this.V;
    }

    public ArrayList<Integer>[] getEdgesMapping() {
        return this.adj;
    }

    private void dfsUtil(int v, int[] visited) {

        visited[v] = 1;
        System.out.println(v);
        for(int i : adj[v]) {
            if(visited[i] == 0) {
                dfsUtil(i, visited);
            }
        }
    }

    public void dfs() {
        int[] visited = new int[this.V];
        for(int i = 0; i < this.V; i++) {
            if(visited[i] == 0) {
                dfsUtil(i, visited);
            }
        }
    }

    private void bfsUtil(int u, int[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        visited[u] = 1;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.println(v);
            for(int i : adj[v]) {
                if(visited[i] == 0) {
                    visited[i] = 1;
                    queue.add(i);
                }
            }
        }

    }

    public void bfs() {
        int[] visited = new int[this.V];
        for(int i = 0; i < this.V; i++) {
            if(visited[i] == 0) {
                bfsUtil(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(2,3);
        graph.addEdge(0, 1);
        graph.addEdge(0,2);
        graph.addEdge(2,4);
        graph.bfs();
    }
}
