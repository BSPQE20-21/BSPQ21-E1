package es.deusto.bspq21e1.client.gui;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.server.dto.UserDTO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegisterVanWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frame;
	
	private JLabel lblTitle;
	
	private JLabel lblLicensePlate;
	private JTextField txtFieldLicensePlate;
	
	private JLabel lblBrand;
	private JTextField txtFieldBrand;
	
	private JLabel lblMoldel;
	private JTextField txtFieldModel;
	
	private JLabel lblCapacity;
	private JSpinner spinnerCapacity;
	
	private JLabel lblKitchen;
	private ButtonGroup kitchenButtonGroup;
	private JRadioButton rdbtnKitchenTrue;
	private JRadioButton rdbtnKitchenFalse;
	
	private JLabel lblShower;
	private ButtonGroup showerButtonGroup;
	private JRadioButton rdbtnShowerTrue;
	private JRadioButton rdbtnShowerFalse;
	
	private ButtonGroup offRoadButtonGroup;
	private JLabel lblOffRoad;
	private JRadioButton rdbtnOffRoadTrue;
	private JRadioButton rdbtnOffRoadFalse;
	
	private JLabel lblPricePerDay;
	private JSpinner spinnerPrice;
	private JButton btnCancel;
	
	/**
	 * Create the application.
	 */
	public RegisterVanWindow(Controller controller, UserDTO user) {
		this.controller = controller;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 475, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTitle = new JLabel("Resgister your van");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(62, 37, 237, 19);
		frame.getContentPane().add(lblTitle);
		
		//LicensePlate
		lblLicensePlate = new JLabel("License Plate");
		lblLicensePlate.setBounds(31, 117, 121, 19);
		frame.getContentPane().add(lblLicensePlate);
		
		txtFieldLicensePlate = new JTextField();
		txtFieldLicensePlate.setBounds(217, 117, 96, 19);
		frame.getContentPane().add(txtFieldLicensePlate);
		txtFieldLicensePlate.setColumns(10);
		
		//Brand
		lblBrand = new JLabel("Brand");
		lblBrand.setBounds(33, 161, 78, 13);
		frame.getContentPane().add(lblBrand);
		
		txtFieldBrand = new JTextField();
		txtFieldBrand.setBounds(217, 158, 96, 19);
		frame.getContentPane().add(txtFieldBrand);
		txtFieldBrand.setColumns(10);
		
		//Model
		lblMoldel = new JLabel("Model");
		lblMoldel.setBounds(31, 206, 45, 13);
		frame.getContentPane().add(lblMoldel);
		
		txtFieldModel = new JTextField();
		txtFieldModel.setBounds(217, 203, 96, 19);
		frame.getContentPane().add(txtFieldModel);
		txtFieldModel.setColumns(10);
		
		//Capacity
		lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(31, 257, 80, 13);
		frame.getContentPane().add(lblCapacity);
		
		SpinnerNumberModel capacitySpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);  
		spinnerCapacity = new JSpinner(capacitySpinnerModel);
		spinnerCapacity.setBounds(219, 253, 54, 20);
		frame.getContentPane().add(spinnerCapacity);
		
		//Kitchen
		lblKitchen = new JLabel("Kitchen");
		lblKitchen.setBounds(33, 317, 54, 13);
		frame.getContentPane().add(lblKitchen);
		
		kitchenButtonGroup = new ButtonGroup();
		
		rdbtnKitchenTrue = new JRadioButton("Yes");
		rdbtnKitchenTrue.setBounds(148, 313, 63, 21);
		rdbtnKitchenTrue.setSelected(true);
		frame.getContentPane().add(rdbtnKitchenTrue);
		kitchenButtonGroup.add(rdbtnKitchenTrue);
		
		rdbtnKitchenFalse = new JRadioButton("No");
		rdbtnKitchenFalse.setBounds(250, 313, 63, 21);
		frame.getContentPane().add(rdbtnKitchenFalse);
		kitchenButtonGroup.add(rdbtnKitchenFalse);
		
		//Shower
		lblShower = new JLabel("Shower");
		lblShower.setBounds(33, 361, 54, 13);
		frame.getContentPane().add(lblShower);
		
		showerButtonGroup = new ButtonGroup();
		
		rdbtnShowerTrue = new JRadioButton("Yes");
		rdbtnShowerTrue.setBounds(148, 357, 63, 21);
		rdbtnShowerTrue.setSelected(true);
		frame.getContentPane().add(rdbtnShowerTrue);
		showerButtonGroup.add(rdbtnShowerTrue);
		
		rdbtnShowerFalse = new JRadioButton("No");
		rdbtnShowerFalse.setBounds(250, 357, 63, 21);
		frame.getContentPane().add(rdbtnShowerFalse);
		showerButtonGroup.add(rdbtnShowerFalse);
		
		//Off-road
		lblOffRoad = new JLabel("Off-Road");
		lblOffRoad.setBounds(31, 403, 80, 13);
		frame.getContentPane().add(lblOffRoad);
		
		offRoadButtonGroup = new ButtonGroup(); 
		
		rdbtnOffRoadTrue = new JRadioButton("Yes");
		rdbtnOffRoadTrue.setSelected(true);
		rdbtnOffRoadTrue.setBounds(148, 399, 63, 21);
		frame.getContentPane().add(rdbtnOffRoadTrue);
		offRoadButtonGroup.add(rdbtnOffRoadTrue);
		
		rdbtnOffRoadFalse = new JRadioButton("No");
		rdbtnOffRoadFalse.setBounds(250, 399, 63, 21);
		frame.getContentPane().add(rdbtnOffRoadFalse);
		offRoadButtonGroup.add(rdbtnOffRoadFalse);
		
		//Price
		lblPricePerDay = new JLabel("Price per day");
		lblPricePerDay.setBounds(31, 447, 80, 13);
		frame.getContentPane().add(lblPricePerDay);
		
		SpinnerNumberModel priceSpinnerModel = new SpinnerNumberModel(99.99, 0.0, 1500.0, 10.0);
		spinnerPrice = new JSpinner(priceSpinnerModel);
		spinnerPrice.setBounds(219, 444, 94, 20);
		frame.getContentPane().add(spinnerPrice);
		
		//BUTTON: Register Van
		JButton btnRegisterVan = new JButton("Register van");
		btnRegisterVan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CONTROLLER
				
			}
		});
		btnRegisterVan.setBounds(148, 516, 147, 23);
		frame.getContentPane().add(btnRegisterVan);
		
		//BUTTON: Cancel
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnCancel.setBounds(355, 516, 89, 23);
		frame.getContentPane().add(btnCancel);
		
	}
}
