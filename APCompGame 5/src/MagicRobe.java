
public class MagicRobe extends Armour{
	
	public MagicRobe()
	{
		super("Magic Robe");
		defense = setDefense();
		attack = setAttack();
		speed = setSpeed();
	}
	
	protected int setDefense()
	{
		int d;
		d = (int) (random()*2/3);
		return d;
	}
	protected int setAttack()
	{
		int a;
		a = (int) (random()*1/3);
		return a;
	}
	protected int setSpeed()
	{
		int s;
		s = (int)(random()+1);
		return s;
	}
	public int drop()
	{
		return 4;
	}
	/*
	public static void main (String[] args)
	{
		
		for (int x = 0; x<10; x++)
		{
			Armour a = new MagicRobe();
			System.out.println(a.getDefense());
			System.out.println(a.getAttack());
			System.out.println(a.getSpeed());
			System.out.println();
		}
			
			
	}
	*/
}
