package Characters;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import WindowsPack.GameFrame;

public class Hunter extends GameObject implements KeyListener {

  public Hunter(int x, int y) {
    super(x, y);
    setDirection(Direction.NONE);
    
    imageList = new LinkedList<Icon>();
    imageList.add(new ImageIcon("Images/HunterDown.png"));
    imageList.add(new ImageIcon("Images/HunterDown.png"));
    imageList.add(new ImageIcon("Images/HunterRight2.png"));
    imageList.add(new ImageIcon("Images/HunterRight2.png"));
    
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
        setX(getX() + getVelocity());
        if (getX() + iconWidth > canvasWidth) {
          setX((int)(canvasWidth - iconWidth));
        }
        break;
      case Direction.RIGHT:
        setX(getX() - getVelocity());
        if (getX() < 0) {
          setX(0);
        }
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
	      case Direction.LEFT:
	        currentImage = 2;
	        break;
	      case Direction.RIGHT:
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
      System.out.println("                                                                                                     UP button pressed");
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_S) {
      setDirection(Direction.DOWN);
      //test for the key press
      System.out.println("                                                                                                     DOWN button pressed");
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_D) {
      setDirection(Direction.LEFT);
      //test for the key press
      System.out.println("                                                                                                     LEFT button pressed");
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_A) {
      setDirection(Direction.RIGHT);
      //test for the key press
      System.out.println("                                                                                                     RIGHT button pressed");
    }
    if(e.getKeyCode() == KeyEvent.VK_F||e.getKeyCode() == KeyEvent.VK_P) {
    	setDirection(Direction.NONE); //makes sure the hunter doesn't move while shooting
    	System.out.println("                                                                                                     PEW!!");
    }
  }
  //tracks the position of the hunter so the vampire can follow
  public int trackex() {
	  return this.getX();
  }
  public int trackey() {
	  return this.getY();
  }
  
}