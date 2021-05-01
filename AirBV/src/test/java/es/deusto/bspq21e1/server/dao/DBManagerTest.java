package es.deusto.bspq21e1.server.dao;

import org.junit.*;

import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import junit.framework.JUnit4TestAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;

import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DBManagerTest {

	static Logger logger = Logger.getLogger(DBManagerTest.class.getName());
	/**
	 * This variable represents the persistence manager factory instance
	 */
	private static PersistenceManagerFactory pmf = null;
	/**
	* This variable represents the persistence manager instance
	*/
	private static PersistenceManager pm = null;
	/**
	* This variable represents the transaction instance
	*/
	private static Transaction tx = null;
	
	private static User user1;
	private static User user2;
	private static Van van1;
	private static Reservation res1;
	private static Review rev1;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(DBManagerTest.class);
	}
	
	/**
	 * It initializes the variables used by the other methods
	 */
	@BeforeClass
	public static void setUp() {
		logger.info("Entering setUp");
		// Code executed before each test    
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        
        user1 = new User("1234567A", "userOne", "user1@gmail.com", "123");
        user2 = new User("7654321Z", "userTwo", "user2@gmail.com", "321");
        van1 = new Van("1111ABC", "Volkswagen", "T25", "qwerty", true, false, false, 2, 100, user1.getDni());
        rev1 = new Review(5, "It is wonderful", van1.getLicensePlate());
        
        Date date = null;
        try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse("30/09/2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        res1 = new Reservation(date, 3, van1.getLicensePlate(), user1.getDni());
        
        assertTrue(DBManager.getInstance().store(user1));
		assertTrue(DBManager.getInstance().store(user2));
		assertTrue(DBManager.getInstance().store(van1));
		assertTrue(DBManager.getInstance().store(res1));
		assertTrue(DBManager.getInstance().store(rev1));
		
		logger.info("Leaving setUp");
	}
	
	@Test
	public void validateUserTest() {
		logger.info("Testing validation of users");
		
		assertEquals(user1, DBManager.getInstance().validateLogin("user1@gmail.com", "123"));
		assertEquals(null, DBManager.getInstance().validateLogin("user2@gmail.com", "123"));
		
		logger.info("Validation of users tested");
	}
	
	@Test
	public void getReservationsByUser() {
		logger.info("Testing getting reservations by user");
		
		assertEquals(1, DBManager.getInstance().getReservationsByUser("1234567A").size());
		assertEquals(0, DBManager.getInstance().getReservationsByUser("7654321Z").size());
		
		logger.info("Getting reservations by users tested");
	}
	
	@Test
	public void getVansByUser() {
		logger.info("Testing getting vans by user");
		
		assertEquals(1, DBManager.getInstance().getVansByUser("1234567A").size());
		assertEquals(0, DBManager.getInstance().getVansByUser("7654321Z").size());
		
		logger.info("Getting vans by users tested");
	}
	
	@Test
	public void searchVanTest() {
		logger.info("Testing searching of vans");
		
		assertEquals(van1, DBManager.getInstance().getVansByLocation("qwerty").get(0));
		
		logger.info("Searching of vans tested");
	}
	
	@Test
	public void getUserTest() {
		logger.info("Testing getting a user");
		
		assertEquals(user1, DBManager.getInstance().getUser("1234567A"));
		assertNull(DBManager.getInstance().getUser("1236547A"));
		
		logger.info("Getting a user tested");
	}
	
	/**
	 * Removes everything not needed after executing a test
	*/
	@AfterClass
    public static void tearDown() throws Exception {
		
		assertTrue(DBManager.getInstance().deleteReservation(res1.getCode()));
		assertFalse(DBManager.getInstance().deleteReservation(res1.getCode()));
		
		assertTrue(DBManager.getInstance().deleteVan(van1.getLicensePlate()));
		assertFalse(DBManager.getInstance().deleteVan("54154"));
		
		assertTrue(DBManager.getInstance().deleteUser(user1.getDni()));
		assertTrue(DBManager.getInstance().deleteUser(user2.getDni()));
		assertFalse(DBManager.getInstance().deleteUser("148419814"));
		
        if (pm != null) {
			pm.close();
		}
		
    }
}
