import java.awt.image.BufferedImage;


public class Image {
	private BufferedImage image;
	private int x,y;

	public Image(BufferedImage i, int xpos, int ypos){
		this.image = i;
		this.x = xpos;
		this.y = ypos;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public BufferedImage getImage(){
		return image;
	}
}
