import java.util.ArrayList;

public class KB {
	
	private String Percept;
	private double PerceptImpact = 0 ;
	private boolean MoveAllowed; //-1 means take move back after it has been made, 0 means no suggestion, 1 to 8 means direction to move in next

	public KB() {
		this.Percept = "";
		this.PerceptImpact = 0 ;
		this.MoveAllowed = true; 	
	}
	
	public KB(String Percept, int Impact, boolean allowable) {
		this.Percept = "";
		this.PerceptImpact = 0 ;
		this.MoveAllowed = allowable ; 
	}
	
	public String getPercept() {
		return Percept;
	}

	public void setPercept(String percept) {
		Percept = percept;
	}

	public double getPerceptImpact() {
		return PerceptImpact;
	}

	public void setPerceptImpact(double perceptImpact) {
		PerceptImpact = perceptImpact;
	}

	public boolean isMoveAllowed() {
		return MoveAllowed;
	}

	public void setMoveAllowed(boolean moveAllowed) {
		MoveAllowed = moveAllowed;
	}

	
	
}
