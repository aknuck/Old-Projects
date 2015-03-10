
public class HealthPotion extends Consumable{

	public HealthPotion()
	{
		super("Health Potion");
	}
	
	public int use()
	{
		return 10; //adds 10 to current health
	}

	public int drop()
	{
		return 9;
	}
	
	
}
