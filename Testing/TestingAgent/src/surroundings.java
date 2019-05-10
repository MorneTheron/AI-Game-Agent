import java.util.ArrayList;

public class surroundings {
	
	private int moveLocation = 0 ;
	private ArrayList<String> percepts ;
	private double heuristicValue = 0 ;
	
	public surroundings(int moveLocation, ArrayList<String> percepts, int Value)
	{
		this.moveLocation = moveLocation ;
		this.percepts = percepts ;
		this.heuristicValue = Value ;
	}

	public int getMoveLocation() {
		return moveLocation;
	}

	public void setMoveLocation(int moveLocation) {
		this.moveLocation = moveLocation;
	}

	public ArrayList<String> getPercepts() {
		return percepts;
	}

	public void setPercepts(ArrayList<String> percepts) {
		this.percepts = percepts;
	}

	public double getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(double heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	
	
	
	
}
