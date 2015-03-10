import java.awt.image.BufferedImage;
import java.util.Random;

public class Staff extends Weapon{
	
	private String sheet = "itemsSheet";
	
	protected int mAttack, sAttack, weakmpCost, strongmpCost;
	public Staff()
	{
		super(name());
		attack = setAttack();
		mAttack = setMDmg();
		sAttack = setsAttack();
		speed = setSpeed();
		range = super.range + 6;
		weakmpCost = 3;
		strongmpCost = 12;
		image = Sprite.getSprite(2,0,sheet);
		largeImage = Sprite.getSprite(2,0,"itemsLargeSheet",64);
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
		return m;
	}
	protected int setsAttack()
	{
		int superA;
		superA = (int)(random())*8-2;
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

	public int mpCost(Player p)
	{
		if (p.currentAttackType() == 1)
			return 0;
		else if(p.currentAttackType() == 1)
			return weakmpCost;
		else if(p.currentAttackType() == 2)
			return strongmpCost;
		return 0;
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

	
}
