import java.awt.image.BufferedImage;


public class Mage extends Player{
	String sheet = "playerSheet";
	private BufferedImage[] right = {Sprite.getSprite(4, 0, sheet),Sprite.getSprite(5,0,sheet)};
	private Animation rightA = new Animation(right, 25);
	private BufferedImage[] left = {Sprite.getSprite(4, 1,sheet),Sprite.getSprite(5,1,sheet)};
	private Animation leftA = new Animation(left, 25);
	private Animation currentAnimation = rightA;

	public Mage(int dif, String n, int h, int m, DunginGame g) {
		super(dif, n, h, m, g);
		rightA.start();
		leftA.start();
		setAtk(5);
		setDef(3);
		setSpd(4);
		manaCap = 75;
		hpCap = 100;
		setMP(25);
	}
	
	public Animation getAnimation(){
		return currentAnimation;
	}
	
	public void updateAnimation(){
		currentAnimation.update();
	}

	public void setDirection(String d) {
		if (d.equals("right"))
			currentAnimation = rightA;
		else if (d.equals("left"))
			currentAnimation = leftA;
	}
}
