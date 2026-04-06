package WindowsPack;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class MainFrame extends JPanel{
	
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
		 try {//sets the background
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
		
		//start button
		startBtn = new JButton("Start a Game");
		startBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));//used to be Pristina
		startBtn.setBounds(630, 355, 221, 50);
		add(startBtn);
		
		//leaderboard button
		leaderboardBtn = new JButton("Leaderboard");
		leaderboardBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		leaderboardBtn.setBounds(630, 455, 221, 50);
		add(leaderboardBtn);
		
		//options button
		cheatBtn = new JButton("Options");
		cheatBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		cheatBtn.setBounds(630, 555, 221, 50);
		add(cheatBtn);
		
		//exit game button
		exitBtn = new JButton("Exit Game");
		exitBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		exitBtn.setBounds(630, 655, 221, 50);//old: 630, 555, 221, 50
		add(exitBtn);
		
		//start button listener
		startBtn.addActionListener(e -> {
			window.menuSelect("Difficulty"); 
	       });
		
		//leaderboard button listener
		leaderboardBtn.addActionListener(e -> {
	        window.menuSelect("LeaderBoard"); 
	       });
		
		//options button listener
		cheatBtn.addActionListener(e -> {
	        window.menuSelect("Cheat"); 
	       });
		
		//exit button listener
		exitBtn.addActionListener(e -> {
	         System.exit(0);
	       });
	}
}
