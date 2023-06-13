package com.group.project;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subway_station")
public class SubwayStation implements Serializable, Station{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subway_station_id")
    private int id;

    @Column(name = "subway_station_name")
    private String name;

    @Column(name = "subway_station_line")
    private String line;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
