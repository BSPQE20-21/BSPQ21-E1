package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JList;

import es.deusto.bspq21e1.client.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class CancelReservationWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frame = new JFrame();
	
	public CancelReservationWindow(Controller controller, UserDTO user) {
		frame.getContentPane().setLayout(null);
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(400, 400);
	}
	
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
