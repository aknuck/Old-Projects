import java.util.Random;

public abstract class Item {
	private String name;
	
	public Item (String n) 
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	protected static double random() 
	{	
		Random gen = new Random();
		double z;
		double u1 = gen.nextDouble(); 
		double u2 = gen.nextDouble();
		
		z = Math.sqrt(-2*Math.log(u1))*Math.cos(2*Math.PI*u2);
		
		return z+3;
	}
	
	public abstract int drop();
	
	/*
	public BufferedImage getImage() 
	{
		return image;
	}
	*/	
}
