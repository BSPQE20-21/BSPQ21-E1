package es.deusto.bspq21e1.server.data;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation {
	// Primary key is generated as AUTOINCREMENT.
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String code;
    private String bookingDate;
    private int duration;
    
    private Van van;
    private User vanRenter;

    public Reservation(String bookingDate, int duration, Van van, User vanRenter) {
    	this.bookingDate = bookingDate;
        this.duration = duration;
        this.van = van;
        this.vanRenter = vanRenter;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Van getVan() {
		return van;
	}

	public void setVan(Van van) {
		this.van = van;
	}

	public User getVanRenter() {
		return vanRenter;
	}

	public void setVanRenter(User vanRenter) {
		this.vanRenter = vanRenter;
	}
}
