package com.group.project;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bus2sub")
public class Bus2Sub implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus2sub_id")
    private int id;

    @Column(name = "bus")
    private int bus_number;

    @Column(name = "sub")
    private int sub_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBus_number() {
        return bus_number;
    }

    public void setBus_number(int bus_number) {
        this.bus_number = bus_number;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }
}
