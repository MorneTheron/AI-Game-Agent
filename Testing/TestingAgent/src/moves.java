
public class moves {
	
	private boolean allowed;
	private int Row ;
	private int Col ;
	
	public moves()
	{
		allowed = true ;
		Row = 0 ;
		Col = 0 ;
	}
	
	public moves(int r , int c)
	{
		allowed = true ;
		Row = r ;
		Col = c ;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}

	public int getRow() {
		return Row;
	}

	public void setRow(int row) {
		Row = row;
	}

	public int getCol() {
		return Col;
	}

	public void setCol(int col) {
		Col = col;
	}
	
	

}
