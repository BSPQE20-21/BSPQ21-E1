package es.deusto.bspq21e1.serialization;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    
    private String dni;
    private String name;
    private String email;
    private List<Integer> stars;

    public UserData(String dni, String name, String email) {
    	this.dni = dni;
        this.name = name;
        this.email = email;
        this.stars = new ArrayList<Integer>();
    }
    
    // Getters y setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getStars() {
		return stars;
	}

	public void setStars(List<Integer> stars) {
		this.stars = stars;
	}

	//To String
	@Override
	public String toString() {
		return "UserData [dni=" + dni + ", name=" + name + ", email=" + email + ", stars=" + stars + "]";
	}
	
}
