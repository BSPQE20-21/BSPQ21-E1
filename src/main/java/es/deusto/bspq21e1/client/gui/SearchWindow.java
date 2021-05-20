package es.deusto.bspq21e1.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;


public class SearchWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SearchWindow.class.getName());
	private Controller controller;
	private boolean visible;
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
	private JTable jtVansTable = new JTable();
	private JScrollPane scrollVans;
	private ArrayList<VanData> vans = new ArrayList<>();
	private Date pickUpDate;
	private Date returnDate;
	JButton btnCharacteristics;
	JButton btnBook;
	JButton btnBackButton;
	JButton btnSearch;
	private DefaultTableModel tableModel;
	
	private javax.swing.DefaultListModel<String> vansList = new javax.swing.DefaultListModel<String>();

	/**
	 * Create the application.
	 */
	public SearchWindow(Controller controller, UserData user, JFrame mainWindow, boolean visible) {
		this.controller = controller;
		this.visible = visible;
		this.user = user;
		this.mainWindow = mainWindow;
		frmSearchVans.setTitle(controller.getResourcebundle().getString("search_window_tittle_msg"));
		frmSearchVans.setResizable(false);
		frmSearchVans.setVisible(visible);
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
		frmSearchVans.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
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
		
		//SEARCH BUTTON
		btnSearch = new JButton(controller.getResourcebundle().getString("search_button_msg"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPickUp.getText() != null && txtReturn.getText() != null && txtLocation.getText() != null) {
					vans = controller.searchVans(txtLocation.getText(), txtPickUp.getText(), txtReturn.getText());
					updateLists(vans);
					logger.debug("List of vans updated");
				}else {
					JOptionPane.showMessageDialog(frmSearchVans, controller.getResourcebundle().getString("search_window_error_empty_txt_fields_msg"));
					logger.error("Wrong data entered into parameters");
				}
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
		
		//TABLE MODEL
		tableModel = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tableModel.setColumnIdentifiers(new String[] {controller.getResourcebundle().getString("brand_msg"), 
													controller.getResourcebundle().getString("model_msg"),
													controller.getResourcebundle().getString("location_msg"),
													controller.getResourcebundle().getString("capacity_msg"),
													controller.getResourcebundle().getString("price_per_day_msg")
													});
		logger.debug("Table model well generated");
		
		//JTABLE
		jtVansTable.setBounds(574, 118, -558, -83);
		jtVansTable.setModel(tableModel);
		jtVansTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtVansTable.getTableHeader().setResizingAllowed(false);	  //not allow resizing of columns
		jtVansTable.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
		
		scrollVans = new JScrollPane();
		scrollVans.setBounds(10, 40, 633, 84);
		scrollVans.setViewportView(jtVansTable);
		visualizePanel.add(scrollVans);
		
		//BOOK BUTTON
		btnBook = new JButton(controller.getResourcebundle().getString("book_button_msg"));
		btnBook.setEnabled(false);
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String pickUp = txtPickUp.getText();
				try {
					pickUpDate = new SimpleDateFormat("dd-MM-yyyy").parse(pickUp);
				} catch (ParseException e1) {
					e1.printStackTrace();
					logger.error("Error parsing pickup date");
				}
				
				try {
					returnDate = new SimpleDateFormat("dd-MM-yyyy").parse(txtReturn.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
					logger.error("Error parsing return date");
				}
				
				VanData van = vans.get(jtVansTable.getSelectedRow());
				
				int milisecondsByDay = 86400000;
				int days = (int) ((returnDate.getTime()-pickUpDate.getTime()) / milisecondsByDay);
				
				if(txtPickUp.getText() != null && txtReturn.getText() != null && txtLocation.getText() != null) {
					controller.registerReservation(pickUpDate, days, van, user);
					logger.debug("Reservation well generated");
				}
				mainWindow.setVisible(visible);
				frmSearchVans.dispose();
				
			}
		});
		btnBook.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnBook.setBounds(209, 138, 100, 20);
		visualizePanel.add(btnBook);
		
		//CHARACTERISTICS BUTTON
		btnCharacteristics = new JButton(controller.getResourcebundle().getString("characteristics_button_msg"));
		btnCharacteristics.setEnabled(false);
		btnCharacteristics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtVansTable.getSelectedRow() != -1) {
					VanData van = vans.get(jtVansTable.getSelectedRow());
					frmSearchVans.setVisible(false);
					new CharacteristicsWindow(controller, van, frmSearchVans, visible);
				}
			}
		});
		btnCharacteristics.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnCharacteristics.setBounds(325, 138, 130, 20);
		visualizePanel.add(btnCharacteristics);
		
		//BACK BUTTON
		btnBackButton = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBackButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow(controller, user, visible);
				frmSearchVans.dispose();
			}
		});
		btnBackButton.setBounds(10, 157, 89, 20);
		visualizePanel.add(btnBackButton);
		btnBook.updateUI();
		
		separator = new JSeparator();
		separator.setBounds(0, 142, 662, 160);
		frmSearchVans.getContentPane().add(separator);
		separator.updateUI();
		
		logger.info("SearchWindow well initialized");
	}
	
	// METHODS FOR DATA DISPLAY IN THE GUI WINDOW
	private void updateLists(ArrayList<VanData> vans) {
		logger.debug("Inside method updateLists -> " + vans);
		if(vans.size() > 0) {
			btnCharacteristics.setEnabled(true);
			tableModel.setRowCount(0); //CLEAR THE TABLE
			for (int i = 0; i < vans.size(); i++) {
				VanData v = vans.get(i);
				String[] row = {v.getBrand(), v.getModel(), v.getLocation(), String.valueOf(v.getCapacity()), String.valueOf(v.getPricePerDay())};
				tableModel.addRow(row);
			}
			jtVansTable.setRowSelectionInterval(0, 0);
			btnBook.setEnabled(true);
		} else {
			btnCharacteristics.setEnabled(false);
			btnBook.setEnabled(false);
			tableModel.setRowCount(0); //CLEAR THE TABLE
		}
		jtVansTable.setModel(tableModel);
		jtVansTable.updateUI();
		scrollVans.updateUI();
		visualizePanel.updateUI();
	}

	//GETTERS AND SETTERS
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		SearchWindow.logger = logger;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public JFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}

	public JFrame getFrmSearchVans() {
		return frmSearchVans;
	}

	public void setFrmSearchVans(JFrame frmSearchVans) {
		this.frmSearchVans = frmSearchVans;
	}

	public JPanel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(JPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public JPanel getVisualizePanel() {
		return visualizePanel;
	}

	public void setVisualizePanel(JPanel visualizePanel) {
		this.visualizePanel = visualizePanel;
	}

	public JLabel getLblSearchTitle() {
		return lblSearchTitle;
	}

	public void setLblSearchTitle(JLabel lblSearchTitle) {
		this.lblSearchTitle = lblSearchTitle;
	}

	public JLabel getLblResultsTitle() {
		return lblResultsTitle;
	}

	public void setLblResultsTitle(JLabel lblResultsTitle) {
		this.lblResultsTitle = lblResultsTitle;
	}

	public JLabel getLblLocation() {
		return lblLocation;
	}

	public void setLblLocation(JLabel lblLocation) {
		this.lblLocation = lblLocation;
	}

	public JLabel getLblPickUp() {
		return lblPickUp;
	}

	public void setLblPickUp(JLabel lblPickUp) {
		this.lblPickUp = lblPickUp;
	}

	public JLabel getLblReturn() {
		return lblReturn;
	}

	public void setLblReturn(JLabel lblReturn) {
		this.lblReturn = lblReturn;
	}

	public JTextField getTxtLocation() {
		return txtLocation;
	}

	public void setTxtLocation(JTextField txtLocation) {
		this.txtLocation = txtLocation;
	}

	public JTextField getTxtPickUp() {
		return txtPickUp;
	}

	public void setTxtPickUp(JTextField txtPickUp) {
		this.txtPickUp = txtPickUp;
	}

	public JTextField getTxtReturn() {
		return txtReturn;
	}

	public void setTxtReturn(JTextField txtReturn) {
		this.txtReturn = txtReturn;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JTable getJtVansTable() {
		return jtVansTable;
	}

	public void setJtVansTable(JTable jtVansTable) {
		this.jtVansTable = jtVansTable;
	}

	public JScrollPane getScrollVans() {
		return scrollVans;
	}

	public void setScrollVans(JScrollPane scrollVans) {
		this.scrollVans = scrollVans;
	}

	public ArrayList<VanData> getVans() {
		return vans;
	}

	public void setVans(ArrayList<VanData> vans) {
		this.vans = vans;
	}

	public Date getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public JButton getBtnCharacteristics() {
		return btnCharacteristics;
	}

	public void setBtnCharacteristics(JButton btnCharacteristics) {
		this.btnCharacteristics = btnCharacteristics;
	}

	public JButton getBtnBook() {
		return btnBook;
	}

	public void setBtnBook(JButton btnBook) {
		this.btnBook = btnBook;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public javax.swing.DefaultListModel<String> getVansList() {
		return vansList;
	}

	public void setVansList(javax.swing.DefaultListModel<String> vansList) {
		this.vansList = vansList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
