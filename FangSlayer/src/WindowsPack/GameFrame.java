package WindowsPack;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import Characters.Arrow;
import Characters.Direction;
import Characters.GameObject;
import Characters.Hunter;
import Characters.Vampire;
import Storage.Scorage;
import ScorePack.Score;
import javax.swing.JLabel;
import java.awt.Font;
import javax.imageio.ImageIO;

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
	private int point=0;
	private boolean onslaught; 
	
	private int toSpawn=0; //this will be used to determine when/how vampires spawn
	private int haveSpawned=0;
	private int w8amin=0;
	private String difficulty="";
	
	private Image background; //this is the castle grounds NOTE TO SELF DRAW A CASTLE INSTEAD OF CURRENT IMAGE

	
	//items
	private boolean crossbow=true; //this means the crossbow is equipped by default
	private boolean silverDagger=false; //a special item for later
	private boolean woodenStake=false; // toggle to make arrows into wooden stakes (pierces thru multiple vamps) 
	private int daggercount =0; // can kill up to 5 vamps before dagger is gone 
	private int woodenStakecount=0; //you have a max of 3 wooden stakes 
	
	//idea: double barrel shotgun???? (fire 3 arrows[do i rly wanna draw bullets??] in a spread pattern)
	
	public GameFrame() {
		//setting the background image
		//background=new ImageIcon("Images/tempbackgrnd.png");
		  try {
		        background = ImageIO.read(new File("Images/castleofthedamned.png"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		
		// make a list of characters
		gameObjectList = new LinkedList<GameObject>();
		//  make a window
		frame = new JFrame("Minimap");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		
		
		//sets the frame to full screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
		//draw the background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		
		//draw the character
		for (GameObject s : gameObjectList) {
			s.draw(this, g);
		}
		//draw the score and round numbers
		 g.setFont(new Font("Tahoma", Font.PLAIN, 25));
		    g.drawString("Score: "+point, 10, 35);        // placeholder
		    g.drawString("Round: " + round, 10, 70);
		    g.drawString("Daggers: " + daggercount, 10, 105);
		    if (crossbow && !woodenStake) {
		    	g.drawString("Crossbow", 1400, 750);
		    }
		    if (woodenStake) {
		    	if (woodenStakecount>0) {
		    	g.drawString("Wooden Stakes: "+(woodenStakecount-1), 1300, 750); }
		    	//phantom stake glitch where last one isn't used so we just display one less than the actual count 
		    	else {g.drawString("Wooden Stakes: 0", 1300, 750);}
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
		
		//note: used to be AFTER arrowCheck()p
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
		
		//clock++;
		//will keep checking if any vampires have been hit by arrows/daggers  (run BEFORE abitterend to confirm that hunter isn't killed 
			//while using a dagger)
		fretNot();
			
		//will keep checking if the hunter has been caught
		aBitterEnd();
		//if all vampires are dead more shall arise
		ariiiiise();
		 //arrow is detroyed at the edge of the window
		arrowCheck();
		
	//	if (h!=null) {
		//aimdir = ((Hunter)gameObjectList.get(index)).aim();}
		
	//	for (GameObject gameObject : gameObjectList) {
			
			//if (gameObject != gameObjectList.get(highlighted)) {
				//gameObject.autoMove();
			//}
		//	gameObject.move(this);
			//gameObject.setImage();
			//System.out.println("                                                                    Hunter position:"+ trackex()+","+trackey());
		//}
		repaint();
	}


	
	
	// ****************************************************
	// Canvas must implement the inherited abstract methods
	// for KeyListener
	
	  public void keyTyped(KeyEvent e) {
	  }

//functionality for arrows and special items
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
		    //this specific version is the original mechanics which makes the "lazer beam" glitch
		    //this glitch is fine, but only on baby mode =]
		    //checks to see if crossbow is equipped before trying to shoot
		    if (crossbow) {
		    if (difficulty.equals("baby")) {
		    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
		    	//one wooden stake is used per shot 
		    	if (woodenStake && woodenStakecount>0) {
		 	     woodenStakecount--;
		 	      }
		    	if (woodenStakecount==0) {
		    		woodenStake=false;//auto switch to regular arrows when out of stakes
		    	}
		    	
		    	Arrow arr = new Arrow(s.getX(),s.getY());
		    	//arr.setDirection(aimdir);
		    	arr.setDirection(((Hunter) s).aim());
		    	this.addGameObject(arr);
		    	System.out.println("                                                                                                     PEW!!");
//		    	System.out.println("                                                                                                     "+this.getWidth()+"width "+this.getHeight()+"height");
//note to self: 1536 width 793 height is full screen
		    	//if the arrow hits the edge of the window remove it from game object list
		    	if(arr.getX()==this.getX()&&arr.getY()==this.getY()) {
		    		gameObjectList.remove(arr);
		    	}
		    }}}
		    
		    //crossbow toggle 
		   if (e.getKeyCode()==KeyEvent.VK_1) {
			   System.out.println("Crossbow equipped");
			   crossbow=true;
			   silverDagger=false;
			   woodenStake=false; //just in case the player had a different item equipped before
		   }
		   if (e.getKeyCode()==KeyEvent.VK_2) {
			   System.out.println("Silver Dagger equipped");
			   crossbow=false;
			   silverDagger=true;
		   }
		   if (e.getKeyCode()==KeyEvent.VK_3) {
			   System.out.println("Wooden Stakes equipped");
			   crossbow=true;//wooden stake is a variation of arrows
			   silverDagger=false;
			   woodenStake=true;
		   }
	  }

	  public void keyReleased(KeyEvent e) {
	   GameObject s = gameObjectList.get(index);
	    s.setVelocity(0); 
	    
	    //only shoots if in crossbow
	    if(crossbow) {
	    	
	    //testing to see if releasing arrow button prevents "lazer beam" glitch
	    if (difficulty.equals("normal")||difficulty.equals("seasoned hunter")) {
	    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
	    	Arrow arr = new Arrow(s.getX(),s.getY());
	    	if (woodenStake && woodenStakecount>0) { //makes sure we are using wooden stake images instead of regular arrows
	    		arr.setWood(true);
	    	}
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
	    }
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
				  //has to be a high number like 25 instead of 5 otherwise the vampires just orbit the player
				  //used to be 25 but it caused immortality glitch where spamming arrows could block vampires from getting close enough
				  if (Math.abs(gameObjectList.get(i).getX() - trackex()) <= 27 && Math.abs(gameObjectList.get(i).getY() - trackey()) <= 27) {
					  System.out.println("The hunter has been caught by the vampire! Game Over! Your score was: "+point);
					  
					  gameLoopTimer.stop();//ends the loop and prevents from infinite main menus
					  frame.dispose(); //exits the gameframe
					  
					  //checks the top 10 highscores in the database and adds the player score if it's high enough
					  List <Score> scorelist = Scorage.getTopTen(); //returns the database (should be only 10 scores)
					  
					  //takes the user to the score input frame if the list is smaller than 10 
					  //(shouldn't happen tho because this is a beta thing. the users should already have the top 10 scores)
					  if (scorelist.size()<10) {
						  ScoreFrame skills4bills = new ScoreFrame(point); //makes and opens a score frame
						   skills4bills.setVisible(true);
						   return;
					  }
					  //takes the user to the score input frame if their score is higher than any scores in the database
					  for (int j=0; j<scorelist.size();j++) {
					  if (point>= scorelist.get(j).getPoints()) {
					  //should take the user to a menu to input their username and highscore to the database
						  ScoreFrame skills4bills = new ScoreFrame(point); //makes and opens a score frame
						   skills4bills.setVisible(true);
						   return;
					  }
					  }
					  
					  
					  MainFrame menu = new MainFrame(); //makes and opens the main menu
					   menu.setVisible(true);
					   return;
				  }
			  }
		  }
	  }
	  //this makes the vampires killable (arrows kill vampires)
	  public void fretNot() {
		  if (gameObjectList.size()<2) {
			  return;
		  }
		  //silver dagger works by having the hunter run into a vampire and slice them (bascially the vampire code in reverse)
		    if (silverDagger && daggercount>0) {
		    	for (int v=0; v<gameObjectList.size();v++) {
					  if (gameObjectList.get(v) instanceof Vampire) {
						  //has to be a high number like 25 instead of 5 otherwise the vampires just orbit the player
						  //used to be 25 but it caused immortality glitch where spamming arrows could block vampires from getting close enough
						  if (Math.abs(gameObjectList.get(v).getX() - trackex()) <= 27 && Math.abs(gameObjectList.get(v).getY() - trackey()) <= 27) {
							  System.out.println("SLICE!!");
							  gameObjectList.remove(gameObjectList.get(v));
							  daggercount--; //lose one dagger use
							  if (daggercount==0) {
								  crossbow=true; //automatically switch back to crossbow when no more dagger uses
							  }
							  break;
						  }
		    }
		    	}
		    }
		  
		   //if the crossbow is equipped the hunter can shoot arrows and kills vamps
		  if (crossbow) {
			  
			  
		  int deadVamp=100;
		  int usedArrow=100;
		  //checks gamelist for a vamp
		  for (int v=0; v<gameObjectList.size();v++) {
			  if (gameObjectList.get(v) instanceof Vampire) {
				  //checks gamelist for arrow
				  for (int a=0; a<gameObjectList.size();a++) {
					  if (gameObjectList.get(a) instanceof Arrow) {
						  //old system didn't only took DIAGONAL distance into account
						  int huntdistx = Math.abs(gameObjectList.get(v).getX() - h.getX());
						  int huntdisty = Math.abs(gameObjectList.get(v).getY() - h.getY());
						  double huntdist = Math.sqrt(huntdistx*huntdistx+huntdisty*huntdisty); //the distance diagonally AND horizontally/vertically				  
						  
						  //in close range and in seasoned hunter the hitbox is 25, but any other time it's 50
						  int hitbox = 25;
						  
						  if (!difficulty.equals("seasoned hunter")&& huntdist>35) {
							  hitbox = 50;
						  }
						  
						  if (Math.abs(gameObjectList.get(v).getX() - gameObjectList.get(a).getX()) <= hitbox //
							  && Math.abs(gameObjectList.get(v).getY() - gameObjectList.get(a).getY()) <= hitbox) {
									System.out.println("A vampire has been slain by an arrow!");
									//higher the difficulty means higher the bounty
									if (difficulty.equals("baby")) {point+=15;}  
									else if (difficulty.equals("seasoned hunter")) {
										if (round>=7) {point+=25+round;} //if the hunter is a pro they get a point multiplier for late rounds
										else{point+=25;}}
										
									else {point+=20;}
									deadVamp=v;
									if (!woodenStake||woodenStakecount==0) {
									usedArrow=a;}
									if (woodenStake //&& woodenStakecount>0
											) {
										//woodenStakecount--; 
										//doesn't get removed until it hits the edge of the window and kills all vamps in its path
										gameObjectList.remove(gameObjectList.get(v));
										v--; //no skips in the list after removing a vampire
										
										point+=10; //bonus points for using a wooden stake
										deadVamp=100; //prevents weird glitches
									}
									
									
									break;
						  }
						  if (deadVamp!=100&&usedArrow!=100) {break;}
						  }
				  }
			  }
			  if (woodenStake) {
				  usedArrow=100;
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
	  }
	  
	  //spawns more vampires overt time
	  public void ariiiiise() {

		    int vampCount = 0;
		    boolean hunterAlive = false;

		    for (GameObject obj : gameObjectList) {
		        if (obj instanceof Vampire) vampCount++;
		        if (obj instanceof Hunter) hunterAlive = true;
		    }

		    if (!hunterAlive) return;

		    //start round if all vamps are dead
		    if (vampCount == 0 && toSpawn == 0) {
		        round++;
		        
		        //every 5 rounds hunter gets a special gadget (silver dagger is the only one right now)
		        if (round%5==0) {
		        	int rand = (int) (Math.random()*2)+1;//gives either a wooden stake or silver dagger every 5 rounds
		        	//rand=2;//TEMPORARY TESTING
		        	if(rand==1) {
		        	daggercount=5;} //reset dagger uses to 5		        
		        	if (rand==2) {
		        		woodenStakecount=4; //gives 3 wooden stakes (4 one is glitched/phantom stake)
		        	}
		        }
		        
		        //# of vamps per round
		        if (difficulty.equals("baby")) {
		        toSpawn = round + 1;   }
		        else if (difficulty.equals("seasoned hunter")) {
		        toSpawn = round*2;	// >=]
		        }
		        else {//normal is the classic amount of zombies
		        	toSpawn = round + 2; 
		        }
		        haveSpawned = 0;
		        w8amin = 0;
		        onslaught = true;
		        System.out.println("=== ROUND " + round + " START ===");
		    }

		    // slow vamp spawn R8
		    if (onslaught && haveSpawned < toSpawn) {
		        w8amin++;

		        //if the timer between spawns is greater than the requirement of 8 ticks more vamps spawn
		        if (w8amin >= 2) {
		            w8amin = 0;
		            necromancy();
		            haveSpawned++;
		        }
		    }

		    //no more will spawn
		    if (haveSpawned >= toSpawn) {
		        onslaught = false;
		        toSpawn=0;haveSpawned=0;
		    }
		}
	  
	  
	  private void necromancy() {
		  int corner = (int)(Math.random()*4); 
		  //spices up spawn location 
		  int LX = (int)(Math.random()*200); 
		  int RX = this.getWidth()- (int)(Math.random()*300)-200; 
		  int UY = (int)(Math.random()*200); 
		  int DY = this.getHeight()- (int)(Math.random()*300)-100; 
		  switch(corner) { 
		  case 0: Vampire alucard = new Vampire(LX,UY); 
		  this.addGameObject(alucard);break; 
		  case 1: Vampire adamsandler = new Vampire(RX,UY); 
		  this.addGameObject(adamsandler);break; 
		  case 2: Vampire marceline = new Vampire(LX,DY); 
		  this.addGameObject(marceline);break; 
		  case 3: Vampire mrburns = new Vampire(RX,DY); 
		  this.addGameObject(mrburns);break; }
	  }
	  
	  //arrow is detroyed at the edge of the window
	  public void arrowCheck() {
		  for (int i=0; i<gameObjectList.size();i++) {
			  if (gameObjectList.get(i) instanceof Arrow) {
				  Arrow arr = (Arrow)gameObjectList.get(i);
				  double arrw = arr.getCurrentImage().getIconWidth();
				  double arrh = arr.getCurrentImage().getIconHeight();
				  if (arr.getX()+arrw <= 0 || arr.getY()+arrh <= 0 ||
						  arr.getX()>this.getWidth()||arr.getY()>this.getHeight()) {
						  //old version
						  //arr.getX()<=0 || arr.getY()<=0 ||
						  //note to self: previous line used to use Math.abs
						  //arr.getX()+arr.getCurrentImage().getIconWidth() >= this.getWidth() || arr.getY()+arr.getCurrentImage().getIconHeight() >= this.getHeight()) {
		    	    gameObjectList.remove(arr);
		  }
			  }
		  }
	  }
	  
	  //sets the difficulty at the beginning of the game (impacts how many vampires spawn, how fast they spawn, how accurate your shots need to b
	  //etc.)
	  public void setDifficulty(String diff) {
		  difficulty = diff;
	  }
}
//first dev score(easy mode): 1305
//first dev score (normal mode): 915
//second dev score (normal mode): 2430 (no lazer tactic)
//third dev score (normal mode): 7125 (after lazer glitch was removed)
