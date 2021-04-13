# BSPQ20-E1

# About the project



## Prerequisites
- [Java JDK 12](https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html)
- [MySQL](https://dev.mysql.com/downloads/mysql/)
- [MySQL Workbench](https://www.mysql.com/products/workbench/)
- [Maven](https://maven.apache.org/download.cgi)

🠮Also the following libraries should be added to *pom.xml* file for the project to work: **Jetty, JUnit, OpenCSV, MySQL, Datanucleus, Log4J, Jersey, Jacoco, ContiPerf, DOxygen.**

# Building and running the project
These are the steps that must be followed in order to succesfully build and run the project:

*cmd's must be opened in the directory where* ***pom.xml*** *is located.*
### 1- Compile the project:
```mvn clean compile```
### 2- Execute .sql script in MySQL WorkBench:
Click on the thunder icon and refesh tables.
### 3- Create SQL schema: 
```mvn datanucleus:schema-create```
### 4- Run Web Server: 
```mvn jetty:run```

*open another cmd window to run client side*
### 6- Run Client App:
```mvn exec:java -Pclient``` 
