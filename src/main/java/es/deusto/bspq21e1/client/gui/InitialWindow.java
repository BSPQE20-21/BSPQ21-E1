package es.deusto.bspq21e1.client.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import es.deusto.bspq21e1.client.controller.Controller;
import javax.swing.JLabel;

/**
 * Window class for the initial point of the program, where users can register or log in.
 * @author SPQ Group 1
 * @version 1.0
 */
public class InitialWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(InitialWindow.class.getName());
    private Controller controller;

    private JFrame frmAirbv = new JFrame();
    private JLabel lblHead;
    private ImagePanel panel;
    private JButton btnRegister, btnLogin, btnExit;
    
    private String imagePath = "${basedir}/src/main/resources/images/initialWindow.png";

    /**
	 * Creates the initial window of the program.
     * @param controller Controller used for handling all requests in the system.  
	 */
    public InitialWindow(Controller controller) {
        this.controller = controller;
        frmAirbv.setTitle("AirB&V");
        frmAirbv.setVisible(true);
        initialize();
    }

    /**
	 * Initializes all the elements the window needs to show to the user and their functionality.
	 */
    private void initialize() {
        frmAirbv.setBounds(100, 100, 255, 320);
        frmAirbv.setLocationRelativeTo(null);
        frmAirbv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAirbv.getContentPane().setLayout(null);
		
        lblHead = new JLabel(controller.getResourcebundle().getString("initial_head_msg"));
        lblHead.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHead.setBounds(23, 25, 202, 25);
        frmAirbv.getContentPane().add(lblHead);

        panel = new ImagePanel();
        panel.setImage(imagePath);
        panel.setBounds(20, 50, 202, 210);
        frmAirbv.getContentPane().add(panel);
        panel.setLayout(null);

        btnRegister = new JButton(controller.getResourcebundle().getString("register_msg"));
        btnRegister.setBounds(25, 36, 150, 25);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new RegisterUserWindow(controller);
                frmAirbv.dispose();
            }
        } );
        panel.add(btnRegister);
        btnRegister.updateUI();

        btnLogin = new JButton(controller.getResourcebundle().getString("login_msg"));
        btnLogin.setBounds(25, 85, 150, 25);
        btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LogInWindow(controller);
				frmAirbv.dispose();
			}
		});
        panel.add(btnLogin);
        btnLogin.updateUI();

        btnExit = new JButton(controller.getResourcebundle().getString("exit_msg"));
        btnExit.setBounds(25, 180, 150, 25);
        btnExit.setBackground(Color.RED);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frmAirbv.dispose();
            }
        } );
        panel.add(btnExit);
        btnExit.updateUI();

        logger.info("InitialWindow well initialized");
    }
    
}