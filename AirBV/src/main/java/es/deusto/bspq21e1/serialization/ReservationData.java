package es.deusto.bspq21e1.serialization;

import java.util.Date;

/**
 * Class for the representation of the Reservation object which is going to be sended to clients.
 * @author SPQ Group 1
 * @version 1.0
 */
public class ReservationData {
    
    private String code;
    private Date bookingDate;
    private int duration;
    
    private VanData van;
    private UserData vanRenter;
    
	/**
	 * Creates the object that has the data from Reservation.
	 * @param code Code of the reservation.
	 * @param bookingDate Date of the reservation.
	 * @param duration Duration of the reservation in days.
	 * @param van Rented van.
	 * @param vanRenter Owner of the rented van.
	 */
    public ReservationData(String code, Date bookingDate, int duration, VanData van, UserData vanRenter) {
		super();
		this.code = code;
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
	public VanData getVan() {
		return van;
	}
	public void setVan(VanData van) {
		this.van = van;
	}
	public UserData getVanRenter() {
		return vanRenter;
	}
	public void setVanRenter(UserData vanRenter) {
		this.vanRenter = vanRenter;
	}
	
	@Override
	public String toString() {
		return "ReservationData [code=" + code + ", bookingDate=" + bookingDate + ", duration=" + duration + ", van="
				+ van + ", vanRenter=" + vanRenter + "]";
	}

}
