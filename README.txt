CREATE ORACLE USER 

create user iot_shop IDENTIFIED by iot_shop;

-- ROLES
GRANT "CONNECT" TO "IOT_SHOP";
GRANT "RESOURCE" TO "IOT_SHOP" ;

-- SYSTEM PRIVILEGES
GRANT CREATE TRIGGER TO "IOT_SHOP" ;
GRANT ALTER SESSION TO "IOT_SHOP" ;
GRANT CREATE VIEW TO "IOT_SHOP" ;
GRANT CREATE SESSION TO "IOT_SHOP" ;
GRANT CREATE TABLE TO "IOT_SHOP" ;
GRANT CREATE TYPE TO "IOT_SHOP" ;
GRANT CREATE SYNONYM TO "IOT_SHOP" ;
GRANT CREATE SEQUENCE TO "IOT_SHOP" ;
GRANT CREATE DATABASE LINK TO "IOT_SHOP" ;
GRANT UNLIMITED TABLESPACE TO "IOT_SHOP" ;
GRANT CREATE PROCEDURE TO "IOT_SHOP" ;


---------------------------------------------------------------------------------------------------------------------------

check DatabaseTables.txt file
----------------------------------------------------------------------------------------------------------------------------

run with embedded tomcat server and maven 

--------------------------------------------------------
check api\target\classes\application.properties	
