package es.deusto.bspq21e1.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

public class RemoveVanWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CancelReservationWindow.class.getName());
	private Controller controller;
	private UserData user;
	private JFrame frmMain;
	
	private JFrame frmRemoveVan = new JFrame();
	
	private JLabel lblText;
	private JButton btnRemove, btnBack;
	private JTable jtVansTable = new JTable();
	private JScrollPane scrollVans;
	private ArrayList<VanData> vans = new ArrayList<VanData>();
	private DefaultListModel<String> vansList = new DefaultListModel<String>();
	private DefaultTableModel tableModel;
	
	public RemoveVanWindow(Controller controller, UserData user, JFrame frmMain) {
		this.controller = controller;
		this.user = user;
		this.frmMain = frmMain;
		frmRemoveVan.setTitle(controller.getResourcebundle().getString("remove_van_btn_msg"));
		frmRemoveVan.setResizable(false);
		frmRemoveVan.setVisible(true);
		initialize();
	}
	
	private void initialize() {
		frmRemoveVan.setBounds(50, 50, 715, 500);
		frmRemoveVan.setLocationRelativeTo(null);
		frmRemoveVan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRemoveVan.getContentPane().setLayout(null);
		frmRemoveVan.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		//TABLE MODEL
		tableModel = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tableModel.setColumnIdentifiers(new String[] {controller.getResourcebundle().getString("license_plate_msg"), 
													controller.getResourcebundle().getString("brand_msg"),
													controller.getResourcebundle().getString("model_msg"),
													controller.getResourcebundle().getString("location_msg"),
													controller.getResourcebundle().getString("capacity_msg"),
													controller.getResourcebundle().getString("price_per_day_msg")
													});
		//JTABLE
		jtVansTable.setModel(tableModel);
		jtVansTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtVansTable.getTableHeader().setResizingAllowed(false);
		jtVansTable.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
		
		scrollVans = new JScrollPane();
		scrollVans.setBounds(25, 80, 650, 320);
		scrollVans.setViewportView(jtVansTable);
		frmRemoveVan.getContentPane().add(scrollVans);
		
		//BACK BUTTON
		btnBack = new JButton(controller.getResourcebundle().getString("back_button_msg"));
		btnBack.setBounds(25, 420, 100, 25);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(true);
				frmRemoveVan.dispose();
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnBack.setBounds(20, 420, 110, 26);
        		btnBack.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnBack.setBounds(25, 420, 100, 25);
		    	btnBack.updateUI();
		    }
		});
		frmRemoveVan.getContentPane().add(btnBack);
		btnBack.updateUI();
		
		//REMOVE BUTTON
		btnRemove = new JButton(controller.getResourcebundle().getString("remove_msg"));
		btnRemove.setBounds(575, 420, 100, 25);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VanData van = vans.get(jtVansTable.getSelectedRow());
				controller.eraseVan( van.getLicensePlate() );
				frmMain.setVisible(true);
				frmRemoveVan.dispose();
			}
		});
		btnRemove.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRemove.setBounds(570, 420, 110, 26);
        		btnRemove.setBackground(Color.RED);
        		btnRemove.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnRemove.setBounds(575, 420, 100, 25);
		    	btnRemove.setBackground(btnBack.getBackground());
		    	btnRemove.updateUI();
		    }
		});
		frmRemoveVan.getContentPane().add(btnRemove);
		btnRemove.updateUI();
		
		lblText = new JLabel(controller.getResourcebundle().getString("choose_van_msg"));
		lblText.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblText.setBounds(135, 10, 580, 50);
		frmRemoveVan.getContentPane().add(lblText);
		lblText.updateUI();
		
		vans = controller.getMyVans(user.getDni());
		if (vans.size() != 0) {
			updateList();
		} else {
			btnRemove.setEnabled(false);
		}
		
		logger.info("RemoveVanWindow well initialized");
	}
	
	private void updateList() {
		logger.debug("Inside function -> " + vans);
		if(vans.size()>0) {
			for (int i = 0; i < vans.size(); i++) {
				VanData v = vans.get(i);
				String[] row = {v.getLicensePlate(), v.getBrand(), v.getModel(), v.getLocation(), String.valueOf(v.getCapacity()), String.valueOf(v.getPricePerDay())};
				tableModel.addRow(row);
			}
			jtVansTable.setModel(tableModel);
			jtVansTable.setRowSelectionInterval(0, 0);
		}else {
			btnRemove.setEnabled(false);
		}
		jtVansTable.updateUI();
		scrollVans.updateUI();
	}
	
}
