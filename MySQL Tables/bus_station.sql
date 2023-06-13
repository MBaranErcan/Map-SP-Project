CREATE TABLE bus_station (
    bus_station_number INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bus_station_name VARCHAR(60) NOT NULL
);

ALTER TABLE bus_station MODIFY bus_station_name VARCHAR(20) NOT NULL;

SELECT * FROM transportations.bus_station;

describe bus_station