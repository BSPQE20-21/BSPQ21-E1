package es.deusto.bspq21e1.server;

import java.util.ArrayList;
import java.util.Date;
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
            DBManager.getInstance().delete(reservations.get(code));
            reservations.remove(code);
            return true;
        }
        return false;
    }
    
    public void registerReservation(Date bookingDate, int duration, Van van, User vanRenter) {
    	DBManager.getInstance().store(new Reservation(bookingDate, duration, van, vanRenter));
    }

	public ArrayList<Van> searchVans(String location, String pickUp, String returnDate) {
		ArrayList<Van> vans = DBManager.getInstance().getAllVans();
		ArrayList<Van> vansInLocation = new ArrayList<Van>();
		for (Van van : vans) {
			if(van.getLocation().equals(location)) {
				vansInLocation.add(van);
			}
		}
		return vansInLocation;
	}
    
}
