package es.deusto.bspq21e1.server.dao;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.mysql.cj.QueryResult;

import javax.jdo.Query;

import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

/**
 * Singleton DBManager class.
 * @author 		Group E1
 * @version		1.0-SNAPSHOT
 */
public class DBManager {

	private static DBManager instance = null;
	private PersistenceManagerFactory pmf;
	private static Logger logger = Logger.getLogger(DBManager.class.getName());

	private DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Method that creates a DBManager instance if needed and returns it.
	 * @return DBManager instance
	 */
	public static DBManager getInstance() {
		if ( null==instance ) instance = new DBManager();
		logger.info("DBManager instance created or/and returned");
		return instance;
	}


	////////////////////////////
	//		STORE OBJECT      //
	////////////////////////////
	/**
	 * Stores a user in the DB (makes the object persistent).
	 * @param user
	 */
	public boolean store( User user ) {
		logger.info("User stored in DB");
		return this.storeObject( user );
	}

	/**
	 * Stores a van in the DB (makes the object persistent).
	 * @param van
	 */
	public boolean store( Van van ) {
		logger.info("Van stored in DB");
		return this.storeObject( van );
	}

	/**
	 * Stores a review in the DB (makes the object persistent).
	 * @param review
	 */
	public boolean store( Review review ) {
		logger.info("Review stored in DB");
		return this.storeObject( review );
	}

	/**
	 * Stores a reservation in the DB (makes the object persistent).
	 * @param reservation
	 */
	public boolean store( Reservation reservation ) {
		if(getUser(reservation.getVanRenter()) == null) {
			return false;
		}
		if(getVan(reservation.getVan()) == null) {
			return false;
		}
		logger.info("Reservation stored in DB");
		return this.storeObject( reservation );
	}
	
	/**
	 * Necessary for all of the store functions to work.
	 * @param object
	 */
	private boolean storeObject( Object object ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("   * Storing an object: " + object);
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception e) {
			logger.error("   $ Error storing an object: " + e.getMessage());
			return false;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		return true;
	}
	
	//////////////////////////////
	//		DELETE OBJECT      //
	//////////////////////////////
	/**
	 * Deletes a reservation from the DB.
	 * @param reservation
	 */
	public boolean deleteReservation( String reservation ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			
			Query<Reservation> query = pm.newQuery(Reservation.class);
			
			query.setFilter("code == '" + reservation + "'");
			query.setUnique(true);
			
			Reservation res = (Reservation)query.execute();

			logger.info("   * Deleting a reservation");
			
			if(res == null) {
				return false;
			}
			
			pm.deletePersistent(res);
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("   $ Error deleting an object: " + e.getMessage());
			return false;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
	}
	
	public boolean deleteUser(String dni) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			
			Query<User> query = pm.newQuery(User.class);
			query.setFilter("dni== '" + dni+ "'");
			query.setUnique(true);
			
			User user = (User)query.execute();
			
			if(user == null) {
				return false;
			}

			logger.info("   * Deleting an object: " + user.getName());
			
			ArrayList<Van> listOfVans = (ArrayList<Van>)getVansByUser(dni);
			
			for (Van van : listOfVans) {
				deleteVan(van.getLicensePlate());
			}
			
			pm.deletePersistent(user);
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("   $ Error deleting an object: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		return false;
		
	}
	
	public boolean deleteVan(String licensePlate) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			
			Query<Van> query = pm.newQuery(Van.class);
			query.setFilter("licensePlate== '" + licensePlate+ "'");
			query.setUnique(true);
			
			Van van = (Van)query.execute();
			
			if(van == null) {
				return false;
			}

			logger.info("   * Deleting an object: " + van.getLicensePlate());
			
			pm.deletePersistent(van);
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("   $ Error deleting an object: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		return false;
		
	}
	
//	/**
//	 * Necessary for all of the delete functions to work.
//	 * @param object
//	 */
//	private void deleteObject( Object object ) {
//		PersistenceManager pm = pmf.getPersistenceManager();
//		Transaction tx = pm.currentTransaction();
//		
//		try {
//			tx.begin();
//			logger.info("   * Deleting an object: " + object);
//			pm.deletePersistent(object);
//			tx.commit();
//		} catch (Exception e) {
//			logger.error("   $ Error deleting an object: " + e.getMessage());
//		} finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}	
//			pm.close();
//		}
//	}

	
	
	////////////////////////////////
	//		SPECIFIC QUERIES      //
	////////////////////////////////
	public User getUser( String dni) { 
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {			
			logger.info("   * Retrieving user with dni: " + dni);
			pm.getFetchPlan().setMaxFetchDepth(4);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<User> query = pm.newQuery(User.class);
			query.setFilter("dni== '" + dni+ "'");
			query.setUnique(true);
			
			User user = (User)query.execute();
			logger.info("User retrieved from DB: " + user.getName());
			
			return user;
		} catch (Exception e) {
			logger.error("   $ Error retrieving user: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
	
		return null;
	}
	
	public Van getVan( String licensePlate) { 
		if(licensePlate == null) {
			return null;
		}
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {			
			logger.info("   * Retrieving van with license plate: " + licensePlate);
			pm.getFetchPlan().setMaxFetchDepth(4);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<Van> query = pm.newQuery(Van.class);
			query.setFilter("licensePlate== '" + licensePlate+ "'");
			query.setUnique(true);
			
			Van van = (Van)query.execute();
			logger.info("Van retrieved from DB: " + van.getLicensePlate());
			
			return van;
		} catch (Exception e) {
			logger.error("   $ Error retrieving user: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
	
		return null;
	}
	
	
	/**
	 * Method that finds and returns all of the reservations given a user.
	 * @param vanRenter to retrieve the reservations from
	 * @return an ArrayList with all of the user's reservations  
	 */
	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationsByUser( String vanRenter ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {
			logger.info("   * Retrieving reservation for user: " + vanRenter);
			pm.getFetchPlan().setMaxFetchDepth(4);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<Reservation> query = pm.newQuery(Reservation.class);
			query.setFilter("vanRenter== '" + vanRenter+ "'");
			
			// Java's error is due to a possible ClassCastException. In this case, it should not happen.
			List<Reservation> list = (List<Reservation>)query.execute();
			
			List<Reservation> ress = new ArrayList<Reservation>();
			for (Reservation r : list) {
				Reservation res = new Reservation();
				res.setBookingDate(r.getBookingDate());
				res.setCode(r.getCode());
				res.setDuration(r.getDuration());
				res.setVan(r.getVan());
				res.setVanRenter(r.getVanRenter());
				ress.add(res);
			}
			return ress;
		} catch (Exception e) {
			logger.error("   $ Error retrieving reservations from van renter: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		
		return null;
	}
	
	
	/**
	 * Methods used in getVansByDatess
	 * @param initialDate
	 * @param duration
	 * @return initialDate + duration in Date format.
	 */
	private Date calculateReturnDate( Date initialDate, int duration ) {
		Calendar c = Calendar.getInstance();
		c.setTime( initialDate );
		c.add(Calendar.DAY_OF_MONTH, duration);
		return c.getTime();
	}
	
	private boolean isVanAvailable(Date resPickUp, Date resReturn,
										Date queryPickUp, Date queryReturn ) {
		return true;
//		return ( queryPickUp.before(resPickUp) && queryReturn.before(resReturn) ||
//				queryPickUp.after(resPickUp) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Van> getVansByDates( String location, Date pickUpDate, Date returnDate ) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {
			logger.info("   * Retrieving vans from " + location + "; with dates: [" + pickUpDate + " —— " + returnDate + "]");

			
			/* We first get all the vans from a location,
			 * then we filter by date. 
			 */
			
			List<Van> availableVans = new ArrayList<>();
			List<Van> vansByLocation = this.getVansByLocation(location);
			boolean validReservation = true;
			System.out.println(" DEBUGGING: número de vans in " + location + ": " + vansByLocation.size() );
			for( Van v : vansByLocation ) {
				List<Reservation> vanReservations = this.getReservationsByVan( v.getLicensePlate() );
				
				/*
				 * Self explanatory code is a myth sometimes...
				 * The logic behind this is that if there are any 
				 * already booked/reserved dates that concur with
				 * the query dates, the query dates would not be
				 * valid (van not available for those dates).
				 * I.e:
				 * 		Query date: [17/05 - 22/05]
				 * 		Booked dates 1: [12/05 - 14/05; 15/05 - 18/05] --> second date would make the van not available
				 * 		Booked dates 2: [12/05 - 14/05; 23/05 - 24/05] --> no intersection of dates, therefore van is available
				 * 
				 * Long story short, if the function isVanAvailable
				 * returns ANY false, then the van is not available.
				 */
				
				if ( null != vanReservations ) {
					for( Reservation r : vanReservations ) {
						System.out.println(" DEBUGGING: número de reservas en la van: " + v.getLicensePlate() + ": " + vansByLocation.size() );
						if ( !isVanAvailable(r.getBookingDate(),
								calculateReturnDate(r.getBookingDate(), r.getDuration()),
								pickUpDate,
								returnDate) ) {
							validReservation = false;
							break;
						}
					}
					
					if ( validReservation ) {
						availableVans.add( v );
					}
					validReservation = true;
				}
			}
			
			System.out.println(" DEBUGGING: devolviendo " + availableVans.size() + " vans");
			return availableVans;
			
		} catch (Exception e) {
			logger.error("   $ Error retrieving vans " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}

		return new ArrayList<Van>();
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationsByVan( String vanLicensePlate ) { 
 
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {
			logger.info("   * Retrieving all reservations from van: " + vanLicensePlate);
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<Reservation> query = pm.newQuery(Reservation.class);
			query.setFilter("van== '" + vanLicensePlate+ "'");
			
			// Java's error is due to a possible ClassCastException. In this case, it should not happen.
			List<Reservation> listOfReservations = (List<Reservation>)query.execute();
			
			return listOfReservations;
		} catch (Exception e) {
			logger.error("   $ Error retrieving vans with location: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		
		return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Van> getVansByLocation( String location ) { 
 
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {
			logger.info("   * Retrieving all vans from location: " + location);
			pm.getFetchPlan().setMaxFetchDepth(4);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<Van> query = pm.newQuery(Van.class);
			query.setFilter("location== '" + location+ "'");
			
			// Java's error is due to a possible ClassCastException. In this case, it should not happen.
			List<Van> listOfVans = (List<Van>)query.execute();
			
			List<Van> vans = new ArrayList<Van>();
			for (Van v : listOfVans) {
				Van van = new Van();
				van.setBrand(v.getBrand());
				van.setCapacity(v.getCapacity());
				van.setKitchen(v.hasKitchen());
				van.setLicensePlate(v.getLicensePlate());
				van.setLocation(v.getLocation());
				van.setModel(v.getModel());
				van.setOffRoad(v.isOffRoad());
				van.setPricePerDay(v.getPricePerDay());
				van.setShower(v.hasShower());
				van.setStatus(v.getStatus());
				van.setUser(v.getUser());
				vans.add(van);
			}
			return vans;
		} catch (Exception e) {
			logger.error("   $ Error retrieving vans with location: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		
		return null;
		
	}
	
	public List<Van> getVansByUser( String user) { 
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {
			logger.info("   * Retrieving all user's (" + user + ") vans");
			pm.getFetchPlan().setMaxFetchDepth(4);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<Van> query = pm.newQuery(Van.class);
			query.setFilter("user== '" + user+ "'");
			
			// Java's error is due to a possible ClassCastException. In this case, it should not happen.
			List<Van> listOfVans = (List<Van>)query.execute();
			
			List<Van> vans = new ArrayList<Van>();
			for (Van v : listOfVans) {
				Van van = new Van();
				van.setBrand(v.getBrand());
				van.setCapacity(v.getCapacity());
				van.setKitchen(v.hasKitchen());
				van.setLicensePlate(v.getLicensePlate());
				van.setLocation(v.getLocation());
				van.setModel(v.getModel());
				van.setOffRoad(v.isOffRoad());
				van.setPricePerDay(v.getPricePerDay());
				van.setShower(v.hasShower());
				van.setStatus(v.getStatus());
				van.setUser(v.getUser());
				vans.add(van);
			}
			return vans;
		} catch (Exception e) {
			logger.error("   $ Error retrieving vans by user: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		
		return null;
		
	}
	
	/**
	 * Method that searchs for a user given an email and password.
	 * @param user's email and password
	 * @return user if valid login, null otherwise
	 */
	public User validateLogin( String email, String password ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {			
			logger.info("   * Attempting loging for user: " + email);
			pm.getFetchPlan().setMaxFetchDepth(4);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<User> query = pm.newQuery(User.class);
			query.setFilter("email == '" + email+ "' && password == '" + password + "'");
			query.setUnique(true);
			
			User user = (User)query.execute(); // The UNIQUE query itself returns null if there are no results. 
			
			if( null == user ) {
				logger.error("   * Email or passord incorrect in login query" );
				return null;
			} else {
				logger.error("   * User retrieved from DB: " + user.getName());
			}
			
			User u = new User();
			
			u.setDni(user.getDni());
			u.setEmail(user.getEmail());
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			u.setStars(user.getStars());
			
			return u;
			
		} catch (Exception e) {
			logger.error("   $ Error retrieving user: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		return null;
	}
	
	////////////////////////////
	//		"GET IT ALL"      //
	////////////////////////////
//	/**
//	 * Method for debugging.
//	 * @return arraylist of all users
//	 */
//	public ArrayList<User> getAllUsers() {
//		PersistenceManager pm = pmf.getPersistenceManager();
//		pm.getFetchPlan().setMaxFetchDepth(2);
//		
//		Transaction tx = pm.currentTransaction();
//		
//
//		ArrayList<User> usersList = new ArrayList<>();
//
//		try {
//			tx.begin();
//			logger.info("   * Retrieving all Users");
//			//Extent<User> e = pm.getExtent(User.class, true);
////			Iterator<User> iter = pm.getExtent(User.class, true ).iterator();
////			while (iter.hasNext()) {
////				usersList.add(iter.next());
////			}
//			pm.getExtent(User.class, true).iterator().forEachRemaining(usersList::add); // Apparently faster.
//			tx.commit();
//		} catch (Exception e) {
//			logger.error("   $ Error retrieving all Users: " + e.getMessage());
//		} finally {
//			if (tx.isActive()) {
//				tx.rollback();
//			}
//			pm.close();
//		}
//
//		return usersList;
//	}
//
//	/**
//	 * Method for debugging.
//	 * @return arraylist of all vans
//	 */
//	public ArrayList<Van> getAllVans() {
//		PersistenceManager pm = pmf.getPersistenceManager();
//		pm.getFetchPlan().setMaxFetchDepth(2);
//
//		Transaction tx = pm.currentTransaction();
//
//		ArrayList<Van> vanList = new ArrayList<>();
//
//		try {
//			tx.begin();
//			logger.info("   * Retrieving all Vans");
//			//Extent<User> e = pm.getExtent(Van.class, true);
////			Iterator<User> iter = pm.getExtent(Van.class, true ).iterator();
////			while (iter.hasNext()) {
////				vanList.add(iter.next());
////			}
//			pm.getExtent(Van.class, true).iterator().forEachRemaining(vanList::add); // Apparently faster.
//			tx.commit();
//		} catch (Exception e) {
//			logger.error("   $ Error retrieving all Vans: " + e.getMessage());
//		} finally {
//			if (tx.isActive()) {
//				tx.rollback();
//			}
//			pm.close();
//		}
//
//		return vanList;
//	}
}
