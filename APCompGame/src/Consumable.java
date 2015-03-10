
public abstract class Consumable extends Item{

	public Consumable(String n)
	{
		super(n);
	}
	
	public abstract int use();

	public abstract int drop();
	
}
