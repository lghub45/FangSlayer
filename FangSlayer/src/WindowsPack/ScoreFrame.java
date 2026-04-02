package WindowsPack;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
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


//used to extend JFrame
public class ScoreFrame extends JPanel{

	//private JFrame frame;
	private JTextField textField;
	private int points;
	private FangSlayerWindow window;
	private Image background;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ScoreFrame(//int points, 
			FangSlayerWindow window) {
		this.window=window;
		//this.points=points;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//makes the window
		//setBounds(100, 100, 450, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//label for the header
		JLabel headerlbl = new JLabel("You're a professional monster hunter!");
		headerlbl.setVerticalAlignment(SwingConstants.TOP);
		headerlbl.setFont(new Font("Pristina", Font.BOLD, 45));//used to be 17
		headerlbl.setBounds(460, 155, 700, 50);
		//headerlbl.setBounds(98, 52, 253, 39);
		headerlbl.setForeground(Color.red);
		add(headerlbl);
		
		//second label for the header
		JLabel lblCongrats = new JLabel("CONGRATS! ");
		lblCongrats.setVerticalAlignment(SwingConstants.TOP);
		lblCongrats.setFont(new Font("Pristina", Font.BOLD, 65));//used to be 25
		lblCongrats.setBounds(570, 72, 500, 50);
		//lblCongrats.setBounds(136, 21, 155, 39);
		lblCongrats.setForeground(Color.red);
		add(lblCongrats);
		
		textField = new JTextField();
		textField.setBounds(630, 355, 221, 50);
		textField.setFont(new Font("Pristina", Font.BOLD, 25));//used to be 25
		//textField.setBounds(154, 130, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton highBtn = new JButton("Submit High Score");
		highBtn.setFont(new Font("Pristina", Font.PLAIN, 25));
		highBtn.setBounds(630, 555, 221, 50);
		//highBtn.setBounds(135, 159, 137, 21);
		add(highBtn);
		
		
		//accompanying listener for the button
				highBtn.addActionListener(e -> {
					Scorage.addScore(new Score(points,textField.getText())); //adds the score and inserted username to the database
					// dispose();
		            //LeaderFrame champ=new LeaderFrame();
		            //champ.setVisible(true);
					
			       });
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setBackground(String map) {
		//set the background
		 try {
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
