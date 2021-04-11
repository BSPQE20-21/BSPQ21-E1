package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Window class for the visualization of the logic behind cancellations of vans by a user.
 * @author SPQ Group 1
 * @version 1.0
 */
public class CancelReservationWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private UserData user;
	
	private JFrame frame = new JFrame();
	private JScrollPane scrollReservations;
	private JList<String> jlReservationsList = new JList<String>();
	private ArrayList<ReservationData> reservations = new ArrayList<>();
	private javax.swing.DefaultListModel<String> reservationsList = new javax.swing.DefaultListModel<String>();
	
	/**
	 * Creates the window for a specific user.
	 * @param controller Controller used for handling all requests in the system.
	 * @param userData The user that has logged in.
	 */
	public CancelReservationWindow(Controller controller, UserData userData) {
		this.controller = controller;
		this.user = userData;
		setTitle("Cancel a reservation");
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(423, 316);
	}
	
	/**
	 * Initializes all the elements the window needs to show to the user and their functionality.
	 */
	private void initialize() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Cancel any of your reservations");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(50, 11, 298, 24);
		frame.getContentPane().add(lblTitle);
		
		jlReservationsList.setBounds(10, 46, 414, 169);
		jlReservationsList.setModel(reservationsList);
		jlReservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollReservations = new JScrollPane();
		scrollReservations.setBounds(10, 40, 374, 188);
		scrollReservations.setViewportView(jlReservationsList);
		frame.getContentPane().add(scrollReservations);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cancelReservation(jlReservationsList.getSelectedValue());
			}
		});
		btnCancel.setBounds(146, 239, 102, 24);
		frame.getContentPane().add(btnCancel);
		
		reservations = controller.getMyReservations(user);
		updateLists(reservations);
	}
	
	// METHODS FOR DATA DISPLAY IN THE GUI WINDOW
	private void updateLists(ArrayList<ReservationData> reservations) {
		System.out.println("Dentro funcion -> " + reservations);
		reservationsList.clear();
		for (int i = 0; i < reservations.size(); i++) {
			ReservationData v = (ReservationData) reservations.get(i);
			reservationsList.addElement(v.toString());
		}
		jlReservationsList.setSelectedIndex(0);
	}
	

}
