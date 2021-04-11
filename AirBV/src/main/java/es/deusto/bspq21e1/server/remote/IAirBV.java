package es.deusto.bspq21e1.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public interface IAirBV extends Remote {
    public void registerUser(String dni, String name, String email) throws RemoteException;
    public void registerVan(VanData vanData) throws RemoteException;
    public boolean cancelReservation(String code) throws RemoteException;
    public void registerReservation(Date bookingDate, int duration, VanData vanData, UserData vanRenter) throws RemoteException;
    public ArrayList<VanData> searchVans(String location) throws RemoteException;
	public ArrayList<ReservationData> getUserReservations(UserData userData);
}
