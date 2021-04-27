package es.deusto.bspq21e1.server.dto;

import java.util.ArrayList;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.serialization.ReviewData;
import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;
import es.deusto.bspq21e1.server.data.Review;

public class Assembler {
    
    public UserData assembleUser(User u) {
		UserData userData = new UserData(u.getDni(), u.getName(), u.getName());
		return userData;
    }

	public ReservationData assembleReservation(Reservation r) {
		VanData vanData = assembleVan(r.getVan());
		UserData vanRenter = assembleUser(r.getVanRenter());
		
		ReservationData reservationData = new ReservationData(r.getCode() , r.getBookingDate(), r.getDuration(), vanData, vanRenter);
		return reservationData;
    }

    public VanData assembleVan(Van v) {
    	ArrayList<ReviewData> reviews = new ArrayList<ReviewData>();
    	if(v.getReviews() != null) {
    		for (Review r : v.getReviews()) {
    			reviews.add(assembleReview(r));
    		}
    	}
    	
    	VanData vanData = new VanData(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.getCapacity(), v.hasKitchen(), v.hasShower(), v.isOffRoad(),  v.getPricePerDay(), v.getUser(), reviews);
    	return vanData;	
    }
    
    public ReviewData assembleReview(Review r) {
		ReviewData reviewData = new ReviewData(r.getCode(), r.getComment());
		return reviewData;
    }

    public User disassembleUser(UserData u) {
    	User user = new User(u.getDni(), u.getName(), u.getEmail());
    	return user;

    }

    public Reservation disassembleReservation(ReservationData r) {
    	Van van = disassembleVan(r.getVan());
    	User user = disassembleUser(r.getVanRenter());
    	
    	Reservation reservationData = new Reservation(r.getBookingDate(), r.getDuration(), van, user);
    	return reservationData;
    }

    public Van disassembleVan(VanData v) {
    	ArrayList<Review> reviews = new ArrayList<Review>();
    	if(v.getReviews() != null) {
    		for (ReviewData r : v.getReviews()) {
    			reviews.add(disassembleReview(r));
    		}
    	}
    	
    	Van van = new Van(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.hasKitchen(), v.hasShower(), v.isOffRoad(), v.getCapacity(), v.getPricePerDay(), v.getUser(), reviews);
    	
		System.out.println("$ DEBUGGING\n" +
				"\tPrinting Van and User from Assembler in Server side:\n"+
				"\tVan: " + van +
				"\n\tUser: " + van.getUser().toString() +
				"\n=======================\n");
		
    	return van;
    	
    }

    public Review disassembleReview(ReviewData r) {
		Review review = new Review(r.getCode(), r.getComment());
		return review;
    }
}
