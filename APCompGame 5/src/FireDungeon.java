import java.util.ArrayList;
import java.util.Random;

//REQUIRES ROOM CLASS
//Basically a grass dungeon

public class FireDungeon extends Dungeon{
	ArrayList<Room> rooms;
	//ArrayList<Item> possibleItems;
	Tile[][] map;

    public FireDungeon(int dif, Player p, int lvl, DunginGame dm) {
    	super(dif,p,lvl,dm);
    	Random generator = new Random();
    	int curroom,width,length;
    	rooms = new ArrayList<Room>();
    	Room workingRoom;
    	enemies = new ArrayList<Enemy>();
    	
    	//everything to do with generating dungeon
    	curroom = 0;
    	String[][] board = new String[100][100];
    	width = generator.nextInt(7)+6;
    	length = generator.nextInt(7)+6;
    	rooms.add(new Room(5,5,width,length));
    	board = drawRoom(rooms.get(0),board);
    	for (int x=0; x<750;x++){
    		workingRoom = new Room(5,5,generator.nextInt(5)+5,generator.nextInt(5)+5);
    		while (workingRoom.getX()+workingRoom.getWid()+4 < 100 && !(checkForRoom(workingRoom,board))){
    			workingRoom.setX(workingRoom.getX()+1);
    		}
    		if (workingRoom.getX()+workingRoom.getWid()+4 < 100){
    			workingRoom.setX(workingRoom.getX()+generator.nextInt(2)+2);
    		    workingRoom.setY(workingRoom.getY()+generator.nextInt(35));

    		    if (workingRoom.getX()+workingRoom.getWid()+4 < 100 && checkForRoom(workingRoom,board)){
    		            board = drawRoom(workingRoom,board);
    		            curroom ++;
    		            rooms.add(workingRoom);
    		    }
    		}
    		workingRoom = new Room(5,5,generator.nextInt(5)+5,generator.nextInt(5)+5);
    		while (workingRoom.getY()+workingRoom.getLen()+4 < 100 && !checkForRoom(workingRoom,board)){
    		    workingRoom.setY(workingRoom.getY()+1);
    		}
    		if (workingRoom.getY()+workingRoom.getLen()+4 < 100){
    		    workingRoom.setY(workingRoom.getY()+generator.nextInt(2)+2);
    		    workingRoom.setX(workingRoom.getX()+generator.nextInt(35));

    		    if (workingRoom.getY()+workingRoom.getLen()+4 < 100 && checkForRoom(workingRoom,board)){
    		        board = drawRoom(workingRoom,board);
    		        curroom ++;
    		        rooms.add(workingRoom);
    		    }
    		}
    	}
    	board = updateMap(board);
		board = addCorridors(rooms,board);
		board = updateMap(board);
		board = drawWalls(board);
    	//draw(board);
    	map = convertToTile(board);
    	int numEnemies = (int) (10.00+(double)LEVEL*(double)LEVEL*(3.00*((double)DIFFICULTY*0.01)+0.005));
    	int ranx,rany,pick;
    	Enemy enm;
    	for (int i=0; i<numEnemies; i++){
    		while (true){
    			ranx = generator.nextInt(map.length);
    			rany = generator.nextInt(map[0].length);
    			if (map[ranx][rany].type.equals("floor") && !map[ranx][rany].hasEnemy()){
    				pick = generator.nextInt(4)+1;
    				
    				//set to 4 types of possible enemies
    				if (pick==1){
    					enm = new Firedrake(LEVEL,dif,ranx,rany,gm);
    					map[ranx][rany].setUnit(enm);
    					enemies.add(enm);
    				}
    				if (pick==2){
    					enm = new Fireskull(LEVEL,dif,ranx,rany,gm);
    					map[ranx][rany].setUnit(enm);
    					enemies.add(enm);
    				}
    				if (pick==3){
    					enm = new Firegolem(LEVEL,dif,ranx,rany,gm);
    					map[ranx][rany].setUnit(enm);
    					enemies.add(enm);
    				}
    				if (pick==4){
    					enm = new Firelizard(LEVEL,dif,ranx,rany,gm);
    					map[ranx][rany].setUnit(enm);
    					enemies.add(enm);
    				}
    				break;
    			}
    		}
    	}
    	while (true){
    		ranx = generator.nextInt(map.length);
			rany = generator.nextInt(map[0].length);
			if (map[ranx][rany].type.equals("floor") && !map[ranx][rany].hasEnemy()){
				map[ranx][rany].setUnit(player);
				player.setX(ranx);
				player.setY(rany);
				break;
			}
    	}
    	
    }
    
    public void kill(Enemy e){
    	enemies.remove(e);
    }
    
    public Tile get(int x, int y){
    	return map[x][y];
    }
    
    //adds room to board
    private String[][] drawRoom(Room r, String[][] board){
    	for (int x=r.getX(); x<r.getWid()+r.getX();x++){
    		for (int y=r.getY(); y<r.getLen()+r.getY();y++){
    			board[x][y] = ".";
    		}
    	}
    	return board;
    }
    
    //checks surrounding area for room
    private boolean checkForRoom(Room r, String[][] board){
    	for (int x=r.getX()-3; x<r.getWid()+r.getX()+3;x++){
    		for (int y=r.getY()-3; y<r.getLen()+r.getY()+3;y++){
    			if (x<100 && y<100){
    				if (board[x][y] != null)
    					return false;
    			}
    		}
    	}
    	return true;
    }
    
    //draw the board on the screen
    private void draw(String[][] board){
    	for (int x=0; x<board.length;x++){
    		for (int y=0; y<board[0].length;y++){
    			if(board[x][y] != null){
    				System.out.print(board[x][y]);
    			}
    			else{
    				System.out.print("@");
    			}
    			
    		}
    		System.out.println("");
    	}
    }

	private String[][] updateMap(String[][] board){
    	String[][] b2 = new String[100][100];
    	String line;
   		for (int x=1; x<board.length-1;x++){
    	    line = "";
    	    for (int y=1;y<board[0].length-1;y++){
    	    	if ((board[x][y] == null) && (board[x+1][y-1] != null || board[x+1][y] != null || board[x+1][y+1] != null || board[x][y-1] != null|| board[x][y+1] != null || board[x-1][y+1] != null|| board[x-1][y] != null || board[x-1][y-1] != null)){
    	            line += "+";
    	            b2[x][y] = "+";
    	    	}
     	    	else if(board[x][y] == null){
     	        	line += "@";
    	            b2[x][y] = "@";
     	    	}
     	    	else{
    	        	line += board[x][y];
      	          	b2[x][y] = board[x][y];
     	    	}
    	    }
   		}
    	return b2;
	}

	private String[][] addCorridors(ArrayList<Room> rooms,String[][] board){
		boolean br;
	    for(Room r : rooms){
	        br = false;
	        for (int x=r.getX()+1; x<r.getX()+r.getWid();x++){
	            for (int y= r.getY()-2; y>r.getY()-(int)(r.getLen()*1.5);y--){
	            	if (!br){
	                    if (x > 0 && y > 0){
	                        if (board[x][y].equals("+") && !board[x][y-1].equals("+")){
	                            board = drawRoom(new Room(x,y,(int)(r.getLen()*1.5),1),board);
	                            board[x][y] = "*";
	                            br = true;
	                        }
	                    }
	                }
	            }
	        }
	        br = false;
	        for (int y=r.getY(); y<r.getY()+r.getLen();y++){
	            for (int x=r.getX()+r.getWid()+1; x<r.getX()+r.getWid()+r.getWid();x++){
	                if (!br){
	                    if (x < 99 && y < 99){
	                        if (board[x][y].equals("+") && !board[x+1][y].equals("+")){
	                            board = drawRoom(new Room(x-r.getWid()+3,y,1,(int)(r.getWid()*1.5)),board);
	                            board[x][y] = "*";
	                            br = true;
	                        }
	                    }
	                }
	            }
	        }
	    }
	    return board;
	}
	
	private String[][] drawWalls(String[][] board){
	    for (int x=2; x<board.length-2;x++){
	        for (int y=2; y<board[0].length-2;y++){
	        	if (board[x][y].equals("*")) //Getting rid of doors here because too lazy to fix right now
	        		board[x][y] = ".";
	        	if ((board[x][y-1].equals(".") && board[x][y+1].equals(".")) || (board[x-1][y].equals(".") && board[x+1][y].equals(".")))
	            	board[x][y] = ".";
	        	if (board[x][y].equals("@") && ((board[x][y-1].equals("+") && board[x][y+1].equals(".")) || (board[x][y+1].equals("+") && board[x][y-1].equals(".")) || (board[x-1][y].equals("+") && board[x+1][y].equals(".")) || (board[x+1][y].equals("+") && board[x-1][y].equals("."))))
	            	board[x][y] = ".";
	        	if (board[x][y].equals("@")&&(board[x-1][y-1].equals(".") || board[x-1][y].equals(".") || board[x-1][y+1].equals(".") || board[x][y-1].equals(".") || board[x][y+1].equals(".") || board[x+1][y-1].equals(".") || board[x+1][y].equals(".") || board[x+1][y+1].equals(".")))
	            //if (board[x][y].equals("@")&&((board[x-1][y-1].equals(".") && board[x-1][y].equals(".") && board[x-1][y+1].equals(".")) || (board[x+1][y-1].equals(".") && board[x+1][y].equals(".") && board[x+1][y+1].equals(".")) || (board[x-1][y-1].equals(".") && board[x][y-1].equals(".") && board[x+1][y-1].equals(".")) || (board[x-1][y+1].equals(".") && board[x][y+1].equals(".") && board[x+1][y+1].equals(".")) && board[x][y].equals("@"))){
	                board[x][y] = "+";
	        	if (board[x][y].equals("+") && ((board[x-1][y].equals(".") && board[x+1][y].equals(".")) || (board[x][y-1].equals(".") && board[x][y+1].equals("."))))
	        		board[x][y] = ".";
	        	if (board[x][y].equals("@") && board[x+1][y].equals("+") && board[x][y+1].equals("+") && board[x+1][y+1].equals("."))
	        		board[x][y] = "+";
	        	if (board[x][y].equals("@") && board[x-1][y].equals("+") && board[x][y+1].equals("+") && board[x-1][y+1].equals("."))
	        		board[x][y] = "+";
	            //}
	            
	        }
	    }
	    return board;
	}
	
	private Tile[][] convertToTile(String[][] board){
		Tile[][] tileMap = new Tile[board.length][board[0].length];
		for (int x=0; x<board.length;x++){
			for (int y=0; y<board[0].length;y++){
				if (board[x][y] != null){
					if (board[x][y].equals("@"))
						tileMap[x][y] = new Tile("solid",x,y,"fireDungeonSheet");
					else if (board[x][y].equals("+"))
						tileMap[x][y] = new Tile("wall",x,y,"fireDungeonSheet");
					else if (board[x][y].equals("."))
						tileMap[x][y] = new Tile("floor",x,y,"fireDungeonSheet");
					else if (board[x][y].equals("*"))
						tileMap[x][y] = new Tile("door",x,y,"fireDungeonSheet");
					else
						tileMap[x][y] = new Tile("solid",x,y,"fireDungeonSheet");
				}
				else
					tileMap[x][y] = new Tile("solid",x,y,"fireDungeonSheet");
			}
		}
		for (int x=0; x<board.length;x++){
			for (int y=0; y<board[0].length;y++){
				tileMap[x][y].checkTiles(tileMap);
			}
		}
		for (int x=0; x<board.length;x++){
			for (int y=0; y<board[0].length;y++){
				tileMap[x][y].setImage();
			}
		}
		
		return tileMap;
	}
	
	public Tile[][] getMap(){
		return map;
	}
	
}