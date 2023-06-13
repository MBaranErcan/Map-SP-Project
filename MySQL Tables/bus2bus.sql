CREATE TABLE bus2bus (
  bus2bus_id INT NOT NULL,
  bus1 INT NOT NULL,
  bus2 INT NOT NULL,
  PRIMARY KEY (bus2bus_id) ,
  FOREIGN KEY (bus1) REFERENCES bus_station(bus_station_number),
  FOREIGN KEY (bus2) REFERENCES bus_station(bus_station_number)
);ALTER TABLE bus2bus MODIFY COLUMN bus2bus_id INT AUTO_INCREMENT;

ALTER TABLE bus2bus AUTO_INCREMENT = 1;

delete from bus2bus where bus2bus_id>0;

SELECT * FROM transportations.bus2bus;