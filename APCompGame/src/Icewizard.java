import java.awt.image.BufferedImage;

//BOSS FOR WATER DUNGEON
public class Icewizard extends Enemy{
	//Common Enemy
		// - Stone Dungeon
		// - High HP, low DEF
		String sheet = "waterDungeonSheet";
		private BufferedImage[] right = {Sprite.getSprite(0,7,sheet), Sprite.getSprite(1,7,sheet)}; //get images from sprite sheet
		private BufferedImage[] left = {Sprite.getSprite(2,7,sheet), Sprite.getSprite(3,7,sheet)};
		private Animation rightA = new Animation(right, 25); //animation of facing right
		private Animation leftA = new Animation(left, 25); //animation of facing left
		private Animation currentAnimation = rightA; //the animation that is currently being used
		
		//Common enemy
		// - Stone Dungeon
		// - Low stats
	    public Icewizard(int l,int d, int x, int y, DunginGame gm) {
	    	super(l,d,x,y,(int)((0.25*(Math.pow(l,1.6))+(2*d)+2))+5,gm);
	    	name = "Ice Wizard";
	    	
	    	rightA.start(); //start updating the animations as the game updates
	    	leftA.start();
	    	
			setHP((int)((0.25*(Math.pow(l,1.6))+(2*d)+2))+5);
	    	setAtk((int)(0.25*(Math.pow(l,1.6))+(2*d)+2));
	    	setDef((int)(0.25*(Math.pow(l,1.6))+(2*d)+2)/2);
	    	setSpd((int)(0.25*(Math.pow(l,1.6))+(2*d)+2)/2);
	    }

		 public Animation getAnimation(){
			 if (currentAnimation != null)
				 return currentAnimation;
			 else
				 return null;
	    }
		 
		 public void updateAnimation(){
			 currentAnimation.update();
		 }
		 
}
