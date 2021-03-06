package es.deusto.bspq21e1.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.apache.log4j.Logger;

import javax.swing.JSpinner;
import javax.swing.JRadioButton;

import es.deusto.bspq21e1.client.controller.Controller;
import es.deusto.bspq21e1.serialization.UserData;
import es.deusto.bspq21e1.serialization.VanData;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class RegisterVanWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RegisterVanWindow.class.getName());
	private Controller controller;
	private boolean visible;
	private UserData user;
	private JFrame frmMain;
	
	private JFrame frmRegistrationOfVans = new JFrame();
	
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
	private JButton btnCancel, btnRegisterVan;
	
	/**
	 * Create the application.
	 */
	public RegisterVanWindow(Controller controller, UserData user, JFrame frmMain, boolean visible) {
		this.controller = controller;
		this.visible = visible;
		this.user = user;
		this.frmMain = frmMain;
		initialize();
		frmRegistrationOfVans.setTitle(controller.getResourcebundle().getString("resgister_van_window_tittle_msg"));
		frmRegistrationOfVans.setVisible(visible);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistrationOfVans.setResizable(false);
		frmRegistrationOfVans.setBounds(100, 100, 380, 575);
		frmRegistrationOfVans.setLocationRelativeTo(null);
		frmRegistrationOfVans.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistrationOfVans.getContentPane().setLayout(null);
		frmRegistrationOfVans.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
		lblTitle = new JLabel(controller.getResourcebundle().getString("register_your_van_msg"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(100, 30, 200, 20);
		frmRegistrationOfVans.getContentPane().add(lblTitle);
		
		//LicensePlate
		lblLicensePlate = new JLabel(controller.getResourcebundle().getString("license_plate_msg"));
		lblLicensePlate.setBounds(40, 81, 125, 19);
		frmRegistrationOfVans.getContentPane().add(lblLicensePlate);
		
		txtFieldLicensePlate = new JTextField("");
		txtFieldLicensePlate.setBounds(200, 81, 120, 19);
		frmRegistrationOfVans.getContentPane().add(txtFieldLicensePlate);
		txtFieldLicensePlate.setColumns(10);
		
		//Brand
		lblBrand = new JLabel(controller.getResourcebundle().getString("brand_msg"));
		lblBrand.setBounds(40, 120, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblBrand);
		
		txtFieldBrand = new JTextField("");
		txtFieldBrand.setBounds(200, 117, 120, 19);
		frmRegistrationOfVans.getContentPane().add(txtFieldBrand);
		txtFieldBrand.setColumns(10);
		
		//Model
		lblMoldel = new JLabel(controller.getResourcebundle().getString("model_msg"));
		lblMoldel.setBounds(40, 160, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblMoldel);
		
		txtFieldModel = new JTextField("");
		txtFieldModel.setBounds(200, 156, 120, 19);
		frmRegistrationOfVans.getContentPane().add(txtFieldModel);
		txtFieldModel.setColumns(10);
		
		//Location
		lblLocation = new JLabel(controller.getResourcebundle().getString("location_msg"));
		lblLocation.setBounds(40, 200, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblLocation);
		
		txtFieldLocation = new JTextField("");
		txtFieldLocation.setColumns(10);
		txtFieldLocation.setBounds(200, 197, 120, 19);
		frmRegistrationOfVans.getContentPane().add(txtFieldLocation);
		
		//Capacity
		lblCapacity = new JLabel(controller.getResourcebundle().getString("capacity_msg"));
		lblCapacity.setBounds(40, 250, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblCapacity);
		
		SpinnerNumberModel capacitySpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);  
		spinnerCapacity = new JSpinner(capacitySpinnerModel);
		spinnerCapacity.setBounds(200, 247, 54, 20);
		frmRegistrationOfVans.getContentPane().add(spinnerCapacity);
		
		//Kitchen
		lblKitchen = new JLabel(controller.getResourcebundle().getString("kitchen_msg"));
		lblKitchen.setBounds(40, 300, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblKitchen);
		
		kitchenButtonGroup = new ButtonGroup();
		
		rdbtnKitchenTrue = new JRadioButton(controller.getResourcebundle().getString("yes_msg"));
		rdbtnKitchenTrue.setBounds(180, 296, 70, 21);
		rdbtnKitchenTrue.setSelected(true);
		frmRegistrationOfVans.getContentPane().add(rdbtnKitchenTrue);
		kitchenButtonGroup.add(rdbtnKitchenTrue);
		
		rdbtnKitchenFalse = new JRadioButton(controller.getResourcebundle().getString("no_msg"));
		rdbtnKitchenFalse.setBounds(270, 296, 70, 21);
		frmRegistrationOfVans.getContentPane().add(rdbtnKitchenFalse);
		kitchenButtonGroup.add(rdbtnKitchenFalse);
		
		//Shower
		lblShower = new JLabel(controller.getResourcebundle().getString("shower_msg"));
		lblShower.setBounds(40, 350, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblShower);
		
		showerButtonGroup = new ButtonGroup();
		
		rdbtnShowerTrue = new JRadioButton(controller.getResourcebundle().getString("yes_msg"));
		rdbtnShowerTrue.setBounds(180, 346, 70, 21);
		rdbtnShowerTrue.setSelected(true);
		frmRegistrationOfVans.getContentPane().add(rdbtnShowerTrue);
		showerButtonGroup.add(rdbtnShowerTrue);
		
		rdbtnShowerFalse = new JRadioButton(controller.getResourcebundle().getString("no_msg"));
		rdbtnShowerFalse.setBounds(270, 346, 70, 21);
		frmRegistrationOfVans.getContentPane().add(rdbtnShowerFalse);
		showerButtonGroup.add(rdbtnShowerFalse);
		
		//Off-road
		lblOffRoad = new JLabel(controller.getResourcebundle().getString("off_road_msg"));
		lblOffRoad.setBounds(40, 400, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblOffRoad);
		
		offRoadButtonGroup = new ButtonGroup(); 
		
		rdbtnOffRoadTrue = new JRadioButton(controller.getResourcebundle().getString("yes_msg"));
		rdbtnOffRoadTrue.setSelected(true);
		rdbtnOffRoadTrue.setBounds(180, 396, 70, 21);
		frmRegistrationOfVans.getContentPane().add(rdbtnOffRoadTrue);
		offRoadButtonGroup.add(rdbtnOffRoadTrue);
		
		rdbtnOffRoadFalse = new JRadioButton(controller.getResourcebundle().getString("no_msg"));
		rdbtnOffRoadFalse.setBounds(270, 396, 70, 21);
		frmRegistrationOfVans.getContentPane().add(rdbtnOffRoadFalse);
		offRoadButtonGroup.add(rdbtnOffRoadFalse);
		
		//Price
		lblPricePerDay = new JLabel(controller.getResourcebundle().getString("price_per_day_msg"));
		lblPricePerDay.setBounds(40, 450, 125, 13);
		frmRegistrationOfVans.getContentPane().add(lblPricePerDay);
		
		SpinnerNumberModel priceSpinnerModel = new SpinnerNumberModel(99.99, 0.0, 1500.0, 10.0);
		spinnerPrice = new JSpinner(priceSpinnerModel);
		spinnerPrice.setBounds(200, 447, 94, 20);
		frmRegistrationOfVans.getContentPane().add(spinnerPrice);
		
		//BUTTON: Cancel
		btnCancel = new JButton(controller.getResourcebundle().getString("cancel_button_msg"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.setVisible(visible);
				frmRegistrationOfVans.dispose();
				
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnCancel.setBounds(35, 495, 110, 26);
        		btnCancel.setBackground(Color.RED);
        		btnCancel.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnCancel.setBounds(40, 495, 100, 25);
		    	btnCancel.setBackground(btnRegisterVan.getBackground());
		    	btnCancel.updateUI();
		    }
		});
		btnCancel.setBounds(40, 495, 100, 25);
		frmRegistrationOfVans.getContentPane().add(btnCancel);
		
		//BUTTON: Register Van
		btnRegisterVan = new JButton(controller.getResourcebundle().getString("register_van_button_msg"));
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
				if( licensePlate != "" && brand != "" && model != "" && location != ""
						&& null != licensePlate && null != brand && null != model && null != location ) {
					
					VanData vanData = new VanData(licensePlate, brand, model, location, capacity, kitchen, shower, offRoad, pricePerDay, user.getDni());
					// DEBUGGING:
					logger.debug("$ DEBUGGING\n" +
							"\tPrinting VanData and User from RegisterVanWindow in Client side:\n"+
							"\tVan: " + vanData +
							"\n\tUser: " + vanData.getUser() +
							"\n=======================\n");
					controller.registerVan(vanData);
					frmMain.setVisible(visible);
					frmRegistrationOfVans.dispose();
					logger.debug("Van correctly registered");
				}
			}
		});
		btnRegisterVan.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRegisterVan.setBounds(165, 495, 165, 26);
        		btnRegisterVan.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnRegisterVan.setBounds(170, 495, 155, 25);
		    	btnRegisterVan.updateUI();
		    }
		});
		btnRegisterVan.setBounds(170, 495, 155, 25);
		frmRegistrationOfVans.getContentPane().add(btnRegisterVan);
	
		logger.info("RegisterVanWindow well initialized");
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		RegisterVanWindow.logger = logger;
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

	public JFrame getFrmMain() {
		return frmMain;
	}

	public void setFrmMain(JFrame frmMain) {
		this.frmMain = frmMain;
	}

	public JFrame getFrmRegistrationOfVans() {
		return frmRegistrationOfVans;
	}

	public void setFrmRegistrationOfVans(JFrame frmRegistrationOfVans) {
		this.frmRegistrationOfVans = frmRegistrationOfVans;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JLabel getLblLicensePlate() {
		return lblLicensePlate;
	}

	public void setLblLicensePlate(JLabel lblLicensePlate) {
		this.lblLicensePlate = lblLicensePlate;
	}

	public JTextField getTxtFieldLicensePlate() {
		return txtFieldLicensePlate;
	}

	public void setTxtFieldLicensePlate(JTextField txtFieldLicensePlate) {
		this.txtFieldLicensePlate = txtFieldLicensePlate;
	}

	public JLabel getLblBrand() {
		return lblBrand;
	}

	public void setLblBrand(JLabel lblBrand) {
		this.lblBrand = lblBrand;
	}

	public JTextField getTxtFieldBrand() {
		return txtFieldBrand;
	}

	public void setTxtFieldBrand(JTextField txtFieldBrand) {
		this.txtFieldBrand = txtFieldBrand;
	}

	public JLabel getLblMoldel() {
		return lblMoldel;
	}

	public void setLblMoldel(JLabel lblMoldel) {
		this.lblMoldel = lblMoldel;
	}

	public JTextField getTxtFieldModel() {
		return txtFieldModel;
	}

	public void setTxtFieldModel(JTextField txtFieldModel) {
		this.txtFieldModel = txtFieldModel;
	}

	public JLabel getLblLocation() {
		return lblLocation;
	}

	public void setLblLocation(JLabel lblLocation) {
		this.lblLocation = lblLocation;
	}

	public JTextField getTxtFieldLocation() {
		return txtFieldLocation;
	}

	public void setTxtFieldLocation(JTextField txtFieldLocation) {
		this.txtFieldLocation = txtFieldLocation;
	}

	public JLabel getLblCapacity() {
		return lblCapacity;
	}

	public void setLblCapacity(JLabel lblCapacity) {
		this.lblCapacity = lblCapacity;
	}

	public JSpinner getSpinnerCapacity() {
		return spinnerCapacity;
	}

	public void setSpinnerCapacity(JSpinner spinnerCapacity) {
		this.spinnerCapacity = spinnerCapacity;
	}

	public JLabel getLblKitchen() {
		return lblKitchen;
	}

	public void setLblKitchen(JLabel lblKitchen) {
		this.lblKitchen = lblKitchen;
	}

	public ButtonGroup getKitchenButtonGroup() {
		return kitchenButtonGroup;
	}

	public void setKitchenButtonGroup(ButtonGroup kitchenButtonGroup) {
		this.kitchenButtonGroup = kitchenButtonGroup;
	}

	public JRadioButton getRdbtnKitchenTrue() {
		return rdbtnKitchenTrue;
	}

	public void setRdbtnKitchenTrue(JRadioButton rdbtnKitchenTrue) {
		this.rdbtnKitchenTrue = rdbtnKitchenTrue;
	}

	public JRadioButton getRdbtnKitchenFalse() {
		return rdbtnKitchenFalse;
	}

	public void setRdbtnKitchenFalse(JRadioButton rdbtnKitchenFalse) {
		this.rdbtnKitchenFalse = rdbtnKitchenFalse;
	}

	public JLabel getLblShower() {
		return lblShower;
	}

	public void setLblShower(JLabel lblShower) {
		this.lblShower = lblShower;
	}

	public ButtonGroup getShowerButtonGroup() {
		return showerButtonGroup;
	}

	public void setShowerButtonGroup(ButtonGroup showerButtonGroup) {
		this.showerButtonGroup = showerButtonGroup;
	}

	public JRadioButton getRdbtnShowerTrue() {
		return rdbtnShowerTrue;
	}

	public void setRdbtnShowerTrue(JRadioButton rdbtnShowerTrue) {
		this.rdbtnShowerTrue = rdbtnShowerTrue;
	}

	public JRadioButton getRdbtnShowerFalse() {
		return rdbtnShowerFalse;
	}

	public void setRdbtnShowerFalse(JRadioButton rdbtnShowerFalse) {
		this.rdbtnShowerFalse = rdbtnShowerFalse;
	}

	public ButtonGroup getOffRoadButtonGroup() {
		return offRoadButtonGroup;
	}

	public void setOffRoadButtonGroup(ButtonGroup offRoadButtonGroup) {
		this.offRoadButtonGroup = offRoadButtonGroup;
	}

	public JLabel getLblOffRoad() {
		return lblOffRoad;
	}

	public void setLblOffRoad(JLabel lblOffRoad) {
		this.lblOffRoad = lblOffRoad;
	}

	public JRadioButton getRdbtnOffRoadTrue() {
		return rdbtnOffRoadTrue;
	}

	public void setRdbtnOffRoadTrue(JRadioButton rdbtnOffRoadTrue) {
		this.rdbtnOffRoadTrue = rdbtnOffRoadTrue;
	}

	public JRadioButton getRdbtnOffRoadFalse() {
		return rdbtnOffRoadFalse;
	}

	public void setRdbtnOffRoadFalse(JRadioButton rdbtnOffRoadFalse) {
		this.rdbtnOffRoadFalse = rdbtnOffRoadFalse;
	}

	public JLabel getLblPricePerDay() {
		return lblPricePerDay;
	}

	public void setLblPricePerDay(JLabel lblPricePerDay) {
		this.lblPricePerDay = lblPricePerDay;
	}

	public JSpinner getSpinnerPrice() {
		return spinnerPrice;
	}

	public void setSpinnerPrice(JSpinner spinnerPrice) {
		this.spinnerPrice = spinnerPrice;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JButton getBtnRegisterVan() {
		return btnRegisterVan;
	}

	public void setBtnRegisterVan(JButton btnRegisterVan) {
		this.btnRegisterVan = btnRegisterVan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
