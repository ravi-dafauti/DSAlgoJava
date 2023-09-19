package graph;

import java.util.ArrayList;

public class GraphWeighted {

    private int V;
    private ArrayList<Edge>[] adj;

    public class Edge {
        int v;
        int w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public GraphWeighted(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int w) {
        adj[u].add(new Edge(v, w));
        //adj[v].add(new Edge(u, w)); uncomment for prims as it works for undirected only
    }

    public int getVertices() {
        return V;
    }

    public ArrayList<Edge>[] getEdgesMapping() {
        return adj;
    }
}
