import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Menu{

	private double mouseX = 0;
	private double mouseY = 0;
	private double logoBounce = 1;
	private double logoBounceDirection = 0.5; //0.5 = down, -0.5 = up
	private boolean showStartPressed;
	private DunginGame gm;
	//static GUI images
	private BufferedImage dunginLogo;
	private BufferedImage menuImage;
	private BufferedImage pressedStartButtonImage;
	private String pickedClass = "knight";

	public Menu(DunginGame g){
		this.gm = g;
		getImages();
	}
	
	private void getImages(){
		try{
			this.dunginLogo = ImageIO.read(new File("imgs/dunginLogo.png"));
			this.menuImage = ImageIO.read(new File("imgs/menuScreen.png"));
			this.pressedStartButtonImage = ImageIO.read(new File("imgs/pressedStartButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void update(){
		if (logoBounce < 25 && logoBounce > 0){
			logoBounce += logoBounceDirection;
		}
		else{
			logoBounceDirection = -(logoBounceDirection);
			logoBounce += logoBounceDirection;
		}
	}

	protected ArrayList<Image> getImageList(){
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(new Image(menuImage,0,0));
		images.add(new Image(dunginLogo,135,(int) (20+logoBounce)));
		if (showStartPressed)
			images.add(new Image(pressedStartButtonImage,252,280));
		
		return images;
		
	}
	
	public void setCharacter(String c){
		if (c.equals("knight") || c.equals("mage") || c.equals("archer"))
			pickedClass = c;
	}
	
	private void startPressed(){
		Game game = null;
		if (pickedClass.equals("knight"))
			game = new Game(2,new Knight(2,"john",100,10, gm), gm);
		else if (pickedClass.equals("mage"))
			game = new Game(2,new Mage(2,"john",100,10, gm), gm);
		else if (pickedClass.equals("archer"))
			game = new Game(2,new Archer(2,"john",100,10, gm), gm);
		gm.changeState("game");
		new Thread(game).start();
	}

	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		//251,280,292,90
		if (251 < mouseX && mouseX < (251+292) && mouseY > 280 && mouseY < (292+90))
			showStartPressed = true;
		else
			showStartPressed = false;
	}

	public void mouseReleased(MouseEvent e) {
		showStartPressed = false;
		
		if (251 < mouseX && mouseX < (251+292) && mouseY > 280 && mouseY < (292+90))
			startPressed();

	}

}