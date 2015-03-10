import java.awt.image.BufferedImage;
import java.util.Random;


public class Skeleton extends Enemy {

	//Common Enemy
	// - Stone Dungeon
	// - High ATK, low HP
	String sheet = "stoneDungeonSheet";
	private BufferedImage[] right = {Sprite.getSprite(0,4,sheet), Sprite.getSprite(1,4,sheet)}; //get images from sprite sheet
	private BufferedImage[] left = {Sprite.getSprite(2,4,sheet), Sprite.getSprite(3,4,sheet)};
	private Animation rightA = new Animation(right, 25); //animation of facing right
	private Animation leftA = new Animation(left, 25); //animation of facing left
	private Animation currentAnimation = rightA; //the animation that is currently being used

	//Common enemy
	// - Stone Dungeon
	// - Low stats
	public Skeleton(int l,int d, int x, int y, DunginGame gm) {
		super(l,d,x,y,(int)((0.25*(Math.pow(l,1.6))+(2*d)+2))+5,gm);
		name = "skeleton";

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
		Tile[][] map = d.getMap();

		if (!((x-p.getX())*(x-p.getX())+(y-p.getY())*(y-p.getY()) < 25)){
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
		else{
			String dir;
			if (Math.abs(x-p.getX()) > Math.abs(y-p.getY())){
				dir = "x";
			}
			else{
				dir = "y";
			}
			if (dir.equals("x")){
				if (x > p.getX() && map[x-1][y].getType().equals("floor")){
					if (map[x-1][y].hasPlayer()){
						//fight
					}
					else{
						map[x][y].removeUnit();
						map[x-1][y].setUnit(this);
						x --;
					}
				}
				else if (x <= p.getX() && map[x+1][y].getType().equals("floor")){
					if (map[x+1][y].hasPlayer()){
						//fight
					}
					else{
						map[x][y].removeUnit();
						map[x+1][y].setUnit(this);
						x ++;
					}
				}
				else{
					dir = "y";
				}
			}
			if (dir.equals("y")){
				if (y > p.getY() && map[x][y-1].getType().equals("floor")){
					if (map[x][y-1].hasPlayer()){
						//fight
					}
					else{
						map[x][y].removeUnit();
						map[x][y-1].setUnit(this);
						y --;
					}
				}
				else if (y <= p.getY() && map[x][y+1].getType().equals("floor")){
					if (map[x][y+1].hasPlayer()){
						//fight
					}
					else{
						map[x][y].removeUnit();
						map[x][y+1].setUnit(this);
						y ++;
					}
				}
				else{
					dir = "x";
				}
			}
			if (dir.equals("x")){
				if (x > p.getX() && map[x-1][y].getType().equals("floor")){
					if (map[x-1][y].hasPlayer()){
						map[x][y].removeUnit();
						map[x-1][y].setUnit(this);
						x --;
					}
				}
				else if (x <= p.getX() && map[x+1][y].getType().equals("floor")){
					if (map[x+1][y].hasPlayer()){
						//fight
					}
					else{
						map[x][y].removeUnit();
						map[x+1][y].setUnit(this);
						x ++;
					}
				}
				else{
					dir = "y";
				}
			}
		}

	}
}

