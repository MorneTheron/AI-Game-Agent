import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * @author Johhny
 *
 */
public class GameMap {

    private int dx;
    private int dy;
    private int x = 0;
    private int Row = 0 ;
    private int y = 0;
    private int Col = 0;
    private int w;
    private int h;
    private int BlockSize = 25;
    private Image image;
    private long lastPressProcessed = 0;
    private int GoalRow = -1 ; 
    private int GoalCol = -1 ; 
    private ArrayList<String> PerceptSequence ;
    
    
    private Blocks[][] GameMap ;

    public GameMap() {

    	GameMap = new Blocks[30][30] ;
    	PerceptSequence = new ArrayList<String>();
    	GenerateMap("TrainMap.txt") ;
        loadPlayer();
    }

    private void loadPlayer() {
        
        ImageIcon ii = new ImageIcon("data/agent.png");
        image = ii.getImage(); 
        
        
        w = image.getWidth(null);
        h = image.getHeight(null);
    }
    
    private void GenerateMap(String TrainMap)
    {
    	
    	File file = new File("data/TrainMap.txt"); 
    	String st; 
    	BufferedReader br;
    	ArrayList<String> Percepts = new ArrayList<String>();
    	Percepts.add("none") ;
		try {
			br = new BufferedReader(new FileReader(file));
			for(int r = 0 ; r <30; r++)
			{
			if ((st = br.readLine()) != null) 
			  {
			    System.out.println(st); 
			    for(int c = 0 ; c < 30 ; c++)
	    		{
			    	switch (st.charAt(c)) {
					case '8':
						GameMap[r][c] = new Blocks(false,8,0,Percepts,"data/wall.png");
						break;
					case '2':
						GameMap[r][c] = new Blocks(false,2,-100,Percepts,"data/enimy.png");
						break;
					case '3':
						GameMap[r][c] = new Blocks(false,3,-1000,Percepts,"data/enimy.png");
						break;
					case '4':
						GameMap[r][c] = new Blocks(false,4,1000,Percepts,"data/treasure.png");
						GoalCol = c ;
						GoalRow = r ;
						break;

					default:
						GameMap[r][c] = new Blocks(false,0,-1,Percepts,"data/empty.png");
						break;
					}
	    			
	    		}
			  }
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		for(int r = 0 ; r <30; r++)
		{
		    for(int c = 0 ; c < 30 ; c++)
    		{
		    	switch (GameMap[r][c].getType()) {
				case '2':
					UpdatePercepts(r,c,"feeling weird","toxic");
					break;
				case '3':
					UpdatePercepts(r,c,"somewhat smelly", "horrible smell");
					break;
				case '4':
					UpdatePercepts(r,c,"sparkling","very shiny");
				default:
					//nothing
					break;
				}
    			
    		}
		}
    }

    public int getX() {
        
        return x;
    }

    public int getY() {
        
        return y;
    }
    
    public int getWidth() {
        
        return w;
    }
    
    public int getHeight() {
        
        return h;
    }    

    public Image getImage() {
        
        return image;
    }
    
    public int getGoalRow() {
		return GoalRow;
	}

	public void setGoalRow(int goalRow) {
		GoalRow = goalRow;
	}

	public int getGoalCol() {
		return GoalCol;
	}

	public void setGoalCol(int goalCol) {
		GoalCol = goalCol;
	}
	
	private void UpdatePercepts(int R, int C, String Percept1, String Percept2 )
	{
			for(int r = R - 1; r< R + 1; r++)
				{
						for(int c = C - 1; c< C + 1; c++)
						{
							if(r > 0 && r<26)
							{
								if(c > 0 && c<29)
								{
									GameMap[r][c].addPercept(Percept1);
								}
							}
						}
				}
			
			for(int r = R - 2; r< R + 2; r++)
				{
						for(int c = C - 2; c< C + 2; c++)
						{
							if(r > 0 && r<26)
							{
								if(c > 0 && c<29)
								{
									System.out.println("r " + r + " c " + c);
									GameMap[r][c].addPercept(Percept2);
								}
							}
						}
				}
	}
	
	public boolean DetermineGoalState()
	{
		if(Col == GoalCol && Row == GoalRow)
		{
		return true;
		}
		return false;
	}

	public void keyPressed(KeyEvent e) {
        
		if(DetermineGoalState() == false)
		{
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	if(DetectCollision(Row, Col - 1)  == false)
        	{
        	x = x - 30;	
        	Col = Col - 1;
        	}
            
        }

        if (key == KeyEvent.VK_RIGHT) {
        	if(DetectCollision(Row, Col + 1) == false)
        	{
            x = x + 30;
            Col = Col +1;
        	}
        }

        if (key == KeyEvent.VK_UP) {
        	if(DetectCollision(Row - 1, Col)  == false)
        	{
            y = y -30;
            Row = Row - 1;
        	}
        }

        if (key == KeyEvent.VK_DOWN) {
        	if(DetectCollision(Row + 1, Col)  == false)
        	{
            y = y + 30;
            Row = Row + 1;
        	}
        }
		}

		PerceptSequence = GameMap[Row][Col].getPercepts() ;
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    public boolean DetectCollision(int Row, int Col)
    {
    	if(Row < 0 | Row >26 | Col < 0 | Col>29)
    	{
    		return true;
    	}
    	if(GameMap[Row][Col].getType() == 8 )
    	{
    		return true;
    	}
		return false;
    	
    }

	public Blocks[][] getGameMap() {
		return GameMap;
	}

	public void setGameMap(Blocks[][] gameMap) {
		GameMap = gameMap;
	}

	public ArrayList<String> getPerceptSequence() {
		return PerceptSequence;
	}

	public void setPerceptSequence(ArrayList<String> perceptSequence) {
		PerceptSequence = perceptSequence;
	}
	
	
    
    
}