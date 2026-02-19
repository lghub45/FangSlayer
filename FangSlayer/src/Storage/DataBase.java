package Storage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQLite connection manager.
 * - DB file: data/app.db (create data/ directory if missing)
 * - Initializes schema if missing
 * - Requires sqlite-jdbc-3.50.30.jar on the classpath
 */
public class DataBase {
    private static final String DB_FILE = "scoredata/app.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;

    private static DataBase instance;
    //private final Connection conn; 

    private DataBase() throws SQLException {
    	
    	File dir = new File("scoredata");
    	if (!dir.exists()) {
    	    dir.mkdirs();
    	}
    	
    	 try {
    	        Class.forName("org.sqlite.JDBC");
    	    } catch (ClassNotFoundException e) {
    	        throw new SQLException("SQLite JDBC driver not found", e);
    	    }
    	
         Connection conn = DriverManager.getConnection(URL);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON;");
        }
    }

    public static synchronized DataBase getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    //opens new connection each time
    public Connection getConnection()  throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }

    /** Create tables if they don't exist. Safe to call multiple times. */
   public void initialize() throws SQLException {
       //scoressql should hold score objects which have the number of points, their placement, and the name entered
	   //which means a usersSql SHOULD be redundant
	   //String usersSql = ""
                //+ "CREATE TABLE IF NOT EXISTS users ("
                //+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                //+ "username TEXT NOT NULL UNIQUE,"
                //+ "password_hash TEXT NOT NULL,"
               // + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
              //  + ");";
        String scoresSql = ""
                + "CREATE TABLE IF NOT EXISTS scores ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
               // + "place INTEGER NOT NULL,"
                + "user TEXT NOT NULL,"
                + "points INTEGER NOT NULL,"
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
      //          + "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE"
                + ");";
        try (Connection conn = getConnection();
        		Statement st = conn.createStatement()) {
          //  st.execute(usersSql);
            st.execute(scoresSql);
            st.execute("CREATE INDEX IF NOT EXISTS idx_scores_points ON scores(points DESC);"); //makes an index if the table exists
        }
    }
}
