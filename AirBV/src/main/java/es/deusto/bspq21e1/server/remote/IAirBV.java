package es.deusto.bspq21e1.server.remote;

import java.rmi.Remote;

import es.deusto.bspq21e1.server.data.User;

public interface IAirBV extends Remote {
    public void registerUser(String dni, String name, String email);
    public boolean cancelReservation(String code);
}
