
public class Spear extends Weapon{
	public Spear()
	{
		super("Spear");
		attack = setAttack();
		speed = setSpeed();
		range = super.range + 1;
	}
	protected int setAttack()
	{	
		int a;
		a = (int)(random()*3+1); //medium- high attack as compensation for speed
		return a;
	}
	
	protected int setSpeed()
	{
		int s;
		s = (int)(random()*1.50+1); //medium-low speed due to cumbersomness
		return s;
	}
	
	public int drop()
	{
		return 5;
	}
	
	public static void main(String[] args)
	{
		for(int x = 0; x < 10; x++)
		{
			Weapon b = new Spear();
		
			System.out.println(b.getSpeed());
			System.out.println(b.getAttack());
			System.out.println();
		}
	}
	
}
