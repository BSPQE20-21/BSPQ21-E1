package es.deusto.bspq21e1.client.gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.ReservationData;
import es.deusto.bspq21e1.serialization.UserData;
import junit.framework.JUnit4TestAdapter;

public class CancelReservationWindowTest {

	static Logger logger = Logger.getLogger(CancelReservationWindowTest.class.getName());
	
	Controller controller;
	UserData user;
	
	JFrame frmMain, frmCancelReservation;
	JButton btnCancel, btnBack;
	JScrollPane scrollReservations;
	JList<String> jlReservationsList1;
	JTable jtReservationsTable;
	DefaultListModel<String> reservationsList;
	DefaultTableModel tableModel;
	JLabel lblTitle;
	
	ArrayList<ReservationData> reservations;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CancelReservationWindowTest.class);
	}
}
