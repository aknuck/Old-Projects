import java.awt.image.BufferedImage;


public class Enchantedbow extends Weapon{
	
	private String sheet = "itemsSheet";
	
	public Enchantedbow()
	{
		super("Longbow");
		attack = setAttack();
		speed = setSpeed();
		range = super.range + 1;
		image = Sprite.getSprite(0,0,sheet);
		largeImage = Sprite.getSprite(0,0,"itemsLargeSheet",64);
	}
	protected int setAttack()
	{	
		int a;
		a = (int)(random()*4+2); //medium- high attack as compensation for speed
		return a;
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random()*1.3+1); //medium-low speed due to draw
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
			Weapon b = new Longbow();
		
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
