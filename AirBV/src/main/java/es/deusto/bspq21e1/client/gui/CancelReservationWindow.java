package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JList;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.server.dto.UserDTO;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class CancelReservationWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frame;
	
	public CancelReservationWindow(Controller controller, UserDTO userDTO) {
		frame.getContentPane().setLayout(null);
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(400, 400);
	}
	
	private void initialize() {
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(145, 300, 89, 23);
		getContentPane().add(btnCancel);
		

		
		JList<String> list = new JList<String>();
		list.setBounds(50, 35, 275, 250);
		getContentPane().add(list);
	}
	

}
