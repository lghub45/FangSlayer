package Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ScorePack.Score;

public class Scorage {
//storage for high scores (scorage) =]
	
	private static final String GET_ALL_SCORES_SQL =
		    "SELECT user, points, difficulty FROM scores ORDER BY points DESC LIMIT 10";

		private static final String SELECT_SCORE_SQL =
		    "SELECT user, points, difficulty FROM scores ORDER BY points DESC LIMIT 1 OFFSET ?";
    private static final String INSERT_SCORE_SQL =
            "INSERT INTO scores(user, points, difficulty) VALUES( ?, ?,?)";
   
    //makes sure there are only 10 scores in the table 
    private static final String CROWD_CONTROL_SQL =
            "DELETE FROM scores WHERE id NOT IN (SELECT id FROM scores ORDER BY points DESC, created_at ASC LIMIT 10)"; 
    
    //helps collect the topten high scores into a list (used by the leaderboard window)
    public static List<Score> getTopTen() {
        List<Score> scores = new ArrayList<>();
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL_SCORES_SQL)) {
            try (ResultSet rs = ps.executeQuery()) {
            	int placement=1;
                while (rs.next()) {
                	int points = rs.getInt("points");
                    String user = rs.getString("user");
                    String difficulty = rs.getString("difficulty");
                    Score s = new Score(points, placement++,user, difficulty);
                    scores.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scores;
    }
//helps collect a specific score by placement
    public static Score getScore(int place) {
    	if (place<1) return null; // error
    	  try (Connection conn = DataBase.getInstance().getConnection();
    	             PreparedStatement ps = conn.prepareStatement(SELECT_SCORE_SQL)) {
    	             ps.setInt(1, place-1);
    	            try (ResultSet rs = ps.executeQuery()) {
    	                while (rs.next()) {
    	                	int points = rs.getInt("points");
    	                    String user = rs.getString("user");
    	                    String diff=rs.getString("difficulty");
    	                    Score s = new Score(points,user,diff);
    	                    return s;
    	                }
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	return null; //no score found
    }
    
    //used to input a new score into the database
    public static void addScore(Score score) {
    	if (score.getUser()==null ||score.getUser().isBlank()) {
    		System.out.println("Error: need a name to submit a score");//error must have a name
    		return;} 
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SCORE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, score.getUser());
            ps.setInt(2,score.getPoints());
            ps.setString(3, score.getDiff());
            int affected = ps.executeUpdate();
            if (affected == 0) return;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    score.setPlace(keys.getInt(1));
                } else { // fallback to last_insert_rowid()
                    try (Statement st = conn.createStatement();
                         ResultSet rs = st.executeQuery("SELECT last_insert_rowid()")) {
                        if (rs.next()) score.setPlace(rs.getInt(1));
                    }
                }
            }
            //trims the top 10
            try (PreparedStatement ps2 = conn.prepareStatement(CROWD_CONTROL_SQL)) {ps2.executeUpdate();}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //used for dev tests to wipe the board clean
    public static void clearScores() {
    			try (Connection conn = DataBase.getInstance().getConnection();
			 PreparedStatement ps = conn.prepareStatement("DELETE FROM scores")) {
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
    }
    }
    
    //this is for a MANUAL addition of scores (used for testing)
    public static void insertScore(Score score) {
    	  try (Connection conn = DataBase.getInstance().getConnection();
    	             PreparedStatement ps = conn.prepareStatement(INSERT_SCORE_SQL, Statement.RETURN_GENERATED_KEYS)) {
    	            ps.setString(1, score.getUser());
    	            ps.setInt(2,score.getPoints());
    	            ps.setString(3, score.getDiff());
    	            int affected = ps.executeUpdate();
    	            if (affected == 0) return;
    	            try (ResultSet keys = ps.getGeneratedKeys()) {
    	                if (keys.next()) {
    	                    score.setPlace(keys.getInt(1));
    	                } else { // fallback to last_insert_rowid()
    	                    try (Statement st = conn.createStatement();
    	                         ResultSet rs = st.executeQuery("SELECT last_insert_rowid()")) {
    	                        if (rs.next()) score.setPlace(rs.getInt(1));
    	                    }
    	                }
    	            }
    	            //trims the top 10
    	            try (PreparedStatement ps2 = conn.prepareStatement(CROWD_CONTROL_SQL)) {ps2.executeUpdate();}
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    }
}
