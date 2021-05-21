package es.deusto.bspq21e1.server.data;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;

import junit.framework.JUnit4TestAdapter;

public class VanTest {
	
	static Logger logger = Logger.getLogger(VanTest.class.getName());
	
	String licensePlate;
    String brand;
    String model;
    String status;
    String location;
    boolean kitchen;
    boolean shower;
    boolean offRoad;
    int capacity;
    double pricePerDay;
    String user;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(VanTest.class);
	}
	
	@Before
	public void setUp() {
		logger.info("Before tests code execution begins");
		licensePlate = "1245ABC";
	    brand = "Ford";
	    model = "Focus";
	    status = "OK";
	    location = "Bilbao";
	    kitchen = true;
	    shower = true;
	    offRoad = true;
	    capacity = 5;
	    pricePerDay = 50;
	    user = "12345678A";
	    logger.info("Before tests code execution ends properly");
	}

	@SuppressWarnings("unused")
	@Test
	public void constructorTest() {
		Van v = new Van();
		
		assertNull(v.getBrand());
		assertNull(v.getLicensePlate());
		assertNull(v.getLocation());
		assertNull(v.getModel());
		assertNull(v.getStatus());
		assertNull(v.getUser());
		assertEquals(0, v.getCapacity());
		assertEquals(0.0, v.getPricePerDay(), 0.00000001);
		assertFalse(v.isShower());
		assertFalse(v.isKitchen());
		assertFalse(v.isOffRoad());
		
		Van v1 = new Van(licensePlate, brand, model, location, kitchen, shower, offRoad, capacity, pricePerDay, user);
		
		assertEquals(brand, v1.getBrand());
		assertEquals(licensePlate, v1.getLicensePlate());
		assertEquals(location, v1.getLocation());
		assertEquals(model, v1.getModel());
		assertEquals("Open", v1.getStatus());
		assertEquals(user, v1.getUser());
		assertEquals(capacity, v1.getCapacity());
		assertEquals(pricePerDay, v1.getPricePerDay(), 0.00000001);
		assertTrue(v1.isShower());
		assertTrue(v1.isKitchen());
		assertTrue(v1.isOffRoad());
		logger.info("Reservation constructor tested");
	}
	
	@Test
	public void equalsTest() {
		Van v1 = new Van(licensePlate, brand, model, location, kitchen, shower, offRoad, capacity, pricePerDay, user);
		Van v2 = new Van(licensePlate, brand, model, location, kitchen, shower, offRoad, capacity, pricePerDay, user);
		
		assertEquals(v1, v2);
	}

}

