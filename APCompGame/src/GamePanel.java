import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
	private static final int PWIDTH = 800;
	private static final int PHEIGHT = 600;
	
	private Thread animator;
	private volatile boolean running = false;
	
	private volatile boolean gameOver = false;
	
	
	public GamePanel(){
				
		setBackground(Color.white);
		setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
