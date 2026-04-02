package WindowsPack;

import java.awt.Color;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

//used to extend JFrame
public class DifficultyFrame extends JPanel{
	
	private JLabel difflbl;
	private JLabel maplbl;
	//private JButton babyBtn;
	private JRadioButton babyRadio;
	private JRadioButton normalRadio;
	private JRadioButton hardRadio;
	
	private JRadioButton vampRadio;
	private JRadioButton wolfRadio;
	private JRadioButton bossRadio;
	
	private JButton startBtn;
	private JButton exitBtn;
	//private JButton normalBtn;
	//private JButton hardBtn;
	private ButtonGroup difficulties;
	private ButtonGroup maps;
	private String difficulty;
	private FangSlayerWindow window;
	private Image Background;
	private String map;
	/**
	 * Create the application.
	 */
	public DifficultyFrame(FangSlayerWindow window) {
		this.window=window;
		this.map="";
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
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		difficulty="";
		//setBounds(100, 100, 450, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		difflbl = new JLabel("Difficulty");
		difflbl.setBounds(1242, 210, 149, 31);
		difflbl.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		difflbl.setForeground(Color.red);
		add(difflbl);
		
		maplbl = new JLabel("Hunting Grounds");
		maplbl.setBounds(152, 210, 249, 31);
		maplbl.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		maplbl.setForeground(Color.red);
		add(maplbl);
		
		startBtn = new JButton("Happy Hunting");
		startBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));//used to be Pristina
		startBtn.setBounds(630, 155, 221, 50); //used to be 156, 118, 121, 21
		add(startBtn);
		
		exitBtn = new JButton("<html>Exit to Main Menu</html>");
		exitBtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		exitBtn.setBounds(1242, 700, 179, 81);
		add(exitBtn);
		
		//babyBtn = new JButton("Babyyy");
		//babyBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		//babyBtn.setBounds(156, 118, 121, 21);
		//add(babyBtn);
		
		//normalBtn = new JButton("Normal");
		//normalBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		//normalBtn.setBounds(156, 146, 121, 21);
		//add(normalBtn);
		
		//hardBtn = new JButton("Seasoned Hunter");
		//hardBtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		//hardBtn.setBounds(156, 177, 121, 21);
		//add(hardBtn);
		
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
		
		vampRadio = new JRadioButton("Castle of the Damned");
		vampRadio.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		vampRadio.setBounds(162, 245, 229, 31);//og: 242, 245, 149, 31
		add(vampRadio);
		
		//experimental (replaces name with small image of map)
		ImageIcon bloodicon = new ImageIcon("Images/bloodmoonforest.png");
		Image bloodimg= bloodicon.getImage();
		Image scaledimg=bloodimg.getScaledInstance(229, 31, java.awt.Image.SCALE_SMOOTH);
		ImageIcon scaledblood = new ImageIcon(scaledimg);
		
		wolfRadio = new JRadioButton("Blood Moon Forest");
		//wolfRadio = new JRadioButton(scaledblood);
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
		
		//easy baby mode button
		//babyBtn.addActionListener(e -> {
			//difficulty="baby";
			//dispose();
             //GameFrame minimap=new GameFrame();
            // minimap.setVisible(true);
			//window.difficultySelect(difficulty); 
           //Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     		//minimap.addGameObject(VictorVanBadAss);
     		//minimap.setDifficulty(difficulty);
	       //});
		
		babyRadio.addActionListener(e -> {
			difficulty="baby";
			//window.difficultySelect(difficulty); 
	       });
		
		normalRadio.addActionListener(e -> {
			difficulty="normal";
			//window.difficultySelect(difficulty); 
	       });
		
		hardRadio.addActionListener(e -> {
			difficulty="seasoned hunter";
			//window.difficultySelect(difficulty); 
	       });
		
		vampRadio.addActionListener(e -> {
			map="vamps";
			//window.difficultySelect(difficulty); 
	       });
		
		wolfRadio.addActionListener(e -> {
			map="wolves";
			//window.difficultySelect(difficulty); 
	       });
		bossRadio.addActionListener(e -> {
			map="finalboss";
			//window.difficultySelect(difficulty); 
	       });
		
		exitBtn.addActionListener(e -> {
			window.menuSelect("Main"); 
	       });
		
		//normalBtn.addActionListener(e -> {
			//difficulty="normal";
			//dispose();
             //GameFrame minimap=new GameFrame();
             //minimap.setVisible(true);
			//window.difficultySelect(difficulty);
			//Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     		//minimap.addGameObject(VictorVanBadAss);
     	//	minimap.setDifficulty(difficulty);
	      // });
		
	//	hardBtn.addActionListener(e -> {
		//	difficulty="seasoned hunter";
			//dispose();
             //GameFrame minimap=new GameFrame();
             //minimap.setVisible(true);
		//	window.difficultySelect(difficulty);
			//Hunter VictorVanBadAss = new Hunter(400,400);
     		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
     	//	minimap.addGameObject(VictorVanBadAss);
     		//minimap.setDifficulty(difficulty);
	    //   });
		
		startBtn.addActionListener(e -> {
			//difficulty="seasoned hunter";
			window.difficultySelect(difficulty,map);
			System.out.println("map:"+map);
	       });
	}
}
