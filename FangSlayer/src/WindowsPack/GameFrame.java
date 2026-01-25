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

import Characters.Arrow;
import Characters.Direction;
import Characters.GameObject;
import Characters.Hunter;
import Characters.Vampire;

public class GameFrame extends JComponent implements ActionListener, KeyListener {
	// DEFAULT SERIAL NUMBER
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Timer gameLoopTimer;
	public List<GameObject> gameObjectList;
	//these will help find the hunter in the game object list
	private Hunter h;
	private int index = 0; 
	private int aimdir =0;

	public GameFrame() {
		// make a list of characters
		gameObjectList = new LinkedList<GameObject>();

		//  make a window
		frame = new JFrame("Minimap");
		frame.setSize(1000, 1000);
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
		//detects early on where the hunter is in the list
		if (sprite instanceof Hunter) {
			h = (Hunter)sprite;
			index = gameObjectList.indexOf(sprite);
		}
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
		if (gameObjectList.size()>1) {
			for (int i=0; i<gameObjectList.size();i++) {
				if (gameObjectList.get(i) instanceof Vampire) {
					Vampire vamps = (Vampire)gameObjectList.get(i); //casting just in case
					vamps.huntHim(trackex(),trackey());
						}
			}
		}
		//will keep checking if the hunter has been caught
		aBitterEnd();
		
		if (h!=null) {
		aimdir = ((Hunter)gameObjectList.get(index)).aim();}
		
		for (GameObject gameObject : gameObjectList) {
			
			//if (gameObject != gameObjectList.get(highlighted)) {
				//gameObject.autoMove();
			//}
			gameObject.move(this);
			gameObject.setImage();
			//System.out.println("                                                                    Hunter position:"+ trackex()+","+trackey());
		}
		repaint();
	}


	
	
	// ****************************************************
	// Canvas must implement the inherited abstract methods
	// for KeyListener
	
	  public void keyTyped(KeyEvent e) {
	  }

	  public void keyPressed(KeyEvent e) {
		  //looks for the hunter
		  //for (GameObject obj: gameObjectList) {
			//  if (obj instanceof Hunter){
				//  h = (Hunter)obj;
				  //index= gameObjectList.indexOf(obj);
			  //}
		  //}
		  gameObjectList.get(index).keyPressed(e);
		  GameObject s = gameObjectList.get(index);
		    s.setVelocity(20); 
		    //if the fire key is pressed create a new arrow at the hunter's position that flies in the hunter's direction (away from hunter)
		    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
		    	Arrow arr = new Arrow(s.getX(),s.getY());
		    	//arr.setDirection(aimdir);
		    	arr.setDirection(((Hunter) s).aim());
		    	this.addGameObject(arr);
		    	System.out.println("                                                                                                     PEW!!");
		    	//if the arrow hits the edge of the window remove it from game object list
		    	//if(arr.getX()==this.getX()&&arr.getY()==this.getY()) {
		    		//gameObjectList.remove(arr);
		    	//}
		    }
	  }

	  public void keyReleased(KeyEvent e) {
	   GameObject s = gameObjectList.get(index);
	    s.setVelocity(0); 
	  }
	  public int trackex() {
		  return gameObjectList.get(index).getX();
	  }
	  public int trackey() {
		  return gameObjectList.get(index).getY();
	  }
	  //this makes Van Slechkont mortal (the vampires kill him if they catch him)
	  public void aBitterEnd() {
		  //makes sure we don't try to use the hunter before he exists
		  for (int i=0; i<gameObjectList.size();i++) {
			  if (gameObjectList.get(i) instanceof Vampire) {
				  if (Math.abs(gameObjectList.get(i).getX() - trackex()) <= 5 && Math.abs(gameObjectList.get(i).getY() - trackey()) <= 5) {
					  System.out.println("The hunter has been caught by the vampire! Game Over!");
					   System.exit(0);
				  }
			  }
		  }
	  }
}
