package es.deusto.bspq21e1.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.server.dto.VanDTO;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class SearchWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frame;
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
	private JList<String> jlVansList;
	private JScrollPane scrollVans;
	
	private javax.swing.DefaultListModel<String> vansList;

	/**
	 * Create the application.
	 */
	public SearchWindow(Controller controller) {
		this.controller = controller;
		frame.setVisible(true);
		jlVansList.setModel(vansList);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 585, 133);
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 61, 40, 14);
		searchPanel.add(lblLocation);
		
		lblPickUp = new JLabel("Pick up date");
		lblPickUp.setBounds(190, 61, 58, 14);
		searchPanel.add(lblPickUp);
		
		lblReturn = new JLabel("Return date");
		lblReturn.setBounds(388, 61, 58, 14);
		searchPanel.add(lblReturn);
		
		txtLocation = new JTextField();
		txtLocation.setBounds(60, 58, 120, 20);
		searchPanel.add(txtLocation);
		txtLocation.setToolTipText("\"Bilbao\", \"Madrid\" ...");
		txtLocation.setColumns(10);
		
		txtPickUp = new JTextField();
		txtPickUp.setBounds(258, 58, 120, 20);
		searchPanel.add(txtPickUp);
		txtPickUp.setColumns(10);
		
		txtReturn = new JTextField();
		txtReturn.setBounds(456, 58, 120, 20);
		searchPanel.add(txtReturn);
		txtReturn.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<VanDTO> vans = controller.searchVans(txtLocation.getText(),
						txtPickUp.getText(), txtReturn.getText());
				if (vans != null) {
					updateLists(vans);
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnSearch.setBounds(236, 99, 89, 23);
		searchPanel.add(btnSearch);
		
		lblSearchTitle = new JLabel("Search for your perfect van");
		lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchTitle.setBounds(10, 11, 368, 30);
		searchPanel.add(lblSearchTitle);
		
		separator = new JSeparator();
		separator.setBounds(0, 131, 585, 2);
		searchPanel.add(separator);
		
		visualizePanel = new JPanel();
		visualizePanel.setBounds(0, 133, 586, 128);
		frame.getContentPane().add(visualizePanel);
		visualizePanel.setLayout(null);
		
		lblResultsTitle = new JLabel("Results");
		lblResultsTitle.setBounds(10, 11, 56, 19);
		lblResultsTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		visualizePanel.add(lblResultsTitle);
		
		jlVansList = new JList<String>();
		jlVansList.setBounds(574, 118, -558, -83);
		jlVansList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollVans = new JScrollPane();
		scrollVans.setBounds(10, 33, 566, 84);
		scrollVans.setViewportView(jlVansList);
		visualizePanel.add(scrollVans);
	}
	
	// METHODS FOR DATA DISPLAY IN THE GUI WINDOW
	private void updateLists(ArrayList<VanDTO> vans) {
		System.out.println("Dentro funcion -> " + vans);
		vansList.clear();
		for (int i = 0; i < vans.size(); i++) {
			VanDTO v = (VanDTO) vans.get(i);
			vansList.addElement("Van: " + v.getBrand() + " " + v.getModel() + " (" + v.getCapacity() + "people) Price: " + v.getPricePerDay());
		}
		jlVansList.setSelectedIndex(0);
	}
}


