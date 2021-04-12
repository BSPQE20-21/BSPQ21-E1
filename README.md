# BSPQ21-E1

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
