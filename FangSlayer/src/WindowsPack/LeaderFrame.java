package WindowsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import ScorePack.Score;
import Storage.Scorage;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class LeaderFrame extends JFrame{

	//private JFrame frame;
	private JButton menubtn;
	private JLabel boardlbl;
	public DefaultListModel<Score> model;
	public JList<Score> highscores;

	/**
	 * Create the application.
	 */
	public LeaderFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    
	    //actually makes the window
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//title label
		JLabel leaderlbl = new JLabel("Global Leaderboard");
		leaderlbl.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 25));
		leaderlbl.setBounds(115, 10, 206, 45);
		getContentPane().add(leaderlbl);
		
		//the list that will display the top 10 highscores 
		model = new DefaultListModel<>();
	    highscores = new JList<>(model);
	    
	    boardUpdate(); //get the score from the database and put in list
	    
	    
	    //board label (will show the top 10 high scores or an error message)
		if (highscores.getModel().getSize()<1) { //checks if no scores
	    boardlbl = new JLabel("Error Global leaderboard empty");
	    boardlbl.setFont(new Font("Pristina", Font.PLAIN, 16));
		boardlbl.setBounds(115,65,183,143); 
		getContentPane().add(boardlbl);
		}
		else {
			JScrollPane goodboard = new JScrollPane(highscores);
			goodboard.setBounds(105,65,230,140);//used to be 115, 65, 200, 140
			getContentPane().add(goodboard);
			highscores.setFont(new Font("Pristina", Font.PLAIN,16));
			
			
			//old version of the code
			//String text = "";
			//if there are 2 or more scores, display the whole list 
			//for (int i=0; i<highscores.getModel().getSize();i++) {
			//text += highscores.getModel().getElementAt(i).toString();} //used to say: "new JLabel" in front of highscores...
			//boardlbl = new JLabel(text);
		}
		
		
	    //the main menu button
		menubtn = new JButton("Main Menu");
		menubtn.setFont(new Font("Pristina", Font.PLAIN, 16));
		menubtn.setBounds(164, 232, 108, 21);
		getContentPane().add(menubtn);
		
		//accompanying listener for the button
		menubtn.addActionListener(e -> {
			 dispose();
            MainFrame mm=new MainFrame();
            mm.setVisible(true);
	       });
		
	}
	
	  public void boardUpdate() {
	        List<Score> scores = Scorage.getTopTen();
	        model.clear();
	        for (Score s : scores) {
	            model.addElement(s);
	        }
	    }
}
