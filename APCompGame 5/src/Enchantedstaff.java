import java.awt.image.BufferedImage;
import java.util.Random;

public class Enchantedstaff extends Weapon{
	
	private String sheet = "itemsSheet";
	
	protected int mAttack, sAttack, mpCost;
	public Enchantedstaff()
	{
		super(name());
		attack = setAttack();
		mAttack = setMDmg();
		sAttack = setsAttack();
		speed = setSpeed();
		range = super.range + 3;
		image = Sprite.getSprite(3,1,sheet);
		largeImage = Sprite.getSprite(3,1,sheet,64);
	}
	protected int setAttack()
	{	
		int a;
		a = (int)(random()); //medium- high attack as compensation for speed
		return a;
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random()*2); //medium-high speed due 
		return s;
	}
	
	protected int setMDmg()
	{
		int m;
		m = (int)(random()*3);
		mpCost = 3;
		return m;
	}
	protected int setsAttack()
	{
		int superA;
		superA = (int)(random())*8-2;
		mpCost = 12;
		return superA;
	}
	
	protected int getmAttack()
	{
		return mAttack;
	}

	protected int getsAttack()
	{
		return sAttack;
	}	

	public int mpCost()
	{
		return mpCost;
	}
	public int drop()
	{
		return 5;
	}
	
	private static String name()
	{
		String name = "";
		Random g = new Random();
		int a = g.nextInt(4)+1;
		
		switch(a) 
		{
		case 1:
			name = "Stone ";
		case 2:
			name = "Fire ";
		case 3:
			name = "Grass ";
		case 4:
			name = "Water ";
		}
		return name + "Wand";
		
	}
	public int randDrop() {
		return 5;
	}
	@Override
	public int mpCost(Player p) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
