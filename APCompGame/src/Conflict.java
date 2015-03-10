import java.awt.image.BufferedImage;

//Basically a class for an animation that runs only once
public class Conflict extends SingleAnimation{
	private int speed = 1;
	String sheet = "singleAnimationsSheet";
	private BufferedImage[] images = {Sprite.getSprite(0, 0,sheet),Sprite.getSprite(1,0,sheet),Sprite.getSprite(0,1,sheet),Sprite.getSprite(1,1,sheet)}; //get sprites
	private Animation animation = new Animation(images, speed); //set animation. This one is quicker than player/enemy animations so it's at speed
	int animationCount = 0; //keeps track of how many frames of the animation have already been shown
	
	public Conflict(double x, double y){
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
