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
	private JToggleButton boomstickToggleButton;
	private JToggleButton lazerToggleButton;
	private JButton exitBtn;
	private boolean boomtrue;
	private boolean lazertrue;
	
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
		JLabel headerlbl = new JLabel("Cheat Codes");
		headerlbl.setVerticalAlignment(SwingConstants.TOP);
		headerlbl.setFont(new Font("Viner Hand ITC", Font.BOLD, 35));
		headerlbl.setForeground(Color.red);
		headerlbl.setBounds(98, 192, 300, 39);//old:98, 192, 253, 39
		add(headerlbl);
		
		//music header
		JLabel musicheaderlbl = new JLabel("Music Toggle");
		musicheaderlbl.setVerticalAlignment(SwingConstants.TOP);
		musicheaderlbl.setFont(new Font("Viner Hand ITC", Font.BOLD, 35));
		musicheaderlbl.setForeground(Color.red);
		musicheaderlbl.setBounds(1100, 192, 253, 39);//old:98, 52, 253, 39
		add(musicheaderlbl);
		
		
		textField = new JTextField();
		textField.setBounds(154, 250, 96, 19);//old:154, 130, 96, 19
		add(textField);
		textField.setColumns(10);
		
		JButton cheatBtn = new JButton("Submit Cheat Code");
		cheatBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
		cheatBtn.setBounds(115, 279, 190, 31);//old:135, 159, 137, 21
		add(cheatBtn);
		
		JLabel boomsticklbl = new JLabel("Boomstick Toggle");
		boomsticklbl.setVerticalAlignment(SwingConstants.TOP);
		boomsticklbl.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		boomsticklbl.setForeground(Color.red);
		boomsticklbl.setBounds(115, 330, 253, 39);//old:98, 52, 253, 39
		
		JLabel lazerlbl = new JLabel("Lazer Toggle");
		lazerlbl.setVerticalAlignment(SwingConstants.TOP);
		lazerlbl.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		lazerlbl.setForeground(Color.red);
		lazerlbl.setBounds(115, 380, 253, 39);//old:98, 52, 253, 39
		
		
		//boomstick toggle 
		boomstickToggleButton = new JToggleButton("On");
		boomstickToggleButton.setBounds(315, 330, 140, 31);
		boomstickToggleButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		
		//lazer toggle
		lazerToggleButton = new JToggleButton("On");
		lazerToggleButton.setBounds(315, 380, 140, 31);
		lazerToggleButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		
		
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
					String message = "Invalid cheat code... poser";
					
					//window.menuSelect("Main"); 
					
					if (textField.getText().equals("groovy")) {
					window.setPrim(2);
					//we now have the option to set the primary weapon to boomstick
					add(boomstickToggleButton);
					add(boomsticklbl);
					boomstickToggleButton.setSelected(true);
					
					int rand = (int) (Math.random()*2)+1;
					if (rand==1) {
					message ="Hail to the king baby! You unlocked the Boomstick!";}
					else {
						message = "Be vewy vewy quiet, it's vampire season and You unlocked the Boomstick!";
					}
					}
					else if (textField.getText().equals("crossbowgobrrr")) {
						window.setLazer(true);
						add(lazerToggleButton);
						add(lazerlbl);
						lazerToggleButton.setSelected(true);
						int rand = (int) (Math.random()*2)+1;
						if (rand==1) {
						message ="Tee hee hee, crossbow go brRrRrRRrRrRRrRrRrrRrr!";}
						else {
							message = "It's not a bug, its a feature! You unlocked the lazer crossbow!";
						}
					}
					//THESE ARE DEV CHEAT CODES (SPECIFICALLY FOR TESTING PURPOSES ONLY)
					else if (textField.getText().equals("godmode")) {
						window.setLazer(true);
						window.setPrim(2);
						add(boomstickToggleButton);
						add(boomsticklbl);
						add(lazerToggleButton);
						add(lazerlbl);
						message ="Jarvis, activate all cheat codes.";
					}
					else if (textField.getText().equals("vanilla")) {
						window.setLazer(false);
						window.setPrim(1);
						remove(boomstickToggleButton);
						remove(boomsticklbl);
						remove(lazerToggleButton);
						remove(lazerlbl);
						message ="Lets go back to basics.";
					}
					
					
					JOptionPane.showMessageDialog(null, message);
					repaint();
			       });
				//MUSIC TOGGLE
				musicToggleButton.addActionListener(e -> {
					if (musicToggleButton.isSelected()) {//checks if the button is selected if so no music
						window.setJamsesh(false);
						musicToggleButton.setText("Off");
					}
					else {
						window.setJamsesh(true);
					musicToggleButton.setText("On");} //if not there IS music
			       });
			//BOMMSTICK TOGGLE
				boomstickToggleButton.addActionListener(e -> {
					if (boomstickToggleButton.isSelected()) {//checks if the button is selected if so no music
						window.setPrim(1);
						boomstickToggleButton.setText("Off");
					}
					else {
						window.setPrim(2);
					boomstickToggleButton.setText("On");} //if not there IS music
			       });
				//LAZER TOGGLE
				lazerToggleButton.addActionListener(e -> {
					if (lazerToggleButton.isSelected()) {//checks if the button is selected if so no music
						window.setLazer(false);
						lazerToggleButton.setText("Off");
					}
					else {
						window.setLazer(true);
					lazerToggleButton.setText("On");} //if not there IS music
			       });
				
				exitBtn.addActionListener(e -> {
					window.menuSelect("Main"); 
			       });
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
