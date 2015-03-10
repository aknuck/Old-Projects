import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


public abstract class Enemy{
	
	protected String name;
	protected int hp, atk, def, lvl, spd; //health, attack, defense, level, speed
	protected int dif;
	protected Sprite sprt;
	protected int x;
	protected int y;
	protected int maxHp;
	protected boolean hasAttacked = false;
	protected DunginGame gm;
	
	// Constructor
	public Enemy(int level, int difficulty, int x, int y, int m, DunginGame g)
	{
		this.gm = g;
		this.x = x;
		this.y = y;
		this.lvl = level;
		this.dif = difficulty;
		this.maxHp = m;
	}
	
	//".set" methods
	public void setHP(int h)
	{
		hp = h;
	}
	
	public void hasAttacked(){
		hasAttacked = true;
	}
	
	public boolean getAttacked(){
		return hasAttacked;
	}
	
	public void setAtk(int a)
	{
		atk = a;
	}
	
	public void setDef(int d)
	{
		def = d;
	}
	
	public void setLvl(int l)
	{
		lvl = l;
	}
	
	public void setSpd(int s){
		this.spd = s;
	}
	
	
	//".get" methods
	public int getHP()
	{
		return hp;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAtk()
	{
		return atk;
	}
	
	public int getDef()
	{
		return def;
	}

	public int getLvl()
	{
		return lvl;
	}
	
	public int getDifficulty(){
		return dif;
	}
	
	public int getSpd(){
		return this.spd;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public abstract Animation getAnimation();

	public abstract void updateAnimation();
	
	public void move(Dungeon d, Player p){
		Tile[][] map = d.getMap();
		
		if (!hasAttacked){
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
							p.fight(this, d);
							gm.addAnim(new Conflict(x-0.5,y));
							
						}
						else if (!map[x-1][y].hasEnemy()){
							map[x][y].removeUnit();
							map[x-1][y].setUnit(this);
							x --;
						}
					}
					else if (x <= p.getX() && map[x+1][y].getType().equals("floor")){
						if (map[x+1][y].hasPlayer()){
							p.fight(this, d);
							gm.addAnim(new Conflict(x+0.5,y));
						}
						else if (!map[x+1][y].hasEnemy()){
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
							p.fight(this, d);
							gm.addAnim(new Conflict(x,y-0.5));
						}
						else if (!map[x][y-1].hasEnemy()){
							map[x][y].removeUnit();
							map[x][y-1].setUnit(this);
							y --;
						}
					}
					else if (y <= p.getY() && map[x][y+1].getType().equals("floor")){
						if (map[x][y+1].hasPlayer()){
							p.fight(this, d);
							gm.addAnim(new Conflict(x,y+0.5));
						}
						else if (!map[x][y+1].hasEnemy()){
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
						else if (!map[x-1][y].hasEnemy()){
							map[x][y].removeUnit();
							map[x-1][y].setUnit(this);
							x --;
						}
					}
					else if (x <= p.getX() && map[x+1][y].getType().equals("floor")){
						if (map[x+1][y].hasPlayer()){
							p.fight(this, d);
							gm.addAnim(new Conflict(x+0.5,y));
						}
						else if (!map[x+1][y].hasEnemy()){
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
		hasAttacked = false;
		
 }

	public int getMaxHP() {
		return maxHp;
	}
}
