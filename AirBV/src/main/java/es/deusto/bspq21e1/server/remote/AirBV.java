package es.deusto.bspq21e1.server.remote;

import java.rmi.server.UnicastRemoteObject;

import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.server.AirBVService;

// This is the REMOTE FACADE
public class AirBV extends UnicastRemoteObject implements IAirBV {
    
    private static final long serialVersionUID = 1L;
    private AirBVService airbvService;

    public AirBV(AirBVService airbvService) {
        super();
        this.airbvService = airbvService;
    }

    // Here will go the methods
    public User registerUser(String dni, String name, String email) {
        return airbvService.registerUser(dni, name, email);
    }

    public boolean cancelReservation(String code) {
        return airbvService.cancelReservation(code);
    }


}
