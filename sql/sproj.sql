CREATE TABLE project (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  owner varchar(255) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  urgency int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);
