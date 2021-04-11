# BSPQ21-E1

First install jar dependencies:
mvn install:install-file -Dfile={Location on your computer}\BSPQ21-E1\AirBV\dist\AirBVServer.jar -DgroupId=AirBVServer -DartifactId=AirBVServer -Dversion=1.0 -Dpackaging=jar

First, compile the whole code:
1. mvn compile

Then, in three separate cmd windows, run:

1. mvn jetty:run 
2. mvn exec:java -Pserver
3. mvn exec:java -Pclient