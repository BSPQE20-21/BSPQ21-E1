package es.deusto.bspq21e1.server.dto;

import es.deusto.bspq21e1.server.data.Reservation;
import es.deusto.bspq21e1.server.data.Review;
import es.deusto.bspq21e1.server.data.User;
import es.deusto.bspq21e1.server.data.Van;

public class Assembler {
    
    public UserDTO assembleUser(User u) {
		UserDTO userDTO = new UserDTO(u.getDni(), u.getName(), u.getName());
		return userDTO;
    }

	/*public ReservationDTO assembleReservation(Reservation r) {

    }*/

    public VanDTO assembleVan(Van v) {
    	UserDTO userDTO = assembleUser(v.getOwner());
    	VanDTO vanDTO = new VanDTO(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.hasKitchen(), v.hasShower(), v.isOffRoad(), v.getCapacity(), v.getPricePerDay(), userDTO);
    	return vanDTO;	
    }

    /*public ReviewDTO assembleReview(Review re) {
        
    }*/

    public User disassembleUser(UserDTO u) {
    	User user = new User(u.getDni(), u.getName(), u.getEmail());
    	return user;

    }

    /*public Reservation disassembleReservation(ReservationDTO r) {

    }*/

    public Van disassembleVan(VanDTO v) {
    	User user = disassembleUser(v.getOwner());
    	Van van = new Van(v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), v.hasKitchen(), v.hasShower(), v.isOffRoad(), v.getCapacity(), v.getPricePerDay(), user);
    	return van;
    }

    /*public Review disassembleReview(ReviewDTO re) {
        
    }*/
}
