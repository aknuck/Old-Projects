import java.util.ArrayList;


public abstract class Player{
	
	final int GAMEDIFFICULTY;
	final DunginGame gm;
	private String name;
	private int hp, mp, atk, def, spd, exp, lvl, x, y, attackType;
	protected ArrayList<Item> inventory;
	private Weapon equippedWeapon = null;
	private Armour equippedArmour = null;
	protected int manaCap;
	protected int hpCap;
	
	// Constructor
	public Player(int dif, String n, int h, int m, DunginGame g)
	{
		GAMEDIFFICULTY = dif;
		gm = g;
		name = n;
		hp = h;
		mp = m;
		atk = 0;
		def = 0;
		spd = 0;
		exp = 0;
		lvl = 1;
		attackType = 1;
		inventory = new ArrayList<Item>();
	}
	
	//".set" methods
	public void setHP(int h)
	{
		hp = h;
	}
	
	public void giveExp(int x){
		exp += x;
		checkLvlUp();
	}
	
	public void setMP(int m)
	{
		mp = m;
	}
	
	public void setAtk(int a)
	{
		atk = a;
	}
	
	public void setDef(int d)
	{
		def = d;
	}
	
	public void setSpd(int s){
		this.spd = s;
	}
	
	//".get" methods
	public int getHP()
	{
		return hp;
	}
	
	public int getMP()
	{
		return mp;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAtk()
	{
		int a = atk;
		if (equippedWeapon != null)
			a += equippedWeapon.getAttack();
		if (equippedArmour != null)
			a += equippedArmour.getAttack();
		return a;
	}
	
	public int getDef()
	{
		return def;
	}
	
	public int getExp()
	{
		return exp;
	}
	
	public int getLvl()
	{
		return lvl;
	}
	
	public int getSpd(){
		return this.spd;
	}
	
	public int getMaxHp(){
		return this.hpCap;
	}
	
	public int getMaxMp(){
		return this.manaCap;
	}
	
	public int currentAttackType()
	{
		return attackType;
	}
	
	public Weapon getWeapon()
	{
		return equippedWeapon;
	}
	
	public void setWeapon(Weapon w){
		if (equippedWeapon != null){
			inventory.add(equippedWeapon);
		}
		equippedWeapon = w;
	}
	
	public Armour getArmour()
	{
		return equippedArmour;
	}
	
	public void setArmor(Armour a){
		if (equippedArmour != null){
			inventory.add(equippedArmour);
		}
		equippedArmour = a;
	}
	
	public void giveItem(Item i){
		inventory.add(i);
	}
	
	public void removeItem(Item i){
		inventory.remove(i);
	}
	
	public void setX(int newX){
		x = newX;
	}
	
	public void setY(int newY){
		y = newY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getLvlUpCost(){
		return (int) Math.pow(1.3,lvl)*10;
	}
	
	public void takeDamage(int amt){
		hp -= amt; //change to account for armor + def
		//also have it show animation for damage being done
	}
	
	public ArrayList<Item> getInventory(){
		if (inventory != null)
			return inventory;
		else{
			return null;
		}
	}
	
	public void checkLvlUp(){
		if (exp >= (int) Math.pow(1.3,lvl)*10){
			lvl++;
			atk++;
			def++;
			spd++;
			gm.addAnim(new LevelUp(this.x,this.y-1));
			System.out.println("lvlup");
			hp = hpCap;
			if (mp < manaCap-20)
				mp += 10;
			else
				mp = manaCap - 10;
			exp -= (int) Math.pow(1.3,lvl-1)*10;
		}
	}
	
	public abstract Animation getAnimation();

	public abstract void updateAnimation();
	
	public abstract void setDirection(String d);
	
	public void fight(Enemy e, Dungeon d){
		Tile [][] map = d.getMap();
		
		if (e.getSpd() > this.spd){
			if (e.getAtk() - this.def > 0) {
				this.hp -= e.getAtk() - this.def;
				this.isAlive();
			}
			else {
				this.hp -= 1;
				this.isAlive();
			}
			if (this.hp >= 0){
				e.setHP(e.getHP()-this.getAtk());
				e.hasAttacked();
			}
			if (e.getHP() <= 0){
				d.kill(e);
				map[e.getX()][e.getY()].removeUnit();
				exp += 2+Math.pow(1.08,e.getLvl()+3);
				checkLvlUp();
			}
		}
		else{
			e.setHP(e.getHP()-this.getAtk());
			if (e.getHP() > 0){
				e.hasAttacked();
				if (e.getAtk() - this.def > 0) {
					this.hp -= e.getAtk() - this.def;
					this.isAlive();
				}
				else {
					this.hp -= 1;
					this.isAlive();
			}
		}
			else if (e.getHP() <= 0){
				d.kill(e);
				map[e.getX()][e.getY()].removeUnit();
				exp += 2+Math.pow(1.08,e.getLvl()+3);
				checkLvlUp();
			}
		}
	}
	
	public boolean isAlive() {
		if(hp <= 0) {
			System.exit(0);
			return false;
		}
		else
			return true;
	}
	
}
