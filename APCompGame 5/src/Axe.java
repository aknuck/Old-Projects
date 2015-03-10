import java.awt.image.BufferedImage;


public class Axe extends Weapon{
	
	private String sheet = "itemsSheet";
	
	public Axe()
	{
		super("Axe");
		attack = setAttack();
		speed = setSpeed();
		image = Sprite.getSprite(2,2,sheet);
		largeImage = Sprite.getSprite(2,2,"itemsLargeSheet",64);
	}
	protected int setAttack()
	{	
		int a;
		a = (int)(random()*5+2); //high attack as compensation for speed
		return a;
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random()*.75+1); //low speed due to high attack
		return s;
	}
	
	public int drop()
	{
		return 7;
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
	
}
