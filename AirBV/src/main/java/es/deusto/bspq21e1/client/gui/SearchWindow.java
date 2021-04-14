package es.deusto.bspq21e1.client.gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.apache.log4j.SimpleLayout;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.Color;


public class SearchWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private UserData user;
	
	private JFrame frmSearchVans = new JFrame();;
	private JPanel searchPanel;
	private JPanel visualizePanel;
	private JLabel lblSearchTitle;
	private JLabel lblResultsTitle;
	private JLabel lblLocation;
	private JLabel lblPickUp;
	private JLabel lblReturn;
	private JTextField txtLocation;
	private JTextField txtPickUp;
	private JTextField txtReturn;
	private JSeparator separator;
	private JList<String> jlVansList = new JList<String>();
	private JScrollPane scrollVans;
	private ArrayList<VanData> vans = new ArrayList<>();
	private Date pickUpDate;
	private Date returnDate;
	
	private javax.swing.DefaultListModel<String> vansList = new javax.swing.DefaultListModel<String>();

	/**
	 * Create the application.
	 */
	public SearchWindow(Controller controller, UserData user) {
		this.controller = controller;
		this.user = user;
		frmSearchVans.setTitle("Search vans");
		frmSearchVans.setResizable(false);
		frmSearchVans.setVisible(true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearchVans.setBounds(100, 100, 668, 353);
		frmSearchVans.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSearchVans.getContentPane().setLayout(null);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 662, 140);
		frmSearchVans.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 61, 63, 14);
		searchPanel.add(lblLocation);
		
		lblPickUp = new JLabel("Pick up date");
		lblPickUp.setBounds(213, 61, 79, 14);
		searchPanel.add(lblPickUp);
		
		lblReturn = new JLabel("Return date");
		lblReturn.setBounds(433, 61, 80, 14);
		searchPanel.add(lblReturn);
		
		txtLocation = new JTextField();
		txtLocation.setBounds(70, 58, 133, 20);
		searchPanel.add(txtLocation);
		txtLocation.updateUI();
		txtLocation.setToolTipText("\"Bilbao\", \"Madrid\" ...");
		txtLocation.setColumns(10);
		
		txtPickUp = new JTextField();
		txtPickUp.setBounds(289, 58, 133, 20);
		searchPanel.add(txtPickUp);
		txtPickUp.updateUI();
		txtPickUp.setColumns(10);
		
		txtReturn = new JTextField();
		txtReturn.setBounds(510, 58, 133, 20);
		searchPanel.add(txtReturn);
		txtReturn.updateUI();
		txtReturn.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vans = controller.searchVans(txtLocation.getText());
				if (vans.size() != 0) {
					updateLists(vans);
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnSearch.setBounds(275, 109, 87, 20);
		searchPanel.add(btnSearch);
		btnSearch.updateUI();
		
		lblSearchTitle = new JLabel("Search for your perfect van");
		lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchTitle.setBounds(10, 11, 320, 34);
		searchPanel.add(lblSearchTitle);
		
		JLabel lblNDateExample = new JLabel("dd/MM/yyyy");
		lblNDateExample.setForeground(Color.BLUE);
		lblNDateExample.setBounds(311, 44, 93, 13);
		searchPanel.add(lblNDateExample);
		lblSearchTitle.updateUI();
		
		visualizePanel = new JPanel();
		visualizePanel.setBounds(0, 144, 662, 169);
		frmSearchVans.getContentPane().add(visualizePanel);
		visualizePanel.setLayout(null);
		
		lblResultsTitle = new JLabel("Results");
		lblResultsTitle.setBounds(10, 11, 148, 29);
		lblResultsTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		visualizePanel.add(lblResultsTitle);
		lblResultsTitle.updateUI();
		
		jlVansList.setBounds(574, 118, -558, -83);
		jlVansList.setModel(vansList);
		jlVansList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollVans = new JScrollPane();
		scrollVans.setBounds(10, 40, 633, 84);
		scrollVans.setViewportView(jlVansList);
		visualizePanel.add(scrollVans);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String pickUp = txtPickUp.getText();
				try {
					pickUpDate = new SimpleDateFormat("dd/MM/yyyy").parse(pickUp);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					returnDate = new SimpleDateFormat("dd/MM/yyyy").parse(txtReturn.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				VanData van = vans.get(jlVansList.getSelectedIndex());
				
				int milisecondsByDay = 86400000;
				int days = (int) ((pickUpDate.getTime()-returnDate.getTime()) / milisecondsByDay);
				
				controller.registerReservation(pickUpDate, days, van, user);
			
				
				
			}
		});
		btnBook.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnBook.setBounds(275, 138, 87, 20);
		visualizePanel.add(btnBook);
		btnBook.updateUI();
		
		separator = new JSeparator();
		separator.setBounds(0, 142, 662, 160);
		frmSearchVans.getContentPane().add(separator);
		separator.updateUI();
	}
	
	// METHODS FOR DATA DISPLAY IN THE GUI WINDOW
	private void updateLists(ArrayList<VanData> vans) {
		System.out.println("Dentro funcion -> " + vans);
		vansList.clear();
		for (int i = 0; i < vans.size(); i++) {
			VanData v = vans.get(i);
			vansList.addElement("Van: " + v.getBrand() + " " + v.getModel() + " (" + v.getCapacity() + " people) Price: " + v.getPricePerDay());
		}
		jlVansList.setSelectedIndex(0);
	}
}


