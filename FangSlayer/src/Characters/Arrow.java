package Characters;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import WindowsPack.GameFrame;

public class Arrow extends GameObject{

	public Arrow(int x, int y) {
		super(x,y);
		
		imageList = new LinkedList<Icon>();
	    imageList.add(new ImageIcon("Images/ArrowUp1.png"));
	    imageList.add(new ImageIcon("Images/ArrowDown1.png"));
	    imageList.add(new ImageIcon("Images/ArrowLeft1.png"));
	    imageList.add(new ImageIcon("Images/ArrowRight1.png"));
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
		//whatever the hunter's direction is when the fire key is pressed is the arrow's PERMANENT direction
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
