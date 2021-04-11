package es.deusto.bspq21e1.serialization;

import java.util.Date;

public class ReservationData {
    
    private String code;
    private Date bookingDate;
    private int duration;
    
    private VanData van;
    private UserData vanRenter;
    
    public ReservationData(String code, Date bookingDate, int duration, VanData van, UserData vanRenter) {
		super();
		this.code = code;
		this.bookingDate = bookingDate;
		this.duration = duration;
		this.van = van;
		this.vanRenter = vanRenter;
	}
	//GETTERS and SETTERS
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
	
	//To String
	@Override
	public String toString() {
		return "ReservationData [code=" + code + ", bookingDate=" + bookingDate + ", duration=" + duration + ", van="
				+ van + ", vanRenter=" + vanRenter + "]";
	}

	
}
