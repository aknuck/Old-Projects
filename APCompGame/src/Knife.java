
public class Knife extends Weapon{
	
	public Knife()
	{
		super("Knife");
		attack = setAttack();
		speed = setSpeed();
	}
	
	protected int setAttack()
	{	
		int a;
		a = (int)(random()*.75+1); //low attack
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
		return 6;
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
			Weapon b = new Knife();
		
			System.out.println(b.getSpeed());
			System.out.println(b.getAttack());
			System.out.println();
		}
	}
	*/

}
