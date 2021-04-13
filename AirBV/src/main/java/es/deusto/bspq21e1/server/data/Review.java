package es.deusto.bspq21e1.server.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Review {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private int code;
    private int stars;
    private String comment;

    public Review(int stars, String comment) {
        this.code = (int)Math.random()*10000;
        this.stars = stars;
        this.comment = comment;
    }

	public Review(int code, int stars, String comment) {
		this.code = code;
		this.stars = stars;
		this.comment = comment;
	}

	// Getters and setters
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	//To String
	@Override
	public String toString() {
		return "Review [code=" + code + ", stars=" + stars + ", comment=" + comment + "]";
	}

    
}
