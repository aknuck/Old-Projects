import java.util.Random;

/* Game
 * Called by the Menu class, creates a new game
 * 
 * 
 */

public class Game implements Runnable{
	private final int DIFFICULTY; // 1-3
	private Player player;
	private Random generator;
	private Dungeon dung;
	private DunginGame gm;
	private boolean nextDung;
	private int temp = 0;
	boolean running = false;
	
	/*
	 * Precondish - difficulty is 1-3
	 */
	public Game(int dif, Player p, DunginGame newgm){
		DIFFICULTY = dif;
		player = p;
		player.giveItem(new Wand());
		player.giveItem(new Longsword());
		generator = new Random();
		gm = newgm;
	}
	
	public int getDifficulty()
	{
		return DIFFICULTY;
	}
	
	/*
	 * Returns number of dungeons beaten
	 */
	
	public int startGame(){
		gm.setPlayer(player);
		gm.setGame(this);
		boolean alive = true;
		int numDungeons = 0;
		while (alive){ // While player health > 0
			System.out.println("New Dung");
			//dung = new StoneDungeon(DIFFICULTY,player,numDungeons); //create the dungeon
			
			int num = generator.nextInt(4);
			if (num == 0)
				dung = new GrassDungeon(DIFFICULTY, player, numDungeons,gm);
			else if (num == 1)
				dung = new StoneDungeon(DIFFICULTY, player, numDungeons,gm);
			else if (num == 2)
				dung = new WaterDungeon(DIFFICULTY, player, numDungeons,gm);
			else if (num == 3)
				dung = new FireDungeon(DIFFICULTY, player, numDungeons,gm);
			
			alive = raid(dung); // Begin the dungeon
			if (alive)
				numDungeons ++;
			else
				System.exit(0);
		}

		return numDungeons;
	}
	
	//This is an important one
    //This runs the game pretty much, although technically it's running the dungeon
    //Returns whether the player survived and move onto the next dungeon, or if they died in the dungeon
    public boolean raid(Dungeon d){
    	nextDung = false;
    	gm.checkPlayer();
    	gm.drawMap(d.getMap());
    	running = true;
    	while (!nextDung){
    		try {
        	    Thread.sleep(40);
        	} catch (InterruptedException e) {
        	    e.printStackTrace();
        	}
    	}
    	
    	return player.isAlive();
    }
    
    public void newDung(){
    	if (nextDung)
    		System.out.println("Currently true");
    	nextDung = true;
    }
    
    public Dungeon getDungeon(){
    	return dung;
    }
    
    public String movePlayer(String dir){
    	if (dir.equals("up") && (dung.get(player.getX(),player.getY()-1).getType().equals("floor") || dung.get(player.getX(),player.getY()-1).getType().equals("door"))){
    		if (!dung.get(player.getX(),player.getY()-1).hasEnemy()){
    			dung.get(player.getX(),player.getY()).removeUnit();
    			player.setY(player.getY()-1);
    			dung.get(player.getX(),player.getY()).setUnit(player);
    			return "moved";
    		}
    		else{
    			Enemy e = (Enemy) dung.get(player.getX(),player.getY()-1).getUnit();
    			player.fight(e,dung);
    			if (e.getHP() <=0){
    				dung.get(player.getX(),player.getY()-1).removeUnit();
    				dung.kill(e);
    			}
    			return "conflict";
    		}
    	}
    	else if (dir.equals("right") && (dung.get(player.getX()+1,player.getY()).getType().equals("floor") || dung.get(player.getX()+1,player.getY()).getType().equals("door"))){
    		if (!dung.get(player.getX()+1,player.getY()).hasEnemy()){
	    		dung.get(player.getX(),player.getY()).removeUnit();
	    		player.setX(player.getX()+1);
	    		dung.get(player.getX(),player.getY()).setUnit(player);
	    		return "moved";
    		}
    		else{
    			Enemy e = (Enemy) dung.get(player.getX()+1,player.getY()).getUnit();
    			player.fight(e,dung);
    			if (e.getHP() <=0){
    				dung.get(player.getX()+1,player.getY()).removeUnit();
    				dung.kill(e);
    			}
    			return "conflict";
    		}
    	}
    	else if (dir.equals("down") && (dung.get(player.getX(),player.getY()+1).getType().equals("floor") || dung.get(player.getX(),player.getY()+1).getType().equals("door"))){
    		if (!dung.get(player.getX(),player.getY()+1).hasEnemy()){
	    		dung.get(player.getX(),player.getY()).removeUnit();
	    		player.setY(player.getY()+1);
	    		dung.get(player.getX(),player.getY()).setUnit(player);
	    		return "moved";
    		}
    		else{
    			Enemy e = (Enemy) dung.get(player.getX(),player.getY()+1).getUnit();
    			player.fight(e,dung);
    			if (e.getHP() <=0){
    				dung.get(player.getX(),player.getY()+1).removeUnit();
    				dung.kill(e);
    			}
    			return "conflict";
    		}
    	}
    	else if (dir.equals("left") && (dung.get(player.getX()-1,player.getY()).getType().equals("floor") || dung.get(player.getX()-1,player.getY()).getType().equals("door"))){
    		if (!dung.get(player.getX()-1,player.getY()).hasEnemy()){
	    		dung.get(player.getX(),player.getY()).removeUnit();
	    		player.setX(player.getX()-1);
	    		dung.get(player.getX(),player.getY()).setUnit(player);
	    		return "moved";
    		}
    		else{
    			Enemy e = (Enemy) dung.get(player.getX()-1,player.getY()).getUnit();
    			player.fight(e,dung);
    			if (e.getHP() <=0){
    				dung.get(player.getX()-1,player.getY()).removeUnit();
    				dung.kill(e);
    			}
    			return "conflict";
    		}
    	}
    	return "should never get here";
   }
   
   public boolean running(){
	   return running;
   }

   public void moveEnemies() {
		for (Enemy e : dung.getEnemies()){
			e.move(dung, player);
		}
	}


public void run() {
	startGame();
}
    
    
}
