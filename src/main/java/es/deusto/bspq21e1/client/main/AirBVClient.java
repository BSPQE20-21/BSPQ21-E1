package es.deusto.bspq21e1.client.main;

import org.apache.log4j.Logger;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.client.gui.InitialWindow;

/**@package client.main
 * @brief This is the documentation for the Java package client.main intended to work as a wrapper for the Domain Object Model (DOM) of the EasyFilmin Project.
 * This package is composed by 2 classes, EasyFilmin and EasyFilminAdmin.
 * The purpose of this classes is to run the User interface or the Admin interface.
 */

/** This class is the main class of our project, gets the arguments of the 
 * @author BSPQ21-E1
 *
 */
public class AirBVClient {
	
	private static Logger logger = Logger.getLogger(AirBVClient.class.getName());
	
	public static void main(String[] args) {
        if (args.length != 3) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}
        
		Controller c = new Controller(args[0], args[1], args[2]); 
		
		new InitialWindow(c, true);
		
		logger.info("Client started without problems");
	}
}


