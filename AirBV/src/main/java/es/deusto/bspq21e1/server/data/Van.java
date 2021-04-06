package es.deusto.bspq21e1.server.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;

public class Van {
    
    private String licensePlate;
    private String brand;
    private String model;
    private String status;
    private String location;

    private boolean kitchen;
    private boolean shower;
    private boolean offRoad;
    private int capacity;
    private double pricePerDay;

    private User owner;
    @Join
    private List<Review> reviews;

    public Van(String licensePlate, String brand, String model, String location, boolean kitchen, boolean shower, boolean offRoad,
    int capacity, double pricePerDay, User owner) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.status = "Open";
        this.location = location;
        this.kitchen = kitchen;
        this.shower = shower;
        this.offRoad = offRoad;
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
        this.owner = owner;
        this.reviews = new ArrayList<Review>();
    }

    // Getters and setters
    
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
 
}
