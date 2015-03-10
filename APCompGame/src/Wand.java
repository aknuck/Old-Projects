import java.awt.image.BufferedImage;
import java.util.Random;
public class Wand extends Weapon{
	
	private String sheet = "itemsSheet";
	
	private int mAttack, sAttack, weakmpCost, strongmpCost;
	public Wand()
	{
		super(name());
		attack = setAttack();
		mAttack = setMDmg();
		sAttack = setsAttack();
		weakmpCost = 2;
		strongmpCost = 10;
		speed = setSpeed();
		range = super.range + 3;
		image = Sprite.getSprite(0,1,sheet);
		largeImage = Sprite.getSprite(0,1,"itemsLargeSheet",64);
	}
	protected int setAttack()
	{	
		int a;
		a = (int)(random() * 2/3); //medium- high attack as compensation for speed
		return a;
		
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random()*2+3); //medium-high speed due 
		return s;
	}
	
	protected int setMDmg()
	{
		int m;
		m = (int)(random())*2;
		return m;
	}
	protected int setsAttack()
	{
		int superA;
		superA = (int)(random())*8;
		return superA;
	}
	
	protected int getMAttack()
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
			return 1;
		else if(p.currentAttackType() == 2)
			return weakmpCost;
		else if(p.currentAttackType() == 3)
			return strongmpCost;
		return 0;
	}
	public int randDrop()
	{
		return (int)random();
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
	
	public int drop()
	{
		return 6;
	}
	/*
	public static void main(String[] args)
	{
		for(int x = 0; x < 10; x++)
		{
			Weapon b = new Wand();
		
			System.out.println(b.getSpeed());
			System.out.println(b.getAttack());
			System.out.println(b.getMDmg());
			System.out.println(b.getsAttack());
			System.out.println();
		}
	}
	*/

}
