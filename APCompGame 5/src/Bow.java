import java.awt.image.BufferedImage;


public class Bow extends Weapon{

	private String sheet = "itemsSheet";
	
	public Bow()
	{
		super("Bow");
		attack = setAttack();
		speed = setSpeed();
		range = super.range + 3;
		image = Sprite.getSprite(2,0,sheet);
		largeImage = Sprite.getSprite(2,0,"itemsLargeSheet",64);
	}
	protected int setAttack()
	{	
		int a;
		a = (int)(random()*2.5+1.5); //medium- high attack as compensation for speed
		return a;
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random()*1.75+1); //medium-low speed due to draw
		return s;
	}
	public int drop()
	{
		return 8;
	}
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
	
	/*
	public static void main(String[] args)
	{
		for(int x = 0; x < 10; x++)
		{
			Weapon b = new Bow();
		
			System.out.println(b.getSpeed());
			System.out.println(b.getAttack());
			System.out.println();
		}
	}
	*/
}
