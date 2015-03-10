
public class SpeedBoost extends Consumable{
	

	public SpeedBoost()
	{
		super("Speed Boost");
	}
	
	public int use()
	{
		return 10; //adds 10 to current speed for 10 seconds
	}

	public int drop()
	{
		return 6;
	}
	
	
}


