package es.deusto.bspq21e1.server.data;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation {
	@PrimaryKey
    private String code;
    private Date bookingDate;
    private int duration;
    
    private String van;
    private String vanRenter;

    public Reservation(Date bookingDate, int duration, String van, String vanRenter) {
    	this.code = bookingDate.getTime()+"van";
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

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getVan() {
		return van;
	}

	public void setVan(String van) {
		this.van = van;
	}

	public String getVanRenter() {
		return vanRenter;
	}

	public void setVanRenter(String vanRenter) {
		this.vanRenter = vanRenter;
	}
}
