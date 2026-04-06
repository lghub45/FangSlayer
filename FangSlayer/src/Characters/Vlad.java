package Characters;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import WindowsPack.GameFrame;

public class Vlad extends GameObject{
//Vladimir the DevourerOfSouls (the big bad)
	
	 public int clock=0;
	 
	//image lists
	 private static LinkedList<Icon> firestates;	 
	 private static LinkedList<Icon> necrostates;	
	 private static LinkedList<Icon> monsterstates;	
	 
	 public boolean openFire;
	 private boolean zoomies;
	 private int windowW;
	 private int windowH;
	 private boolean smokeHappened;
	 
	 //phase 1 (shoot flaming skulls)
	 static {//Each has four images that don't get used because otherwise the code freaks out
	 firestates = new LinkedList<Icon>();
	 firestates.add(new ImageIcon("Images/vladp1fire.png"));
	 firestates.add(new ImageIcon("Images/vladp1fire.png"));
	 firestates.add(new ImageIcon("Images/vladp1.png"));
	 firestates.add(new ImageIcon("Images/vladp1.png"));
	 }
	 static {//phase 2 (summon TONS of vampires)
		 necrostates = new LinkedList<Icon>();
		 necrostates.add(new ImageIcon("Images/vladp1.png"));
		 necrostates.add(new ImageIcon("Images/DamnedSoul.png"));//puff of smoke 
		 necrostates.add(new ImageIcon("Images/vladp1.png"));
		 necrostates.add(new ImageIcon("Images/vladp1.png"));
		 }
	 static {//phase 3 (turn into giant bat)
		 monsterstates = new LinkedList<Icon>();
		 monsterstates.add(new ImageIcon("Images/batu.png"));
		 monsterstates.add(new ImageIcon("Images/batd.png"));
		 monsterstates.add(new ImageIcon("Images/batL.png"));
		 monsterstates.add(new ImageIcon("Images/batr.png"));}
	 
	 public Vlad(int x, int y,int phase) {
		    super(x, y);
		    setDirection(Direction.NONE);

		    if(phase==1) {
		    imageList = firestates;}
		    else if (phase==2) {
		    	imageList=necrostates;
		    }
		    else if (phase==3) {
		    	imageList=monsterstates;
		    }
		    else {
		    	imageList = firestates;//just in case
		    }
		     openFire=false;
		     zoomies=false;
		     smokeHappened=false;
		  }
	 //movement mechanics for bat/monster mode
 public void takeFlight(int huntrx,int huntry) {
	     clock++;
		
	     //vertical movement (slow)
	     int speed=10;
	     if (!zoomies) {
	     if(this.getY() > huntry+15) {setDirection(Direction.UP);setVelocity(speed);} 
		 else if (this.getY() < huntry-15) {setDirection(Direction.DOWN);setVelocity(speed);}
         //horizontal movement (I am speed) 
		 else {
			 if (Math.abs(this.getY() - huntry) <= 15) {
		    	    setY(huntry); //just in case (makes sure that vlad NEVER has a hard time vertically aligning to you)
		    	}
			 if (this.getX() > huntrx) {setDirection(Direction.LEFT);}
			 else if (this.getX()<huntrx) {setDirection(Direction.RIGHT);}
			 else {return;}
		zoomies=true;	 
		setVelocity(40);
		 }}
		 
	     //helps him move till he hits a wall 
		 else {
			 Icon icon = getCurrentImage();
			    int iconWidth = icon.getIconWidth();

			    if (this.getX() <= 0) {
			        // At or beyond left boundary
			        setX(2);
			        setVelocity(0);
			        setDirection(Direction.RIGHT);
			        zoomies = false;
			        return;
			    } else if (this.getX() >= windowW - iconWidth-15) {
			        // At or beyond right boundary
			        setX(windowW - iconWidth - 15);
			        setVelocity(0);
			        setDirection(Direction.LEFT);
			        zoomies = false;
			        return;
			    }			
			 }
	     return;
	 }
 
 public void aimSkullFire(int huntrx,int huntry) {
	 //moves phase 1 vlad left or right and if he aligns with the hunter he shoots a fireball
	 if (this.getX() > huntrx+25) {setDirection(Direction.LEFT);setVelocity(10);}
	 else if (this.getX()<huntrx-25) {setDirection(Direction.RIGHT);setVelocity(10);}
	 else {
		 setVelocity(0);
		 setDirection(Direction.DOWN);//this is the firing animation
		 //if this is true 
		 openFire=true;
	 }
 }
 //gives permission for the gameframe to make a fireball
 public boolean permissionToFire() {
	 return openFire;
 }
 //resets the openFire to false after firing
 public void reload() {
	 openFire=false;
 }
 public void preparePhase2(int windowx, int windowy) {
 		//moves him to the center of the map for phase two
 		
	 long time1 = System.currentTimeMillis();
	 
	 //I plan on drawing a puff of smoke on where vlad WAS and WILL BE (seems like he teleported)
	 	int currentX = getX();
	    int currentY = getY();

	    setVelocity(0);
	    setDirection(Direction.DOWN);//this will be a puff of smoke image
	   
	    setX(windowx);
	    setY(windowy);
	    //by here the image SHOULD be connected to the second image set
	    long currenttime = System.currentTimeMillis();
	    if (currenttime-time1>=25&&!smokeHappened) {setDirection(Direction.DOWN);smokeHappened=true;}
	    else if(smokeHappened) {System.out.println("smoke already happened");}
	    else {setDirection(Direction.UP);}
 	}
 	
 //move to the next phase
 	public void nextPhase(int phase) {
 		if (phase==2) {
	    	imageList=necrostates;
	    }
	    else if (phase==3) {
	    	imageList=monsterstates;
	    }
	    else {
	    	imageList = firestates;//just in case
	    }
 	}

 public int trackex() {
	  return this.getX();
 }
 public int trackey() {
	  return this.getY();
 }
 
 public void setWindowInfo(int windowW,int windowH) {
	 this.windowH=windowH;
	 this.windowW=windowW;
 }
 
	 //just a formality
	 @Override
	 public void keyTyped(KeyEvent e) {
	 }
	 @Override
	 public void keyPressed(KeyEvent e) {
	 }
	 @Override
	 public void keyReleased(KeyEvent e) {
	 }
	 
	 //lets vlad move
	 @Override
	 public void move(GameFrame c) {
		 if (getVelocity() == 0 || getDirection() == Direction.NONE) {//helpful to shoot cursed skulls (used to be direction DOWN)
		        return;
		    }
		 
		 Icon icon = getCurrentImage();

		    int  iconHeight   = icon.getIconHeight();
		    int  iconWidth    = icon.getIconWidth();
		    int  canvasHeight = (int)c.getSize().getHeight();
		    int  canvasWidth  = (int)c.getSize().getWidth();
		    
		    //MOVE vlad
		    switch (getDirection()) {
		      case Direction.UP:
		        setY(getY() - getVelocity());
		        if (getY() < 0) {
		          setY(0);
		        }
		        break;
		      case Direction.DOWN:
		        setY(getY() + getVelocity());
		        if (getY() + iconHeight > canvasHeight) {
		          setY((int)(canvasHeight - iconHeight));
		        }
		        break;
		      case Direction.LEFT:
		    	  setX(getX() - getVelocity());
		    	    if (getX() < 0) {
		    	        setX(0);
		    	    }
		    	    break;
		      case Direction.RIGHT:
		    	   setX(getX() + getVelocity());
		    	    if (getX() + iconWidth > canvasWidth) {
		    	        setX(canvasWidth - iconWidth);
		    	    }
		    	    break;
			default:
				break;
		    }

	 }
	 
	 //EACH PHASE SERVES DIFFERENT PURPOSES FOR DIRECTIONAL IMAGING (only phase 3 using 4 images traditionally)
	 @Override
	 public void setImage() {
		   switch (getDirection()) {
		      case Direction.NONE:
		        break;
		      case Direction.UP:
		        currentImage = 0;
		        break;
		      case Direction.DOWN:
		        currentImage = 1;
		        break;
		      case Direction.LEFT:
		        currentImage = 2;
		        break;
		      case Direction.RIGHT:
		        currentImage = 3;
		        break;
		    }
	 }
}
