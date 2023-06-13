package com.group.project.Graph;

import com.group.project.*;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("project");

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Scanner scan = new Scanner(System.in);

        // Create Graph
        Graph graph = Commands.createGraph(20, 100000, 1000, 1000);
        // Call Dijkstra to find the shortest path.
        DijkstraSP dijkstra = new DijkstraSP(graph,14180);  // SOURCE VERTEX
        List<Integer> path = dijkstra.shortestPath(14164);  // DESTINATION VERTEX

        System.out.println("Path:");
        for (Integer i: path) {
            System.out.println(i);
        }

/*         METHOD FOR CALCULATING THE DISTANCE TO Create TRANSFERS IN BETWEEN BUSES AND SUBS, WHERE THE DISTANCE < MAX_DISTANCE;
        List<SubwayStation> subwayStationList = Commands.getSubwayStations();
        List<BusStation> busStationList = Commands.getBusStations();
        for (SubwayStation ss: subwayStationList) {
            int[] subwayCoordinates = Commands.station_get_xy(ss.getName(), range_x, range_y);

            for (BusStation bs : busStationList) {
                int[] busCoordinates = Commands.station_get_xy(bs.getName(), range_x, range_y);

                int distance = Commands.calculateDistance(subwayCoordinates, busCoordinates);
                if (distance < max_distance) {
                    Commands.addConnection_Bus2Sub(bs.getNumber(), ss.getId());
                }
            }
        }*/
/*        while (true) {
            String line = scan.nextLine();
            if (line.equals("-1")) break;

            // BUS STATION COMMANDS //
            //Commands.addBusStation(100, "This is a test bus_station");
            //Commands.getBusStation(101);
            //Commands.changeBusStationName(101, "Name change test success");
            //Commands.deleteBusStation(100);
            //Commands.getBusStations();

            // SUBWAY STATION COMMANDS //
            //Commands.addSubwayStation("test02","M123");
            //Commands.getSubwayStation(line);  // Search by name (Not Pk).
            //Commands.getSubwayStation(4);     // Search by id (PK).
            //Commands.getSubwayStations();
            //Commands.changeSubwayStationLine(4, "M12");
            //Commands.deleteSubwayStation(3);

            // ADD MULTIPLE subway STATIONS //
*//*            String[] parts = line.split(",");
            Commands.addSubwayStation(parts[0],parts[1]);
            System.out.println(parts[0]+ " line: " + parts[1] + " has been added to the DB.");*//*
            }
        }*/

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("DONE! in " + (timeElapsed/1000) + " seconds.");

        ENTITY_MANAGER_FACTORY.close();
    }

}
