package Characters;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import WindowsPack.GameFrame;

public class Arrow extends GameObject{

	
	public boolean woodenStake = false; //used to determine if the arrow is a wooden stake (good for switching out image files)
	public Arrow(int x, int y,boolean woodenStake) {
		super(x,y);
		
		if (!woodenStake){
		imageList = new LinkedList<Icon>();
	    imageList.add(new ImageIcon("Images/ArrowUp1.png"));
	    imageList.add(new ImageIcon("Images/ArrowDown1.png"));
	    imageList.add(new ImageIcon("Images/ArrowLeft1.png"));
	    imageList.add(new ImageIcon("Images/ArrowRight1.png"));
	    }else{
	    imageList = new LinkedList<Icon>();
	    imageList.add(new ImageIcon("Images/SteakUp1.png"));
	    imageList.add(new ImageIcon("Images/SteakDown1.png"));
	    imageList.add(new ImageIcon("Images/SteakLeft1.png"));
	    imageList.add(new ImageIcon("Images/SteakRight1.png")); }
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

		  //  int  iconHeight   = icon.getIconHeight();
		    //int  iconWidth    = icon.getIconWidth();
		    //int  canvasHeight = (int)c.getSize().getHeight();
		    //int  canvasWidth  = (int)c.getSize().getWidth();
		    
		    //MOVE Hunter OBJECT
		    switch (getDirection()) {
		      case Direction.UP:
		        setY(getY() - getVelocity());
		        //if (getY() < 0) {
		          //setY(0);
		        //}
		        break;
		      case Direction.DOWN:
		        setY(getY() + getVelocity());
		     //   if (getY() + iconHeight > canvasHeight) {
		       //   setY((int)(canvasHeight - iconHeight));
		        //}
		        break;
		      case Direction.LEFT:
		    	  setX(getX() - getVelocity());
		    	//    if (getX() < 0) {
		    	  //      setX(0);
		    	    //}
		    	    break;
		      case Direction.RIGHT:
		    	   setX(getX() + getVelocity());
		    	 //   if (getX() + iconWidth > canvasWidth) {
		    	   //     setX(canvasWidth - iconWidth);
		    	    //}
		    	    break;
			default:
				break;
		    }
		    //arrow is detroyed at the edge of the window
		 //   if (getX() <= 0 || getY() <= 0 ||
		   // 	    Math.abs(getX()+icon.getIconWidth()) >= c.getWidth() || Math.abs(getY()+icon.getIconHeight()) >= c.getHeight()) {
		    //	    c.gameObjectList.remove(this);
		    	//}
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
	
	public boolean getWood() {
		    return woodenStake;
	}
	
	public void setWood(boolean steak) {
		woodenStake=steak;
	}
	
	
}
