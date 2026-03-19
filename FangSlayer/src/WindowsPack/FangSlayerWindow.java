package WindowsPack;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FangSlayerWindow extends JFrame{

	private CardLayout menus;
	private JPanel menucontainer;
	private GameFrame game;
	private ScoreFrame score;
	private int prim;
	//private JFrame frame;
	private boolean jamsesh;

	/**
	 * Create the application.
	 */
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
		
		menucontainer.add(main,"Main");
		menucontainer.add(diff,"Difficulty");
		menucontainer.add(game,"Game");
		menucontainer.add(leader,"LeaderBoard");
		menucontainer.add(score,"Score");
		menucontainer.add(cheat,"Cheat");
		
		add(menucontainer);
		setVisible(true);
		
	}
	public void menuSelect(String menu) {
		menus.show(menucontainer, menu);
	}
	public void difficultySelect(String difficulty,String map) {
		game.jamsesh = jamsesh;
		game.startSlayin(difficulty,map,prim);
		menus.show(menucontainer, "Game");//loads a game
		//game.intialPrim(prim); //1 is crossbow 2 is boomstick
		game.requestFocusInWindow();
	}
	
	public void youDaChamp(int points) {
		menus.show(menucontainer, "Score");
		score.setPoints(points);
	}
	public void setPrim(int weaponOChoice) {
		prim=weaponOChoice;//1 is crossbow 2 is boomstick
	}
	public void setJamsesh(boolean toggle) {
		jamsesh=toggle;
	}

}
