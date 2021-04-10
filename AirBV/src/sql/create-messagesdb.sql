/* DELETE* 'airbvDB' database*/
DROP SCHEMA IF EXISTS airbvDB;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'localhost';

/* CREATE 'airbvDB' DATABASE */
CREATE SCHEMA airbvDB;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';

GRANT ALL ON messagesDB.* TO 'spq'@'localhost';
