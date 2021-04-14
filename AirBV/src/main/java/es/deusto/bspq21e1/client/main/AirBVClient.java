package es.deusto.bspq21e1.client.main;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.client.gui.InitialWindow;

/**@package client.main
 * @brief This is the documentation for the Java package client.main intended to work as a wrapper for the Domain Object Model (DOM) of the EasyFilmin Project.
 * This package is composed by 2 classes, EasyFilmin and EasyFilminAdmin.
 * The purpose of this classes is to run the User interface or the Admin interface.
 */

/** This class is the main class of our project, gets the arguments of the 
 * @author BSPQ-E4
 *
 */
public class AirBVClient {
	public static void main(String[] args) {
        if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}
        
		es.deusto.bspq21e1.client.controller.Controller c = new Controller(args[0], args[1]); 
		
		InitialWindow i = new InitialWindow(c);
		i.setVisible(true);

	}
}


