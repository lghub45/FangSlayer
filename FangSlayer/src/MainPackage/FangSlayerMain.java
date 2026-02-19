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
			SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
	}
//best dev highscore (normal) 8550 =]
}
