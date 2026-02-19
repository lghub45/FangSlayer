package WindowsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import ScorePack.Score;
import Storage.Scorage;
import javax.swing.JButton;

public class ScoreFrame extends JFrame{

	//private JFrame frame;
	private JTextField textField;
	private int points;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ScoreFrame(int points) {
		this.points=points;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//makes the window
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//label for the header
		JLabel headerlbl = new JLabel("You're a professional monster hunter!");
		headerlbl.setVerticalAlignment(SwingConstants.TOP);
		headerlbl.setFont(new Font("Pristina", Font.BOLD, 17));
		headerlbl.setBounds(98, 52, 253, 39);
		getContentPane().add(headerlbl);
		
		//second label for the header
		JLabel lblCongrats = new JLabel("CONGRATS! ");
		lblCongrats.setVerticalAlignment(SwingConstants.TOP);
		lblCongrats.setFont(new Font("Pristina", Font.BOLD, 25));
		lblCongrats.setBounds(136, 21, 155, 39);
		getContentPane().add(lblCongrats);
		
		textField = new JTextField();
		textField.setBounds(154, 130, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton highBtn = new JButton("Submit High Score");
		highBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		highBtn.setBounds(135, 159, 137, 21);
		getContentPane().add(highBtn);
		
		
		//accompanying listener for the button
				highBtn.addActionListener(e -> {
					Scorage.addScore(new Score(points,textField.getText())); //adds the score and inserted username to the database
					 dispose();
		            LeaderFrame champ=new LeaderFrame();
		            champ.setVisible(true);
			       });
	}
}
