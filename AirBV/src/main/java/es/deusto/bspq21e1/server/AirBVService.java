package es.deusto.bspq21e1.server;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import es.deusto.bspq21e1.server.dao.DBManager;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;


/**
 * Application service class.
 */
public class AirBVService {
    
    // Parameters
    private static Map<String, User> usersHM = new HashMap<>();
    private static Map<String, Van> vansHM = new HashMap<>();
    private static Map<String, Reservation> reservationsHM = new HashMap<>();

    public AirBVService() {

    }

    public User registerUser(String dni, String name, String email) {
        usersHM.put(dni, new User(dni, name, email));
        
        DBManager.getInstance().store(usersHM.get(dni));
        
        return usersHM.get(dni);
    }
    
    public void registerVan(Van van) {
    	vansHM.put(van.getLicensePlate(), van);
    	DBManager.getInstance().store(van);
    }

    public boolean cancelReservation(String code) {
        if(reservationsHM.get(code) != null) {
            DBManager.getInstance().delete(reservationsHM.get(code));
            reservationsHM.remove(code);
            return true;
        }
        return false;
    }
    
    public void registerReservation(Date bookingDate, int duration, Van van, User vanRenter) {
    	Reservation reservation = new Reservation(bookingDate, duration, van, vanRenter);
    	DBManager.getInstance().store(reservation);
    	
    	/* WARNING!!
    	 CAREFULL WITH THE ATRIBUTE "CODE" OF THE RESERVATION CLASS,
    	 WE ARE NOT SURE WHEN EXACTLY THE AUTOINCREMENTAL CODE IS GENERATED
    	 (DEFINED IN THE REVIEW CLASS AS PK), WHICH COULD LEAD TO PROBLEMS
    	 WHEN ACCESSING THE HASHMAP.
    	 
    	 Look out for possible null pointers.
    	 @author: Iñigo Marcos
    	 @date: 11/04
    	 */
    	if( reservation.getCode() != null ) {
    		reservationsHM.put(reservation.getCode(), reservation);
    	}
    }

	public ArrayList<Van> searchVans(String location) {
		ArrayList<Van> vanAL = DBManager.getInstance().getVansByLocation(location);
		for( Van v : vanAL ) { vansHM.put(v.getLicensePlate(), v); }
		
		return vanAL;
		
		
	}

	public ArrayList<Reservation> getUserReservations(String dni) {
		return DBManager.getInstance().getReservationsByUser(usersHM.get(dni));
	}
    
}
