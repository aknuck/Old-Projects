import java.awt.image.BufferedImage;


public class Dagger extends Weapon {
	
	private String sheet = "itemsSheet";
	
	
	public Dagger()
	{
		super("Dagger");
		attack = setAttack();
		speed = setSpeed();
		image = Sprite.getSprite(0,2,sheet);
		largeImage = Sprite.getSprite(0,2,"itemsLargeSheet",64);
	}
	
	protected int setAttack()
	{	
		int a;
		a = (int)(random())*2; //low- medium attack
		
		if(a == 0) //ensures no zero attack
			a = 2;
		return a;
	
	}
	protected int setSpeed()
	{
		int s;
		s = (int)(random())*8+2; //very high speed
		return s;
	}
	
	public int drop()
	{
		return 5;
	}
	
	public boolean isCrit()
	{
		double a = random();
		
		if(a <= -1 || a >= 1)
			return true;
		else 
			return false;
	}
	/*
	public static void main(String[] args)
	{
		for(int x = 0; x < 10; x++)
		{
			Weapon b = new Dagger();
		
			System.out.println(b.getSpeed());
			System.out.println(b.getAttack());
			System.out.println();
		}
	}
	 */

	@Override
	public int randDrop() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int mpCost(Player p) {
		// TODO Auto-generated method stub
		return 0;
	}
}
