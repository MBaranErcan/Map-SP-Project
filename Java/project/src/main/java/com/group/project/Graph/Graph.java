package com.group.project.Graph;

import java.util.*;

public class Graph {
    private final int V;                                // Number of vertices.
    private final Bag<DirectedEdge>[] adj;              // Array of bags to represent adjacency lists.

    public Graph(int V) {                               // Constructor.
        this.V = V;                                     // Set V.
        adj = (Bag<DirectedEdge>[]) new Bag[V];          // Initialize adj list.
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();            // Initialize each adjacency list.
        }
    }

    public void addEdge(DirectedEdge e) {                // Method to add an edge to the graph.
        int v = e.from();                               // Get the "from" vertex.
        adj[v].add(e);                                  // Add the edge to the "from" vertex's adjacency list.
    }

    public Iterable<DirectedEdge> adj(int v) {           // Returns the adjacency list of a vertex.
        return adj[v];
    }

    public int V() {                                    // Returns the number of vertices in the graph.
        return V;
    }

    public void printEdges() {
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                System.out.println(e);
            }
        }
    }

}