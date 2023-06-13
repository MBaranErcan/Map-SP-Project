CREATE TABLE bus2sub (
  bus2sub_id INT NOT NULL,
  bus INT NOT NULL,
  sub INT NOT NULL,
  PRIMARY KEY (bus2sub_id) ,
  FOREIGN KEY (bus) REFERENCES bus_station(bus_station_number),
  FOREIGN KEY (sub) REFERENCES subway_station(subway_station_id)
); 
ALTER TABLE bus2sub MODIFY COLUMN bus2sub_id INT AUTO_INCREMENT;

ALTER TABLE bus2sub AUTO_INCREMENT = 1;

SELECT * from bus2sub;

DELETE from bus2sub where bus2sub_id > 0;