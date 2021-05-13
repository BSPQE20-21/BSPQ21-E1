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
import java.awt.Toolkit;

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
	
	private JFrame frmCancelReservation = new JFrame();
	private JButton btnCancel, btnBack;
	private JScrollPane scrollReservations;
	private JList<String> jlReservationsList1 = new JList<String>();
	private JTable jtReservationsTable = new JTable();
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
		frmCancelReservation.setTitle(controller.getResourcebundle().getString("cancel_reservation_window_tittle_msg"));
		frmCancelReservation.setResizable(false);
		frmCancelReservation.setVisible(true);
	}
	
	/**
	 * Initializes all the elements the window needs to show to the user and their functionality.
	 */
	private void initialize() {
		frmCancelReservation.setBounds(100, 100, 720, 315);
		frmCancelReservation.setLocationRelativeTo(null);
		frmCancelReservation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCancelReservation.getContentPane().setLayout(null);
		frmCancelReservation.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		JLabel lblTitle = new JLabel(controller.getResourcebundle().getString("cancel_your_reservations_msg"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(60, 11, 298, 24);
		frmCancelReservation.getContentPane().add(lblTitle);
		
		//TABLE MODEL
				tableModel = new DefaultTableModel() {
					@Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
				};
				tableModel.setColumnIdentifiers(new String[] {controller.getResourcebundle().getString("code_msg"), 
															controller.getResourcebundle().getString("pick_up_date_msg"),
															controller.getResourcebundle().getString("duration_msg"),
															controller.getResourcebundle().getString("van_msg"),
															controller.getResourcebundle().getString("owner_id_msg")
															});
				
				
		
		//JTABLE
		jtReservationsTable.setBounds(10, 46, 691, 169);
		jtReservationsTable.setModel(tableModel);
		jtReservationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtReservationsTable.getTableHeader().setResizingAllowed(false);
		jtReservationsTable.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
		
		
		scrollReservations = new JScrollPane();
		scrollReservations.setBounds(10, 40, 691, 188);
		scrollReservations.setViewportView(jtReservationsTable);
		frmCancelReservation.getContentPane().add(scrollReservations);
		
		//CANCEL BUTTON
		btnCancel = new JButton(controller.getResourcebundle().getString("cancel_button_msg"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cancelReservation(reservations.get(jtReservationsTable.getSelectedRow()).getCode());
				frmMain.setVisible(true);
				frmCancelReservation.dispose();
			}
		});
		btnCancel.setBackground(java.awt.Color.RED);
		btnCancel.setBounds(397, 240, 100, 25);
		frmCancelReservation.getContentPane().add(btnCancel);
		
		//BACK BUTTON
		btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(true);
				frmCancelReservation.dispose();
			}
		});
		btnBack.setBounds(50, 240, 100, 25);
		frmCancelReservation.getContentPane().add(btnBack);
		
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
			jtReservationsTable.setModel(tableModel);
			jtReservationsTable.setRowSelectionInterval(0, 0);
		}else {
			btnCancel.setEnabled(false);
			tableModel.setRowCount(0); //CLEAR THE TABLE
		}
		jtReservationsTable.updateUI();
		scrollReservations.updateUI();
	}
	

}
