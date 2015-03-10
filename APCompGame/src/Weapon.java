import java.util.Random;
public abstract class Weapon extends Item{
	protected int attack, speed, range;	
	
	protected abstract int setAttack();
	protected abstract int setSpeed();
	
	public Weapon(String n){
		super(n);
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	protected void setRange()
	{
		range = 1;
	}
	
	protected int getRange()
	{
		return range;
	}
	
	public abstract int randDrop();
	public abstract int mpCost(Player p);
	
	protected static double random() 
	{	
		Random gen = new Random();
		double z;
		double u1 = gen.nextDouble(); 
		double u2 = gen.nextDouble();
		
		z = Math.sqrt(-2*Math.log(u1))*Math.cos(2*Math.PI*u2);
		
		return z+3;
	}
	
}