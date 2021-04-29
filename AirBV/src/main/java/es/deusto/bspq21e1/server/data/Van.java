package es.deusto.bspq21e1.server.data;

import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Van {
    @PrimaryKey
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
    
    private String user;
    
    public Van() {
    	
    }

    public Van(String licensePlate, String brand, String model, String location, boolean kitchen, boolean shower, boolean offRoad,
    int capacity, double pricePerDay, String user) {
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
        this.user = user;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.licensePlate.equals(((Van)obj).getLicensePlate());
	}

	@Override
	public String toString() {
		return "Van [licensePlate=" + licensePlate + ", brand=" + brand + ", model=" + model + ", status=" + status
				+ ", location=" + location + ", kitchen=" + kitchen + ", shower=" + shower + ", offRoad=" + offRoad
				+ ", capacity=" + capacity + ", pricePerDay=" + pricePerDay + ", user dni=" + user + "]";
	}

	
 
}
