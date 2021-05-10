package es.deusto.bspq21e1.server.data;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.*;

import es.deusto.bspq21e1.server.dao.DBManagerTest;
import junit.framework.JUnit4TestAdapter;

public class ReservationTest {
	
	static Logger logger = Logger.getLogger(DBManagerTest.class.getName());
	
	Date date;
	String code;
	int duration;
	String dni;
	String licensePlate;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReservationTest.class);
	}
	
	@Before
	public void setUp() {
		logger.info("Before tests code execution begins");
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse("30-09-2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		code = "AAAA";
		duration = 3;
		dni = "2145ABC";
		licensePlate = "12345678";
		logger.info("Before tests code execution ends properly");
	}

	@SuppressWarnings("unused")
	@Test
	public void constructorTest() {
		Reservation r = new Reservation();
		assertEquals(r.getClass(), Reservation.class);
		assertNull(r.getBookingDate());
		assertNull(r.getCode());
		assertEquals(0, r.getDuration());
		assertNull(r.getVan());
		assertNull(r.getVanRenter());
		
		Reservation r1 = new Reservation(date, duration, licensePlate, dni);
		assertEquals(r1.getClass(), Reservation.class);
		assertEquals(date, r1.getBookingDate());
		assertEquals(date.getTime() + licensePlate, r1.getCode());
		assertEquals(duration, r1.getDuration());
		assertEquals(licensePlate, r1.getVan());
		assertEquals(dni, r1.getVanRenter());
		logger.info("Reservation constructor tested");
	}
	
	@Test
	public void setterGettersTest() {
		Reservation r = new Reservation();
		
		r.setBookingDate(date);
		r.setCode(code);
		r.setDuration(duration);
		r.setVan(licensePlate);
		r.setVanRenter(dni);
		
		assertEquals(r.getClass(), Reservation.class);
		assertEquals(date, r.getBookingDate());
		assertEquals(code, r.getCode());
		assertEquals(duration, r.getDuration());
		assertEquals(licensePlate, r.getVan());
		assertEquals(dni, r.getVanRenter());
		logger.info("Reservation getters/setters tested");
	}

}
