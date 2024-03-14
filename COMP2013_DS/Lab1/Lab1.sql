CREATE TABLE `q1`(
    id INT PRIMARY KEY,
    message varchar(144)
);

ALTER TABLE q1 ADD `attime` TIME NOT NULL;

SELECT * FROM q1; # To confirm if it is right

DROP TABLE q1;

CREATE TABLE `item` (
    itemid INT PRIMARY KEY,
    itemname VARCHAR(25) UNIQUE NOT NULL,
    itemprice DOUBLE NOT NULL
);

CREATE TABLE `stocklist` (
    itemid INT REFERENCES item(itemid) ON UPDATE CASCADE ON DELETE CASCADE,
    shoplocation VARCHAR(25) NOT NULL,
    count INT NOT NULL,
    PRIMARY KEY (itemid, shoplocation)
);

INSERT INTO item(itemid, itemname, itemprice) VALUES (1, 'MacBook Pro 2019', 3000.00);
INSERT INTO item(itemid, itemname, itemprice) VALUES (2, 'iMac 2020', 4422.53);

UPDATE item SET itemprice = itemprice * 1.1 WHERE itemname = 'IMac 2020'