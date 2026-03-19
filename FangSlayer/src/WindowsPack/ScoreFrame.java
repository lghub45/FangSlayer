package WindowsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import ScorePack.Score;
import Storage.Scorage;
import javax.swing.JButton;


//used to extend JFrame
public class ScoreFrame extends JPanel{

	//private JFrame frame;
	private JTextField textField;
	private int points;
	private FangSlayerWindow window;

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
		headerlbl.setFont(new Font("Pristina", Font.BOLD, 17));
		headerlbl.setBounds(98, 52, 253, 39);
		add(headerlbl);
		
		//second label for the header
		JLabel lblCongrats = new JLabel("CONGRATS! ");
		lblCongrats.setVerticalAlignment(SwingConstants.TOP);
		lblCongrats.setFont(new Font("Pristina", Font.BOLD, 25));
		lblCongrats.setBounds(136, 21, 155, 39);
		add(lblCongrats);
		
		textField = new JTextField();
		textField.setBounds(154, 130, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton highBtn = new JButton("Submit High Score");
		highBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		highBtn.setBounds(135, 159, 137, 21);
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
	
}
