package WindowsPack;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

//used to extend JFrame
public class DifficultyFrame extends JPanel{
	
	private JLabel difflbl;
	private JLabel maplbl;
	private JRadioButton babyRadio;
	private JRadioButton normalRadio;
	private JRadioButton hardRadio;
	
	private JRadioButton vampRadio;
	private JRadioButton wolfRadio;
	private JRadioButton bossRadio;
	
	private JButton startBtn;
	private JButton exitBtn;
	private ButtonGroup difficulties;
	private ButtonGroup maps;
	private String difficulty;
	private FangSlayerWindow window;
	private Image Background;
	private String map;

	public DifficultyFrame(FangSlayerWindow window) {
		this.window=window;
		this.map="";
		//set background
		 try {
		        Background = ImageIO.read(new File("Images/FangDiffMenu1.png"));
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
	
	
	private void initialize() {
		difficulty="";
		setLayout(null);
		//difficulty label
		difflbl = new JLabel("Difficulty");
		difflbl.setBounds(1242, 210, 149, 31);
		difflbl.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		difflbl.setForeground(Color.red);
		add(difflbl);
		//map label
		maplbl = new JLabel("Hunting Grounds");
		maplbl.setBounds(152, 210, 249, 31);
		maplbl.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		maplbl.setForeground(Color.red);
		add(maplbl);
		//button starts the game
		startBtn = new JButton("Happy Hunting");
		startBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));//used to be Pristina
		startBtn.setBounds(630, 155, 221, 50); //used to be 156, 118, 121, 21
		add(startBtn);
		//button exits to main menu
		exitBtn = new JButton("<html>Exit to Main Menu</html>");
		exitBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		exitBtn.setBounds(1242, 700, 179, 81);
		add(exitBtn);
		
		//DIFFICULTY radio buttons
		babyRadio = new JRadioButton("Baby");
		babyRadio.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		babyRadio.setBounds(1242, 245, 225, 31);
		add(babyRadio);
		
		normalRadio = new JRadioButton("Normal");
		normalRadio.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		normalRadio.setBounds(1242, 290, 225, 31);
		add(normalRadio);
		
		hardRadio = new JRadioButton("Seasoned Hunter");
		hardRadio.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		hardRadio.setBounds(1242, 335, 225, 31);//used to be 1242, 335, 149, 31
		add(hardRadio);
		
		//MAP radio buttons
		vampRadio = new JRadioButton("Castle of the Damned");
		vampRadio.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		vampRadio.setBounds(162, 245, 229, 31);//og: 242, 245, 149, 31
		add(vampRadio);
		
		wolfRadio = new JRadioButton("Blood Moon Forest");
		wolfRadio.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		wolfRadio.setBounds(162, 290, 229, 31);//og: 242, 290, 149, 31  form fitting:202, 290, 189, 31
		add(wolfRadio);
		
		bossRadio = new JRadioButton("Boss Blitz");
		bossRadio.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		bossRadio.setBounds(162, 335, 229, 31);//og: 242, 290, 149, 31  form fitting:202, 290, 189, 31
		add(bossRadio);
		
		
		//makes sure you can only pick ONE difficulty
		difficulties = new ButtonGroup();
		difficulties.add(babyRadio);
		difficulties.add(normalRadio);
		difficulties.add(hardRadio);
		
		//and ONE map
		maps=new ButtonGroup();
		maps.add(vampRadio);
		maps.add(wolfRadio);
		maps.add(bossRadio);
		
		//makes sure difficulty is normal by default
		normalRadio.setSelected(true);
		difficulty="normal";
		
		//makes sure castle of the damned by default
		vampRadio.setSelected(true);
		map="vamps";
	
		//listeners for the radio buttons and start button	
		babyRadio.addActionListener(e -> {
			difficulty="baby";
	       });
		
		normalRadio.addActionListener(e -> {
			difficulty="normal";
	       });
		
		hardRadio.addActionListener(e -> {
			difficulty="seasoned hunter";
	       });
		
		vampRadio.addActionListener(e -> {
			map="vamps";
	       });
		
		wolfRadio.addActionListener(e -> {
			map="wolves";
	       });
		bossRadio.addActionListener(e -> {
			map="finalboss";
	       });
		
		exitBtn.addActionListener(e -> {
			window.menuSelect("Main"); 
	       });
		
		startBtn.addActionListener(e -> {
			window.difficultySelect(difficulty,map);
			System.out.println("map:"+map);
	       });
	}
}
