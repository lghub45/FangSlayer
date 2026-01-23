package MainPackage;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.*;

import javax.swing.SwingUtilities;

import Characters.Hunter;
import Characters.Vampire;
//import Storage.DB;
import WindowsPack.GameFrame;


public class FangSlayerMain {

	public static void main(String[] args)// throws SQLException
	{
		//make a canvas
		 GameFrame minimap = new GameFrame();
		//minimap.requestFocus();
		
		
	//make our protagonist 
		Hunter VictorVanBadAss = new Hunter(200,200);
		//NOTE TO SELF SPAWN VAMPIRES ROUGHLY AT 0,0 EDGES OF MAP
		minimap.addGameObject(VictorVanBadAss);
		
		Vampire vlad = new Vampire(0,0);
		minimap.addGameObject(vlad);
		//  DB db;
		 //try {
			//db = DB.getInstance();
			//db.initialize();
		//} catch (SQLException e) {
			//e.printStackTrace();
		//}
		//used to say "loginframe" but changed to gameframe
		//SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true)); //after database is doublechecked, start up the login process
	}

}
