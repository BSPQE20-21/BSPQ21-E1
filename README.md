# BSPQ21-E1

Generate jar: jar -cf AirBVexport.jar IAirBV.java

Install jar dependencies:

```bash
mvn install:install-file -Dfile={Location on your computer}\BSPQ21-E1\AirBV\dist\AirBVServer.jar -DgroupId=AirBVServer -DartifactId=AirBVServer -Dversion=1.0 -Dpackaging=jar
```

Compile the whole code:

```bash
mvn clean compile
```

Create the schema

```bash
mvn datanucleus:schema-create
```

In three separate cmd windows, run:

```bash
mvn jetty:run 
mvn exec:java -Pserver
mvn exec:java -Pclient
mvn datanucleus:schema-create
```
