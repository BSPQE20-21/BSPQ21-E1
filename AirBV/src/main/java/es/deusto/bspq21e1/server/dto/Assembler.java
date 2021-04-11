package es.deusto.bspq21e1.server.dto;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

public class Assembler {
    
    public UserData assembleUser(User u) {
		UserData userData = new UserData(u.getDni(), u.getName(), u.getName());
		return userData;
    }

	public ReservationData assembleReservation(Reservation r) {
		VanData vanData = assembleVan(r.getVan());
		UserData vanRenter = assembleUser(r.getVanRenter());
		ReservationData reservationData = new ReservationData(r.getCode() ,r.getBookingDate(), r.getDuration(), vanData, vanRenter);
		return reservationData;
    }

    public VanData assembleVan(Van v) {
    	UserData userData = assembleUser(v.getOwner());
    	VanData vanData = new VanData(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.getCapacity(), v.hasKitchen(), v.hasShower(), v.isOffRoad(),  v.getPricePerDay(), userData);
    	return vanData;	
    }

    /*public ReviewDTO assembleReview(Review re) {
        
    }*/

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
    	User user = disassembleUser(v.getOwner());
    	Van van = new Van(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.hasKitchen(), v.hasShower(), v.isOffRoad(), v.getCapacity(), v.getPricePerDay(), user);
    	return van;
    }

    /*public Review disassembleReview(ReviewDTO re) {
        
    }*/
}
