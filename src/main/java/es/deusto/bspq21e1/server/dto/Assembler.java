package es.deusto.bspq21e1.server.dto;

import org.apache.log4j.Logger;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.serialization.ReviewData;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.data.Review;

public class Assembler {
    
	private static Logger logger = Logger.getLogger(Assembler.class.getName());
	
    public UserData assembleUser(User u) {
		UserData userData = new UserData(u.getDni(), u.getName(), u.getEmail(), u.getPassword());
		logger.info("User assembled");
		return userData;
    }

	public ReservationData assembleReservation(Reservation r) {		
		ReservationData reservationData = new ReservationData(r.getCode() , r.getBookingDate(), r.getDuration(), r.getVan(), r.getVanRenter());
		logger.info("Reservation assembled");
		return reservationData;
    }

    public VanData assembleVan(Van v) {
    	VanData vanData = new VanData(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.getCapacity(), v.isKitchen(), v.isShower(), v.isOffRoad(),  v.getPricePerDay(), v.getUser());
    	logger.info("Van assembled");
    	return vanData;	
    }
    
    public ReviewData assembleReview(Review r) {
		ReviewData reviewData = new ReviewData(r.getCode(), r.getStars(), r.getComment(), r.getVan());
		logger.info("Review assembled");
		return reviewData;
    }

    public User disassembleUser(UserData u) {
    	User user = new User(u.getDni(), u.getName(), u.getEmail(), u.getPassword());
    	logger.info("User disassembled");
    	return user;

    }

    public Reservation disassembleReservation(ReservationData r) {    	
    	Reservation reservationData = new Reservation(r.getBookingDate(), r.getDuration(), r.getVan(), r.getVanRenter());
    	logger.info("Reservation disassembled");
    	return reservationData;
    }

    public Van disassembleVan(VanData v) {
    	Van van = new Van(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.isKitchen(), v.isShower(), v.isOffRoad(), v.getCapacity(), v.getPricePerDay(), v.getUser());
    	
		logger.debug("$ DEBUGGING\n" +
				"\tPrinting Van and User from Assembler in Server side:\n"+
				"\tVan: " + van +
				"\n\tUser: " + van.getUser().toString() +
				"\n=======================\n");
		logger.info("Van disassembled");
    	return van;
    	
    }

    public Review disassembleReview(ReviewData r) {
		Review review = new Review(r.getCode(), r.getStars(), r.getComment(), r.getVan());
		logger.info("Review disassembled");
		return review;
    }
}
