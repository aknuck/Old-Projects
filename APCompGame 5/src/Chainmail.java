
public class Chainmail extends Armour{

	public Chainmail()
	{
		super("Chainmail");
		defense = setDefense();
		attack = setAttack();
		speed = setSpeed();
	}
	
	protected int setDefense()
	{
		int d;
		d = (int) (random()*2) +1;
		return d;
	}
	protected int setAttack()
	{
		int a;
		a = (int) (random()*2/3);
		return a;
	}
	protected int setSpeed()
	{
		int s;
		s = 0;
		return s;
	}
	public int drop()
	{
		return 6;
	}
	/*
	public static void main (String[] args)
	{
		
		for (int x = 0; x<10; x++)
		{
			Armour a = new Chainmail();
			System.out.println(a.getDefense());
			System.out.println(a.getAttack());
			System.out.println();
		}
			
			
	}
	*/
}
