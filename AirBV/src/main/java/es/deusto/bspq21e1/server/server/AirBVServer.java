package es.deusto.bspq21e1.server.server;

import java.rmi.Naming;

import es.deusto.bspq21e1.server.remote.AirBV;
import es.deusto.bspq21e1.server.remote.IAirBV;

public class AirBVServer {
    
    public static void main(String[] args) {
        if (args.length != 3) { // Change no of arguments
            System.exit(0);
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

        try {
            IAirBV airbvServer = new AirBV( new AirBVService() );
            Naming.rebind(name, airbvServer);
            System.out.println("- AirBVServer -> " + name + " -> active and waiting...");
        } catch (Exception e) {
            System.err.println("$ AirBVServer exception -> " + e.getMessage());
            e.printStackTrace();
        }
    }
}
