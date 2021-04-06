package es.deusto.bspq21e1.client.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import es.deusto.bspq21e1.client.controller.Controller;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

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
	private JSpinner spinner;
	

	/**
	 * Create the application.
	 */
	public RegisterVanWindow(Controller controller) {
		this.controller = controller;
		frame.setVisible(true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 575);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTitle = new JLabel("Resgister your van");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(62, 37, 237, 19);
		frame.getContentPane().add(lblTitle);
		
		lblLicensePlate = new JLabel("License Plate:");
		lblLicensePlate.setBounds(33, 117, 121, 19);
		frame.getContentPane().add(lblLicensePlate);
		
		txtFieldLicensePlate = new JTextField();
		txtFieldLicensePlate.setBounds(217, 117, 96, 19);
		frame.getContentPane().add(txtFieldLicensePlate);
		txtFieldLicensePlate.setColumns(10);
		
		lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(33, 180, 45, 13);
		frame.getContentPane().add(lblBrand);
		
		txtFieldBrand = new JTextField();
		txtFieldBrand.setBounds(217, 177, 96, 19);
		frame.getContentPane().add(txtFieldBrand);
		txtFieldBrand.setColumns(10);
		
		lblMoldel = new JLabel("Model");
		lblMoldel.setBounds(33, 223, 45, 13);
		frame.getContentPane().add(lblMoldel);
		
		txtFieldModel = new JTextField();
		txtFieldModel.setBounds(217, 220, 96, 19);
		frame.getContentPane().add(txtFieldModel);
		txtFieldModel.setColumns(10);
		
		lblCapacity = new JLabel("New label");
		lblCapacity.setBounds(33, 273, 45, 13);
		frame.getContentPane().add(lblCapacity);
		
		SpinnerNumberModel capacitySpinnerModel = new SpinnerNumberModel(1, 1, 3, 1);  
		spinner = new JSpinner(capacitySpinnerModel);
		spinner.setBounds(217, 270, 45, 20);
		frame.getContentPane().add(spinner);
	}
}
