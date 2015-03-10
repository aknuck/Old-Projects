
public abstract class Armour extends Item {
	protected int defense, speed, attack;
	
	public Armour(String n)
	{
		super(n);
	}
	
	protected abstract int setDefense();
	protected abstract int setSpeed();
	protected abstract int setAttack();
	
	public int getDefense()
	{
		return defense;
	}
	public int getSpeed()
	{
		return speed;
	}
	public int getAttack()
	{
		return attack;
	}

}