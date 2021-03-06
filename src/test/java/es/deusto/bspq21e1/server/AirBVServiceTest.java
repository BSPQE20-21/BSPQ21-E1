package es.deusto.bspq21e1.server;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

public class AirBVServiceTest {
	
	static Logger logger = Logger.getLogger(AirBVServiceTest.class.getName());
	
	AirBVService service;
	String userDni;
	String userDni2;
	String userName;
	String userEmail;
	String userEmail2;
	String userPass;
	User u;
	Van van;
	Van van2;
	Date date1 = null;
	Date date2 = null;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();

	@Before
	public void setUp() throws Exception {
		logger.info("Before tests code execution begins");
		service = new AirBVService();
		
		userDni = "00000001A";
		userDni2 = "00000002A";
		userName = "Rita";
		userEmail = "rita@gmail.com";
		userEmail2 = "rita2@gmail.com";
		userPass = "123";
		u = new User();
		u.setDni(userDni2);
		van = new Van("1234ABC", "Ford", "Focus", "Bilbao", true, true, true, 3, 50, userDni);
		van2 = new Van("1235ABC", "Ford", "Focus", "Bilbao",  true, true, true, 3, 50, userDni2);
		
        try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
        try {
			date2 = new SimpleDateFormat("dd-MM-yyyy").parse("20-10-2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
        assertTrue(service.registerUser(userDni, userName, userEmail, userPass));
        assertTrue(service.registerUser(userDni2, userName, userEmail2, userPass));
        
        assertTrue(service.registerVan(van));
        assertTrue(service.registerVan(van2));
    	
    	van.setUser("7777");
    	assertFalse(service.registerVan(van));
    	van.setUser("userDni");
    	
    	assertTrue(service.registerReservation(date1, 3, van.getLicensePlate(), u.getDni()));
    	assertFalse(service.registerReservation(date1, 3, van.getLicensePlate(), new User().getDni()));
    	assertFalse(service.registerReservation(date1, 3, "0000CCC", u.getDni()));
    	
    	logger.info("Before tests code execution ends properly");
	}

	@After
	public void tearDown() throws Exception {
		logger.info("After tests code execution begins");
		assertTrue(service.cancelReservation(date1.getTime()+van.getLicensePlate()));
    	assertFalse(service.cancelReservation(date1.getTime()+"ABC"));
    	
    	assertTrue(service.deleteVan(van2.getLicensePlate()));
    	assertFalse(service.deleteVan("8754ACB"));
    	
    	assertTrue(service.deleteUser("00000001A"));
		assertTrue(service.deleteUser("00000002A"));
		assertEquals(0, service.searchVans("Bilbao", "10-10-2021", "20-10-2021").size());
		
		logger.info("After tests code execution begins properly");
	}

	@Test
	@PerfTest(invocations = 5, threads = 20)
	@Required(max = 1200, average = 600)
	public void searchVansTest() {
		assertEquals(1, service.searchVans("Bilbao", "10-10-2021", "20-10-2021").size());
		assertEquals(0, service.searchVans("Madrid", "10-10-2021", "20-10-2021").size());
		logger.info("Test of searchVans done");
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 20)
	@Required(max = 1200, average = 600)
	public void searchVansByLocationTest() {
		assertEquals(2, service.searchVansByLocation("Bilbao").size());
		assertEquals(0, service.searchVansByLocation("Madrid").size());
		logger.info("Test of searchVans done");
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 20)
	@Required(max = 750, average = 500)
	public void loginTest() {
		User us = service.login(userEmail2, userPass);
		assertEquals(userDni2, us.getDni());
		
		us = service.login(userEmail, "321");
		assertNull(us);
		logger.info("Test of login done");
	}

	@Test
	@PerfTest(invocations = 5, threads = 20)
	@Required(max = 600, average = 250)
	public void getUserReservationsTest() {
		assertEquals(1, service.getUserReservations(u.getDni()).size());
		assertEquals(0, service.getUserReservations(new UserData().getDni()).size());
		logger.info("Test of getUserReservations done");
	}
	
	@Test
	@PerfTest(invocations = 5, threads = 20)
	@Required(max = 600, average = 250)
	public void getUserVans() {
		assertEquals(1, service.getUserVans(userDni).size());
		assertEquals(0, service.getUserVans("9516203").size());
		logger.info("Test of getUserVans done");
	}

}
