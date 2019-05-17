
public class Node {
	
    public final String value;
    public double g_scores;
    public final double h_scores;
    public double f_scores = 0;
    public Edge[] adjacencies;
    public Node parent;
    public int r;
    public int c;
    public boolean Blocked;

    public Node(int r, int c,String val, double hVal, boolean Blocked){
        this.r = r ;
        this.c = c ;
    	value = val;
        h_scores = hVal;
        this.Blocked = Blocked ;
    }

    public String toString(){
        return value;
    }

}
