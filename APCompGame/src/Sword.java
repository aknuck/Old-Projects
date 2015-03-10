
public class Sword extends Weapon{
	
	public Sword()
	{
		super("Sword");
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
	
	public int drop()
	{
		return 8;
	}
	/*
	public static void main(String[] args)
	{
		for(int x = 0; x < 10; x++)
		{
			Weapon b = new Sword();
		
			System.out.println(b.getSpeed());
			System.out.println(b.getAttack());
			System.out.println();
		}
	}
	*/
}
