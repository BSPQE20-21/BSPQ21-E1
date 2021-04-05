package es.deusto.bspq21e1.server.server;

import java.util.HashMap;
import java.util.Map;

import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;

// This is the APPLICATION SERVICE
public class AirBVService {
    
    // Parameters
    Map<String, User> users = new HashMap<String, User>();
    Map<String, Reservation> reservations = new HashMap<String, Reservation>();

    public AirBVService() {

    }

    public User registerUser(String dni, String name, String email) {
        users.put(dni, new User(dni, name, email));
        //TODO falta añadir a BD
        return users.get(dni);
    }

    public boolean cancelReservation(String code) {
        if(reservations.get(code) != null) {
            reservations.remove(code);
            return true;
        }
        return false;
    }
}
