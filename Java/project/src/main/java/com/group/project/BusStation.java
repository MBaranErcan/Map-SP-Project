package com.group.project;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bus_station")
public class BusStation implements Serializable, Station {

    @Id
    @Column(name = "bus_station_number")
    private int number;

    @Column(name = "bus_station_name")
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
