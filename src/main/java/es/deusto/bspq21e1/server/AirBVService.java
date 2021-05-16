package es.deusto.bspq21e1.server;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public boolean registerUser(String dni, String name, String email, String password) {
    	logger.info("Creating and storing a new User:" + name);
    	User u = new User(dni, name, email, password);
    	usersHM.put(dni, u);
        return DBManager.getInstance().store(u);
    }
    
    public boolean registerVan(Van van) {		
    	logger.info("Creating and storing the new van:" + van.getLicensePlate());
    	
    	vansHM.put(van.getLicensePlate(), van);

    	return DBManager.getInstance().store(van);
    }

    public boolean cancelReservation(String code) {
    	logger.info("Canceling the reservation with code: " + code);
        return DBManager.getInstance().deleteReservation(code);
    }
    
    public boolean registerReservation(Date bookingDate, int duration, String van, String vanRenter) {
    	logger.info("Creating and storing new Reservation");
    	Reservation reservation = new Reservation(bookingDate, duration, van, vanRenter);
    	if( DBManager.getInstance().store(reservation) ) {
    		reservationsHM.put(reservation.getCode(), reservation);
    		return true;
    	}
    	return false;
    }

	public ArrayList<Van> searchVans(String location, String pickUpDateString, String returnDateString) {
		logger.info("Searching vans with location: "+ location+" from " + pickUpDateString + " to " + returnDateString);
		
		Date pickUpDate = null;
		try {
			pickUpDate = new SimpleDateFormat("dd-MM-yyyy").parse(pickUpDateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Date returnDate = null;
		try {
			returnDate = new SimpleDateFormat("dd-MM-yyyy").parse(returnDateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<Van> vanAL = new ArrayList<Van>(DBManager.getInstance().getVansByDates(location, pickUpDate, returnDate));
		for( Van v : vanAL ) { 
			logger.debug("Van in AIRBVService: " + v);
			vansHM.put(v.getLicensePlate(), v); 
		}
		
		return vanAL;
	}
	
	public ArrayList<Van> searchVansByLocation(String location) {
		logger.info("Searching vans with location: "+ location);
		ArrayList<Van> vanAL = new ArrayList<Van>(DBManager.getInstance().getVansByLocation(location));
		for( Van v : vanAL ) { 
			logger.debug("Van in AIRBVService: " + v);
			vansHM.put(v.getLicensePlate(), v); 
		}
		
		return vanAL;
	}
	
	public User login(String email, String password) {
		logger.info("Verificating credentials and returning user: " + email);
		User user = DBManager.getInstance().validateLogin(email, password);
		return user;
	}

	public ArrayList<Reservation> getUserReservations(String dni) {
		logger.info("Getting all reservations of user: " + dni);
		return (ArrayList<Reservation>) DBManager.getInstance().getReservationsByUser(dni);
	}
	
	public ArrayList<Van> getUserVans(String dni) {
		logger.info("Getting all vans of user: " + dni);
		ArrayList<Van> l = (ArrayList<Van>) DBManager.getInstance().getVansByUser(dni);
		return l;
	}

	public boolean deleteUser(String dni) {
		logger.info("Deleting user with dni: " + dni);
		if(usersHM.get(dni) != null ) {
			usersHM.remove(dni);
		}
		return DBManager.getInstance().deleteUser(dni);
	}
	
	public boolean deleteVan(String licensePlate) {
		logger.info("Deleting van with license plate: " + licensePlate);
		if(vansHM.get(licensePlate) != null ) {
			vansHM.remove(licensePlate);
		}
		return DBManager.getInstance().deleteVan(licensePlate);
	}
    
}
