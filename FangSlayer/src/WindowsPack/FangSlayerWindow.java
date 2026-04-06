package WindowsPack;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FangSlayerWindow extends JFrame{

	private CardLayout menus;
	private JPanel menucontainer;
	private GameFrame game;
	private ScoreFrame score;
	private int prim;
	private boolean jamsesh;
	private String savemap;//used to save the map for the score screen
	public boolean lazertrue;
	public String difficulty;

	public FangSlayerWindow() {
		setTitle("Fang Slayer"); //hell yea >=]
		setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jamsesh=true;
		prim =1;
		menus = new CardLayout();
		menucontainer = new JPanel(menus);//jpanel holds the stuff for the menus
		
		//used for making the different menus
		MainFrame main = new MainFrame(this);
		LeaderFrame leader = new LeaderFrame(this);
		game = new GameFrame(this);
		DifficultyFrame diff = new DifficultyFrame(this);
		score = new ScoreFrame(this);
		CheatWindow cheat = new CheatWindow(this);
		lazertrue=false;
		
		//adds our different menus to the game
		menucontainer.add(main,"Main");
		menucontainer.add(diff,"Difficulty");
		menucontainer.add(game,"Game");
		menucontainer.add(leader,"LeaderBoard");
		menucontainer.add(score,"Score");
		menucontainer.add(cheat,"Cheat");
		
		add(menucontainer);
		setVisible(true);
		difficulty="";
		
	}
	//used to switch between menus
	public void menuSelect(String menu) {
		menus.show(menucontainer, menu);
	}
	//starts the game with given setting (map, difficulty, weapon choice, music, and lazer glitch)
	public void difficultySelect(String difficulty,String map) {
		savemap=map;
		this.difficulty=difficulty;
		game.jamsesh = jamsesh;
		game.lazertrue = lazertrue;
		game.startSlayin(difficulty,map,prim);
		menus.show(menucontainer, "Game");//loads a game
		game.requestFocusInWindow();
	}
	//takes player to the score screen to enter their name 
	public void youDaChamp(int points) {
		menus.show(menucontainer, "Score");
		score.setPoints(points);
		score.setBackground(savemap);//sets background of score screen to map they just played on
		score.setDiff(difficulty);
	}
	public void setPrim(int weaponOChoice) {
		prim=weaponOChoice;//1 is crossbow 2 is boomstick
	}
	public void setJamsesh(boolean toggle) {
		jamsesh=toggle; //music on/off
	}
	
	public void setLazer(boolean beam) {
	    this.lazertrue = beam;//just in case
	    game.lazertrue = beam; 
	}
	
	public void refreshLeader() {
		//updates score screen with new scores
		LeaderFrame leader = (LeaderFrame) menucontainer.getComponent(3);
		leader.refresh();
	}

}
