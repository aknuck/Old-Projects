import java.awt.image.BufferedImage;


public class Longsword extends Weapon { //starts a new tier of weapon
	
	private String sheet = "itemsSheet";
	
	public Longsword()
	{
		super("Longsword");
		attack = setAttack();
		speed = setSpeed();
		image = Sprite.getSprite(1,2,sheet);
		largeImage = Sprite.getSprite(1,2,"itemsLargeSheet",64);
	}
	
	protected int setAttack()
	{	
		int a;
		a = (int)(random())*5+2; //medium-high attack
		return a;
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random())*5+2; //medium-high speed
		return s;
	}
	public int drop()
	{
		return 4;
	}
	/*
	public static void main(String[] args)
	{
		for(int x = 0; x < 10; x++)
		{
			Weapon b = new Longsword();
		
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
