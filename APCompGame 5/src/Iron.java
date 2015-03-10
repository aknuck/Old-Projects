
public class Iron extends Armour{
	public Iron()
	{
		super("Iron Armour");
		defense = setDefense();
		attack = setAttack();
		speed = setSpeed();
	}
	
	protected int setDefense()
	{
		int d;
		d = (int) (random()*2) + 2;
		return d;
	}
	protected int setAttack()
	{
		int a;
		a = (int) (random()*3/4);
		return a;
	}
	protected int setSpeed()
	{
		int s;
		s = -1*(int)(random()+1);
		return s;
	}
	public int drop()
	{
		return 5;
	}
	/*
	public static void main (String[] args)
	{
		
		for (int x = 0; x<10; x++)
		{
			Armour a = new Iron();
			System.out.println(a.getDefense());
			System.out.println(a.getAttack());
			System.out.println(a.getSpeed());
			System.out.println();
		}
			
		
	}
	*/
}
