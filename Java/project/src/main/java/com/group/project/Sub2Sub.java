package com.group.project;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "sub2sub")
public class Sub2Sub implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub2sub_id")
    private int id;

    @Column(name = "sub1")
    private int sub_station_1;

    @Column(name = "sub2")
    private int sub_station_2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSub_station_1() {
        return sub_station_1;
    }

    public void setSub_station_1(int sub_station_1) {
        this.sub_station_1 = sub_station_1;
    }

    public int getSub_station_2() {
        return sub_station_2;
    }

    public void setSub_station_2(int sub_station_2) {
        this.sub_station_2 = sub_station_2;
    }
}