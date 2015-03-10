public class DemoWeapon extends Weapon{
	
	public DemoWeapon()
	{
		super("demo weapon");
		attack = setAttack();
		speed = setSpeed();
	}
	
	protected int setAttack()
	{	
		int a;
		a = (int)(random())*2+1; //medium attack
		return a;
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random())*2+1; //medium speed
		return s;
	}
	
	public int randDrop()
	{
		return (int)random();
	}

}