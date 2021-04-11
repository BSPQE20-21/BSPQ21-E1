package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JList;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Window class for the visualization of the logic behind cancellations of vans by a user.
 * @author SPQ Group 1
 * @version 1.0
 */
public class CancelReservationWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frame = new JFrame();
	
	/**
	 * Creates the window for a specific user.
	 * @param controller Controller used for nahdling all requests in the system.
	 * @param userData The user that has logged in.
	 */
	public CancelReservationWindow(Controller controller, UserData userData) {
		frame.getContentPane().setLayout(null);
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(400, 400);
	}
	
	/**
	 * Initializes all the elements the window needs to show to the user.
	 */
	private void initialize() {
		
		JList<String> list = new JList<String>();
		list.setBounds(50, 35, 275, 250);
		getContentPane().add(list);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cancelReservation(list.getSelectedValue());
			}
		});
		btnCancel.setBounds(145, 300, 89, 23);
		getContentPane().add(btnCancel);
	}
	

}
