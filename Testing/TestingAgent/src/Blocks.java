import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Blocks 
{
    private boolean Goal; 
    private int Type;
    private int ScoreImpact ; //If player makes a move to any empty block, -1 will be subtracted, this optimizes the strategy to use as little moves as possible
    private ArrayList<String> Percepts ;     //Send an array of what is being perceived on this block, such as toxis smell, light glitter etc. 
	private java.awt.Image Image ;
    
	public Blocks() 
	{
		Goal = false ; 
	    ScoreImpact  = -1 ;
	    Percepts = new ArrayList<String>() ;
	}
	
	public Blocks(boolean Goal,int Type, int ScoreImpact, String ImageLoc) 
	{
		this.Percepts = new ArrayList<String>() ;
		this.Goal = Goal ; 
		this.Type = Type ;
	    this.ScoreImpact  = ScoreImpact ;
	    this.Percepts = new ArrayList<String>() ;
	    ImageIcon ii = new ImageIcon(ImageLoc);
	    Image = ii.getImage(); 
	}

	public boolean isGoal() {
		return Goal;
	}

	public void setGoal(boolean goal) {
		Goal = goal;
	}

	public int getScoreImpact() {
		return ScoreImpact;
	}

	public void setScoreImpact(int scoreImpact) {
		ScoreImpact = scoreImpact;
	}

	public ArrayList<String> getPercepts() {
		return Percepts;
	}

	public void setPercepts(ArrayList<String> percepts) {
		Percepts = percepts;
	}
	
	public void addPercept(String Percept)
	{
		Percepts.add(0, Percept)  ;
	}

	public java.awt.Image getImage() {
		return Image;
	}

	public void setImage(java.awt.Image image) {
		Image = image;
	}

	public int getType() {
		
		return Type ;
	}

	public void setType(int type) {
		Type = type;
	}

}
