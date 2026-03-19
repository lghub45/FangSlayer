package WindowsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Characters.Hunter;

//import Storage.UserStorage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

//used to extend JFrame
public class MainFrame extends JPanel{
	
	private JLabel titlelbl;
	private JButton startBtn;
	private JButton leaderboardBtn;
	private JButton exitBtn;
	private JButton cheatBtn;
	private FangSlayerWindow window;
	private Image Background;

	/**
	 * Create the application.
	 */
	public MainFrame(FangSlayerWindow window) {
		this.window=window;
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
		//window.setBounds(100, 100, 450, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//sets the frame to full screen
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		//titlelbl = new JLabel("Fang Slayer");
		//titlelbl.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 25));
		//titlelbl.setBounds(156, 10, 131, 31);
		//add(titlelbl);
		
		startBtn = new JButton("Start a Game");
		startBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));//used to be Pristina
		startBtn.setBounds(630, 355, 221, 50);
		add(startBtn);
		
		leaderboardBtn = new JButton("Leaderboard");
		leaderboardBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		leaderboardBtn.setBounds(630, 455, 221, 50);
		add(leaderboardBtn);
		
		cheatBtn = new JButton("Cheat Codes");
		cheatBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		cheatBtn.setBounds(630, 555, 221, 50);
		add(cheatBtn);
		
		exitBtn = new JButton("Exit Game");
		exitBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		exitBtn.setBounds(630, 655, 221, 50);//old: 630, 555, 221, 50
		add(exitBtn);
		
		//start button
		startBtn.addActionListener(e -> {
			window.menuSelect("Difficulty"); 
			
			//dispose();
             //DifficultyFrame nodiff=new DifficultyFrame();
             //nodiff.setVisible(true);
           //Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     		//minimap.addGameObject(VictorVanBadAss);
	       });
		
		//leaderboard button
		leaderboardBtn.addActionListener(e -> {
	        window.menuSelect("LeaderBoard"); 
			
			//dispose();
	       //LeaderFrame liter = new LeaderFrame();
	       //liter.setVisible(true);
	       });
		
		cheatBtn.addActionListener(e -> {
	        window.menuSelect("Cheat"); 
			
			//dispose();
	       //LeaderFrame liter = new LeaderFrame();
	       //liter.setVisible(true);
	       });
		
		//exit button
		exitBtn.addActionListener(e -> {
	         System.exit(0);
	       });
	}
}
