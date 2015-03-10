
public class Room {
	private int x,y,width,length;
	
	public Room(int xPos, int yPos, int l, int w){
		x = xPos;
		y = yPos;
		length = l;
		width = w;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getLen(){
		return length;
	}
	public int getWid(){
		return width;
	}
	public void setX(int nx){
		x = nx;
	}
	public void setY(int ny){
		y = ny;
	}
	public boolean overlap(Room r){
		return this.x+this.width >= r.getX() &&
	               this.x <= r.getX()+r.getWid() &&
	               this.y +this.length>= r.getY() &&
	               this.y <= r.getY() + r.getLen(); 

	}
}
