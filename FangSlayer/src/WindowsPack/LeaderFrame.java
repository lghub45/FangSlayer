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

//used to extend JFrame
public class LeaderFrame extends JPanel{

	//private JFrame frame;
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
	    
	    //actually makes the window
		//setBounds(100, 100, 450, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//title label
		//JLabel leaderlbl = new JLabel("Global Leaderboard");
		//leaderlbl.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 25));
		//leaderlbl.setBounds(115, 10, 206, 45);
		//add(leaderlbl);
		
		//the list that will display the top 10 highscores 
		model = new DefaultListModel<>();
	    highscores = new JList<>(model);
	    
	    boardUpdate(); //get the score from the database and put in list
	    
	    
	    //board label (will show the top 10 high scores or an error message)
		//if (highscores.getModel().getSize()<1) { //checks if no scores
	    boardlbl = new JLabel("Error Global leaderboard empty");
	    boardlbl.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
	    //boardlbl.setForeground(Color.red);
		boardlbl.setBounds((window.getWidth()/2)+550,(window.getHeight())+340/2,430,440);//used to be: (115,65,183,143); 
		add(boardlbl);
		//}
		//else {
			JScrollPane goodboard = new JScrollPane(highscores);
			//should be placed so the leaderboard appears at the center of the window
			//width used to be 230 and height used to be 140
			goodboard.setBounds((window.getWidth()/2)+550,(window.getHeight())+340/2,430,440);
			add(goodboard);
			highscores.setFont(new Font("Viner Hand ITC", Font.PLAIN,25));
			
			
			//old version of the code
			//String text = "";
			//if there are 2 or more scores, display the whole list 
			//for (int i=0; i<highscores.getModel().getSize();i++) {
			//text += highscores.getModel().getElementAt(i).toString();} //used to say: "new JLabel" in front of highscores...
			//boardlbl = new JLabel(text);
		//}
		
			//checks if there are no scores (updates visuals based off of this )
			   if (model.isEmpty()) {
			    	//boardlbl=new JLabel("Error Global leaderboard empty");
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
		
		//accompanying listener for the button
		menubtn.addActionListener(e -> {
			// dispose();
            //MainFrame mm=new MainFrame();
            //mm.setVisible(true);
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
		  boardUpdate(); //reload the data
		    
		    if (model.isEmpty()) {
		    	//boardlbl=new JLabel("Error Global leaderboard empty");
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
