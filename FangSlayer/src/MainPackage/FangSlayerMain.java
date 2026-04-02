
package MainPackage;

import java.sql.SQLException;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.*;

import javax.swing.SwingUtilities;
import Characters.Hunter;
import Characters.Vampire;
import Storage.DataBase;
import WindowsPack.FangSlayerWindow;
//import Storage.DB;
import WindowsPack.GameFrame;
import WindowsPack.MainFrame;


public class FangSlayerMain {

	public static void main(String[] args)// throws SQLException
	{	
		//establish the database connection
		  DataBase db;
		 try {
			db = DataBase.getInstance();
			db.initialize();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//make a main menu frame
			SwingUtilities.invokeLater(() -> new FangSlayerWindow());//used to be MainFrame
			
			//NOTE TO SELF: New Krita maps are made on a 1536 width and 793 height scale
	}
//best dev highscore (normal) 8550 =]
}
