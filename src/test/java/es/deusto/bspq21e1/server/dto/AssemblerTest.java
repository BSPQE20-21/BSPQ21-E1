package es.deusto.bspq21e1.server.dto;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.ReviewData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

public class AssemblerTest {
	static Logger logger = Logger.getLogger(AssemblerTest.class.getName());
	private Assembler as;
	
	@Before
	public void setUp() {
		as = new Assembler();
	}

	@Test
	public void assembleUserTest() {
		User u = new User("12345678A", "Pepa", "pepa@gmail.com", "1234");
		UserData userData = as.assembleUser(u);
		
		assertEquals(UserData.class, userData.getClass());
		assertEquals(u.getDni(), userData.getDni());
		assertEquals(u.getName(), userData.getName());
		assertEquals(u.getEmail(), userData.getEmail());
		assertEquals(u.getPassword(), userData.getPassword());
		
		logger.info("Assemble user method tested");
		
    }

	@Test
	public void assembleReservation() {		
		Reservation r = new Reservation(new Date(), 3, "1234ABC", "12345678A");
		ReservationData resData = as.assembleReservation(r);
		
		assertEquals(ReservationData.class, resData.getClass());
		assertEquals(r.getBookingDate(), resData.getBookingDate());
		assertEquals(r.getCode(), resData.getCode());
		assertEquals(r.getDuration(), resData.getDuration());
		assertEquals(r.getVan(), resData.getVan());
		assertEquals(r.getVanRenter(), resData.getVanRenter());
		
		logger.info("Assemble reservation method tested");
    }

	@Test
    public void assembleVan() {
    	Van v = new Van("1234ABC", "Ford", "Focus", "Bilbao", true, false, true, 5,  45, "12345678A");
    	VanData vanData = as.assembleVan(v);
    	
    	assertEquals(VanData.class, vanData.getClass());
    	assertEquals(v.getBrand(), vanData.getBrand());
    	assertEquals(v.getCapacity(), vanData.getCapacity());
    	assertEquals(v.getLicensePlate(), vanData.getLicensePlate());
    	assertEquals(v.getLocation(), vanData.getLocation());
    	assertEquals(v.getModel(), vanData.getModel());
    	assertTrue(v.getPricePerDay() == vanData.getPricePerDay());
    	assertEquals(v.getUser(), vanData.getUser());
    	
    	logger.info("Assemble van method tested");
    }
    
	@Test
    public void assembleReview() {
		Review r = new Review(0001, "I liked it", "1234ABC");
		ReviewData revData = as.assembleReview(r);
		
		assertEquals(ReviewData.class, revData.getClass());
		assertEquals(r.getCode(), revData.getCode());
		assertEquals(r.getComment(), revData.getComment());
		assertEquals(r.getStars(), revData.getStars());
		assertEquals(r.getVan(), revData.getVan());
		
		logger.info("Assemble review method tested");
    }

	@Test
    public void disassembleUserTest() {
		UserData u = new UserData("12345678A", "Pepa", "pepa@gmail.com", "1234");
		User user = as.disassembleUser(u);
		
		assertEquals(User.class, user.getClass());
		assertEquals(u.getDni(), user.getDni());
		assertEquals(u.getName(), user.getName());
		assertEquals(u.getEmail(), user.getEmail());
		assertEquals(u.getPassword(), user.getPassword());
		
		logger.info("Disassemble user method tested");
    }

	@Test
    public void disassembleReservation() {    	
		ReservationData r = new ReservationData(new Date(), 3, "1234ABC", "12345678A");
		Reservation res = as.disassembleReservation(r);
		
		assertEquals(Reservation.class, res.getClass());
		assertEquals(r.getBookingDate(), res.getBookingDate());
		assertEquals(r.getCode(), res.getCode());
		assertEquals(r.getDuration(), res.getDuration());
		assertEquals(r.getVan(), res.getVan());
		assertEquals(r.getVanRenter(), res.getVanRenter());
		
		logger.info("Disassemble reservation method tested");
    }

	@Test
    public void disassembleVan() {
		VanData v = new VanData("1234ABC", "Ford", "Focus", "Bilbao", 5, true, false, true,  45, "12345678A");
    	Van van = as.disassembleVan(v);
    	
    	assertEquals(Van.class, van.getClass());
    	assertEquals(v.getBrand(), van.getBrand());
    	assertEquals(v.getCapacity(), van.getCapacity());
    	assertEquals(v.getLicensePlate(), van.getLicensePlate());
    	assertEquals(v.getLocation(), van.getLocation());
    	assertEquals(v.getModel(), van.getModel());
    	assertTrue(v.getPricePerDay() == van.getPricePerDay());
    	assertEquals(v.getUser(), van.getUser());
    	assertEquals("Open", van.getStatus());
    	
    	logger.info("Disassemble van method tested");
    	
    }

	@Test
    public void disassembleReview() {
		ReviewData r = new ReviewData(0001, "I liked it", "1234ABC");
		Review rev = as.disassembleReview(r);
		
		assertEquals(Review.class, rev.getClass());
		assertEquals(r.getCode(), rev.getCode());
		assertEquals(r.getComment(), rev.getComment());
		assertEquals(r.getStars(), rev.getStars());
		assertEquals(r.getVan(), rev.getVan());
		
		logger.info("Disassemble review method tested");
    }

}
