package es.deusto.bspq21e1.paymentServer.server;

import java.rmi.Naming;

import es.deusto.bspq21e1.paymentServer.remote.IPaymentSystem;
import es.deusto.bspq21e1.paymentServer.remote.PaymentSystem;

public class PaymentServer {
    
    public static void main(String[] args) {
        if (args.length != 3) {
			System.exit(0);
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

        try {
			IPaymentSystem ISserver = new PaymentSystem();
			Naming.rebind(name, ISserver);
			System.out.println("- PaymentSystem '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ PaymentSystem exception: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
