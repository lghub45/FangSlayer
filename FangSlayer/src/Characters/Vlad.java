package Characters;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import WindowsPack.GameFrame;

public class Vlad extends GameObject{
//Vladimir DevourerOfSouls the big bad
	
	 
	 public int clock=0;
	 
	 private static LinkedList<Icon> firestates;	 
	 private static LinkedList<Icon> necrostates;	
	 private static LinkedList<Icon> monsterstates;	
	 public boolean openFire;
	 
	 
	 //NOTE TO SELF THESE ARE TEMPORARY IMAGES
	 
	 //phase 1 (shoot flaming skulls)
	 static {
	 firestates = new LinkedList<Icon>();
	 firestates.add(new ImageIcon("Images/vampu2.png"));
	 firestates.add(new ImageIcon("Images/vampd2.png"));
	 }
	 static {//phase 2 (summon TONS of vampires)
		 necrostates = new LinkedList<Icon>();
		 necrostates.add(new ImageIcon("Images/vampr2.png"));}
	 static {//phase 3 (turn into giant bat)
		 monsterstates = new LinkedList<Icon>();
		 monsterstates.add(new ImageIcon("Images/huntu2.png"));
		 monsterstates.add(new ImageIcon("Images/huntd2.png"));
		 monsterstates.add(new ImageIcon("Images/huntL2.png"));
		 monsterstates.add(new ImageIcon("Images/huntr2.png"));}
	 
	 public Vlad(int x, int y,int phase) {
		    super(x, y);
		    setDirection(Direction.NONE);
		    
		    if(phase==0) {
		    imageList = firestates;}
		    else if (phase==1) {
		    	imageList=necrostates;
		    }
		    else if (phase==2) {
		    	imageList=monsterstates;
		    }
		    else {
		    	imageList = firestates;//just in case
		    }
		     openFire=false;
		  }
	 //movement mechanics for bat/monster mode
 public void takeFlight(int huntrx,int huntry) {
		 
		 //Modern Mechanics (This is normal Mode, hard mode will have faster speed)
		 int decidedistX = Math.abs(huntrx-this.trackex());//distance in y direction from Van Slechkont 2 vampire
		 int decidedistY = Math.abs(huntry-this.trackey());//distance in x direction from Van Slechkont 2 vampire
	     
	     clock++;
		//if (decidedistY>decidedistX && clock>=10)  {
		//if(this.getY() > huntry+2) {setDirection(Direction.UP);setVelocity(5);}//inner if 1
		 //else if (this.getY() < huntry-2) {setDirection(Direction.DOWN);setVelocity(5);}//inner else 1
		//clock=0;
		//}
		
		//else if(decidedistX>decidedistY && clock>=10) {
	//	 if (this.getX() > huntrx) {setDirection(Direction.LEFT);setVelocity(5);}//inner if 1
		// else if (this.getX()<huntrx) {setDirection(Direction.RIGHT);setVelocity(5);}//inn
		//clock=0;
		//}
		 
		 
	     //old movement mechanics (THIS WILL BE USED FOR EASY MODE)
	     //this makes sure he moves CRAYZ fast horizontally 
	     //(he should move up and down on the sides of the map and once he reaches the correct height he swoops across the map)
		 if(this.getY() > huntry+2) {setDirection(Direction.UP);setVelocity(5);}
		 else if (this.getY() < huntry-2) {setDirection(Direction.DOWN);setVelocity(5);}
		 
		 else if (this.getX() > huntrx) {setDirection(Direction.LEFT);setVelocity(45);}
		 else if (this.getX()<huntrx) {setDirection(Direction.RIGHT);setVelocity(45);}
	 }
 
 public void aimSkullFire(int huntrx,int huntry) {
	 //moves phase 1 vlad left or right and if he aligns with the hunter he shoots a fireball
	 if (this.getX() > huntrx) {setDirection(Direction.LEFT);setVelocity(45);}
	 else if (this.getX()<huntrx) {setDirection(Direction.RIGHT);setVelocity(45);}
	 else {
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
 		
 		if (this.getX()!=windowx && this.getY()!=windowy) {
 		setDirection(Direction.DOWN);setVelocity(5);}
 		else {setVelocity(0);}
 	}

 public int trackex() {
	  return this.getX();
 }
 public int trackey() {
	  return this.getY();
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
	 @Override
	 public void move(GameFrame c) {
	 }
	 @Override
	 public void setImage() {
	 }
}
