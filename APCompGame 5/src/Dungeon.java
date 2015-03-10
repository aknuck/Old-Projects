import java.util.ArrayList;


public abstract class Dungeon{
	final int DIFFICULTY;
	protected Player player;
	final int LEVEL;
	protected DunginGame gm;
	
	public Dungeon(int dif, Player p, int lvl, DunginGame dm) {
		DIFFICULTY = dif;
		player = p;
		LEVEL = lvl;
		this.gm = dm;
	}
	
	private String name;
	protected Enemy[] possibleEnemies; //have the 4 enemies that show up in the dungeon
	protected ArrayList<Enemy> enemies; //a list of the enemies currently in the current dungeon. Enemies are removed as they die

	public abstract Tile[][] getMap();

	public abstract Tile get(int x, int y);

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}

	public abstract void kill(Enemy e);

	

	
}
