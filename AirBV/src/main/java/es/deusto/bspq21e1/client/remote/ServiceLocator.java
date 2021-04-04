package es.deusto.bspq21e1.client.remote;

public class ServiceLocator {

    private IAirBV airBVService;

    public ServiceLocator() {

    }

    /**
     * Sets the service the client will use.
     * @param args Arguments of the Main entered via Maven
     */
    public void setService(String args[]) {
        try {
            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            airBVService = (IAirBV) java.rmi.Naming.lookup(name);
        } catch (Exception e) {
            System.err.println("$ AirBV client: Error setting services");
        }
    }

    /**
     * Returns the service the client is using.
     * @return The service
     */
    public IAirBV getAirBVService() {
        return airBVService;
    }
}