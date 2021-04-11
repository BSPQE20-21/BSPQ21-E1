package es.deusto.bspq21e1.server.server;

import java.util.HashMap;
import java.util.Map;

import es.deusto.bspq21e1.server.dao.DBManager;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

// This is the APPLICATION SERVICE
public class AirBVService {
    
    // Parameters
    Map<String, User> users = new HashMap<String, User>();
    Map<String, Van> vans = new HashMap<String, Van>();
    Map<String, Reservation> reservations = new HashMap<String, Reservation>();

    public AirBVService() {

    }

    public User registerUser(String dni, String name, String email) {
        users.put(dni, new User(dni, name, email));
        
        DBManager.getInstance().store(users.get(dni));
        
        return users.get(dni);
    }
    
    public void registerVan(Van van) {
    	DBManager.getInstance().store(van);
    }

    public boolean cancelReservation(String code) {
        if(reservations.get(code) != null) {
            reservations.remove(code);
            //TODO falta eliminar de BD ¿?
            return true;
        }
        return false;
    }
}
