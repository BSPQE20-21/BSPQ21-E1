package es.deusto.bspq21e1.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String dni;
    private String name;
    private String email;
    private List<Integer> stars;
    
    public UserDTO(String dni, String name, String email) {
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

}
