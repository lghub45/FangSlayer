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
import javax.swing.JLabel;
import java.awt.Font;

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
	private int round=0;
	private int score=0;
	private boolean onslaught; 
	private int clock=0; //this will be used to help determine how the vampires spawn
	private int clockbackup=clock;
	private int toSpawn=0; //this will be used in conjuction to clock

	public GameFrame() {
		// make a list of characters
		gameObjectList = new LinkedList<GameObject>();
		//  make a window
		frame = new JFrame("Minimap");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		
		setFocusable(true);
		requestFocusInWindow();

		// make a timer for the game loop
		gameLoopTimer = new Timer(25, this);
		gameLoopTimer.start();
		
		setFocusTraversalKeysEnabled(false);
	    addKeyListener(this);
	    
	   // JLabel scorelbl = new JLabel("Score:");
	    //scorelbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    //scorelbl.setBounds(10, 10, 266, 45);
	    //add(scorelbl);
	    
	    //roundlbl = new JLabel("Round:");
	    //roundlbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    //roundlbl.setBounds(10, 65, 266, 45);
	    //add(roundlbl);

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
		 g.setFont(new Font("Tahoma", Font.PLAIN, 25));
		    g.drawString("Score: "+score, 10, 35);        // placeholder
		    g.drawString("Round: " + round, 10, 70);
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
		//clock++;
		//will keep checking if the hunter has been caught
		aBitterEnd();
		//will keep checking if any vampires have been hit by arrows
		fretNot();
		//if all vampires are dead more shall arise
		ariiiiise();
		 //arrow is detroyed at the edge of the window
		arrowCheck();
		
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
					  System.out.println("The hunter has been caught by the vampire! Game Over! Your score was: "+score);
					   System.exit(0);
				  }
			  }
		  }
	  }
	  //this makes the vampires killable (arrows kill vampires)
	  public void fretNot() {
		  if (gameObjectList.size()<2) {
			  return;
		  }
		  int deadVamp=100;
		  int usedArrow=100;
		  for (int v=0; v<gameObjectList.size();v++) {
			  if (gameObjectList.get(v) instanceof Vampire) {
				  for (int a=0; a<gameObjectList.size();a++) {
					  if (gameObjectList.get(a) instanceof Arrow) {
						  //NOTE TO SELF: 25 WILL BE THE HARD MODE COLLISION SIZE, AND _____ WILL BE THE NORMAL MODE
						  if (Math.abs(gameObjectList.get(v).getX() - gameObjectList.get(a).getX()) <= 50 && Math.abs(gameObjectList.get(v).getY() - gameObjectList.get(a).getY()) <= 50) {
							  System.out.println("A vampire has been slain by an arrow!");
							  deadVamp=v;
							  usedArrow=a;
							  score+=15;
							 // gameObjectList.remove(v);
							 // gameObjectList.remove(a);
						  }
						  }
				  }
			  }
		  }
		  if (deadVamp==100||usedArrow==100) {
			  return;
		  }
		  else if(deadVamp>usedArrow) {
		  gameObjectList.remove(deadVamp);
		  gameObjectList.remove(usedArrow);
		  }
		  else {gameObjectList.remove(usedArrow);
		  gameObjectList.remove(deadVamp);}
		  }
	  
	  //spawns more vampires overt time
	  public void ariiiiise() {
		  //double checks all vamps are dead
		  int vampcount=0;
		  
		  for (int v=0; v<gameObjectList.size();v++) {
			  if (gameObjectList.get(v) instanceof Vampire) {
				  vampcount=vampcount+1;
			  }
		  }
		  boolean hunteralive=false;
		  //double check hunter is alive (otherwise game just crashes =[)
		  for (GameObject obj: gameObjectList) {
			  if (obj instanceof Hunter) {
				  index = gameObjectList.indexOf(obj);
				  hunteralive=true;break;
			  }
		  }
		  
		  //used to say && canspawn ==false {onslaught=true; return;}
		  if (vampcount>0 && onslaught==true) {return;}
		  
		  if (vampcount==0 && hunteralive) {toSpawn=round+2;onslaught=false;round=round+1;}
		  
		  //if (vampcount==0 && hunteralive) {
			 // onslaught=false;
			 // round=round+1;
			  
			  //in order to make the horde we need to make sure there's not an active wave and the clock has progressed forwards 
			  if (!onslaught && toSpawn>clock) {
				  clock++;
			  for (int i=0;i<round+2;i++) {
				  //generates a random corner of the window for the vampire to spawn at
				  int corner = (int)(Math.random()*4);
				  //spices up spawn location
				  int LX = (int)(Math.random()*200);
				  int RX = this.getWidth()- (int)(Math.random()*300)-200;
				  int UY = (int)(Math.random()*200);
				  int DY = this.getHeight()- (int)(Math.random()*300);
				  
				  
				  switch(corner) {
				  case 0: 
				  Vampire alucard = new Vampire(LX,UY);
				  this.addGameObject(alucard); break;
				  case 1: Vampire adamsandler = new Vampire(RX,UY);
				  this.addGameObject(adamsandler);break;
				  case 2: Vampire marceline = new Vampire(LX,DY);
				  this.addGameObject(marceline);break;
				  case 3: Vampire mrburns = new Vampire(RX,DY);
				  this.addGameObject(mrburns);break;
				  }
				  
			  }
		  }
			  if (clock==toSpawn) {toSpawn=0;clock=0;onslaught=true;}
			  System.out.println("                                                                                                     ROUND:"+round);
		 // }
		  if (gameObjectList.size()<2) {
			  return;
		  }
	  }
	  //arrow is detroyed at the edge of the window
	  public void arrowCheck() {
		  for (int i=0; i<gameObjectList.size();i++) {
			  if (gameObjectList.get(i) instanceof Arrow) {
				  Arrow arr = (Arrow)gameObjectList.get(i);
				  if (arr.getX() <= 0 || arr.getY() <= 0 ||
		    	    Math.abs(arr.getX()+arr.getCurrentImage().getIconWidth()) >= this.getWidth() || Math.abs(arr.getY()+arr.getCurrentImage().getIconHeight()) >= this.getHeight()) {
		    	    gameObjectList.remove(arr);
		  }
			  }
		  }
	  }
}
//first dev score(easy mode): 1305
//first dev score (normal mode): 915
//
