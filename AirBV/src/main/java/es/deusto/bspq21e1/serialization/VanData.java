package es.deusto.bspq21e1.serialization;

import java.util.List;

import javax.jdo.annotations.Join;

import es.deusto.bspq21e1.server.data.Review;

/**
 * Class for the representation of the Van object which is going to be sended to clients.
 * @author SPQ Group 1
 * @version 1.0
 */
public class VanData {
    
    private String licensePlate;
    private String brand;
    private String model;
    private String location;

    private boolean kitchen;
    private boolean shower;
    private boolean offRoad;
    private int capacity;
    private double pricePerDay;
    
    private String user;
    
    public VanData() {
    	
    }

	/**
	 * Creates the object that has the data from Van.
	 * @param licensePlate License plate of the van.
	 * @param brand Brand of the van.
	 * @param model Model of the van.
	 * @param location Town in which the van is located.
	 * @param capacity Maximum number of people allowed to rent the van.
	 * @param kitchen If it has a kitchen.
	 * @param shower If it has a shower.
	 * @param offRoad If it has off road capabilities.
	 * @param pricePerDay The price per day that cost to rent it.
	 * @param owner User that owns the van (just the data version).
	 */
    public VanData(String licensePlate, String brand, String model, String location, int capacity, boolean kitchen, boolean shower, boolean offRoad,
     double pricePerDay, String user) {
    	
    	 this.licensePlate = licensePlate;
    	 this.brand = brand;
         this.model = model;
         this.location = location;
         this.kitchen = kitchen;
         this.shower = shower;
         this.offRoad = offRoad;
         this.capacity = capacity;
         this.pricePerDay = pricePerDay;
         this.user = user;
    	
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VanData [licensePlate=" + licensePlate + ", brand=" + brand + ", model=" + model + ", location="
				+ location + ", kitchen=" + kitchen + ", shower=" + shower + ", offRoad=" + offRoad + ", capacity="
				+ capacity + ", pricePerDay=" + pricePerDay + ", user dni=" + user + "]";
	}

}
