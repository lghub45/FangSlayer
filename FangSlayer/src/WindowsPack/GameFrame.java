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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import Characters.Arrow;
import Characters.CursedSkull;
import Characters.Direction;
import Characters.GameObject;
import Characters.Hunter;
import Characters.Vampire;
import Characters.Vlad;
import Storage.Scorage;
import ScorePack.Score;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.net.URL;

//used to b "GameFrame extends JComponent implements ActionListener, KeyListener"
public class GameFrame extends JPanel implements ActionListener, KeyListener {
	// DEFAULT SERIAL NUMBER
	private static final long serialVersionUID = 1L;

	//private JFrame frame;
	private Timer gameLoopTimer;
	public List<GameObject> gameObjectList;
	//these will help find the hunter in the game object list
	private Hunter h;
	private int index = 0; 
	private int aimdir =0;
	private int round=0;
	private int point=0;
	private int phase=1; //boss phase
	private boolean onslaught; 
	
	private int toSpawn=0; //this will be used to determine when/how vampires spawn
	private int haveSpawned=0;
	private int w8amin=0;
	private String difficulty="";
	
	private Image background; //this is the castle grounds/map image
	//private Image filter; //this is the old timey film filter
	private FangSlayerWindow window;
	private static String map="vamps"; // this decides which background to use and what monsters to spawn (vamps by default just incase)
	
	//items
	private boolean crossbow;//=true; //this means the crossbow is equipped by default
	private int primary=1;//this determines a primary weapon (crossbow by default)
	private boolean boomstick;
	private long lastshot=0;
	private long boomcool=750;//the boomstick has a slight cooldown after firing 250 milliseconds= .25 seconds
	private long magiccool=250;//the boomstick has a slight cooldown after firing 250 milliseconds= .25 seconds
	private long vladcool=65;//vlad has a grace period after being hit (prevents instakill bugs) 250 milliseconds = .25 seconds
	private long vladhit=0;
	private boolean vladdamage; //vlad needs hist grace period and this is the just in case
	private long spellcool=0;
	private long wizardtime=0;//helps give vlad magic fireballs instead of a f---ing lazer beam
	
	private boolean silverDagger=false; //a special item for later
	private boolean woodenStake=false; // toggle to make arrows into wooden stakes (pierces thru multiple vamps) 
	private int daggercount =0; // can kill up to 5 vamps before dagger is gone 
	private int woodenStakecount=0; //you have a max of 3 wooden stakes 
	
	private static Clip song;//this will be the track played later
	private static File track;
	public boolean jamsesh;
	
	private int vladlife;
	private int intVladLife;
	private GameObject vladsDemise;
	private boolean vladstruck;
	private boolean finalstand;
	private int vampcounter=0; // used in phase 2 to keep track of how many vamps killed to provide special items
	public boolean lazertrue=false;
	
	public GameFrame(FangSlayerWindow window) {
		this.window=window;
		//setting the background image
		//background=new ImageIcon("Images/tempbackgrnd.png");
		 // try {
			//  if (map.equals("vamps")) {
		      //  background = ImageIO.read(new File("Images/castleofthedamned.png"));}
			 // else if (map.equals("wolves")) {
				//background = ImageIO.read(new File("Images/bloodmoonforest.png"));
			 // }
		    //} catch (IOException e) {
		      //  e.printStackTrace();
		    //}
		
		// make a list of characters
		gameObjectList = new LinkedList<GameObject>();
		//  make a window
	//	frame = new JFrame("Minimap");
		//frame.setSize(1000, 1000);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(this);
		
		
		//sets the frame to full screen
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setFocusable(true);
		//requestFocusInWindow();

		// make a timer for the game loop
		gameLoopTimer = new Timer(25, this);
		//gameLoopTimer.start();
		
		setFocusTraversalKeysEnabled(false);
	    addKeyListener(this);
	   
	    //adds the hunter to the game 
	    //this.addGameObject(new Hunter(400,400));
	    
		// show the window
		//frame.setVisible(true);
	}
	
	public void startSlayin(String diff,String map,int prim) {
		difficulty = diff;
		this.map=map;
		vladsDemise=null;
		vladstruck=false;
		finalstand=false;
		
		//just in case: reset all the things
		gameObjectList.clear(); round=0; point=0; daggercount=0; woodenStakecount=0;
		
		this.intialPrim(prim);
		
		//preps the boss battle
		  if (map.equals("finalboss")) {
			 if (difficulty.equals("baby")){vladlife=3; intVladLife=3;}//13
			 else if (difficulty.equals("nightmare")){vladlife=130; intVladLife=130;}
			 else if (difficulty.equals("seasoned hunter")){vladlife=26;intVladLife=26;}//used to be 52, but too hard
			 else {vladlife=26;intVladLife=26;}//normal diff
		    	//vladlife=26;//used to be 13 the 169 then 26
		    	//intVladLife=26;//this is a CONSTANT (doesn't change)
		    	phase=1;
		    	//spawns him with 13 hearts of health at the dead center top of the map
		    	Vlad vlad = new Vlad(625,30,0);
		    	//this.addGameObject(new Vlad(650,40,0));//this.window.getWidth())/2
		    	this.addGameObject(vlad);
		    	vlad.setWindowInfo(this.window.getWidth(), this.window.getHeight());
		    }
		
		//set the background
		 try {
			  if (map.equals("vamps")) {
		        background = ImageIO.read(new File("Images/castleofthedamned.png"));}
			  else if (map.equals("wolves")) {
				background = ImageIO.read(new File("Images/bloodmoonforest.png"));
			  }
			  else if (map.equals("finalboss")) {
				  if (phase!=3) { background = ImageIO.read(new File("Images/bossphase1.png"));}
				  else { background = ImageIO.read(new File("Images/bossphase3.png"));}
				  //background = ImageIO.read(new File("Images/tempbackgrnd.png")); //TEMPORARY BACKGROUND
			  }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 //set a filter
		// try {
		  //      filter = ImageIO.read(new File("Images/grainedit.gif"));
		    //} catch (IOException e) {
		      //  e.printStackTrace();
		    //}

		// make a timer for the game loop
		gameLoopTimer.start();
		
		//setFocusTraversalKeysEnabled(false);
	    //addKeyListener(this);
	   
	    //adds the hunter to the game
		int cheat=1;
		if (boomstick==true) {cheat=2;crossbow=false;}

	    this.addGameObject(new Hunter(400,400,cheat));
	    
	    
	    //play the music
	    if (jamsesh) {
	    try {
			letsRocknRoll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
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
	@Override
	//used to be public synchronized also used to not have @override above it
	protected void paintComponent(Graphics g) {
		//draw the background
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		
		//draw the character
		for (GameObject s : gameObjectList) {
			s.draw(this, g);
		}
		//draw the score and round numbers
		 g.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		 g.setColor(Color.red);
		 if (map.equals("finalboss")) {g.drawString("Phase: "+phase, 10, 35); }
		 //if (map.equals("wolves")) {
			// g.setColor(Color.red);
			 //}
		 else {
		    g.drawString("Score: "+point, 10, 35);        // placeholder
		    g.drawString("Round: " + round, 10, 70);
		    //g.drawString("Daggers: " + daggercount, 10, 105);
		    g.drawString("Difficulty: "+difficulty,10,140);
		// else {g.drawString("Phase: "+phase, 10, 35); 
		    }
		 g.drawString("Daggers: " + daggercount, 10, 105);
		    if (crossbow && !woodenStake) {
		    	g.drawString("Crossbow", 1400, 750);
		    }
		    if (woodenStake) {
		    	if (woodenStakecount>0) {
		    	g.drawString("Wooden Stakes: "+woodenStakecount, 1300, 750); }
		    	//phantom stake glitch where last one isn't used so we just display one less than the actual count 
		    	else {g.drawString("Wooden Stakes: 0", 1300, 750);}
		    }
		    if (map.equals("finalboss")) {
		    	int xcoord= 25;
		    	for (int i=0; i<vladlife;i++) {
		    		g.drawRect(xcoord, 35, 25, 15);
		    		xcoord+=25;
		    	}
		    }
		    
		    //draw the old timey filter
		   // g.drawImage(filter, 0, 0, getWidth(), getHeight(), this);
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
			//double checks vlad phase 1 and that there actually will be a vlad
			if(map.equals("finalboss")&&phase==1) {
				for (int i=0; i<gameObjectList.size();i++) {
				if (gameObjectList.get(i) instanceof Vlad) {
				Vlad vlal = (Vlad)gameObjectList.get(i);
				vlal.aimSkullFire(trackex(),trackey());//confirm the shot
				long currenttime = System.currentTimeMillis();
				if (vlal.permissionToFire()&&currenttime - wizardtime >= magiccool) {
					//it's wizard time mfer! FIREBALL!!!
					CursedSkull ghostrider = new CursedSkull(vlal.getX(),vlal.getY());
					gameObjectList.add(ghostrider);
					ghostrider.setDirection(Direction.DOWN);
					vlal.reload();
					wizardtime=System.currentTimeMillis();
				}
				}
				}
			}
			
			//double checks that vlad is in phase 3 / at 6 or less health
			if (vladlife<= (intVladLife*1/3) &&phase==3) {
				for (int i=0; i<gameObjectList.size();i++) {
				if (gameObjectList.get(i) instanceof Vlad) {
					Vlad vlal = (Vlad)gameObjectList.get(i); //casting just in case
					vlal.nextPhase(phase);//sets to phase 3 
					System.out.println(phase+" phase");
					vlal.takeFlight(trackex(),trackey());
					
						}
			}}
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
			//vladstruck=false;
		//clock++;
		//will keep checking if any vampires have been hit by arrows/daggers  (run BEFORE abitterend to confirm that hunter isn't killed 
			//while using a dagger)
			
		//if (map.equals("finalboss")) {
		//long currenttime = System.currentTimeMillis();
			//if (currenttime - vladhit >= vladcool) {
			//fretNot();}}
		//else {
			fretNot();
			//}
			
		//will keep checking if the hunter has been caught
		aBitterEnd();
		//if all vampires are dead more shall arise (ONLY IF NOT ON FINAL BOSS. Vlad controls vampires spawning there)
		if (!map.equals("finalboss")) {
		ariiiiise();}
		//cool down SPECIFICALLY in phase 2 for spawning vampires
		else if (map.equals("finalboss")&&phase==2) {
			long currenttime = System.currentTimeMillis();
			if (currenttime - spellcool >= 750) {
			
				necromancy(); 
			
				spellcool = System.currentTimeMillis();}}
		else if (map.equals("finalboss")&&phase==3) {
			if (!finalstand) {
			try {
				background = ImageIO.read(new File("Images/bossphase3.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			finalstand=true;
			}
			
			//for (GameObject vamp : gameObjectList) {
				//if (vamp instanceof Vampire) {
				//gameObjectList.remove(vamp);}
			//}
			for (int i = gameObjectList.size() - 1; i >= 0; i--) {
			    if (gameObjectList.get(i) instanceof Vampire) {
			        gameObjectList.remove(i);
			    }
			}
		}
		
		
		 //arrow is detroyed at the edge of the window
		arrowCheck();
		setPrimary(primary);//sets the primary weapon
		
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
	  public void keyPressed(KeyEvent e) {//--------------------------------------------------------------------------
		  gameObjectList.get(index).keyPressed(e);
		  GameObject s = gameObjectList.get(index);
		    s.setVelocity(20); 
		    //if the fire key is pressed create a new arrow at the hunter's position that flies in the hunter's direction (away from hunter)
		    //this specific version is the original mechanics which makes the "lazer beam" glitch
		    //this glitch is fine, but only on baby mode =]
		    //checks to see if crossbow is equipped before trying to shoot
		    
		    if (lazertrue==true) {  //LAZER BEAM GLITCH only works with the lazer beam cheat code
		    if (crossbow) {
		    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
		    	//one wooden stake is used per shot 
		    	if (woodenStake && woodenStakecount>0) {
		 	     woodenStakecount--;
		 	     woodenStake=true; //using wooden stakes instead of regular arrows
		 	      }
		    	else if (woodenStakecount==0) {
		    		woodenStake=false;//auto switch to regular arrows when out of stakes
		    	}
		    	
		    	Arrow arr = new Arrow(s.getX(),s.getY(),woodenStake);
		    	if (woodenStake && woodenStakecount>0) { //makes sure we are using wooden stake images instead of regular arrows
		    		arr.setWood(true);
		    	}
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
		    }}
		    if(boomstick) {
		    	boomcool = 250; //significant decrease in cooldown
		    	
			    //testing to see if releasing arrow button prevents "lazer beam" glitch
			   
			    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
			    	if (woodenStake && woodenStakecount>0) {
				 	     woodenStakecount--;
				 	     //woodenStake=true; //using wooden stakes instead of regular arrows
				 	      }
				    	else if (woodenStakecount==0) {
				    		woodenStake=false;//auto switch to regular arrows when out of stakes
				    	}
			    	Arrow arr = new Arrow(s.getX(),s.getY(),true);
			    	
			    	//should only have ONE wooden stake and not 3
			    	if (woodenStake && woodenStakecount>0) { //makes sure we are using wooden stake images instead of regular arrows
			    		arr.setWood(true);
			    	
			    	//arr.setDirection(aimdir);
			    	arr.setDirection(((Hunter) s).aim());
			    	this.addGameObject(arr);
			    	return;}
			    	
			    	long current = System.currentTimeMillis();
			    	if (current-lastshot>=boomcool) {//boomstick has cooldown, but lazer beam doesn't have cooldown
			    		lastshot=current;
			    	
			    	Arrow arr1 =new Arrow(s.getX(),s.getY(),true);
			    	Arrow arr2;
			    	Arrow arr3;
			    	
			    	if (((Hunter) s).aim()==Direction.DOWN||((Hunter) s).aim()==Direction.UP) {//if up or down the bullets have a horizontal spread
			    		arr2 = new Arrow(s.getX()+13,s.getY(),true);
			    		arr3 = new Arrow(s.getX()-13,s.getY(),true);
			    	}
			    	else if (((Hunter) s).aim()==Direction.LEFT||((Hunter) s).aim()==Direction.RIGHT) {//if left or right the bullets have veritcal spread
			    		arr2 = new Arrow(s.getX(),s.getY()+13,true);
			    		arr3 = new Arrow(s.getX(),s.getY()-13,true);
			    	}
			    	else {//vertical spread just in case 
			    		arr2 = new Arrow(s.getX(),s.getY()+13,true);
			    		arr3 = new Arrow(s.getX(),s.getY()-13,true);
			    	}
			    	
			    	arr1.setDirection(((Hunter) s).aim());
			    	arr2.setDirection(((Hunter) s).aim());
			    	arr3.setDirection(((Hunter) s).aim());
			    	this.addGameObject(arr1);
			    	this.addGameObject(arr2);
			    	this.addGameObject(arr3);
			    	System.out.println("                                                                                                     PEW!!");
			    }
			    }
			    
			    }
		}
		    //crossbow toggle 
		   if (e.getKeyCode()==KeyEvent.VK_1) {
			   System.out.println("Crossbow equipped");
			   //crossbow=true;
			   setPrimary(primary); //switch back to whatever the primary weapon was (crossbow or boomstick)
			   silverDagger=false;
			   woodenStake=false; //just in case the player had a different item equipped before
			   h.bowBack();//switch to crossbow images
		   }
		   if (e.getKeyCode()==KeyEvent.VK_2 && daggercount>0) {//makes sure you actually have daggers before equipping
			   System.out.println("Silver Dagger equipped");
			   crossbow=false;
			   silverDagger=true;
			   h.sliceAndDice();//switch to the dagger images
		   }
		   if (e.getKeyCode()==KeyEvent.VK_3 && woodenStakecount>0) {//double checks stake count before equipping
			   System.out.println("Wooden Stakes equipped");
			   crossbow=true;//wooden stake is a variation of arrows
			   silverDagger=false;
			   woodenStake=true;
			   h.bowBack();//switch to crossbow images (just for visuals.. the stakes are still fired)
		   }
		
	  }//--------------------------------------------------------------------------

	  public void keyReleased(KeyEvent e) {
	   GameObject s = gameObjectList.get(index);
	    s.setVelocity(0); 
	    
	    if (lazertrue==false) {
	    //only shoots if in crossbow
	    if(crossbow) {
	    	
	    //testing to see if releasing arrow button prevents "lazer beam" glitch
	    
	    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
	    	if (woodenStake && woodenStakecount>0) {
		 	     woodenStakecount--;
		 	     woodenStake=true; //using wooden stakes instead of regular arrows
		 	      }
		    	else if (woodenStakecount==0) {
		    		woodenStake=false;//auto switch to regular arrows when out of stakes
		    	}
	    	Arrow arr = new Arrow(s.getX(),s.getY(),woodenStake);
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
	    
	    if(boomstick) {
	    	
		    //testing to see if releasing arrow button prevents "lazer beam" glitch
		   boomcool=750;
		    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
		    	if (woodenStake && woodenStakecount>0) {
			 	     woodenStakecount--;
			 	     //woodenStake=true; //using wooden stakes instead of regular arrows
			 	      }
			    	else if (woodenStakecount==0) {
			    		woodenStake=false;//auto switch to regular arrows when out of stakes
			    	}
		    	Arrow arr = new Arrow(s.getX(),s.getY(),true);
		    	
		    	//should only have ONE wooden stake and not 3
		    	if (woodenStake && woodenStakecount>0) { //makes sure we are using wooden stake images instead of regular arrows
		    		arr.setWood(true);
		    	
		    	//arr.setDirection(aimdir);
		    	arr.setDirection(((Hunter) s).aim());
		    	this.addGameObject(arr);
		    	return;}
		    	
		    	long current = System.currentTimeMillis();
		    	if (current-lastshot>=boomcool||lazertrue) {//boomstick has cooldown, but lazer beam doesn't have cooldown
		    		lastshot=current;
		    	
		    	Arrow arr1 =new Arrow(s.getX(),s.getY(),true);
		    	Arrow arr2;
		    	Arrow arr3;
		    	
		    	if (((Hunter) s).aim()==Direction.DOWN||((Hunter) s).aim()==Direction.UP) {//if up or down the bullets have a horizontal spread
		    		arr2 = new Arrow(s.getX()+13,s.getY(),true);
		    		arr3 = new Arrow(s.getX()-13,s.getY(),true);
		    	}
		    	else if (((Hunter) s).aim()==Direction.LEFT||((Hunter) s).aim()==Direction.RIGHT) {//if left or right the bullets have veritcal spread
		    		arr2 = new Arrow(s.getX(),s.getY()+13,true);
		    		arr3 = new Arrow(s.getX(),s.getY()-13,true);
		    	}
		    	else {//vertical spread just in case 
		    		arr2 = new Arrow(s.getX(),s.getY()+13,true);
		    		arr3 = new Arrow(s.getX(),s.getY()-13,true);
		    	}
		    	
		    	arr1.setDirection(((Hunter) s).aim());
		    	arr2.setDirection(((Hunter) s).aim());
		    	arr3.setDirection(((Hunter) s).aim());
		    	this.addGameObject(arr1);
		    	this.addGameObject(arr2);
		    	this.addGameObject(arr3);
		    	System.out.println("                                                                                                     PEW!!");
		    	//if the arrow hits the edge of the window remove it from game object list
		    	//if(arr.getX()==this.getX()&&arr.getY()==this.getY()) {
		    		//gameObjectList.remove(arr);
		    	//}
		    }
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
		  
		  long currentTime = System.currentTimeMillis();

		  
		  
		  //makes sure we don't try to use the hunter before he exists
		  for (int i=0; i<gameObjectList.size();i++) {
			  //hunter is killed by vampires, Vlad the Devourer of Souls and his Cursed Skulls
			  if (gameObjectList.get(i) instanceof Vampire||gameObjectList.get(i) instanceof Vlad||gameObjectList.get(i) instanceof CursedSkull) {
				  
				  //has to be a high number like 25 instead of 5 otherwise the vampires just orbit the player
				  //used to be 25 but it caused immortality glitch where spamming arrows could block vampires from getting close enough
				  if (Math.abs(gameObjectList.get(i).getX() - trackex()) <= 27 && Math.abs(gameObjectList.get(i).getY() - trackey()) <= 27) {
					  
					  //lets us hit vlad with a silver dagger
					  if (gameObjectList.get(i) instanceof Vlad && currentTime - vladhit < vladcool && map.equals("finalboss")) {
					      return; 
					  }
					  
					  System.out.println("The hunter has been caught by the vampire! Game Over! Your score was: "+point);
					  
					  gameLoopTimer.stop();//ends the loop and prevents from infinite main menus
					  //frame.dispose(); //exits the gameframe
					  
					  song.stop(); //stops the song if dead
					  
					  if (point>6942.0) {
						  JOptionPane.showMessageDialog(null, "Congrats! You unlocked the Lazer beam glitch! "
						  		+ "Use the code 'crossbowgobrrr' in the cheat "+ "menu");
					  }
					  
					  
					  //checks the top 10 highscores in the database and adds the player score if it's high enough
					  List <Score> scorelist = Scorage.getTopTen(); //returns the database (should be only 10 scores)
					  
					  //takes the user to the score input frame if the list is smaller than 10 
					  //(shouldn't happen tho because this is a beta thing. the users should already have the top 10 scores)
					  if (scorelist.size()<10) {
						 // ScoreFrame skills4bills = new ScoreFrame(point); //makes and opens a score frame
						   //skills4bills.setVisible(true);
						  window.youDaChamp(point);
						   return;
					  }
					  //takes the user to the score input frame if their score is higher than any scores in the database
					  for (int j=0; j<scorelist.size();j++) {
					  if (point>= scorelist.get(j).getPoints()) {
					  //should take the user to a menu to input their username and highscore to the database
						  //ScoreFrame skills4bills = new ScoreFrame(point); //makes and opens a score frame
						   //skills4bills.setVisible(true);
						  window.youDaChamp(point);
						   return;
					  }
					  }
//)					  
					  
					  window.menuSelect("Main"); 
					  //MainFrame menu = new MainFrame(); //makes and opens the main menu
					 //  menu.setVisible(true);
					   return;
				  }
			  }
		  }
	  }
	  //this makes the vampires killable (arrows kill vampires)
	  public void fretNot() {//----------------------------------------------------------------------------------------------------
		 // vladstruck=false;
		  //this won't run unless vlad's cool down is ok
		  vladdamage = false;
		  long currenttime = System.currentTimeMillis();
		  if (map.equals("finalboss")) {
				//long currenttime = System.currentTimeMillis();
				if (vampcounter>=10 && phase==2) {
				vampcounter =0;
				int rand = (int) (Math.random()*2)+1;
				if (rand==1) {
					if (difficulty.equals("baby")) {daggercount+=10;}
					else {daggercount+=5;}
				}
				else {
					if (difficulty.equals("baby")) {woodenStakecount+=5;}
					else {woodenStakecount+=3;}
				}
				
				//if (currenttime - vladhit < vladcool) {
					//return;}
				
				}
		  
		  }
		 // vladhit=System.currentTimeMillis();
		  System.out.println(vladlife);
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
								  h.bowBack(); // back to crossbow images
								  silverDagger=false;
							  }
							  break;
						  }
					  }
					  //coding for vlad 
					  else if (gameObjectList.get(v) instanceof Vlad &&!vladdamage) {
						  //negate damage on vlad if he's in his grace period after being hit
						 // if (currenttime - vladhit < vladcool) {
							//	return;}
						  
						  vladdamage=true;
						  //has to be a high number like 25 instead of 5 otherwise the vampires just orbit the player
						  //used to be 25 but it caused immortality glitch where spamming arrows could block vampires from getting close enough
						  Vlad vlad = (Vlad)gameObjectList.get(v);
						  if (Math.abs(gameObjectList.get(v).getX() - trackex()) <= 60 && Math.abs(gameObjectList.get(v).getY() - trackey()) <= 60) {
							  System.out.println("SLICE!!");
							 
							   if (currenttime - vladhit >= vladcool) {
							        vladhit = System.currentTimeMillis();
							        System.out.println("Vlad hit!");
							    //}
							  
							  
							  //vlad can tank multiple hits
							  if (vladlife==1) {
							  vladsDemise=gameObjectList.get(v);}//vlad gets marked for death
							  else {vladlife--;
							  if (vladlife==(int)(intVladLife*2/3)) {phase=2; //used to be vladlife==9  13/9=1.4
							  vlad.nextPhase(phase);
							  System.out.println(phase+"phase");
							  vlad.preparePhase2(685,340);}
							  if (vladlife==(int)(intVladLife*1/3)) {phase=3;}//used to be vladlife==6 13/6=2.2
							  
							  }//moves to phase 2 @ 9 
							  // phase 3 @ 6 
							  daggercount--; //lose one dagger use
							  if (daggercount==0) {
								  crossbow=true; //automatically switch back to crossbow when no more dagger uses
								  h.bowBack(); // back to crossbow images
								  silverDagger=false;
							  }
							  //vladimir devourer of souls is dead
							 // if (vladsDemise!=null) {
								//  gameObjectList.remove(vladsDemise);
							  //}
							   }
							  break;
						  }
					  }
		    	}
		    	  //vladimir devourer of souls is dead
				 // if (vladsDemise!=null) {
					//  gameObjectList.remove(vladsDemise);
				  //}
		    }
		    
		  
		   //if the crossbow is equipped the hunter can shoot arrows and kills vamps
		  if (crossbow||boomstick) {
			  
			  
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
									//increase the vamp counter if in phase 2 of boss blitz
									if (map.equals("finalboss") && phase == 2) {
									    vampcounter++;
									    System.out.println("Vamp counter: " + vampcounter);
									}
									
									//higher the difficulty means higher the bounty
									if (difficulty.equals("baby")) {point+=15;}//note: 15  
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
			  //code for vlad 
			  if (gameObjectList.get(v) instanceof Vlad&&!vladdamage) {
				  //vlad has a grace period
				  //if (currenttime - vladhit < vladcool) {
					//	return;}
				  
				  vladdamage=true;
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
					  
					  if (//!vladstruck&&
							  Math.abs(gameObjectList.get(v).getX() - gameObjectList.get(a).getX()) <= hitbox //
						  && Math.abs(gameObjectList.get(v).getY() - gameObjectList.get(a).getY()) <= hitbox) {
								System.out.println("vlad has been struck by an arrrow");
								
								  if (currenttime - vladhit >= vladcool) {
								        vladhit = System.currentTimeMillis();
								        System.out.println("Vlad hit!");
								
							//	vladstruck=true;
								
								Vlad vlad = (Vlad)gameObjectList.get(v);
								
								int damage =0;
								
								if (!woodenStake||woodenStakecount==0) {
								usedArrow=a;damage=1;}
								
								if (woodenStake) {damage=2;deadVamp=100;}
								
								if (vladlife==1) {
									vladsDemise=gameObjectList.get(v);
									}
									else {
									 vladlife=vladlife-damage;	
									 if (vladlife==(int)(intVladLife*2/3)) {phase=2; //used to be vladlife==9  13/9=1.4
									 vlad.nextPhase(phase);
									 System.out.println(phase+" phase");
									 vlad.preparePhase2(685,340);
									 //vlad.preparePhase2(window.getWidth()/2,window.getHeight()/2);
									 }
									 if (vladlife==(int)(intVladLife*1/3)) {phase=3;}}//used to be vladlife==6 13/6=2.2
								//moves to phase 2 @ 9 & phase 3 @ 6 
								
								//if (vladsDemise!=null) {
									//gameObjectList.remove(vladsDemise);
								//}
								  }
								break;
					  }
					  if (deadVamp!=100&&usedArrow!=100) {break;}
					  }
			  }
			  }
			  //vladimir devourer of souls is dead
			 // if (vladsDemise!=null) {
				//  gameObjectList.remove(vladsDemise);
			  //}
			  if (woodenStake) {
				  usedArrow=100;
			  }
		  }
		  //vladimir devourer of souls is dead
			 // if (vladsDemise!=null) {
				//  gameObjectList.remove(vladsDemise);
			  //}
		  
		  if (vladsDemise!=null) {
			  gameLoopTimer.stop();//ends the loop and prevents from infinite main menus
			  //frame.dispose(); //exits the gameframe
			  
			  song.stop(); //stops the song if vlad dies
			  window.menuSelect("Main"); 
			  JOptionPane.showMessageDialog(null, "Congrats! You unlocked the secret Boomstick! Use the code 'groovy' in the cheat "
			  		+ "menu");
			   //return;
			//  gameObjectList.remove(vladsDemise);
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
	  }//----------------------------------------------------------------------------------------------------
	  
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
		        
		        //every 5 rounds hunter gets a special item (rn we have silver dagger of wooden stake)
		        if (round%5==0) {
		        	int rand = (int) (Math.random()*2)+1;//gives either a wooden stake or silver dagger every 5 rounds
		        	//rand=2;//TEMPORARY TESTING
		        	if(rand==1) {
		        	daggercount+=5;} //reset dagger uses to 5		        
		        	if (rand==2) {
		        		woodenStakecount+=3; //give 3 stakes
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
		  
		  //this is for boss blitz (we just keep spawning and spawning no regard to rounds)
		  if (map.equals("finalboss")) {
			  
		  int vampCount = 0;
		  boolean hunterAlive = false;

		    for (GameObject obj : gameObjectList) {
		        if (obj instanceof Vampire) vampCount++;
		        if (obj instanceof Hunter) hunterAlive = true;
		    }
		  if (!hunterAlive) return;
		  //vlad's general area is the spawn point for vampires
		  //Vlad's coords are 685,340
		  LX = 570;//645
		  RX = 800;//705
		  UY = 240;//325
		  DY = 440;//355
		  } 
		  
		  int monster =0;
		  if (map.equals("wolves")) {
			  monster=1;
		  }
		  
		  switch(corner) { 
		  case 0: Vampire alucard = new Vampire(LX,UY,monster); 
		  this.addGameObject(alucard);break; 
		  case 1: Vampire adamsandler = new Vampire(RX,UY,monster); 
		  this.addGameObject(adamsandler);break; 
		  case 2: Vampire marceline = new Vampire(LX,DY,monster); 
		  this.addGameObject(marceline);break; 
		  case 3: Vampire mrburns = new Vampire(RX,DY,monster); 
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
			  }}
		  for (int i=0; i<gameObjectList.size();i++) {
			  if (gameObjectList.get(i) instanceof CursedSkull) {
				  CursedSkull yarr = (CursedSkull)gameObjectList.get(i);
				  double yarrw = yarr.getCurrentImage().getIconWidth();
				  double yarrh = yarr.getCurrentImage().getIconHeight();
				  if (yarr.getX()+yarrw <= 0 || yarr.getY()+yarrh <= 0 ||
						  yarr.getX()>this.getWidth()||yarr.getY()>this.getHeight()) {
					 
		    	    gameObjectList.remove(yarr);
		  }
			  }
		  }
	  }
	  
	  //sets the difficulty at the beginning of the game (impacts how many vampires spawn, how fast they spawn, how accurate your shots need to b
	  //etc.)
	  public void setDifficulty(String diff) {
		  difficulty = diff;
	  }
	  
	  public void setPrimary(int primary) {
		  crossbow=false;
		  boomstick=false;
		  
		  
		  if (primary==1) {
			  crossbow=true;
			  boomstick=false;
		  }
		  else if (primary==2) {
			  boomstick=true;
			  crossbow=false;
		  }
	  }
	  
	  public void intialPrim(int primary) {
		  this.primary=primary;
		  if (primary==1) {crossbow=true;boomstick=false;}
		  else if(primary==2) {boomstick=true;crossbow=false;}
		  else{crossbow=true;boomstick=false;}
	  }
	  
	  //special thanks to: https://stackoverflow.com/questions/16867976/how-do-you-add-music-to-a-jframe 
	  //also thanks to: 
	  public static void letsRocknRoll()throws Exception {
		  //just in case (should prevent song related crashes)
		  if (song != null && song.isRunning()) {
			    song.stop();
			}
		  
			if (map.equals("vamps")) {
			  track = new File("Music/Toccata and Fugue in D minor Meets Metal - Johann Sebastian Bach.wav");}
			else if (map.equals("wolves")) {
				track = new File("Music/Hungry Like the Wolf (2001 Remaster).wav");}
			else if (map.equals("finalboss")){
				track = new File("Music/Judas Priest - Nostradamus.wav");
			}
			else {track = new File("Music/Toccata and Fugue in D minor Meets Metal - Johann Sebastian Bach.wav");}
		  song = AudioSystem.getClip();
		  AudioInputStream ais = AudioSystem.getAudioInputStream(track);  
		  song.open(ais);
		  song.loop(Clip.LOOP_CONTINUOUSLY);
		  //SwingUtilities.invokeLater(new Runnable() {
	            //public void run() {
	                // A GUI element to prevent the Clip's daemon Thread 
	                // from terminating at the end of the main()
	            //    JOptionPane.showMessageDialog(null, "Close to exit!");
	          //  }
		  	//});
}
}
//first dev score(easy mode): 1305
//first dev score (normal mode): 915
//second dev score (normal mode): 2430 (no lazer tactic)
//third dev score (normal mode): 7125 (after lazer glitch was removed)
