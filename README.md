# Map-SP-Project
Hibernate ORM prjoect aiming to find Shortest Path from a source to destination.

This program acts as a shortest path finding program for a user who wants to find the ideal way to travel from one point to another. It implements
graph data structure and the Dijkstra Shortest Path algorithm, with a connection established via Hibernate to the database.


Prerequisites:
It has Maven dependencies (available in the pom.xml file) and a MySql database installed in order to be run.


Usage:

Starting Station: 12 (Source)
Ending Station: 10053 (Destination)
DijkstraSP dijkstra = new DijkstraSP(graph,src);  // SOURCE VERTEX
List<Integer> path = dijkstra.shortestPath(des);  // DESTINATION VERTEX

public static Graph createGraph(int max_distance, int V, int range_x, int range_y);


// EXAMPLE SUBWAY STATION COMMANDS // (CRUD commands)
Commands.addSubwayStation("test02","M123");	// Create
Commands.getSubwayStation(line);  // Search by name (Not Pk).
Commands.getSubwayStation(4);     // Search by id (PK).
Commands.changeSubwayStationLine(4, scan.nextLine());	// Update
Commands.deleteSubwayStation(3);	// Delete
