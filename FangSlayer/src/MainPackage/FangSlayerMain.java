
package MainPackage;

import java.sql.SQLException;

import javax.swing.SwingUtilities;
import Storage.DataBase;
import WindowsPack.FangSlayerWindow;

public class FangSlayerMain {

	public static void main(String[] args)
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
			SwingUtilities.invokeLater(() -> new FangSlayerWindow());
			//maps are made on a 1536 width and 793 height scale
	}
//best dev highscore (normal) 8550 =]
}
