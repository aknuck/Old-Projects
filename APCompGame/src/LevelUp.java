import java.awt.image.BufferedImage;


public class LevelUp extends SingleAnimation{

	private int speed = 3;
	String sheet = "singleAnimationsSheet";
	private BufferedImage[] images = {Sprite.getSprite(2, 0,sheet),Sprite.getSprite(3,0,sheet),Sprite.getSprite(4,0,sheet),Sprite.getSprite(5,0,sheet),Sprite.getSprite(6,0,sheet),Sprite.getSprite(7,0,sheet)}; //get sprites
	private Animation animation = new Animation(images, speed); //set animation. This one is quicker than player/enemy animations so it's at speed
	int animationCount = 0; //keeps track of how many frames of the animation have already been shown
	
	public LevelUp(double x, double y){
		super(x,y);
		animation.start();
	}
	
	//returns the animation itself
	public Animation getAnimation() {
		return animation;
	}
	
	//updates the animation
	public void updateAnimation() {
		if (animationCount < images.length*speed)
			animation.update();
		animationCount ++;
	}
	
	//checks if running
	public boolean animationRunning(){
		return (animationCount < images.length*speed);
	}

}
