package es.deusto.bspq21e1.serialization;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.*;

import junit.framework.JUnit4TestAdapter;

public class UserDataTest {
	
	static Logger logger = Logger.getLogger(UserDataTest.class.getName());
	
    String dni;
    String name;
    String email;
    String password;
    List<Integer> stars;
    List<Integer> starsEmpty;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(UserDataTest.class);
	}
	
	@Before
	public void setUp() {
		logger.info("Before tests code execution begins");
		dni = "12345678A";
		name = "Pepe";
		email = "pepe@gmail.com";
		password = "1234";
		
		starsEmpty = new ArrayList<Integer>();
		
		stars = new ArrayList<Integer>();
		stars.add(2);
		stars.add(4);
		stars.add(3);
		logger.info("Before tests code execution ends properly");
	}

	@SuppressWarnings("unused")
	@Test
	public void constructorTest() {
		UserData u = new UserData();
		assertEquals(u.getClass(), UserData.class);
		assertNull(u.getDni());
		assertNull(u.getName());
		assertNull(u.getEmail());
		assertNull(u.getPassword());
		assertNull(u.getStars());
		
		UserData u1 = new UserData(dni, name, email, password);
		assertEquals(u1.getClass(), UserData.class);
		assertEquals(dni, u1.getDni());
		assertEquals(name, u1.getName());
		assertEquals(email, u1.getEmail());
		assertEquals(password, u1.getPassword());
		logger.info("User constructor tested");
	}
	
	@Test
	public void setterGettersTest() {
		UserData u = new UserData();
		
		u.setDni(dni);
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setStars(starsEmpty);
		
		
		u.setStars(stars);
		
		assertEquals(u.getClass(), UserData.class);
		assertEquals(dni, u.getDni());
		assertEquals(name, u.getName());
		assertEquals(email, u.getEmail());
		assertEquals(password, u.getPassword());
		
		logger.info("User getters/setters tested");
	}

	@Test
	public void toStringTest() {
		UserData u1 = new UserData(dni, name, email, password);
		
		assertEquals("UserData [dni=" + dni + ", name=" + name + ", email=" + email + "]", u1.toString());
	}
}