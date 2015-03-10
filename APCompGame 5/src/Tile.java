import java.awt.image.BufferedImage;

/*
 * Make tile for map
 */


public class Tile {
	String type; // floor, wall, water, fire, door, stairs, solid
	Tile n,e,s,w; //the tiles in each compass direction
	int x;
	int y;
	boolean hasEnemy = false;
	Enemy enemy;
	boolean hasPlayer = false;
	Player player;
	String tileSheet; //tilesheet the tile uses to get its image. Dependent on which type of dungeon it is in
	BufferedImage image; //image for the tile
	String sheet; //name of the tilesheet being used
	boolean seen = false; //has this tile been seen by the player yet
	int transparency; // 1-10 with 1 being the most clear and 10 being solid
	boolean isSelected = false;
	//Image pic add pic for image
	
	public Tile(String t, int xcor, int ycor, String s){
		type = t;
		x = xcor; //X coordinate of the Tile in the grid based on the index of the array created in Dungeon
		y = ycor; // Y ""
		sheet = s;
		transparency = 10;
	}
	
	public int getTransparency(){
		return transparency;
	}
	
	public void setTransparency(int t){
		if (t>8 && seen)
			transparency = 8;
		else
			transparency = t;
	}
	
	//if the tile has been seen by the player, sets to true
	public void seen(){
		seen = true;
	}
	
	//returns whether or not the tile has been seen by the player
	public boolean hasBeenSeen(){
		return seen;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void select(){
		isSelected = true;
	}
	
	public void deselect(){
		isSelected = false;
	}
	
	//checks the surrounding tiles based on its location and the map given. Used later for picking texture
	public void checkTiles(Tile[][] map){
		if (x>0)
			s = map[x-1][y];
		else
			s = null;
		if (x<map.length-1)
			n = map[x+1][y];
		else
			n = null;
		if (y>0)
			w = map[x][y-1];
		else
			w = null;
		if (y<map.length-1)
			e = map[x][y+1];
		else
			e = null;
	}
	
	//checks if the tile has a player on it
	public boolean hasPlayer(){
		return hasPlayer;
	}
	
	//checks if the tile has an enemy on it
	public boolean hasEnemy(){
		return hasEnemy;
	}
	
	//returns the enemy on the tile if there is one
	public Object getUnit(){
		if (hasEnemy)
			return enemy;
		else
			return null;
	}
	
	public Player getPlayer(){
		if (hasPlayer)
			return player;
		else
			return null;
	}
	
	//sets a player on the tile
	public void setUnit(Player p){
		hasPlayer = true;
		player = p;
	}
	
	//sets an enemy on the tile
	public void setUnit(Enemy e){
		hasEnemy = true;
		enemy = e;
	}
	
	//removes any unit from the tile
	public void removeUnit(){
		hasEnemy = false;
		hasPlayer = false;
		enemy = null;
		player = null;
	}
	
	//gets the type of tile (wall, floor, solid, etc)
	public String getType(){
		return type;
	}
	
	//gets the image for the tile
	public BufferedImage getImage(){
		return image;
	}
	
	//figures out which image it needs to use based on the surrounding tiles
	public void setImage(){
		if (n != null && e != null && s != null && w != null){
			if (type.equals("wall")){
				if (n.getType().equals("wall")){
					if (e.getType().equals("wall")){
						if (s.getType().equals("wall")){
							if (w.getType().equals("wall")){
								image = Sprite.getSprite(1,1,sheet);
							}
							else{
								image = Sprite.getSprite(3,0,sheet);
							}
						}
						else{
							if (w.getType().equals("wall")){
								image = Sprite.getSprite(0,1,sheet);
							}
							else{
								if (w.getType().equals("floor"))
									image = Sprite.getSprite(2,0,sheet);
								else
									image = Sprite.getSprite(0,0,sheet);
							}
						}
					}
					else{
						if (s.getType().equals("wall")){
							if (w.getType().equals("wall")){
								image = Sprite.getSprite(0,1,sheet);
							}
							else{
								if (w.getType().equals("floor"))
									image = Sprite.getSprite(1,2,sheet);
								else
									image = Sprite.getSprite(0,2,sheet);
							}
						}
						else{
							if (w.getType().equals("wall")){
								if (s.getType().equals("floor"))
									image = Sprite.getSprite(2,1,sheet);
								else
									image = Sprite.getSprite(0,1,sheet);
							}
							else{
								image = Sprite.getSprite(2,0,sheet);
							}
						}
					}
				}
				else{
					if (e.getType().equals("wall")){
						if (s.getType().equals("wall")){
							if (w.getType().equals("wall")){
								image = Sprite.getSprite(3,0,sheet);
							}
							else{
								if (w.getType().equals("floor"))
									image = Sprite.getSprite(3,0,sheet);
								else
									image = Sprite.getSprite(1,0,sheet);
							}
						}
						else{
							if (w.getType().equals("wall")){
								if (n.getType().equals("floor"))
									image = Sprite.getSprite(2,2,sheet);
								else
									image = Sprite.getSprite(3,2,sheet);
							}
							else{
								image = Sprite.getSprite(4,3,sheet);
							}
						}
					}
					else{
						if (s.getType().equals("wall")){
							if (w.getType().equals("wall")){
								if (n.getType().equals("floor"))
									image = Sprite.getSprite(3,1,sheet);
								else
									image = Sprite.getSprite(1,1,sheet);
							}
							else{
								image = Sprite.getSprite(5,2,sheet);
							}
						}
						else{
							if (w.getType().equals("wall")){
								image = Sprite.getSprite(5,2,sheet);
							}
							else{
								image = Sprite.getSprite(4,2,sheet);
							}
						}
					}
				}
			}
			else if (type.equals("floor"))
				image = Sprite.getSprite(4,2,sheet);
			else
				image = Sprite.getSprite(5,2,sheet);
		}
		else{
			image = Sprite.getSprite(5,2,sheet);
		}
	}

}
