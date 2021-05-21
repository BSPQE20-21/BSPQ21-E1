package es.deusto.bspq21e1.serialization;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;

import junit.framework.JUnit4TestAdapter;

public class ReviewDataTest {
	
	static Logger logger = Logger.getLogger(ReviewDataTest.class.getName());
	
    int code;
    int stars;
    String comment;
    String van;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReviewDataTest.class);
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
		ReviewData r = new ReviewData(stars, comment, van);
		
		assertEquals(stars, r.getStars());
		assertEquals(comment, r.getComment());
		assertEquals(van, r.getVan());
		
		ReviewData r1 = new ReviewData(code, stars, comment, van);
		
		assertEquals(code, r1.getCode());
		assertEquals(stars, r1.getStars());
		assertEquals(comment, r1.getComment());
		assertEquals(van, r1.getVan());
		logger.info("Review constructor tested");
	}
	
	@Test
	public void setterGettersTest() {
		ReviewData r = new ReviewData(stars, comment, van);
		
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
	
	@Test
	public void toStringTest() {
		ReviewData r = new ReviewData(stars, comment, van);
		
		assertEquals("ReviewData [code=" + r.getCode() + ", stars=" + stars + ", comment=" + comment +", van=" + van + "]", r.toString());
	}

}
