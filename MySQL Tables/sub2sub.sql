# Create this table
CREATE TABLE sub2sub (
  sub2sub_id INT,
  sub1 INT,
  sub2 INT,
  PRIMARY KEY (sub2sub_id) ,
  FOREIGN KEY (sub1) REFERENCES subway_station(subway_station_id),
  FOREIGN KEY (sub2) REFERENCES subway_station(subway_station_id)
);

 ALTER TABLE sub2sub AUTO_INCREMENT = 1;			#Set id to 1 again

SELECT * FROM transportations.sub2sub;

describe sub2sub