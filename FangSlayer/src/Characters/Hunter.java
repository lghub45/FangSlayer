package Characters;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import WindowsPack.GameFrame;

public class Hunter extends GameObject implements KeyListener {

	//this is used to help make arrows go in the correct direction when fired
	public int arrowAim=Direction.UP;//default value so no frozen arrows at the start of the game
	
  public Hunter(int x, int y) {
    super(x, y);
    //used to be Direction.NONE
    setDirection(Direction.UP); //need to set direction AT THE START otherwise an arrow "booby trap glitch" occurs (arrow freezes in place)
    							 //note to self: maybe we can make a beartrap item that utilizes this glitch on purpose? 
    imageList = new LinkedList<Icon>();
    imageList.add(new ImageIcon("Images/huntu2.png"));//up
    imageList.add(new ImageIcon("Images/huntd2.png.png"));//down
    imageList.add(new ImageIcon("Images/huntr2.png.png"));//right
    imageList.add(new ImageIcon("Images/huntL2.png.png"));//left
    
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
    	    if (getX() < 0) setX(0);
    	    break;

    	case Direction.RIGHT:
    	    setX(getX() + getVelocity());
    	    if (getX() + iconWidth > canvasWidth)
    	        setX(canvasWidth - iconWidth);
    	    break;
	default:
		break;
    }

  }

  //SPECIFY THE IMAGE TO DISPLAY
  //   USED FOR ANIMATION
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
	      case Direction.RIGHT:
	        currentImage = 2;
	        break;
	      case Direction.LEFT:
	        currentImage = 3;
	        break;
	    }
	  }

  public void keyTyped(KeyEvent e) {
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() != KeyEvent.VK_TAB) {
      setDirection(Direction.NONE);
    }
  }

  public void keyPressed(KeyEvent e) {
	  //now compatible for wasd not just arrow keys
    if (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_W) {
      setDirection(Direction.UP);
      //test for the key press
      //System.out.println("                                                                                                     UP button pressed");
    arrowAim = getDirection();
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_S) {
      setDirection(Direction.DOWN);
      //test for the key press
      //System.out.println("                                                                                                     DOWN button pressed");
      arrowAim = getDirection();
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_A) {
      setDirection(Direction.LEFT);
      //test for the key press
      //System.out.println("                                                                                                     LEFT button pressed");
      arrowAim = getDirection();
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_D) {
      setDirection(Direction.RIGHT);
      //test for the key press
      //System.out.println("                                                                                                     RIGHT button pressed");
      arrowAim = getDirection();
    }
    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
    //	arrowAim = getDirection(); 
    	setDirection(Direction.NONE); //makes sure the hunter doesn't move while shooting
    	//System.out.println("                                                                                                     PEW!!");
    }
  }
  //tracks the position of the hunter so the vampire can follow and compare for when they meet
  public int trackex() {
	  return this.getX();
  }
  public int trackey() {
	  return this.getY();
  }
  public int aim() {
	  return arrowAim;
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
	
	//changes images to silver dagger
	public void sliceAndDice(){
		imageList.clear();
		imageList.add(new ImageIcon("Images/shankup.png"));//up
		imageList.add(new ImageIcon("Images/shankdown.png"));//down
		imageList.add(new ImageIcon("Images/shankright.png"));//right
		imageList.add(new ImageIcon("Images/shankleft.png"));//left
	}
	
	//changes imagesto crossbow
		public void bowBack(){
			imageList.clear();
			  imageList.add(new ImageIcon("Images/huntu2.png"));//up
			    imageList.add(new ImageIcon("Images/huntd2.png.png"));//down
			    imageList.add(new ImageIcon("Images/huntr2.png.png"));//right
			    imageList.add(new ImageIcon("Images/huntL2.png.png"));//left
		}
  
}