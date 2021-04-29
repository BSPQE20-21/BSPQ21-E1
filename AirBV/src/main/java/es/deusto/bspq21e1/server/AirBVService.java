package es.deusto.bspq21e1.server;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

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
    private static Logger logger = Logger.getLogger(AirBVService.class.getName());
    
    public AirBVService() {

    }

    /**
     * Registers a new user in Database and stores it in the map
     * @param dni -  new user's dni
     * @param name - new users'name
     * @param email - new user's email
     * @param password - new user's password
     * @return returns the created new User
     */
    public User registerUser(String dni, String name, String email, String password) {
    	logger.debug("Creating and storing a new User:" + name);
    	User u = new User(dni, name, email, password);
    	usersHM.put(dni, u);
        DBManager.getInstance().store(u);
        return u;
    }
    
    public void registerVan(Van van) {		
    	logger.debug("Creating and storing the new van:" + van.getLicensePlate());
    	
    	vansHM.put(van.getLicensePlate(), van);

    	DBManager.getInstance().store(van);
    }

    public boolean cancelReservation(String code) {
    	logger.debug("Canceling the reservation with code: " + code);
        if(reservationsHM.get(code) != null) {
            DBManager.getInstance().deleteReservation(code);
            return true;
        }
        logger.error("Error canceling a reservation, the reservation can't be found on HashMap.");
        return false;
    }
    
    public void registerReservation(Date bookingDate, int duration, String van, String vanRenter) {
    	logger.debug("Creating and storing new Reservation");
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
		logger.debug("Searching vans with location: "+ location);
		ArrayList<Van> vanAL = new ArrayList<Van>(DBManager.getInstance().getVansByLocation(location));
		for( Van v : vanAL ) { 
			logger.debug("Van in AIRBVService: " + v);
			vansHM.put(v.getLicensePlate(), v); 
		}
		
		return vanAL;
		
		
	}
	
	public User login(String email, String password) {
		logger.debug("Verificating credentials and returning user: " + email);
		User user = DBManager.getInstance().validateLogin(email, password);
		return user;
	}

	public ArrayList<Reservation> getUserReservations(String dni) {
		logger.debug("Getting all reservations of user: " + dni);
		return (ArrayList<Reservation>) DBManager.getInstance().getReservationsByUser(dni);
	}

	public void deleteUser(String dni) {
		logger.debug("Deleting user with dni: " + dni);
		if(usersHM.get(dni) != null) {
            usersHM.remove(dni);
        }
		DBManager.getInstance().deleteUser(dni);
	}
	
	public void deleteVan(String licensePlate) {
		logger.debug("Deleting van with license plate: " + licensePlate);
		if(vansHM.get(licensePlate) != null ) {
			vansHM.remove(licensePlate);
		}
		//TODO DBManager.getInstance().deleteVan(licensePlate);
	}
    
}
