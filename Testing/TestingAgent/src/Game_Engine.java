import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game_Engine extends JFrame 
{
	/**
	 * Launch game 
	 */
	private static final long serialVersionUID = 1L;

	public Game_Engine() 
	{
        initUI();
	}

	private void initUI() 
	{
	        add(new Game());

	       // setSize(1500, 1000);using a panel as the content in the frame, it will be scaled according to its size
	        setResizable(false);
	        pack();

	        setTitle("AI Learning Game Agent");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	}
	
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(() -> 
        {
        	Game_Engine ge = new Game_Engine();
            ge.setVisible(true);
        });
    }
	
	

}
