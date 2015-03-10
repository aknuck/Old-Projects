
//A class for when an animation needs to be run through once, then be done.
public abstract class SingleAnimation {
	protected double x; //position of the single animation since it isn't necessarily on the grid (and is more often off of the grid)
	protected double y;
	
	public SingleAnimation(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	//returns the animation object
	public abstract Animation getAnimation();
	
	//updates the animation iff it hasn't finished
	public abstract void updateAnimation();
	
	//returns whether the animation is currently running. Can be used to delete variable after animation done
	public abstract boolean animationRunning();
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
}
