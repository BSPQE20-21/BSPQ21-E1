package es.deusto.bspq21e1.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;

import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;



/**
 * Singleton DBManager class.
 * @author 		Iñigo Marcos <imarcosenciso@opendeusto.es>
 * @version		1.0-SNAPSHOT
 */
public class DBManager {

	private static DBManager instance = null;
	private PersistenceManagerFactory pmf;

	private DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Method that creates a DBManager instance if needed and returns it.
	 * @return DBManager instance
	 */
	public static DBManager getInstance() {
		if ( null==instance ) instance = new DBManager();

		return instance;
	}


	////////////////////////////
	//		STORE OBJECT      //
	////////////////////////////
	/**
	 * Stores a user in the DB (makes the object persistent).
	 * @param user
	 */
	public void store( User user ) {
		this.storeObject( user );
	}

	/**
	 * Stores a van in the DB (makes the object persistent).
	 * @param van
	 */
	public void store( Van van ) {
		this.storeObject( van );
	}

	/**
	 * Stores a review in the DB (makes the object persistent).
	 * @param review
	 */
	public void store( Review review ) {
		this.storeObject( review );
	}

	/**
	 * Stores a reservation in the DB (makes the object persistent).
	 * @param reservation
	 */
	public void store( Reservation reservation ) {
		this.storeObject( reservation );
	}
	
	/**
	 * Necessary for all of the store functions to work.
	 * @param object
	 */
	private void storeObject( Object object ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing an object: " + object);
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception e) {
			System.out.println("   $ Error storing an object: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
	}
	
	//////////////////////////////
	//		DELETE OBJECT      //
	//////////////////////////////
	/**
	 * Deletes a reservation from the DB.
	 * @param reservation
	 */
	public void deleteReservation( Reservation reservation ) {
		this.deleteObject( reservation );
	}
	
	public void deleteUser(String dni) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Necessary for all of the delete functions to work.
	 * @param object
	 */
	private void deleteObject( Object object ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			System.out.println("   * Deleting an object: " + object);
			pm.deletePersistent(object);
			tx.commit();
		} catch (Exception e) {
			System.out.println("   $ Error deleting an object: " + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
	}

	
	
	////////////////////////////////
	//		SPECIFIC QUERIES      //
	////////////////////////////////
	public User getUser( String dni) { 
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {			
			System.out.println("   * Retrieving user with dni: " + dni);
			pm.getFetchPlan().setMaxFetchDepth(4);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<User> query = pm.newQuery(User.class);
			query.setFilter("dni== '" + dni+ "'");
			query.setUnique(true);
			
			// Java's error is due to a possible ClassCastException. In this case, it should not happen.
			@SuppressWarnings("unchecked")
			User user = (User)query.execute();
			System.out.println("User retrieved from DB: " + user.getName());
			
			return user;
		} catch (Exception e) {
			System.out.println("   $ Error retrieving user: " + e.getMessage() );
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
	public List<Reservation> getReservationsByUser( User vanRenter ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		List<Reservation> listOfReservations = new ArrayList<Reservation>();
		try {
			System.out.println("   * Retrieving all reservations from van renter " + vanRenter.getName());
			pm.getFetchPlan().setMaxFetchDepth(2);
			tx = pm.currentTransaction();
			tx.begin();
			
			Query<Reservation> query = pm.newQuery(Reservation.class);
			query.setFilter("vanRenter== " + vanRenter); 
			
			// Java's error is due to a possible ClassCastException. In this case, it should not happen.
			listOfReservations = (List<Reservation>)query.execute();

		} catch (Exception e) {
			System.out.println("   $ Error retrieving reservations from van renter: " + e.getMessage() );
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
		
		return listOfReservations;
	}
	
	public List<Van> getVansByLocation( String location) { 
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = null;
		
		try {
			System.out.println("   * Retrieving all vans from location: " + location);
			pm.getFetchPlan().setMaxFetchDepth(2);
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
				van.setReviews(v.getReviews());
				van.setShower(v.hasShower());
				van.setStatus(v.getStatus());
				van.setUser(v.getUser());
				vans.add(van);
				System.out.println("DBMAnager: " + van);
			}
			return vans;
		} catch (Exception e) {
			System.out.println("   $ Error retrieving vans with location: " + e.getMessage() );
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
	/**
	 * Method for debugging.
	 * @return arraylist of all users
	 */
	public ArrayList<User> getAllUsers() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		
		Transaction tx = pm.currentTransaction();
		

		ArrayList<User> usersList = new ArrayList<>();

		try {
			tx.begin();
			System.out.println("   * Retrieving all Users");
			//Extent<User> e = pm.getExtent(User.class, true);
//			Iterator<User> iter = pm.getExtent(User.class, true ).iterator();
//			while (iter.hasNext()) {
//				usersList.add(iter.next());
//			}
			pm.getExtent(User.class, true).iterator().forEachRemaining(usersList::add); // Apparently faster.
			tx.commit();
		} catch (Exception e) {
			System.out.println("   $ Error retrieving all Users: " + e.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return usersList;
	}

	/**
	 * Method for debugging.
	 * @return arraylist of all vans
	 */
	public ArrayList<Van> getAllVans() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);

		Transaction tx = pm.currentTransaction();

		ArrayList<Van> vanList = new ArrayList<>();

		try {
			tx.begin();
			System.out.println("   * Retrieving all Vans");
			//Extent<User> e = pm.getExtent(Van.class, true);
//			Iterator<User> iter = pm.getExtent(Van.class, true ).iterator();
//			while (iter.hasNext()) {
//				vanList.add(iter.next());
//			}
			pm.getExtent(Van.class, true).iterator().forEachRemaining(vanList::add); // Apparently faster.
			tx.commit();
		} catch (Exception e) {
			System.out.println("   $ Error retrieving all Vans: " + e.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return vanList;
	}
	
	public static void main(String[] args) {
		
		/* IMPORTANT!!
		 * PROYECT NEEDS TO BE COMPILED IN MAVEN IN ORDER FOR OBJECT PERSISTANCE TO WORK!! */
		
		User u1 = new User("22", "Iñigo", "imarcosenciso@opendeusto.es", "111");		
		instance = getInstance();
		instance.store(u1);
		
		
		Van v1 = new Van("123ABC", "Ferrari", "F5", "Bilbao", true, true, true, 1, 222.22, u1.getDni(), new ArrayList<Review>());
		instance.store(v1);
		
	}
}
