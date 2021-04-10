# BSPQ21-E1

EXECUTION OF RMIREGISTRY, SERVER AND CLIENT AS 3 PROCESSES
----------------------------------------------------------

First, compile the whole code:
1. mvn compile

Then, in three separate cmd windows, run:

1. mvn jetty:run 
2. mvn exec:java -Pserver
3. mvn exec:java -Pclient