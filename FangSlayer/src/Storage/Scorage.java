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
			"SELECT user, points FROM scores ORDER BY points DESC LIMIT 10";
    private static final String SELECT_SCORE_SQL =
            "SELECT user, points FROM scores ORDER BY points DESC LIMIT 1 OFFSET ?"; 
    		//updated to limit 1 OFFSET ? to get the score from a specific place
    private static final String INSERT_SCORE_SQL =
            "INSERT INTO scores(user, points) VALUES( ?, ?)";
    //private static final String DELETE_BY_PLACEMENT_SQL =
      //      "DELETE FROM scores WHERE place = ?";
    //makes sure there are only 10 scores in the table 
    private static final String CROWD_CONTROL_SQL =
            "DELETE FROM scores WHERE id NOT IN (SELECT id FROM scores ORDER BY points DESC, created_at ASC LIMIT 10)"; 
    		//previous line is a bit better than older version because it accounts for ties (removes newer score that is tied)
    
    //used to have parameter for int place
    public static List<Score> getTopTen() {
        List<Score> scores = new ArrayList<>();
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL_SCORES_SQL)) {
            //ps.setInt(1, place);
            try (ResultSet rs = ps.executeQuery()) {
            	int placement=1;
                while (rs.next()) {
                	int points = rs.getInt("points");
                    //int placement = rs.getInt("place");
                    String user = rs.getString("user");
                    Score s = new Score(points, placement++,user);
                    scores.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static Score getScore(int place) {
    	if (place<1) return null; // error
    	  try (Connection conn = DataBase.getInstance().getConnection();
    	             PreparedStatement ps = conn.prepareStatement(SELECT_SCORE_SQL)) {
    	             ps.setInt(1, place-1);
    	            try (ResultSet rs = ps.executeQuery()) {
    	                while (rs.next()) {
    	                	int points = rs.getInt("points");
    	                    //int placement = rs.getInt("place");
    	                    String user = rs.getString("user");
    	                    Score s = new Score(points,user);
    	                    return s;
    	                }
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	return null; //no score found
    }
    
    public static void addScore(Score score) {
    	if (score.getUser()==null ||score.getUser().isBlank()) {
    		System.out.println("Error: need a name to submit a score");
    		return;} //error must have a name
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SCORE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, score.getUser());
            ps.setInt(2,score.getPoints());
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
    
    //this is a redundant method (we already have the top 10 trimmer in addscore)
    //but keeping it just in case for later
    
   // public static void newObjective(int place, Score score) {
    //	removeScoreByPlace(place);
    	//addScore(place,score);
    //}

    //public static void removeScoreByPlace(int place) {
      //  try (Connection conn = DataBase.getInstance().getConnection();
        //     PreparedStatement ps = conn.prepareStatement(DELETE_BY_PLACEMENT_SQL)) {
          //  ps.setInt(1, place);
            //ps.executeUpdate();
       // } catch (Exception e) {
         //   e.printStackTrace();
        //}
    //}
}
