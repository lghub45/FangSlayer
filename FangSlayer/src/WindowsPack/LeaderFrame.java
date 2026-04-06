package WindowsPack;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ScorePack.Score;
import Storage.Scorage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;


public class LeaderFrame extends JPanel{

	private JButton menubtn;
	private JLabel boardlbl;
	public DefaultListModel<Score> model;
	public JList<Score> highscores;
	private FangSlayerWindow window;
	private Image Background;

	/**
	 * Create the application.
	 */
	public LeaderFrame(FangSlayerWindow window) {
		this.window=window;
		 try {
			 //sets the background
		        Background = ImageIO.read(new File("Images/FangLeadMenu1.png"));
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
		
		//the list that will display the top 10 highscores 
		model = new DefaultListModel<>();
	    highscores = new JList<>(model);
	    
	    boardUpdate(); //get the score from the database and put in list
	    
	    
	    //board label (will show the top 10 high scores or an error message)
	    boardlbl = new JLabel("Error Global leaderboard empty");
	    boardlbl.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		boardlbl.setBounds((window.getWidth()/2)+550,(window.getHeight())+340/2,430,440);//used to be: (115,65,183,143); 
		add(boardlbl);
			JScrollPane goodboard = new JScrollPane(highscores);
			//should be placed so the leaderboard appears at the center of the window
			goodboard.setBounds((window.getWidth()/2)+550,(window.getHeight())+340/2,430,440);
			add(goodboard);
			highscores.setFont(new Font("Viner Hand ITC", Font.PLAIN,25));
	
			//checks if there are no scores (updates visuals based off of this )
			   if (model.isEmpty()) {
			        boardlbl.setText("Error Global leaderboard empty");
			        boardlbl.setVisible(true);
			        highscores.setVisible(false);
			    } else {
			        boardlbl.setVisible(false);
			        highscores.setVisible(true);
			    }
		
	    //the main menu button
		menubtn = new JButton("Main Menu");
		menubtn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		menubtn.setBounds((window.getWidth()/2)+550,(window.getHeight()/2)+640,430,40);//used to be:(164, 232, 108, 21);
		add(menubtn);
		
		//accompanying listener for the main menu button
		menubtn.addActionListener(e -> {
			window.menuSelect("Main"); 
	       });
	}
	
	  public void boardUpdate() {
	        List<Score> scores = Scorage.getTopTen();
	        model.clear();
	        for (Score s : scores) {
	            model.addElement(s);
	        }
	    }
	  
	  public void refresh() {
		  boardUpdate(); //reload/update the data
		    
		    if (model.isEmpty()) {
		        boardlbl.setText("Error Global leaderboard empty");
		        boardlbl.setVisible(true);
		        highscores.setVisible(false);
		    } else {
		        boardlbl.setVisible(false);
		        highscores.setVisible(true);
		    }

		    revalidate();
		    repaint();
			}
}
