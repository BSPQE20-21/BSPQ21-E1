package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Window class for the visualization of the logic behind cancellations of vans by a user.
 * @author BSPQ21-E1
 * @version 1.0
 */
public class CancelReservationWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CancelReservationWindow.class.getName());
	private Controller controller;
	private UserData user;
	private JFrame frmMain;
	
	private JFrame frame = new JFrame();
	private JButton btnCancel, btnBack;
	private JScrollPane scrollReservations;
	private JList<String> jlReservationsList1 = new JList<String>();
	private JTable jlReservationsList = new JTable();
	private ArrayList<ReservationData> reservations = new ArrayList<>();
	private javax.swing.DefaultListModel<String> reservationsList = new javax.swing.DefaultListModel<String>();
	private DefaultTableModel tableModel;
	
	/**
	 * Creates the window for a specific user.
	 * @param controller Controller used for handling all requests in the system.
	 * @param userData The user that has logged in.
	 */
	public CancelReservationWindow(Controller controller, UserData userData, JFrame frmMain) {
		this.controller = controller;
		this.user = userData;
		this.frmMain = frmMain;
		initialize();
		frame.setTitle(controller.getResourcebundle().getString("cancel_reservation_window_tittle_msg"));
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * Initializes all the elements the window needs to show to the user and their functionality.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 720, 315);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel(controller.getResourcebundle().getString("cancel_your_reservations_msg"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(60, 11, 298, 24);
		frame.getContentPane().add(lblTitle);
		
		//TABLE MODEL
				tableModel = new DefaultTableModel();
				tableModel.setColumnIdentifiers(new String[] {controller.getResourcebundle().getString("code_msg"), 
															controller.getResourcebundle().getString("pick_up_date_msg"),
															controller.getResourcebundle().getString("duration_msg"),
															controller.getResourcebundle().getString("van_msg"),
															controller.getResourcebundle().getString("owner_id_msg")
															});
		
		//JTABLE
		jlReservationsList.setBounds(10, 46, 691, 169);
		jlReservationsList.setModel(tableModel);
		jlReservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollReservations = new JScrollPane();
		scrollReservations.setBounds(10, 40, 691, 188);
		scrollReservations.setViewportView(jlReservationsList);
		frame.getContentPane().add(scrollReservations);
		
		//CANCEL BUTTON
		btnCancel = new JButton(controller.getResourcebundle().getString("cancel_button_msg"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cancelReservation(reservations.get(jlReservationsList.getSelectedRow()).getCode());
				frmMain.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBackground(java.awt.Color.RED);
		btnCancel.setBounds(397, 240, 100, 25);
		frame.getContentPane().add(btnCancel);
		
		//BACK BUTTON
		btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setBounds(50, 240, 100, 25);
		frame.getContentPane().add(btnBack);
		
		//CONTROLLER
		reservations = controller.getMyReservations(user);
		updateLists(reservations);
		
		logger.info("CancelReservationWindow well initialized");
	}
	
	// METHODS FOR DATA DISPLAY IN THE GUI WINDOW
	private void updateLists(ArrayList<ReservationData> reservations) {
		logger.debug("Inside function -> " + reservations);
		tableModel.setRowCount(0); //CLEAR THE TABLE
		if(reservations.size()>0) {
			for (int i = 0; i < reservations.size(); i++) {
				ReservationData v = (ReservationData) reservations.get(i);
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				String strDate= formatter.format(v.getBookingDate());
				String[] row = {v.getCode(), strDate, String.valueOf(v.getDuration()), v.getVan(), v.getVanRenter()};
				tableModel.addRow(row);
			}
			jlReservationsList.setModel(tableModel);
			jlReservationsList.setRowSelectionInterval(0, 0);
		}else {
			btnCancel.setEnabled(false);
			tableModel.setRowCount(0); //CLEAR THE TABLE
		}
		jlReservationsList.updateUI();
		scrollReservations.updateUI();
	}
	

}
