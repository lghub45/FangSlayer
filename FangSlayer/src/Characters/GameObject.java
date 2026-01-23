package Characters;


import java.awt.Color;
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

	//each game object has a collection of images (good for walking animations for hunter)
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
		//test for drawing (note actually this and the border test aren't good ideas because they keep spamming the console)
		//System.out.println("Object drawn at (x,y): ("+x+","+y+")");
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
	public abstract void move(GameFrame c); //used to be Canvas c
	public abstract void setImage();

	//public void autoMove() {
		//int rando = (int)(Math.random()*3); 
	//int bigrando = (int)(Math.random()*5);
		
		//move yellow game object up or down randomly when not highlighted
	//if (this instanceof Type_A_GameObject) {
	//if(rando == 0) {setDirection(Direction.UP);}
	//	else if (rando == 1) {setDirection(Direction.DOWN);}
	//	else if (rando == 2) {setDirection(Direction.NONE);}}
		
		//move red/blue game objects all over randomly when not highlighted
	//	if (this instanceof Type_B_GameObject ||this instanceof Type_D_GameObject) {
	//	if(bigrando == 0) {setDirection(Direction.UP);}
	//	else if (bigrando == 1) {setDirection(Direction.DOWN);}
	//	else if (bigrando == 2) {setDirection(Direction.LEFT);}
	//	else if (bigrando == 3) {setDirection(Direction.RIGHT);}
	//else if (bigrando == 4) {setDirection(Direction.NONE);}}
		
		
		//move purple game object left or right randomly when not highlighted
	//if (this instanceof Type_C_GameObject) {
	//if(rando == 0) {setDirection(Direction.LEFT);}
	//else if (rando == 1) {setDirection(Direction.RIGHT);}
	//else if (rando == 2) {setDirection(Direction.NONE);}}
	}
	