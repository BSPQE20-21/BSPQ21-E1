package es.deusto.bspq21e1.server.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.*;

import junit.framework.JUnit4TestAdapter;

public class UserTest {
	
	static Logger logger = Logger.getLogger(UserTest.class.getName());
	
    String dni;
    String name;
    String email;
    String password;
    List<Integer> stars;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(UserTest.class);
	}
	
	@Before
	public void setUp() {
		dni = "12345678A";
		name = "Pepe";
		email = "pepe@gmail.com";
		password = "1234";
		stars = new ArrayList<Integer>();
		stars.add(2);
		stars.add(4);
		stars.add(3);
		
	}

	@SuppressWarnings("unused")
	@Test
	public void constructorTest() {
		User u = new User();
		assertEquals(u.getClass(), User.class);
		assertNull(u.getDni());
		assertNull(u.getName());
		assertNull(u.getEmail());
		assertNull(u.getPassword());
		assertNull(u.getStars());
		
		User u1 = new User(dni, name, email, password);
		assertEquals(u1.getClass(), User.class);
		assertEquals(dni, u1.getDni());
		assertEquals(name, u1.getName());
		assertEquals(email, u1.getEmail());
		assertEquals(password, u1.getPassword());
	}
	
	@Test
	public void setterGettersTest() {
		User u = new User();
		
		u.setDni(dni);
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setStars(stars);
		
		assertEquals(u.getClass(), User.class);
		assertEquals(dni, u.getDni());
		assertEquals(name, u.getName());
		assertEquals(email, u.getEmail());
		assertEquals(password, u.getPassword());
		assertEquals(3, u.getStarsAverage());
	}

}