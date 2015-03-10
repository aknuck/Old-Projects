
public class TShirt extends Armour{
	
	public TShirt()
	{
		super("T-Shirt");
		defense = setDefense();
		attack = setAttack();
		speed = setSpeed();
	}
	
	protected int setDefense()
	{
		int d;
		d = (int) (random()*1/3);
		return d;
	}
	protected int setAttack()
	{
		return 0;
	}
	protected int setSpeed()
	{
		return 0;
	}	
	
	public int drop()
	{
		return 0;
	}
}
