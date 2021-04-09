package es.deusto.bspq21e1.server.dto;

import java.io.Serializable;

import es.deusto.bspq21e1.server.data.User;

public class VanDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String licensePlate;
    private String brand;
    private String model;
    private String location;

    private boolean kitchen;
    private boolean shower;
    private boolean offRoad;
    private int capacity;
    private double pricePerDay;
    
    private UserDTO owner;

    public VanDTO(String licensePlate, String brand, String model, String location, boolean kitchen, boolean shower, boolean offRoad,
    int capacity, double pricePerDay, UserDTO owner) {
    	
    	 this.licensePlate = licensePlate;
    	 this.brand = brand;
         this.model = model;
         this.location = location;
         this.kitchen = kitchen;
         this.shower = shower;
         this.offRoad = offRoad;
         this.capacity = capacity;
         this.pricePerDay = pricePerDay;
         this.owner = owner;
    	
    }

    // Getters y setters
    
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean hasKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean hasShower() {
		return shower;
	}

	public void setShower(boolean shower) {
		this.shower = shower;
	}

	public boolean isOffRoad() {
		return offRoad;
	}

	public void setOffRoad(boolean offRoad) {
		this.offRoad = offRoad;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}
     
}
