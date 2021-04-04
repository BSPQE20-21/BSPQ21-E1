package es.deusto.bspq21e1.paymentServer.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PaymentSystem extends UnicastRemoteObject implements IPaymentSystem {
    
    private static final long serialVersionUID = 1L;

    // Parameters

    public PaymentSystem() throws RemoteException {
        super();

    }

    // Here will go the methods
}
