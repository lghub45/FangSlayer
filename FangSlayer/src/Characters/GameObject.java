package Characters;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.Icon;

import WindowsPack.GameFrame;

public abstract class GameObject implements KeyListener {
	
	//each character has a location, velocity, and direction
	private int x;
	private int y;
	private int velocity;
	private int direction;

	//each game object has a collection of images 
	protected List<Icon> imageList;
	protected int currentImage; 

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		velocity = 0;
		currentImage = 0;
	}

	public void draw(Component c, Graphics g) {
		imageList.get(currentImage).paintIcon(c, g, x, y);
	}

	// SETTERS AND GETTERS

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Icon getCurrentImage() {
		return imageList.get(currentImage);
	}
	
	//ABSTRACT METHODS
	public abstract void move(GameFrame c); 
	public abstract void setImage();

	}
	