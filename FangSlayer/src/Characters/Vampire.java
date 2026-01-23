package Characters;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import WindowsPack.GameFrame;

public class Vampire extends GameObject {
	 public Vampire(int x, int y) {
		    super(x, y);
		    setDirection(Direction.NONE);
		    
		    imageList = new LinkedList<Icon>();
		    imageList.add(new ImageIcon("Images/HunterDown.png"));
		    imageList.add(new ImageIcon("Images/HunterDown.png"));
		    imageList.add(new ImageIcon("Images/HunterDown.png"));
		    imageList.add(new ImageIcon("Images/HunterDown.png"));
		    
		    
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
	 public void fetchmeTheirSouls(int huntrx,int huntry) {
		
		 
		 if(this.getY() > huntry) {setDirection(Direction.UP);setVelocity(5);}
		 else if (this.getY() < huntry) {setDirection(Direction.DOWN);setVelocity(5);}
		 else if (this.getX() > huntrx) {setDirection(Direction.LEFT);setVelocity(5);}
		 else if (this.getX()<huntrx) {setDirection(Direction.RIGHT);setVelocity(5);}
		 
	 }
	 @Override
	 public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	 }
	 @Override
	 public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	 }
	 @Override
	 public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	 }
}
