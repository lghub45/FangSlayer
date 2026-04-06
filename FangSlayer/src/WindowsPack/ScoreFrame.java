package WindowsPack;

import java.awt.Color;
import javax.swing.JLabel;
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


public class ScoreFrame extends JPanel{

	private JTextField textField;
	private int points;
	private FangSlayerWindow window;
	private Image background;
	private String difficulty;
	
	/**
	 * Create the application.
	 */
	public ScoreFrame(FangSlayerWindow window) {
		this.window=window;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(null);
		
		//label for the header
		JLabel headerlbl = new JLabel("You're a professional monster hunter!");
		headerlbl.setVerticalAlignment(SwingConstants.TOP);
		headerlbl.setFont(new Font("Pristina", Font.BOLD, 45));
		headerlbl.setBounds(460, 155, 700, 50);
		headerlbl.setForeground(Color.red);
		add(headerlbl);
		
		//second label for the header
		JLabel lblCongrats = new JLabel("CONGRATS! ");
		lblCongrats.setVerticalAlignment(SwingConstants.TOP);
		lblCongrats.setFont(new Font("Pristina", Font.BOLD, 65));
		lblCongrats.setBounds(570, 72, 500, 50);
		lblCongrats.setForeground(Color.red);
		add(lblCongrats);
		
		//text field for user to input username
		textField = new JTextField();
		textField.setBounds(630, 355, 221, 50);
		textField.setFont(new Font("Pristina", Font.BOLD, 25));
		add(textField);
		textField.setColumns(10);
		
		//button to submit the high score
		JButton highBtn = new JButton("Submit High Score");
		highBtn.setFont(new Font("Pristina", Font.PLAIN, 25));
		highBtn.setBounds(630, 555, 221, 50);
		add(highBtn);
		
		
		//accompanying listener for high score button
				highBtn.addActionListener(e -> {
					Scorage.addScore(new Score(points,textField.getText(),difficulty)); //adds the score and inserted username to the database
					window.menuSelect("Leader"); //takes the user to bask in their glory
			       });
	}
	//setters for the points, difficulty, and background
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setDiff(String diff) {
		this.difficulty = diff;
	}
	
	public void setBackground(String map) {
		//set the background
		 try {
			 //different map means different background
			  if (map.equals("vamps")) {
		        background = ImageIO.read(new File("Images/castleofthedamned.png"));}
			  else if (map.equals("wolves")) {
				background = ImageIO.read(new File("Images/bloodmoonforest.png"));
			  }
			  else if (map.equals("finalboss")) {
				 background = ImageIO.read(new File("Images/bossphase1.png"));
			  }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		     super.paintComponent(g);
		     if (background != null) {
		         g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		 }
		 
	}
	
}
