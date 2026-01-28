package Characters;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import WindowsPack.GameFrame;

public class Vampire extends GameObject {
	 
	 public int clock=0;
	 
	 private static LinkedList<Icon> vampstates;
	 
	 static {
		 vampstates = new LinkedList<Icon>();
		 vampstates.add(new ImageIcon("Images/VampUp1.png"));
		 vampstates.add(new ImageIcon("Images/VampDown1.png"));
		 vampstates.add(new ImageIcon("Images/VampLeft1.png"));
		 vampstates.add(new ImageIcon("Images/VampRight1.png"));
	 }
	 
	 
	 public Vampire(int x, int y) {
		    super(x, y);
		    setDirection(Direction.NONE);
		    
		    imageList = vampstates;
		    
		    //imageList = new LinkedList<Icon>();
		    //imageList.add(new ImageIcon("Images/VampUp1.png"));
		    //imageList.add(new ImageIcon("Images/VampDown1.png"));
		    //imageList.add(new ImageIcon("Images/VampLeft1.png"));
		    //imageList.add(new ImageIcon("Images/VampRight1.png"));
		    
		  }
	 public void move(GameFrame c) {
		    Icon icon = getCurrentImage();

		    int  iconHeight   = icon.getIconHeight();
		    int  iconWidth    = icon.getIconWidth();
		    int  canvasHeight = (int)c.getSize().getHeight();
		    int  canvasWidth  = (int)c.getSize().getWidth();
		    
		    //MOVE Hunter OBJECT
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
	 //helps the vampire chase the hunter with their current position
	 public void huntHim(int huntrx,int huntry) {
		 
		 //Modern Mechanics (This is normal Mode, hard mode will have faster speed)
		 int decidedistX = Math.abs(huntrx-this.trackex());//distance in y direction from Van Slechkont 2 vampire
		 int decidedistY = Math.abs(huntry-this.trackey());//distance in x direction from Van Slechkont 2 vampire
	     
	     clock++;
	     
	     
	     //we want to make sure the vampires don't move diagonally and (preferably) not too jittery
	     //so we need to make sure they only change AXIS once every 10 ticks or so
	  
		if (decidedistY>decidedistX && clock>=10)  {
		if(this.getY() > huntry+2) {setDirection(Direction.UP);setVelocity(5);}//inner if 1
		 else if (this.getY() < huntry-2) {setDirection(Direction.DOWN);setVelocity(5);}//inner else 1
		clock=0;
		}
		
		else if(decidedistX>decidedistY && clock>=10) {
		 if (this.getX() > huntrx) {setDirection(Direction.LEFT);setVelocity(5);}//inner if 1
		 else if (this.getX()<huntrx) {setDirection(Direction.RIGHT);setVelocity(5);}//inn
		clock=0;
		}
		 
		 
	     //old movement mechanics (THIS WILL BE USED FOR EASY MODE)
		 //if(this.getY() > huntry+2) {setDirection(Direction.UP);setVelocity(5);}
		 //else if (this.getY() < huntry-2) {setDirection(Direction.DOWN);setVelocity(5);}
		 
		 //else if (this.getX() > huntrx) {setDirection(Direction.LEFT);setVelocity(5);}
		 //else if (this.getX()<huntrx) {setDirection(Direction.RIGHT);setVelocity(5);}
	 }
	 
	 //just a formality since vampire doesn't need controls to move
	 @Override
	 public void keyTyped(KeyEvent e) {}
	 @Override
	 public void keyPressed(KeyEvent e) {}
	 @Override
	 public void keyReleased(KeyEvent e) {}
	 
	//tracks the position of the vampire so it can compare for when it meets the hunter
	  public int trackex() {
		  return this.getX();
	  }
	  public int trackey() {
		  return this.getY();
	  }
}
//