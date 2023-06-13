package com.group.project.Graph;

import java.util.*;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;                      // Array to keep track of edges.
    private int[] distTo;                                       // Array to keep track of distances
    private PriorityQueue<DirectedEdge> pq;             // PQ to keep track of vertices to visit.

    public DijkstraSP(Graph G, int s) {
        edgeTo = new DirectedEdge[G.V()];               // Initializes the edgeTo array.
        distTo = new int[G.V()];                                // Initializes the distTo array.
        pq = new PriorityQueue<DirectedEdge>(G.V());    // Initializes the priority queue.

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;                      // Set all distances to MAX_VALUE, so when comparison is made they will be for sure bigger than the newer path.
        }
        distTo[s] = 0;                                          // Set distance to source vertex to 0.

        pq.add(new DirectedEdge(s,s,0));        // Add source vertex to the PQ.
        while (!pq.isEmpty()) {                                 // While the PQ is not empty:
            DirectedEdge e = pq.poll();                 // Get the vertex with the smallest distance,
            int v = e.to();                                     // Get the vertex which is being visited,
            for (DirectedEdge edge : G.adj(v))          // And relax every edge.
                relax(edge);
        }
    }

    private void relax(DirectedEdge e) {                // Method to relax an edge. It takes an edge as an input.
        int v = e.from(), w = e.to();                           // Get vertices connecting by that edge.
        if (distTo[w] > distTo[v] + e.weight()) {               // If the distance to w is greater than the distance to v + the weight of edge;
            distTo[w] = distTo[v] + e.weight();                 // Update distance to w.
            edgeTo[w] = e;                                      // Update edgeTo.
            pq.add(e);                                          // Add w to the PQ.
        }
    }

    public int distTo(int v) {                                  // Returns the distance to vertex v.
        return distTo[v];
    }

    public List<Integer> shortestPath(int destination) {
        List<Integer> path = new ArrayList<>();
        int currentVertex = destination;
        while (edgeTo[currentVertex] != null) {
            path.add(currentVertex);
            currentVertex = edgeTo[currentVertex].from();
        }
        path.add(currentVertex);
        Collections.reverse(path);
        return path;
    }
}
