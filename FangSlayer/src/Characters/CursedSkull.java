package Characters;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import WindowsPack.GameFrame;

public class CursedSkull extends GameObject{

	
	public CursedSkull(int x, int y) {
		super(x,y);

		
		imageList = new LinkedList<Icon>();
	    imageList.add(new ImageIcon("Images/DamnedSoul.png"));
	    imageList.add(new ImageIcon("Images/DamnedSoul.png"));
	    imageList.add(new ImageIcon("Images/DamnedSoul.png"));
	    imageList.add(new ImageIcon("Images/DamnedSoul.png"));
	    
	}

	public void setDirection(int direction) {
		super.setDirection(direction);
		setVelocity(40);
	}
	//formalities so we can utilize GameObject
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void move(GameFrame c) {
		
		//NOTE: the fireball's direction should ALWAYS be down (vlad is at the top of the map and shoots down at hunter)
		 Icon icon = getCurrentImage();

		    switch (getDirection()) {
		      case Direction.UP:
		        setY(getY() - getVelocity());
		     
		        break;
		      case Direction.DOWN:
		        setY(getY() + getVelocity());
		 
		        break;
		      case Direction.LEFT:
		    	  setX(getX() - getVelocity());
		    
		    	    break;
		      case Direction.RIGHT:
		    	   setX(getX() + getVelocity());
		    	    break;
			default:
				break;
		    }
		
	}

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
	
	//note to self remove these or comment them out for the final version
	public int getHeight() {
		 Icon icon = getCurrentImage();
		    int  iconHeight    = icon.getIconHeight();
		    return iconHeight;
	}
	
	public int getWidth() {
		 Icon icon = getCurrentImage();
		    int  iconWidth    = icon.getIconWidth();
		    return iconWidth;
	}
	
	
	
}