package com.group.project;

import com.group.project.Graph.DirectedEdge;
import com.group.project.Graph.Graph;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.group.project.Graph.Main.ENTITY_MANAGER_FACTORY;

public class Commands {

    /************ BUS STATION COMMANDS ****************
     ****************************************************/
    public static void addBusStation(int number, String name) {
        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new BusStation
            BusStation bs = new BusStation();
            bs.setNumber(number);
            bs.setName(name);
            // Save the BusStation object
            em.persist(bs);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static BusStation getBusStation(int number) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT b FROM BusStation b WHERE b.number = :bus_station_number";

        // Issue the query and get a matching BusStation
        TypedQuery<BusStation> tq = em.createQuery(query, BusStation.class);
        tq.setParameter("bus_station_number", number);

        BusStation bs = null;
        try {
            // Get matching BusStation object and output
            bs = tq.getSingleResult();
            //System.out.println(bs.getNumber() + " " + bs.getName());
        }
        catch(NoResultException ex) {
            System.out.println("No results found for " + number);
            ex.printStackTrace();
        }
        finally {
            em.close();
            return bs;
        }
    }

    public static List<BusStation> getBusStations() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT b FROM BusStation b WHERE b.number IS NOT NULL";

        // Issue the query and get a matching BusStation
        TypedQuery<BusStation> tq = em.createQuery(strQuery, BusStation.class);
        List<BusStation> busStationList = null;
        try {
            // Get matching BusStation object and output
            busStationList = tq.getResultList();
            //busStationList.forEach(busStation->System.out.println(busStation.getNumber() + " " + busStation.getName()));
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
            return busStationList;
        }
    }

    public static void changeBusStationName(int number, String newName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        BusStation bs = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find BusStation and make changes
            bs = em.find(BusStation.class, number);
            bs.setName(newName);

            // Save the BusStation object
            em.persist(bs);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void deleteBusStation(int number) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        BusStation bs = null;

        try {
            et = em.getTransaction();
            et.begin();
            bs = em.find(BusStation.class, number);
            em.remove(bs);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }


    /************ SUBWAY STATION COMMANDS ***************
    ****************************************************
    ****************************************************/

    public static void addSubwayStation(String name, String line) {
        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();
            // Create and set values for new SubwayStation
            SubwayStation ss = new SubwayStation();
            ss.setName(name);
            ss.setLine(line);
            // Save the SubwayStation object
            em.persist(ss);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static SubwayStation getSubwayStation(int id) {   // Search by id PK.
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT s FROM SubwayStation s WHERE s.id = :subway_station_id";

        // Issue the query and get a matching SubwayStation
        TypedQuery<SubwayStation> tq = em.createQuery(query, SubwayStation.class);
        tq.setParameter("subway_station_id", id);

        SubwayStation ss = null;
        try {
            // Get matching SubwayStation object and output
            ss = tq.getSingleResult();
            //System.out.println(ss.getId() + " " + ss.getName() + " " + ss.getLine());
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
            return ss;
        }
    }

    public static void getSubwayStation(String name) {  // Search by name, since it is not a PK, it may return a list of objects with same name.
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT s FROM SubwayStation s WHERE s.name = :subway_station_name";

        // Issue the query and get a matching SubwayStation
        TypedQuery<SubwayStation> tq = em.createQuery(query, SubwayStation.class);
        tq.setParameter("subway_station_name", name);

        List<SubwayStation> ssList = null;
        try {
            // Get matching SubwayStation objects and output
            ssList = tq.getResultList();
            if (ssList.isEmpty()) {
                System.out.println("No results found.");
            } else {
                for (SubwayStation ss : ssList) {
                    System.out.println(ss.getId() + " " + ss.getName() + " " + ss.getLine());
                }
            }
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static List<SubwayStation> getSubwayStations() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT s FROM SubwayStation s WHERE s.name IS NOT NULL";

        // Issue the query and get a matching SubwayStation
        TypedQuery<SubwayStation> tq = em.createQuery(strQuery, SubwayStation.class);
        List<SubwayStation> subwayStationList = null;
        try {
            // Get matching SubwayStation object and output
            subwayStationList = tq.getResultList();
            //subwayStationList.forEach(subwayStation->System.out.println(subwayStation.getId()+ " " + subwayStation.getName() + " " + subwayStation.getLine()));
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
            return subwayStationList;
        }
    }

    public static void changeSubwayStationLine(int id, String newLine) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        SubwayStation ss = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find SubwayStation and make changes
            ss = em.find(SubwayStation.class, id);
            ss.setLine(newLine);

            // Save the SubwayStation object
            em.persist(ss);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void deleteSubwayStation(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        SubwayStation ss = null;

        try {
            et = em.getTransaction();
            et.begin();
            ss = em.find(SubwayStation.class, id);
            em.remove(ss);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    /*****************SUB 2 SUB*******************
    **********************************************
    *********************************************/

    public static void addConnection_Sub2Sub(int sub1_id, int sub2_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();
            // Create and set values for new Sub2Sub
            Sub2Sub s2s = new Sub2Sub();
            s2s.setSub_station_1(sub1_id);
            s2s.setSub_station_2(sub2_id);
            // Save the Sub2Sub object
            em.persist(s2s);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void getConnection_Sub2Sub(int id) {  // Search by Sub2Sub id (PK)
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT s2s FROM Sub2Sub s2s WHERE s2s.id = :sub2sub_id";

        // Issue the query and get a matching Sub2Sub
        TypedQuery<Sub2Sub> tq = em.createQuery(query, Sub2Sub.class);
        tq.setParameter("sub2sub_id", id);

        Sub2Sub s2s = null;
        try {
            // Get matching Sub2Sub object and output
            s2s = tq.getSingleResult();
            System.out.println(s2s.getId() + " " + s2s.getSub_station_1() + " - " + s2s.getSub_station_2());
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static List<Sub2Sub> getConnections_Sub2Sub_AllStations() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT s2s FROM Sub2Sub s2s WHERE s2s.id IS NOT NULL";

        // Issue the query and get a matching Sub2Sub
        TypedQuery<Sub2Sub> tq = em.createQuery(strQuery, Sub2Sub.class);
        List<Sub2Sub> sub2subList = null;
        try {
            // Get matching Sub2Sub object and output
            sub2subList = tq.getResultList();
            //sub2subList.forEach(sub2Sub->System.out.println(sub2Sub.getId()+ " " + sub2Sub.getSub_station_1() + " " + sub2Sub.getSub_station_2()));
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
            return sub2subList;
        }
    }

    public static void changeConnection_Sub2Sub(int id, int sub1_id, int sub2_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Sub2Sub s2s = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find Sub2Sub and make changes
            s2s = em.find(Sub2Sub.class, id);
            s2s.setSub_station_1(sub1_id);
            s2s.setSub_station_2(sub2_id);

            // Save the Sub2Sub object
            em.persist(s2s);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void deleteConnection_Sub2Sub(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Sub2Sub s2s = null;

        try {
            et = em.getTransaction();
            et.begin();
            s2s = em.find(Sub2Sub.class, id);
            em.remove(s2s);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void addMultipleConnections_Sub2Sub() {
        Scanner scan = new Scanner(System.in);
        List<Integer> s2sList = new ArrayList<>();

        while (true) {
            String line = scan.nextLine();
            if (line.equals("-1")) break;

            else if (line.equals("")) {
                if (s2sList.size() > 1) {
                    Commands.addConnection_Sub2Sub(
                            s2sList.get(s2sList.size() - 2),
                            s2sList.get(s2sList.size() - 1));
                }
                s2sList.clear();
            } else {
                int number = Integer.parseInt(line);
                s2sList.add(number);
                if (s2sList.size() > 1) {
                    Commands.addConnection_Sub2Sub(
                            s2sList.get(s2sList.size() - 2),
                            s2sList.get(s2sList.size() - 1));
                }
            }
        }
    }

    /*****************BUS 2 BUS*******************
     **********************************************
     *********************************************/

    public static void addConnection_Bus2Bus(int bus1_id, int bus2_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();
            // Create and set values for new Bus2Bus
            Bus2Bus b2b = new Bus2Bus();
            b2b.setBus_station_1(bus1_id);
            b2b.setBus_station_2(bus2_id);
            // Save the Bus2Bus object
            em.persist(b2b);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void getConnection_Bus2Bus(int id) {  // Search by Bus2Bus id (PK)
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT b2b FROM Bus2Bus b2b WHERE b2b.id = :bus2bus_id";

        // Issue the query and get a matching Bus2Bus
        TypedQuery<Bus2Bus> tq = em.createQuery(query, Bus2Bus.class);
        tq.setParameter("bus2bus_id", id);

        Bus2Bus b2b = null;
        try {
            // Get matching Bus2Bus object and output
            b2b = tq.getSingleResult();
            System.out.println(b2b.getId() + " " + b2b.getBus_station_1() + " - " + b2b.getBus_station_2());
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static List<Bus2Bus> getConnections_Bus2Bus_AllStations() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT b2b FROM Bus2Bus b2b WHERE b2b.id IS NOT NULL";

        // Issue the query and get a matching Bus2Bus
        TypedQuery<Bus2Bus> tq = em.createQuery(strQuery, Bus2Bus.class);
        List<Bus2Bus> bus2busList = null;
        try {
            // Get matching Bus2Bus object and output
            bus2busList = tq.getResultList();
            //bus2busList.forEach(bus2bus->System.out.println(bus2bus.getId()+ " " + bus2bus.getBus_station_1() + " " + bus2bus.getBus_station_2()));
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
            return bus2busList;
        }
    }

    public static void changeConnection_Bus2Bus(int id, int bus1_id, int bus2_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Bus2Bus b2b = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find Bus2Bus and make changes
            b2b = em.find(Bus2Bus.class, id);
            b2b.setBus_station_1(bus1_id);
            b2b.setBus_station_2(bus2_id);

            // Save the Bus2Bus object
            em.persist(b2b);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void deleteConnection_Bus2Bus(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Bus2Bus b2b = null;

        try {
            et = em.getTransaction();
            et.begin();
            b2b = em.find(Bus2Bus.class, id);
            em.remove(b2b);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void addMultipleConnections_Bus2Bus() {
        Scanner scan = new Scanner(System.in);
        List<Integer> bus_stations_list = new ArrayList<>();

        while (true) {
            String line = scan.nextLine();
            if (line.equals("-1")) break;

            else if (line.equals("")) {
                if (bus_stations_list.size() > 1) {
                    Commands.addConnection_Bus2Bus(
                            bus_stations_list.get(bus_stations_list.size() - 2),
                            bus_stations_list.get(bus_stations_list.size() - 1));
                }
                bus_stations_list.clear();
            } else {
                int number = Integer.parseInt(line);
                bus_stations_list.add(number);
                if (bus_stations_list.size() > 1) {
                    Commands.addConnection_Bus2Bus(
                            bus_stations_list.get(bus_stations_list.size() - 2),
                            bus_stations_list.get(bus_stations_list.size() - 1));
                }
            }
        }
    }

    /*****************BUS 2 SUB*******************
     **********************************************
     *********************************************/

    public static void addConnection_Bus2Sub(int bus_number, int sub_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();
            // Create and set values for new Bus2Sub
            Bus2Sub b2s = new Bus2Sub();
            b2s.setBus_number(bus_number);
            b2s.setSub_id(sub_id);
            // Save the Bus2Sub object
            em.persist(b2s);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void getConnection_Bus2Sub(int id) {  // Search by Bus2Bus id (PK)
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT b2s FROM Bus2Sub b2s WHERE b2s.id = :bus2sub_id";

        // Issue the query and get a matching Bus2Sub
        TypedQuery<Bus2Sub> tq = em.createQuery(query, Bus2Sub.class);
        tq.setParameter("bus2sub_id", id);

        Bus2Sub b2s = null;
        try {
            // Get matching Bus2Sub object and output
            b2s = tq.getSingleResult();
            System.out.println(b2s.getId() + " " + b2s.getBus_number() + " - " + b2s.getSub_id());
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static List<Bus2Sub> getConnections_Bus2Sub_AllStations() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT b2s FROM Bus2Sub b2s WHERE b2s.id IS NOT NULL";

        // Issue the query and get a matching Bus2Sub
        TypedQuery<Bus2Sub> tq = em.createQuery(strQuery, Bus2Sub.class);
        List<Bus2Sub> bus2subList = null;
        try {
            // Get matching Bus2Sub object and output
            bus2subList = tq.getResultList();
            //bus2subList.forEach(bus2sub->System.out.println(bus2sub.getId()+ " " + bus2sub.getBus_number() + " " + bus2sub.getSub_id()));
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
            return bus2subList;
        }
    }

    public static void changeConnection_Bus2Sub(int id, int bus_number, int sub_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Bus2Sub b2s = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find Bus2Sub and make changes
            b2s = em.find(Bus2Sub.class, id);
            b2s.setBus_number(bus_number);
            b2s.setSub_id(sub_id);

            // Save the Bus2Sub object
            em.persist(b2s);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void deleteConnection_Bus2Sub(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Bus2Sub b2s = null;

        try {
            et = em.getTransaction();
            et.begin();
            b2s = em.find(Bus2Sub.class, id);
            em.remove(b2s);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void addMultipleConnections_Bus2Sub() {
        Scanner scan = new Scanner(System.in);
        List<Integer> b2sList = new ArrayList<>();

        while (true) {
            String line = scan.nextLine();
            if (line.equals("-1")) break;

            else if (line.equals("")) {
                if (b2sList.size() > 1) {
                    Commands.addConnection_Bus2Sub(
                            b2sList.get(b2sList.size() - 2),
                            b2sList.get(b2sList.size() - 1));
                }
                b2sList.clear();
            } else {
                int number = Integer.parseInt(line);
                b2sList.add(number);
                if (b2sList.size() > 1) {
                    Commands.addConnection_Bus2Sub(
                            b2sList.get(b2sList.size() - 2),
                            b2sList.get(b2sList.size() - 1));
                }
            }
        }
    }

    /*
    Method to assign x and y coordinates to the station.
     */
    public static int[] station_get_xy(String str,int rangeX,int rangeY){
        int[] arr = new int[str.length()];
        int num = 0;
        for(int i = str.length()-1; i >= 0; i--){
            arr[arr.length - i-1] = (int)(str.charAt(i));
        }

        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i] * (int)(Math.floor(Math.pow(10,i)));
        }

        for(int i : arr){
            num += i;
        }

        return new int[]{((int)(num/Math.pow(10,arr.length/2)))%rangeX,
                (num%(int)(Math.floor(Math.pow(10,arr.length/2))))%rangeY
        };
    }
    public static int calculateDistance(int[] coor1, int[] coor2) {
        int distance = 0;
        distance = (int) Math.sqrt( Math.pow(coor1[0] - coor2[0], 2) + Math.pow(coor1[1] - coor2[1], 2) );
        return distance;
    }

    public static Graph createGraph(int max_distance, int V, int range_x, int range_y) {
        Scanner scan = new Scanner(System.in);

        List<DirectedEdge> edgeList = new ArrayList<>();
        List<Sub2Sub> sub2SubList = Commands.getConnections_Sub2Sub_AllStations();
        for (Sub2Sub s2s : sub2SubList) {
            SubwayStation ss1 = Commands.getSubwayStation(s2s.getSub_station_1());
            int[] x_y_coordinates_1 = Commands.station_get_xy(ss1.getName(), range_x, range_y);

            SubwayStation ss2 = Commands.getSubwayStation(s2s.getSub_station_2());
            int[] x_y_coordinates_2 = Commands.station_get_xy(ss2.getName(), range_x, range_y);

            int distance = Commands.calculateDistance(x_y_coordinates_1, x_y_coordinates_2);
            DirectedEdge edge1 = new DirectedEdge(ss1.getId(), ss2.getId(), distance);
            DirectedEdge edge2 = new DirectedEdge(ss2.getId(), ss1.getId(), distance);
            edgeList.add(edge1);
            edgeList.add(edge2);
        }
        System.out.println("Subways are done!");


        List<Bus2Bus> bus2BusList = Commands.getConnections_Bus2Bus_AllStations();
        for (Bus2Bus b2b : bus2BusList) {
            BusStation bs1 = Commands.getBusStation(b2b.getBus_station_1());
            int[] x_y_coordinates_1 = Commands.station_get_xy(bs1.getName(), range_x, range_y);

            BusStation bs2 = Commands.getBusStation(b2b.getBus_station_2());
            int[] x_y_coordinates_2 = Commands.station_get_xy(bs2.getName(), range_x, range_y);

            int distance = Commands.calculateDistance(x_y_coordinates_1, x_y_coordinates_2);
            DirectedEdge edge1 = new DirectedEdge(bs1.getNumber(), bs2.getNumber(), distance);
            DirectedEdge edge2 = new DirectedEdge(bs2.getNumber(), bs1.getNumber(), distance);
            edgeList.add(edge1);
            edgeList.add(edge2);
        }
        System.out.println("Buses are done!");

        List<Bus2Sub> bus2SubList = Commands.getConnections_Bus2Sub_AllStations();
        for (Bus2Sub b2s : bus2SubList) {
            BusStation bs = Commands.getBusStation(b2s.getBus_number());
            int[] x_y_coordinates_1 = Commands.station_get_xy(bs.getName(), range_x, range_y);

            SubwayStation ss = Commands.getSubwayStation(b2s.getSub_id());
            int[] x_y_coordinates_2 = Commands.station_get_xy(ss.getName(), range_x, range_y);

            int distance = Commands.calculateDistance(x_y_coordinates_1, x_y_coordinates_2);
            DirectedEdge edge1 = new DirectedEdge(bs.getNumber(), ss.getId(), distance);
            DirectedEdge edge2 = new DirectedEdge(ss.getId(),bs.getNumber(), distance);
            edgeList.add(edge1);
            edgeList.add(edge2);
        }
        System.out.println("Transfers are done!");

        // Create Graph.
        int total = bus2BusList.size() + bus2SubList.size() + sub2SubList.size();
        Graph graph = new Graph(V);
        System.out.println("Graph created!");

        for (DirectedEdge edge: edgeList) {
            graph.addEdge(edge);
        }
        return graph;
    }


    public static List<Station> getTable() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "DROP PROCEDURE IF EXISTS get_possible_connections;\n" +
                "\n" +
                "DELIMITER $$\n" +
                "\n" +
                "CREATE PROCEDURE get_possible_connections(IN target INT, IN at INT)\n" +
                "BEGIN\n" +
                "    -- Drop the temp_table if it exists\n" +
                "    DROP TABLE IF EXISTS temp_table;\n" +
                "\n" +
                "    -- Create the temp_table\n" +
                "    CREATE TEMPORARY TABLE temp_table (bus_no INT);\n" +
                "\n" +
                "    WHILE (SELECT COUNT(*) FROM temp_table WHERE bus_no = target) = 0 DO\n" +
                "            insert into temp_table\n" +
                "            select bus2bus.bus2 from bus2bus\n" +
                "            inner join temp_table\n" +
                "            on temp_table.bus_no = bus2bus.bus1;\n" +
                "    END WHILE;\n" +
                "\n" +
                "    SELECT FROM bus_station\n" +
                "    LEFT JOIN temp_table ON bus_station.bus_station_number = temp_table.bus_no;\n" +
                "\n" +
                "    -- Drop the temp_table at the end\n" +
                "    DROP TEMPORARY TABLE temp_table;\n" +
                "END $$\n" +
                "\n" +
                "DELIMITER ;\n" +
                "\n" +
                "call get_possible_connections(target,starting);";

        // Issue the query and get a matching BusStation
        TypedQuery<Station> tq = em.createQuery(strQuery, Station.class);
        List<Station> busStationList = null;
        try {
            // Get matching BusStation object and output
            busStationList = tq.getResultList();
            //busStationList.forEach(busStation->System.out.println(busStation.getNumber() + " " + busStation.getName()));
        }
        catch(NoResultException ex) {
            System.out.println("No results found.");
            ex.printStackTrace();
        }
        finally {
            em.close();
            return busStationList;
        }
    }


}