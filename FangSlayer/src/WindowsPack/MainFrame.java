package WindowsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Characters.Hunter;

//import Storage.UserStorage;

import java.awt.Font;
import javax.swing.JButton;

public class MainFrame extends JFrame{
	
	private JLabel titlelbl;
	private JButton startBtn;
	private JButton leaderboardBtn;
	private JButton exitBtn;
	

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		titlelbl = new JLabel("Fang Slayer");
		titlelbl.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 25));
		titlelbl.setBounds(156, 10, 131, 31);
		getContentPane().add(titlelbl);
		
		startBtn = new JButton("Start a Game");
		startBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		startBtn.setBounds(156, 118, 121, 21);
		getContentPane().add(startBtn);
		
		leaderboardBtn = new JButton("Leaderboard");
		leaderboardBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		leaderboardBtn.setBounds(156, 146, 121, 21);
		getContentPane().add(leaderboardBtn);
		
		exitBtn = new JButton("Exit Game");
		exitBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		exitBtn.setBounds(156, 177, 121, 21);
		getContentPane().add(exitBtn);
		
		//start button
		startBtn.addActionListener(e -> {
			 dispose();
             DifficultyFrame nodiff=new DifficultyFrame();
             nodiff.setVisible(true);
           //Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     		//minimap.addGameObject(VictorVanBadAss);
	       });
		
		//leaderboard button
		leaderboardBtn.addActionListener(e -> {
	         dispose();
	       LeaderFrame liter = new LeaderFrame();
	       liter.setVisible(true);
	       });
		
		//exit button
		exitBtn.addActionListener(e -> {
	         System.exit(0);
	       });
	}
}
