package WindowsPack;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import Characters.GameObject;
import Characters.Hunter;
import Characters.Vampire;

public class GameFrame extends JComponent implements ActionListener, KeyListener {
	// DEFAULT SERIAL NUMBER
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Timer gameLoopTimer;
	public List<GameObject> gameObjectList;
	private int hunter = 0; 

	public GameFrame() {
		// make a list of characters
		gameObjectList = new LinkedList<GameObject>();

		//  make a window
		frame = new JFrame("Minimap");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
		setFocusable(true);
		requestFocusInWindow();

		// make a timer for the game loop
		gameLoopTimer = new Timer(25, this);
		gameLoopTimer.start();
		
		setFocusTraversalKeysEnabled(false);
	    addKeyListener(this);

		// show the window
		frame.setVisible(true);

	}
	
	/**
	 * Adds GameObjects to the List
	 */
	public synchronized void addGameObject(GameObject sprite) {
		gameObjectList.add(sprite);
	}

	/**
	 * Draws the GameObject graphic onto the Canvas
	 */
	public synchronized void paint(Graphics g) {
		for (GameObject s : gameObjectList) {
			s.draw(this, g);
		}
	}
	
	
	// ****************************************************
	// Canvas must implement the inherited abstract method
	// ActionListener.actionPerformed(ActionEvent)
	public synchronized void actionPerformed(ActionEvent e) {
		for (GameObject gameObject : gameObjectList) {
			
			//if (gameObject != gameObjectList.get(highlighted)) {
				//gameObject.autoMove();
			//}
			
			gameObject.move(this);
			gameObject.setImage();
			System.out.println("Hunter position:"+ trackex()+","+trackey());
			if (gameObjectList.size()>1) {
				for (int i=0; i<gameObjectList.size();i++) {
					if (gameObjectList.get(i) instanceof Vampire) {
						Vampire vamps = (Vampire)gameObjectList.get(i); //casting just in case
						vamps.fetchmeTheirSouls(trackex(),trackey());
					}
				}
			}
		}
		repaint();
	}


	
	
	// ****************************************************
	// Canvas must implement the inherited abstract methods
	// for KeyListener
	
	  public void keyTyped(KeyEvent e) {
	  }

	  public void keyPressed(KeyEvent e) {
		  gameObjectList.get(hunter).keyPressed(e);
		  GameObject s = gameObjectList.get(hunter);
		    s.setVelocity(20); 
	  }

	  public void keyReleased(KeyEvent e) {
		  //if (e.getKeyCode() == KeyEvent.VK_TAB) {
	    	 //  	gameObjectList.get(highlighted).setVelocity(gameObjectList.get(highlighted).getVelocity()+5); 
	    	//previous line undoes velocity decrease later in this method
	    	 //removeKeyListener(gameObjectList.get(highlighted));
	    //  highlighted = highlighted + 1;
	          //     if (highlighted == gameObjectList.size()) {
	    	  //      highlighted = 0;
	        //   }
	      //    gameObjectList.get(highlighted).setVelocity(gameObjectList.get(highlighted).getVelocity()-5); 
	      //previous line  decreases velocity of newly highlighted object (gives the non-highlighted object more speed in comparison
	      //and therefore more movement)
	      
	      //test to see the button press works
	      //System.out.println("                                                                                                     Tab button pressed");
	      //  addKeyListener(gameObjectList.get(highlighted));
	  //  }
	   GameObject s = gameObjectList.get(hunter);
	    s.setVelocity(0); 
	  }
	  public int trackex() {
		  return gameObjectList.get(hunter).getX();
	  }
	  public int trackey() {
		  return gameObjectList.get(hunter).getX();
	  }

}
