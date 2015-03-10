
public class ManaPotion extends Consumable{

	public ManaPotion()
	{
		super("Mana Potion");
	}
	
	public int use()
	{
		return 10; //adds 10 to current mana
	}

	public int drop()
	{
		return 8;
	}
	
	
}
