import java.util.ArrayList;

public class MapKnowledge {
	
	 private double ScoreImpact ; //If player makes a move to any empty block, -1 will be subtracted, this optimizes the strategy to use as little moves as possible
	 private ArrayList<String> Percepts ;     //Send an array of what is being perceived on this block, such as toxis smell, light glitter etc. 
	 private boolean Blocked ;

	 public MapKnowledge(ArrayList<String> Percepts, double ScoreImpact, boolean Blocked)
	 {
		 this.Percepts = Percepts ;
		 this.ScoreImpact = ScoreImpact ;
		 this.Blocked = Blocked ;
	 }

	public double getScoreImpact() {
		return ScoreImpact;
	}

	public void setScoreImpact(double d) {
		ScoreImpact = d;
	}

	public ArrayList<String> getPercepts() {
		return Percepts;
	}

	public void setPercepts(ArrayList<String> percepts) {
		Percepts = percepts;
	}

	public boolean isBlocked() {
		return Blocked;
	}

	public void setBlocked(boolean blocked) {
		Blocked = blocked;
	}
	
	
}
