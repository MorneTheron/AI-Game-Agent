import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, Runnable {
	
	/**
	 * This class is responsible for the game map, the display of player and Agent games
	 */
	private static final long serialVersionUID = 1L;
    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Timer timer;
    private Image star;
    private Thread animator;
    private int x, y;
    
    private GameMap gMap;

	public Game() {
		
		initBoard();
	}
	
	private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at
                    = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
    }
	
	    private void loadImage() {
	
	        ImageIcon ii = new ImageIcon("data/character.jpg");
	        star = ii.getImage();
	    }
	
	   private void initBoard() {

		   	addKeyListener(new TAdapter());
	        setBackground(Color.BLACK);
	        setFocusable(true);
	        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

	        loadImage();
	        
	        x = INITIAL_X;
	        y = INITIAL_Y;	     
	        
	        
	        gMap = new GameMap();
	       
	        timer = new Timer(DELAY, this);
	        timer.start();
	        
	    }
	   
	    private void doDrawing(Graphics g) {
	        
	        Graphics2D g2d = (Graphics2D) g;

	        g2d.drawImage(gMap.getImage(), gMap.getX(),gMap.getY(), this);
	    }
	    
	
	    @Override
	    public void addNotify() {
	        super.addNotify();

	        animator = new Thread(this);
	        animator.start();
	    }
	
	 @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        //drawDonut(g);
	        doDrawing(g);
	        Toolkit.getDefaultToolkit().sync();
	       // drawStar(g);
	    }
	 
	    private void drawStar(Graphics g) {

	        g.drawImage(star, x, y, this);
	        Toolkit.getDefaultToolkit().sync(); //synchronises the painting on systems that buffer graphics events.
	    }
	
	
	    private void cycle() {

	        x += 1;
	        y += 1;

	        if (y > B_HEIGHT) {

	            y = INITIAL_Y;
	            x = INITIAL_X;
	        }
	    }

	@Override
		public void run() 
		{
			
	    long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		step();
		
	}
	
private void step() {
        
	gMap.move();
        
        repaint(gMap.getX()-1, gMap.getY()-1, 
        		gMap.getWidth()+2, gMap.getHeight()+2);     
  
    }    

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	gMap.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	gMap.keyPressed(e);
        }
    }

}