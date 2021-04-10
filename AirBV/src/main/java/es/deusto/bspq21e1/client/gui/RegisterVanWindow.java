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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegisterVanWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JFrame frame = new JFrame();
	
	private JLabel lblTitle;
	
	private JLabel lblLicensePlate;
	private JTextField txtFieldLicensePlate;
	
	private JLabel lblBrand;
	private JTextField txtFieldBrand;
	
	private JLabel lblMoldel;
	private JTextField txtFieldModel;
	
	private JLabel lblLocation;
	private JTextField txtFieldLocation;
	
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTitle = new JLabel("Resgister your van");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(50, 30, 237, 19);
		frame.getContentPane().add(lblTitle);
		
		//LicensePlate
		lblLicensePlate = new JLabel("License Plate");
		lblLicensePlate.setBounds(40, 81, 121, 19);
		frame.getContentPane().add(lblLicensePlate);
		
		txtFieldLicensePlate = new JTextField();
		txtFieldLicensePlate.setBounds(200, 81, 96, 19);
		frame.getContentPane().add(txtFieldLicensePlate);
		txtFieldLicensePlate.setColumns(10);
		
		//Brand
		lblBrand = new JLabel("Brand");
		lblBrand.setBounds(40, 120, 78, 13);
		frame.getContentPane().add(lblBrand);
		
		txtFieldBrand = new JTextField();
		txtFieldBrand.setBounds(200, 117, 96, 19);
		frame.getContentPane().add(txtFieldBrand);
		txtFieldBrand.setColumns(10);
		
		//Model
		lblMoldel = new JLabel("Model");
		lblMoldel.setBounds(40, 160, 45, 13);
		frame.getContentPane().add(lblMoldel);
		
		txtFieldModel = new JTextField();
		txtFieldModel.setBounds(200, 156, 96, 19);
		frame.getContentPane().add(txtFieldModel);
		txtFieldModel.setColumns(10);
		
		//Location
		lblLocation = new JLabel("Location");
		lblLocation.setBounds(40, 200, 45, 13);
		frame.getContentPane().add(lblLocation);
		
		txtFieldLocation = new JTextField();
		txtFieldLocation.setColumns(10);
		txtFieldLocation.setBounds(200, 197, 96, 19);
		frame.getContentPane().add(txtFieldLocation);
		
		//Capacity
		lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(40, 250, 80, 13);
		frame.getContentPane().add(lblCapacity);
		
		SpinnerNumberModel capacitySpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);  
		spinnerCapacity = new JSpinner(capacitySpinnerModel);
		spinnerCapacity.setBounds(200, 247, 54, 20);
		frame.getContentPane().add(spinnerCapacity);
		
		//Kitchen
		lblKitchen = new JLabel("Kitchen");
		lblKitchen.setBounds(40, 300, 54, 13);
		frame.getContentPane().add(lblKitchen);
		
		kitchenButtonGroup = new ButtonGroup();
		
		rdbtnKitchenTrue = new JRadioButton("Yes");
		rdbtnKitchenTrue.setBounds(163, 296, 63, 21);
		rdbtnKitchenTrue.setSelected(true);
		frame.getContentPane().add(rdbtnKitchenTrue);
		kitchenButtonGroup.add(rdbtnKitchenTrue);
		
		rdbtnKitchenFalse = new JRadioButton("No");
		rdbtnKitchenFalse.setBounds(250, 296, 63, 21);
		frame.getContentPane().add(rdbtnKitchenFalse);
		kitchenButtonGroup.add(rdbtnKitchenFalse);
		
		//Shower
		lblShower = new JLabel("Shower");
		lblShower.setBounds(40, 350, 54, 13);
		frame.getContentPane().add(lblShower);
		
		showerButtonGroup = new ButtonGroup();
		
		rdbtnShowerTrue = new JRadioButton("Yes");
		rdbtnShowerTrue.setBounds(163, 346, 63, 21);
		rdbtnShowerTrue.setSelected(true);
		frame.getContentPane().add(rdbtnShowerTrue);
		showerButtonGroup.add(rdbtnShowerTrue);
		
		rdbtnShowerFalse = new JRadioButton("No");
		rdbtnShowerFalse.setBounds(250, 346, 63, 21);
		frame.getContentPane().add(rdbtnShowerFalse);
		showerButtonGroup.add(rdbtnShowerFalse);
		
		//Off-road
		lblOffRoad = new JLabel("Off-Road");
		lblOffRoad.setBounds(40, 400, 80, 13);
		frame.getContentPane().add(lblOffRoad);
		
		offRoadButtonGroup = new ButtonGroup(); 
		
		rdbtnOffRoadTrue = new JRadioButton("Yes");
		rdbtnOffRoadTrue.setSelected(true);
		rdbtnOffRoadTrue.setBounds(163, 396, 63, 21);
		frame.getContentPane().add(rdbtnOffRoadTrue);
		offRoadButtonGroup.add(rdbtnOffRoadTrue);
		
		rdbtnOffRoadFalse = new JRadioButton("No");
		rdbtnOffRoadFalse.setBounds(250, 396, 63, 21);
		frame.getContentPane().add(rdbtnOffRoadFalse);
		offRoadButtonGroup.add(rdbtnOffRoadFalse);
		
		//Price
		lblPricePerDay = new JLabel("Price per day");
		lblPricePerDay.setBounds(40, 450, 80, 13);
		frame.getContentPane().add(lblPricePerDay);
		
		SpinnerNumberModel priceSpinnerModel = new SpinnerNumberModel(99.99, 0.0, 1500.0, 10.0);
		spinnerPrice = new JSpinner(priceSpinnerModel);
		spinnerPrice.setBounds(200, 447, 94, 20);
		frame.getContentPane().add(spinnerPrice);
		
		//BUTTON: Register Van
		JButton btnRegisterVan = new JButton("Register van");
		btnRegisterVan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String licensePlate = txtFieldLicensePlate.getText();
				String brand = txtFieldBrand.getText();
				String model = txtFieldModel.getText();
				String location = txtFieldLocation.getText();
				int capacity = (Integer) spinnerCapacity.getValue();
				Boolean kitchen = rdbtnKitchenTrue.isSelected();
				Boolean shower = rdbtnShowerTrue.isSelected();
				Boolean offRoad = rdbtnOffRoadTrue.isSelected();
				double pricePerDay = (Double) spinnerPrice.getValue();
				
				//CONTROLLER
				if( licensePlate != "" && brand != "" && model != "" && location != "") {
					
					VanDTO vanDTO = new VanDTO(licensePlate, brand, model, location, capacity, kitchen, shower, offRoad, pricePerDay);
					controller.registerVan(vanDTO);
					
				}
				
				
			}
		});
		btnRegisterVan.setBounds(140, 516, 147, 23);
		frame.getContentPane().add(btnRegisterVan);
		
		//BUTTON: Cancel
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnCancel.setBounds(333, 516, 89, 23);
		frame.getContentPane().add(btnCancel);
	
		
	}
}
