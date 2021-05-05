package es.deusto.bspq21e1.server.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.*;

import junit.framework.JUnit4TestAdapter;

public class ReviewTest {
	
	static Logger logger = Logger.getLogger(ReviewTest.class.getName());
	
    int code;
    int stars;
    String comment;
    String van;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReviewTest.class);
	}
	
	@Before
	public void setUp() {
		logger.info("Before tests code execution begins");
		code = 99;
		stars = 5;
		comment = "It is amazing.";
		van = "1245ABC";
		logger.info("Before tests code execution ends properly");
	}

	@SuppressWarnings("unused")
	@Test
	public void constructorTest() {
		Review r = new Review(stars, comment, van);
		
		assertEquals(stars, r.getStars());
		assertEquals(comment, r.getComment());
		assertEquals(van, r.getVan());
		
		Review r1 = new Review(code, stars, comment, van);
		
		assertEquals(code, r1.getCode());
		assertEquals(stars, r1.getStars());
		assertEquals(comment, r1.getComment());
		assertEquals(van, r1.getVan());
		logger.info("Review constructor tested");
	}
	
	@Test
	public void setterGettersTest() {
		Review r = new Review(stars, comment, van);
		
		r.setCode(0);
		r.setComment("");
		r.setStars(0);
		r.setVan("");
		
		assertEquals(0, r.getCode());
		assertEquals(0, r.getStars());
		assertEquals("", r.getComment());
		assertEquals("", r.getVan());
		logger.info("Review getters/setters tested");
	}

}
