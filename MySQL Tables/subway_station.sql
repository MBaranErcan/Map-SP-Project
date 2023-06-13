CREATE TABLE subway_station (
  subway_station_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  subway_station_name VARCHAR(40) NOT NULL,
  subway_station_line VARCHAR(15) NOT NULL
);

ALTER TABLE subway_station MODIFY subway_station_name VARCHAR(50) NOT NULL;
ALTER TABLE subway_station MODIFY subway_station_line VARCHAR(20) NOT NULL;


SELECT * FROM transportations.subway_station;

describe subway_station