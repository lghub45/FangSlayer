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


public class CheatWindow extends JPanel{

	
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
	 * Create the application.
	 */
	public CheatWindow(FangSlayerWindow window) {
		this.window=window;
		//sets the background
		 try {
		        Background = ImageIO.read(new File("Images/FangMainMenu2.png"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
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
		
		//cheatbox user input
		textField = new JTextField();
		textField.setBounds(154, 250, 96, 19);//old:154, 130, 96, 19
		add(textField);
		textField.setColumns(10);
		
		//cheat submit button
		JButton cheatBtn = new JButton("Submit Cheat Code");
		cheatBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
		cheatBtn.setBounds(115, 279, 190, 31);//old:135, 159, 137, 21
		add(cheatBtn);
		
		//TOGGLES
		//boomstick label
		JLabel boomsticklbl = new JLabel("Boomstick Toggle");
		boomsticklbl.setVerticalAlignment(SwingConstants.TOP);
		boomsticklbl.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		boomsticklbl.setForeground(Color.red);
		boomsticklbl.setBounds(115, 330, 253, 39);//old:98, 52, 253, 39
		//lazer label
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
		
		//exit button
		exitBtn = new JButton("Exit to Main Menu");
		exitBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		exitBtn.setBounds(605, 655, 261, 50);
		add(exitBtn);
		
		//music toggle button
		musicToggleButton = new JToggleButton("On");
		musicToggleButton.setBounds(1135, 279, 140, 31);
		musicToggleButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		
		add(musicToggleButton);
		
		
		//listener checks for valid cheat codes and applies accordingly
				cheatBtn.addActionListener(e -> {
					String message = "Invalid cheat code... poser";

					//set boomstick to primary weapon 
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
					//activate the lazer crossbow (weapon FEATURE not a primary weapon)
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
					else if (textField.getText().equals("godmode")) {//applies both cheat codes
						window.setLazer(true);
						window.setPrim(2);
						add(boomstickToggleButton);
						add(boomsticklbl);
						add(lazerToggleButton);
						add(lazerlbl);
						message ="Jarvis, activate all cheat codes.";
					}
					else if (textField.getText().equals("vanilla")) {//reset the cheat codes
						window.setLazer(false);
						window.setPrim(1);
						remove(boomstickToggleButton);
						remove(boomsticklbl);
						remove(lazerToggleButton);
						remove(lazerlbl);
						message ="Lets go back to basics.";
					}
					//removes all the highscores from the database (this is for testing purposes)
					else if (textField.getText().equals("excommunicado")) {
						Scorage.clearScores();
						window.refreshLeader(); // has the leaderboard visually showcase the score board is in fact empty
						message ="Global leaderboard cleared. Excommunicado.";
					}
					//brings back the test high scores (repopulates the database with the test scores)
					else if (textField.getText().equals("VIP")) {
						//just in case the leaderboard is already populated, we clear the scores
						if (Scorage.getTopTen().size()<1) {
							Scorage.clearScores();
						}
						Score s1 = new Score(19870, "wuzd@deByte?","Normal");
						Score s2 = new Score(9861, "JAYsun_vorHEEZ","Normal");
						Score s3 = new Score(9000, "alucard","Normal");
						Score s4 = new Score(9000, "marceline","Normal");
						Score s5 = new Score(8679, "Vamiplier","Normal");
						Score s6 = new Score(8666, "mORBINTIme","Normal");
						Score s7 = new Score(8585, "renfield","Normal");
						Score s8 = new Score(8582, "@um_sandLR","Normal");
						Score s9 = new Score(8558, "vladdDEEinhaler","Normal");
						Score s10 = new Score(8551, "BLAYde","Normal");
						Scorage.insertScore(s1);
						Scorage.insertScore(s2);
						Scorage.insertScore(s3);
						Scorage.insertScore(s4);
						Scorage.insertScore(s5);
						Scorage.insertScore(s6);
						Scorage.insertScore(s7);
						Scorage.insertScore(s8);
						Scorage.insertScore(s9);
						Scorage.insertScore(s10);
						window.refreshLeader(); // visuall updates the leaderboard
						message = "The VIPs are back. Global leaderboard repopulated.";
					}
					//outputs the message for each cheat code
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
