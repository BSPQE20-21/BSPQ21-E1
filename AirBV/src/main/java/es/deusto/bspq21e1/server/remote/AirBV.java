package es.deusto.bspq21e1.server.remote;

import java.rmi.server.UnicastRemoteObject;

// This is the REMOTE FACADE
public class AirBV extends UnicastRemoteObject implements IAirBV {
    
    private static final long serialVersionUID = 1L;
    private AirBVService airbvService;

    public AirBV(AirBVService airbvService) {
        super();
        this.airbvService = airbvService;
    }

    // Here will go the methods

}
