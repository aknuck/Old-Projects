import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Inventory{

	private boolean showHomePressed;
	private DunginGame gm;
	//static GUI images
	private BufferedImage inventoryBackground;
	private BufferedImage pressedHomeButtonImage;
	private BufferedImage inventorySelection;
	private Point location = MouseInfo.getPointerInfo().getLocation();
	private double mouseX = location.getX();
	private double mouseY = location.getY();
	private Item draggedItem = null;
	private Player player;
	private int invSelX=-1, invSelY=-1;

	public Inventory(DunginGame g, Player p){
		this.gm = g;
		this.player = p;
		getImages();
	}

	private void getImages(){
		try{
			this.inventoryBackground = ImageIO.read(new File("imgs/inventoryScreen.png"));
			this.pressedHomeButtonImage = ImageIO.read(new File("imgs/pressedHomeButton.png"));
			this.inventorySelection = ImageIO.read(new File("imgs/inventorySelection.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void update(){
		location = MouseInfo.getPointerInfo().getLocation();
		mouseX = location.getX();
		mouseY = location.getY();
		if (mouseX > 156 && mouseX < 732 && mouseY > 262 && mouseY < 740){
			invSelX = (int) ((mouseX-155)/72);
			invSelY = (int) ((mouseY-302)/72);
		}
	}

	protected ArrayList<Image> getImageList(){
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(new Image(inventoryBackground,0,0));
		if (showHomePressed)
			images.add(new Image(pressedHomeButtonImage,0,548));
		int x = 156;
		int y = 262;
		ArrayList<Item> invLs = player.getInventory();
		for (Item i : invLs){
			images.add(new Image(i.getLargeImage(),x,y));
			if (x < 732)
				x += 72;
			else{
				x = 156;
				y += 72;
			}
		}
		if (player.getWeapon() != null){
			images.add(new Image(player.getWeapon().getImage(),14,222));
		}
		if (invSelX != -1)
			images.add(new Image(inventorySelection,156+(invSelX*72),262+(invSelY*72)));
		if (draggedItem != null)
			images.add(new Image(draggedItem.getImage(),(int)mouseX-5,(int)mouseY-80));

		return images;

	}

	private void homePressed(){
		gm.changeState("menu");
	}
	
	private int getListNum(int xNum, int yNum){
		return xNum+(8*yNum);
	}

	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		//251,280,292,90
		if (mouseX > 156 && mouseX < 732 && mouseY > 262 && mouseY < 740){
			if (player.getInventory().size() > getListNum((int) ((mouseX-155)/72),(int) ((mouseY-302)/72))){
				draggedItem = player.getInventory().get(getListNum((int) ((mouseX-155)/72),(int) ((mouseY-302)/72)));
				player.removeItem(draggedItem);
			}
			else{
				draggedItem = null;
			}
		}
		if (0 < mouseX && mouseX < 88 && mouseY > 550 && mouseY < 640)
			showHomePressed = true;
		if(mouseX > 15 && mouseX < 47 && mouseY > 265 && mouseY < 300 && draggedItem instanceof Weapon){
			player.setWeapon(null);
		}
		else
			showHomePressed = false;
	}

	public void mouseReleased(MouseEvent e) {
		showHomePressed = false;

		if (0 < mouseX && mouseX < 88 && mouseY > 550 && mouseY < 640)
			homePressed();
		if (draggedItem != null){
			if(mouseX > 15 && mouseX < 47 && mouseY > 265 && mouseY < 300 && draggedItem instanceof Weapon){
				player.setWeapon((Weapon) draggedItem);
			}
			else {//if(mouseX > 156 && mouseX < 732 && mouseY > 262 && mouseY < 740){
				player.giveItem(draggedItem);
			}
			draggedItem = null;
			
		}

	}

}