package ScorePack;

public class Score {

		private String user; //the username provided in the highscore frame
	    private int points; //the points associated with that user
	    private int place; //helps 4 removing and editing (also showcases what place the user is at)

	    public Score(int points, int place, String user) {
	        this.points = points;
	        this.place=place;
	        this.user=user;
	    }
	    
	    //default constructor
	    public Score(int points,String user) {
	    	this.points=points;
	    	this.user=user;
	    	this.place=1;
	    }

	    public int getPoints() {
	        return points;
	    }

	    public void setPoints(int points) {
	        this.points = points;
	    }
	    
	    public String getUser() {
	        return user;
	    }

	    public void setUser(String user) {
	        this.user = user;
	    }
	    
	    public void setPlace(int place) {
	        this.place = place;
	    }

	    public int getPlace() {
	        return place;
	    }

	    // equality based on place (used for double checking stuff)
	    //@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Score)) return false;
	        Score other = (Score) o;
	        return this.place== other.place;
	    }

	    @Override
	    public int hashCode() {
	        return Integer.hashCode(place);
	    }

	    //should be used for displaying the score on the leaderboard frame
	    @Override
	    public String toString() {
	        return getPlace() +":"+user+"--------"+ getPoints();
	    }
}
