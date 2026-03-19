package WindowsPack;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import ScorePack.Score;
import Storage.Scorage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JToggleButton;


//used to extend JFrame
public class CheatWindow extends JPanel{

	//private JFrame frame;
	private JTextField textField;
	private int points;
	private FangSlayerWindow window;
	private Image Background;
	private JToggleButton musicToggleButton;
	private JButton exitBtn;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public CheatWindow(//int points, 
			FangSlayerWindow window) {
		this.window=window;
		
		 try {
			 //NOTE TO SELF: TEMPORARY BACKGROUND.... MAKE OFFICIAL BACKGROUND
		        Background = ImageIO.read(new File("Images/FangMainMenu2.png"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		
		//this.points=points;
		initialize();
	}
	
	//paints the background
		@Override
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    if (Background != null) {
		        g.drawImage(Background, 0, 0, getWidth(), getHeight(), this);
		    }
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//makes the window
		//setBounds(100, 100, 450, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//label for the cheat header
		JLabel headerlbl = new JLabel("Insert a cheat code");
		headerlbl.setVerticalAlignment(SwingConstants.TOP);
		headerlbl.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		headerlbl.setForeground(Color.red);
		headerlbl.setBounds(98, 192, 253, 39);//old:98, 52, 253, 39
		add(headerlbl);
		
		//music header
		JLabel musicheaderlbl = new JLabel("Music Toggle");
		musicheaderlbl.setVerticalAlignment(SwingConstants.TOP);
		musicheaderlbl.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		musicheaderlbl.setForeground(Color.red);
		musicheaderlbl.setBounds(1130, 192, 253, 39);//old:98, 52, 253, 39
		add(musicheaderlbl);
		
		
		textField = new JTextField();
		textField.setBounds(154, 250, 96, 19);//old:154, 130, 96, 19
		add(textField);
		textField.setColumns(10);
		
		JButton cheatBtn = new JButton("Submit Cheat Code");
		cheatBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
		cheatBtn.setBounds(115, 279, 190, 31);//old:135, 159, 137, 21
		add(cheatBtn);
		
		exitBtn = new JButton("Exit to Main Menu");
		exitBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		exitBtn.setBounds(605, 655, 261, 50);
		add(exitBtn);
		
		//music toggle button
		//musicToggleButton = new JToggleButton("Music On/Off");
		musicToggleButton = new JToggleButton("On");
		musicToggleButton.setBounds(1135, 279, 140, 31);
		musicToggleButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		
		add(musicToggleButton);
		
		
		//accompanying listener for the button
				cheatBtn.addActionListener(e -> {
					window.menuSelect("Main"); 
					
					if (textField.getText().equals("groovy")) {
					window.setPrim(2);}
					JOptionPane.showMessageDialog(null, "Inputted cheat code: New Primary Weapon: Boomstick!");
			       });
				
				musicToggleButton.addActionListener(e -> {
					if (musicToggleButton.isSelected()) {//checks if the button is selected if so no music
						window.setJamsesh(false);
						musicToggleButton.setText("Off");
					}
					else {
						window.setJamsesh(true);
					musicToggleButton.setText("On");} //if not there IS music
			       });
				
				exitBtn.addActionListener(e -> {
					window.menuSelect("Main"); 
			       });
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
