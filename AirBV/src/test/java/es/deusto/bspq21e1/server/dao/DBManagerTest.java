package es.deusto.bspq21e1.server.dao;

import org.junit.*;

import junit.framework.JUnit4TestAdapter;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;

import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
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
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
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
		logger.info("Leaving setUp");
	}
}
