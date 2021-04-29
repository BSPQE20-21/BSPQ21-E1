package es.deusto.bspq21e1.server.dao;

import org.junit.*;

import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import junit.framework.JUnit4TestAdapter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;

import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//@PerfTest(invocations = 5)
//@Required(max = 1200, average = 250)
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
	
	// If you use the EmptyReportModule, the report is not generated
	//@Rule public ContiPerfRule rule = new ContiPerfRule(new EmptyReportModule());
	//@Rule public ContiPerfRule rule = new ContiPerfRule();
	
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
        van1 = new Van("1111ABC", "Volkswagen", "T25", "qwerty", true, false, false, 2, 100, "1234567A", new ArrayList<Review>() );
        
        DBManager.getInstance().store(user1);
		DBManager.getInstance().store(user2);
		DBManager.getInstance().store(van1);
		
		logger.info("Leaving setUp");
	}
	
	@Test
	public void validateUserTest() {
		logger.info("Testing validation of users");
		
		User user = DBManager.getInstance().validateLogin("user1@gmail.com", "123");
		
		assertEquals(user1.getClass(), user.getClass());
		assertEquals(user1.getDni(), user.getDni());
		assertEquals(user1.getEmail(), user.getEmail());
		assertEquals(user1.getName(), user.getName());
		assertEquals(user1.getPassword(), user.getPassword());
		
		logger.info("Validation of users tested");
	}
	
	@Test
	public void searchVanTest() {
		logger.info("Testing searching of vans");
		
//		assertEquals(van1, DBManager.getInstance().getVansByLocation("qwerty"));
		
		logger.info("Searching of vans tested");
	}
	
	/**
	 * Removes everything not needed after executing a test
	*/
	@AfterClass
    public static void tearDown() throws Exception {
		
		DBManager.getInstance().deleteUser(user1.getDni());
		DBManager.getInstance().deleteUser(user2.getDni());
		
        if (pm != null) {
			pm.close();
		}
		
    }
}
