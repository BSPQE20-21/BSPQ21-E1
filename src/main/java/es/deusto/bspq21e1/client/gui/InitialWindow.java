package es.deusto.bspq21e1.client.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import es.deusto.bspq21e1.client.controller.Controller;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private JPanel panel;
    private JButton btnRegister, btnLogin, btnExit;
    private JLabel lblLogoImg;
    private JLabel lblSpanishImg;
    private JLabel lblEnglishImg;
    private JLabel lblBasqueImg;
    private JLabel lblSpanishButton;
    private JLabel lblEnglishButton;
    private JLabel lblBasqueButton;

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
        frmAirbv.setBounds(100, 100, 355, 350);
        frmAirbv.setResizable(false);
        frmAirbv.setLocationRelativeTo(null);
        frmAirbv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAirbv.getContentPane().setLayout(null);
		frmAirbv.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/AirBV.png"));
		
        lblHead = new JLabel(controller.getResourcebundle().getString("initial_head_msg"));
        lblHead.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHead.setBounds(20, 72, 202, 25);
        frmAirbv.getContentPane().add(lblHead);
        lblHead.updateUI();

        panel = new JPanel();
        panel.setBounds(68, 107, 202, 192);
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
        btnRegister.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRegister.setBounds(13, 36, 175, 29);
        		btnRegister.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnRegister.setBounds(25, 36, 150, 25);
		    	btnRegister.updateUI();
		    }
		});
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
        btnLogin.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
		    	btnLogin.setBounds(13, 85, 175, 29);
		    	btnLogin.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnLogin.setBounds(25, 85, 150, 25);
		    	btnLogin.updateUI();
		    }
		});
        panel.add(btnLogin);
        btnLogin.updateUI();

        btnExit = new JButton(controller.getResourcebundle().getString("exit_msg"));
        btnExit.setBounds(25, 143, 150, 25);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frmAirbv.dispose();
            }
        } );
        btnExit.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
		    	btnExit.setBounds(13, 143, 175, 29);
		    	btnExit.setBackground(Color.RED);
		    	btnExit.updateUI();
		    }
		    public void mouseExited(MouseEvent e) {
		    	btnExit.setBounds(25, 143, 150, 25);
		    	btnExit.setBackground(btnLogin.getBackground());
		    	btnExit.updateUI();
		    }
		});
        panel.add(btnExit);   
        btnExit.updateUI();

        //IMG CARAVAN
        lblLogoImg = new JLabel("");
        lblLogoImg.setBounds(248, 65, 64, 38);
        Image logoImg = new ImageIcon("src/main/resources/images/caravan.png").getImage();
        ImageIcon logoImgScaled = new ImageIcon(logoImg.getScaledInstance(63, 37, Image.SCALE_SMOOTH));
        lblLogoImg.setIcon(logoImgScaled);
        
        frmAirbv.getContentPane().add(lblLogoImg);
        lblLogoImg.updateUI();
        
        //IMG SPANISH
        lblSpanishImg = new JLabel("");
        lblSpanishImg.setBounds(25, 10, 45, 25);
        Image spanishImg = new ImageIcon("src/main/resources/images/spanish_flag.png").getImage();
        ImageIcon spanishImgScaled = new ImageIcon(spanishImg.getScaledInstance(25, 16, Image.SCALE_SMOOTH));
        lblSpanishImg.setIcon(spanishImgScaled);
        lblSpanishImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setLocale("es");	
				frmAirbv.dispose();
				new InitialWindow(controller);
			}
		});
        frmAirbv.getContentPane().add(lblSpanishImg);
        lblSpanishImg.updateUI();
        
        //IMG ENGLISH
        lblEnglishImg = new JLabel("");
        lblEnglishImg.setBounds(129, 10, 45, 25);
        Image englishImg = new ImageIcon("src/main/resources/images/english_flag.png").getImage();
        ImageIcon englishImgScaled = new ImageIcon(englishImg.getScaledInstance(25, 16, Image.SCALE_SMOOTH));
        lblEnglishImg.setIcon(englishImgScaled);
        lblEnglishImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setLocale("en");
				frmAirbv.dispose();
				new InitialWindow(controller);
			}
		});
        frmAirbv.getContentPane().add(lblEnglishImg);
        lblEnglishImg.updateUI();    
        
        //IMG BASQUE
        lblBasqueImg = new JLabel("");
        lblBasqueImg.setBounds(228, 10, 45, 25);
        Image basqueImg = new ImageIcon("src/main/resources/images/basque_flag.png").getImage();
        ImageIcon basqueImgScaled = new ImageIcon(basqueImg.getScaledInstance(25, 16, Image.SCALE_SMOOTH));
        lblBasqueImg.setIcon(basqueImgScaled);
        lblBasqueImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setLocale("eu");	
				frmAirbv.dispose();
				new InitialWindow(controller);
			}
		});
        frmAirbv.getContentPane().add(lblBasqueImg);
        lblBasqueImg.updateUI();
        
        //SPANISH BUTTON
        lblSpanishButton = new JLabel("<html><u>" + controller.getResourcebundle().getString("spanish_msg"));
        lblSpanishButton.setBounds(55, 15, 55, 13);
        lblSpanishButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setLocale("es");	
				frmAirbv.dispose();
				new InitialWindow(controller);
			}
		});
        lblSpanishButton.setForeground(Color.BLUE);
        frmAirbv.getContentPane().add(lblSpanishButton);
        lblSpanishButton.updateUI();
        
        //ENGLISH BUTTON
        lblEnglishButton = new JLabel("<html><u>" + controller.getResourcebundle().getString("english_msg"));
        lblEnglishButton.setForeground(Color.BLUE);
        lblEnglishButton.setBounds(160, 15, 55, 13);
        lblEnglishButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setLocale("en");
				frmAirbv.dispose();
				new InitialWindow(controller);
			}
		});
        frmAirbv.getContentPane().add(lblEnglishButton);
        lblEnglishButton.updateUI();
        
        //BASQUE BUTTON
        lblBasqueButton = new JLabel("<html><u>" + controller.getResourcebundle().getString("basque_msg"));
        lblBasqueButton.setForeground(Color.BLUE);
        lblBasqueButton.setBounds(260, 15, 60, 13);
        lblBasqueButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setLocale("eu");	
				frmAirbv.dispose();
				new InitialWindow(controller);
			}
		});
        frmAirbv.getContentPane().add(lblBasqueButton);
        lblBasqueButton.updateUI();
        
       
        
       
        logger.info("InitialWindow well initialized");
    }
}