# ![alt text](https://github.com/BSPQE20-21/BSPQ21-E1/blob/main/src/images/AirBV.png "Logo") AirB&V project

#### **Table of contents**<br>
[About us](#about-us)<br>
[About the project](#about-the-project)<br>
[Pre-requisites](#pre-requisites)<br>
[Building and running the project](#building-and-running-the-project)

## About us
BSPQ21-E1
## About the project
Airb&V offers an application dedicated to the supply of caravans to individuals and holidaymakers (holiday rentals) through which hosts can advertise and contract the rental of their caravans with their guests.

## Pre-requisites
- Java JDK 12
- MySQL
- MySQL Workbench
- Maven

-> Also the following libraries should be added to pom.xml file for the project to work: Jetty, JUnit, OpenCSV, MySQL, Datanucleus, Log4J, Jersey, Jacoco, ContiPerf, DOxygen.

## Building and running the project
These are the steps that must be followed in order to succesfully build and run the project:

*cmd's must be opened in the directory where pom.xml is located.*

1. Compile the project:
```bash
  mvn clean compile
```

2. Execute .sql script in MySQL WorkBench:
Click on the thunder icon and refesh tables.

3. Create SQL schema:
```bash
  mvn datanucleus:schema-create
```

4. Run Web Server:
```bash
  mvn jetty:run
```

*open another cmd window to run client side*

5. Run Client App:
```bash
  mvn exec:java -Pclient
```

## Generating reports
1. Generate doxygen reports (It is needed to be compiled first):
```bash
  mvn doxygen:report
```
2. Validate:
```bash
  mvn validate
```
