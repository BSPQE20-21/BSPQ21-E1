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

import org.apache.log4j.Logger;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.Color;


public class SearchWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SearchWindow.class.getName());
	private Controller controller;
	private UserData user;
	private JFrame mainWindow;
	
	private JFrame frmSearchVans = new JFrame();
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
	JButton btnCharacteristics;
	
	private javax.swing.DefaultListModel<String> vansList = new javax.swing.DefaultListModel<String>();

	/**
	 * Create the application.
	 */
	public SearchWindow(Controller controller, UserData user, JFrame mainWindow) {
		this.controller = controller;
		this.user = user;
		this.mainWindow = mainWindow;
		frmSearchVans.setTitle(controller.getResourcebundle().getString("search_window_tittle_msg"));
		frmSearchVans.setResizable(false);
		frmSearchVans.setVisible(true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearchVans.setBounds(100, 100, 668, 400);
		frmSearchVans.setLocationRelativeTo(null);
		frmSearchVans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSearchVans.getContentPane().setLayout(null);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 662, 165);
		frmSearchVans.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		lblLocation = new JLabel(controller.getResourcebundle().getString("location_msg"));
		lblLocation.setBounds(20, 61, 100, 14);
		searchPanel.add(lblLocation);
		
		lblPickUp = new JLabel(controller.getResourcebundle().getString("pick_up_date_msg"));
		lblPickUp.setBounds(325, 55, 125, 14);
		searchPanel.add(lblPickUp);
		
		lblReturn = new JLabel(controller.getResourcebundle().getString("return_date_msg"));
		lblReturn.setBounds(325, 85, 125, 14);
		searchPanel.add(lblReturn);
		
		txtLocation = new JTextField();
		txtLocation.setBounds(131, 58, 140, 20);
		searchPanel.add(txtLocation);
		txtLocation.updateUI();
		txtLocation.setToolTipText(controller.getResourcebundle().getString("location_tool_tip_msg"));
		txtLocation.setColumns(10);
		
		txtPickUp = new JTextField();
		txtPickUp.setBounds(460, 52, 133, 20);
		searchPanel.add(txtPickUp);
		txtPickUp.updateUI();
		txtPickUp.setColumns(10);
		
		txtReturn = new JTextField();
		txtReturn.setBounds(460, 82, 133, 20);
		searchPanel.add(txtReturn);
		txtReturn.updateUI();
		txtReturn.setColumns(10);
		
		JButton btnSearch = new JButton(controller.getResourcebundle().getString("search_button_msg"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vans = controller.searchVans(txtLocation.getText(), txtPickUp.getText(), txtReturn.getText());
				updateLists(vans);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnSearch.setBounds(276, 134, 87, 20);
		searchPanel.add(btnSearch);
		btnSearch.updateUI();
		
		lblSearchTitle = new JLabel(controller.getResourcebundle().getString("lbl_search_title_msg"));
		lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchTitle.setBounds(10, 11, 320, 34);
		searchPanel.add(lblSearchTitle);
		
		JLabel lblNDateExample = new JLabel(controller.getResourcebundle().getString("date_example_msg"));
		lblNDateExample.setForeground(Color.BLUE);
		lblNDateExample.setBounds(484, 32, 93, 13);
		searchPanel.add(lblNDateExample);
		lblSearchTitle.updateUI();
		
		visualizePanel = new JPanel();
		visualizePanel.setBounds(0, 170, 662, 200);
		frmSearchVans.getContentPane().add(visualizePanel);
		visualizePanel.setLayout(null);
		
		lblResultsTitle = new JLabel(controller.getResourcebundle().getString("lbl_results_title_msg"));
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
		
		JButton btnBook = new JButton(controller.getResourcebundle().getString("book_button_msg"));
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String pickUp = txtPickUp.getText();
				try {
					pickUpDate = new SimpleDateFormat("dd-MM-yyyy").parse(pickUp);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					returnDate = new SimpleDateFormat("dd-MM-yyyy").parse(txtReturn.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				VanData van = vans.get(jlVansList.getSelectedIndex());
				
				int milisecondsByDay = 86400000;
				int days = (int) ((returnDate.getTime()-pickUpDate.getTime()) / milisecondsByDay);
				
				controller.registerReservation(pickUpDate, days, van, user);
			
				mainWindow.setVisible(true);
				frmSearchVans.dispose();
				
			}
		});
		btnBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnBook.setBounds(209, 138, 100, 20);
		visualizePanel.add(btnBook);
		
		btnCharacteristics = new JButton(controller.getResourcebundle().getString("characteristics_button_msg"));
		btnCharacteristics.setEnabled(false);
		btnCharacteristics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jlVansList.getSelectedIndex() != -1) {
					VanData van = vans.get(jlVansList.getSelectedIndex());
					frmSearchVans.setVisible(false);
					new CharacteristicsWindow(controller, van, frmSearchVans);
				}
			}
		});
		btnCharacteristics.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnCharacteristics.setBounds(325, 138, 130, 20);
		visualizePanel.add(btnCharacteristics);
		
		JButton btnNewButton = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow(controller, user);
				frmSearchVans.dispose();
			}
		});
		btnNewButton.setBounds(10, 157, 89, 20);
		visualizePanel.add(btnNewButton);
		btnBook.updateUI();
		
		separator = new JSeparator();
		separator.setBounds(0, 142, 662, 160);
		frmSearchVans.getContentPane().add(separator);
		separator.updateUI();
		
		logger.info("SearchWindow well initialized");
	}
	
	// METHODS FOR DATA DISPLAY IN THE GUI WINDOW
	private void updateLists(ArrayList<VanData> vans) {
		if(vans.size() > 0) {
			btnCharacteristics.setEnabled(true);
			vansList.clear();
			for (int i = 0; i < vans.size(); i++) {
				VanData v = vans.get(i);
				vansList.addElement("Van: " + v.getBrand() + " " + v.getModel() + " (" + v.getCapacity() + " people) Price: " + v.getPricePerDay());
			}
			jlVansList.setSelectedIndex(0);
		} else {
			btnCharacteristics.setEnabled(false);
			vansList.clear();
		}
		jlVansList.setModel(vansList);
		jlVansList.updateUI();
		scrollVans.updateUI();
		visualizePanel.updateUI();
	}
}


