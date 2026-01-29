package WindowsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Characters.Hunter;

//import Storage.UserStorage;

import java.awt.Font;
import javax.swing.JButton;

public class DifficultyFrame extends JFrame{
	
	private JLabel titlelbl;
	private JButton babyBtn;
	private JButton normalBtn;
	private JButton hardBtn;
	private String difficulty;
	

	/**
	 * Create the application.
	 */
	public DifficultyFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		difficulty="";
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		titlelbl = new JLabel("Choose your fate");
		titlelbl.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 25));
		titlelbl.setBounds(156, 10, 131, 31);
		getContentPane().add(titlelbl);
		
		babyBtn = new JButton("Babyyy");
		babyBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		babyBtn.setBounds(156, 118, 121, 21);
		getContentPane().add(babyBtn);
		
		normalBtn = new JButton("Normal");
		normalBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		normalBtn.setBounds(156, 146, 121, 21);
		getContentPane().add(normalBtn);
		
		hardBtn = new JButton("Seasoned Hunter");
		hardBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		hardBtn.setBounds(156, 177, 121, 21);
		getContentPane().add(hardBtn);
		
		//easy baby mode button
		babyBtn.addActionListener(e -> {
			difficulty="baby";
			dispose();
             GameFrame minimap=new GameFrame();
             minimap.setVisible(true);
           Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     		minimap.addGameObject(VictorVanBadAss);
     		minimap.setDifficulty(difficulty);
	       });
		
		normalBtn.addActionListener(e -> {
			difficulty="normal";
			dispose();
             GameFrame minimap=new GameFrame();
             minimap.setVisible(true);
           Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     		minimap.addGameObject(VictorVanBadAss);
     		minimap.setDifficulty(difficulty);
	       });
		
		hardBtn.addActionListener(e -> {
			difficulty="seasoned hunter";
			dispose();
             GameFrame minimap=new GameFrame();
             minimap.setVisible(true);
           Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     		minimap.addGameObject(VictorVanBadAss);
     		minimap.setDifficulty(difficulty);
	       });
	}
}
