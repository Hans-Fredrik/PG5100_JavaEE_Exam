CREATE TABLE User(
  id INT(6) AUTO_INCREMENT NOT NULL,
  email VARCHAR(128) NOT NULL,
  password VARCHAR(128) NOT NULL,
  userType VARCHAR(128) NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO USER VALUES (null, 'hans@g.no', 'heihans', 'Teacher');
INSERT INTO USER VALUES(null, 'kim@g.no', 'heikim', 'Teacher');

INSERT INTO USER VALUES(null, 'Per@NITH.no', 'heiPer', 'Student');
INSERT INTO USER VALUES(null, 'Ola@NITH.no', 'heiOla', 'Student');
INSERT INTO USER VALUES(null, 'Lise@NITH.no', 'heiLise', 'Student');