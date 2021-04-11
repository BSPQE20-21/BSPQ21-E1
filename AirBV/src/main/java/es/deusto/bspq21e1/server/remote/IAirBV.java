package es.deusto.bspq21e1.server.remote;

import java.rmi.Remote;
import java.util.Date;

import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.dto.VanDTO;

public interface IAirBV extends Remote {
    public void registerUser(String dni, String name, String email);
    public void registerVan(VanDTO vanDTO);
    public boolean cancelReservation(String code);
    public void registerReservation(Date bookingDate, int duration, Van van, User vanRenter);
}
