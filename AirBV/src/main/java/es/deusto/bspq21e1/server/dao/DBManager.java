package es.deusto.bspq21e1.server.dao;

import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

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

	private void storeObject( Object object ) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing an object: " + object);
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}	
			pm.close();
		}
	}

	
	
	
	/**
	 * Method for debugging.
	 * @return arraylist of all users
	 */
	public ArrayList<User> getAllUsers() {
		PersistenceManager pm = pmf.getPersistenceManager();		
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
		} catch (Exception ex) {
			System.out.println("   $ Error retrieving all Users: " + ex.getMessage());
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
		} catch (Exception ex) {
			System.out.println("   $ Error retrieving all Vans: " + ex.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return vanList;
	}
	
	public static void main(String[] args) {
		
		User u1 = new User("22", "Iñigo", "imarcosenciso@opendeusto.es");		
		instance = getInstance();
		instance.store(u1);
		
		
		Van v1 = new Van("123ABC", "Ferrari", "F5", "Bilbao", true, true, true, 1, 222.22, u1);
		instance.store(v1);
		
	}
}
