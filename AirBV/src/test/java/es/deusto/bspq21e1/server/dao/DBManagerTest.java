package es.deusto.bspq21e1.server.dao;

import org.junit.*;

import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import junit.framework.JUnit4TestAdapter;

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
	private PersistenceManagerFactory pmf = null;
	/**
	* This variable represents the persistence manager instance
	*/
	private PersistenceManager pm = null;
	/**
	* This variable represents the transaction instance
	*/
	private Transaction tx = null;
	
	// If you use the EmptyReportModule, the report is not generated
	//@Rule public ContiPerfRule rule = new ContiPerfRule(new EmptyReportModule());
	//@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(DBManagerTest.class);
	}
	
	/**
	 * It initializes the variables used by the other methods
	 */
	@Before
	public void setUp() {
		logger.info("Entering setUp");
		// Code executed before each test    
		this.pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = this.pmf.getPersistenceManager();
        this.tx = this.pm.currentTransaction();
        
        User user1 = new User("1234567A", "userOne", "user1@gmail.com", "123");
        User user2 = new User("7654321Z", "userTwo", "user2@gmail.com", "321");
        Van van1 = new Van("1111ABC", "Volkswagen", "T25", "qwerty", true, false, false, 2, 100, "1234567A", new ArrayList<Review>() );
		
		logger.info("Leaving setUp");
	}
	
	@Test
	public void searchVanTest() {
		
	}
	
	/**
	 * Removes everything not needed after executing a test
	*/
	@After
    public void tearDown() throws Exception {
		
        if (this.pm != null) {
			this.pm.close();
		}
		
    }
}
