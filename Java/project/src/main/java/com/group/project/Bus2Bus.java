package com.group.project;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bus2bus")
public class Bus2Bus implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus2bus_id")
    private int id;

    @Column(name = "bus1")
    private int bus_station_1;

    @Column(name = "bus2")
    private int bus_station_2;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBus_station_1() {
        return bus_station_1;
    }

    public void setBus_station_1(int bus_station_1) {
        this.bus_station_1 = bus_station_1;
    }

    public int getBus_station_2() {
        return bus_station_2;
    }

    public void setBus_station_2(int bus_station_2) {
        this.bus_station_2 = bus_station_2;
    }
}
