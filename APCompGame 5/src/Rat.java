import java.awt.image.BufferedImage;
import java.util.Random;


public class Rat extends Enemy {
	
	String sheet = "stoneDungeonSheet";
	private BufferedImage[] right = {Sprite.getSprite(0,3,sheet), Sprite.getSprite(1,3,sheet)}; //get images from sprite sheet
	private BufferedImage[] left = {Sprite.getSprite(2,3,sheet), Sprite.getSprite(3,3,sheet)};
	private Animation rightA = new Animation(right, 25); //animation of facing right
	private Animation leftA = new Animation(left, 25); //animation of facing left
	private Animation currentAnimation = rightA; //the animation that is currently being used
	
	//Common enemy
	// - Stone Dungeon
	// - Low stats
    public Rat(int l,int d, int x, int y, DunginGame gm) {
    	super(l,d,x,y,(int)((0.25*(Math.pow(l,1.6))+(2*d)+2))+5,gm);
    	name = "rat";
    	
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
    
	 public void move(Dungeon d, Player p){
		 if (!hasAttacked){
			Tile[][] map = d.getMap();
			Random generator = new Random();
			int num;
			boolean finding = true;
			while (finding){
				num = generator.nextInt(4)+1;
				switch(num){
				case 1: //up
					if (map[x][y-1].getType().equals("floor") && !map[x][y-1].hasEnemy() && !map[x][y-1].hasPlayer()){
						map[x][y].removeUnit();
						map[x][y-1].setUnit(this);
						finding = false;
						y --;
					}
					else if (map[x][y-1].hasPlayer()){
						map[x][y-1].getPlayer().takeDamage(atk);
						finding = false;
					}
					break;
				case 2: //right
					if (map[x+1][y].getType().equals("floor") && !map[x+1][y-1].hasEnemy() && !map[x+1][y-1].hasPlayer()){
						map[x][y].removeUnit();
						map[x+1][y].setUnit(this);
						finding = false;
						x ++;
					}
					else if (map[x+1][y].hasPlayer()){
						map[x+1][y].getPlayer().takeDamage(atk);
						finding = false;
					}
					break;
				case 3: //down
					if (map[x][y+1].getType().equals("floor") && !map[x][y+1].hasEnemy() && !map[x][y+1].hasPlayer()){
						map[x][y].removeUnit();
						map[x][y+1].setUnit(this);
						finding = false;
						y ++;
					}
					else if (map[x][y+1].hasPlayer()){
						map[x][y+1].getPlayer().takeDamage(atk);
						finding = false;
					}
					break;
				case 4: //left
					if (map[x-1][y].getType().equals("floor") && !map[x-1][y].hasEnemy() && !map[x-1][y].hasPlayer()){
						map[x][y].removeUnit();
						map[x-1][y].setUnit(this);
						finding = false;
						x --;
					}
					else if (map[x-1][y].hasPlayer()){
						map[x-1][y].getPlayer().takeDamage(atk);
						finding = false;
					}
					break;
				}
			}
		}
	 }
}