
/*
 * Basically a display class that will handle all of the graphics and will display everything
 * can exist in three states - menu, game, or inventory (subject to change)
 * displays the graphics appropriate to the state it's in
 */


import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DunginGame implements Runnable, KeyListener, MouseListener{

	final int WIDTH = 800;
	final int HEIGHT = 640;

	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	private boolean beginning = true;

	public DunginGame(){

		frame = new JFrame("Dungin");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);

		canvas.addKeyListener(this);
		canvas.addMouseListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();
		canvas.setFocusable(true); 
		getImages();
		
		BufferedImage[] selectionImages = {Sprite.getSprite(0, 2,"singleAnimationsSheet"),Sprite.getSprite(1,2,"singleAnimationsSheet"),Sprite.getSprite(2,2,"singleAnimationsSheet"),Sprite.getSprite(1,2,"singleAnimationsSheet")}; //get sprites
		selectionAnimation = new Animation(selectionImages, 8);
		selectionAnimation.start();
		
		altFont = new Font("Lucida Console",10,10);
		
		try {
			defaultFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/visitor1.ttf")).deriveFont(Font.PLAIN, 12f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GraphicsEnvironment gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gc.registerFont(defaultFont);
		
	}


	long desiredFPS = 60;
	long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;

	boolean running = true;

	public void run(){
		if (beginning){
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			beginning = false;
		}

		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		while(running){
			beginLoopTime = System.nanoTime();

			render();

			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));

			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			if(deltaLoop > desiredDeltaLoop){
				//Do nothing. We are already late.
			}else{
				try{
					Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
				}catch(InterruptedException e){
					//Do nothing
				}
			}
		}
	}

	public void stopRunning(){
		running = false;
	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	//---------------------------------------------------------------------------------------------------------------------
	private double x = 0;
	private Tile[][] map;
	private Player player;
	private int xOff = 0;
	private int yOff = 0;
	private Point location = MouseInfo.getPointerInfo().getLocation();
	private double mouseX = location.getX();
	private double mouseY = location.getY();
	private Game game;
	private Menu menu;
	private boolean minimap = false;
	private Inventory inventory;
	
	//Colors
	private Color black = new Color(0,0,0);
	private Color grey1 = new Color(60,60,60);
	private Color grey2 = new Color(120,120,120);
	private Color grey3 = new Color(180,180,180);
	private Color white = new Color(255,255,255);
	private Color healthColor = new Color(240,5,20);
	private Color manaColor = new Color(5,20,240);
	private Color expColor = new Color(10,240,40);
	
	private Font defaultFont, altFont;
	private String currentState = "menu"; //menu, game, inventory
	private Animation selectionAnimation;
	private int selectedX=-1, selectedY=-1;
	private ArrayList<SingleAnimation> anims = new ArrayList<SingleAnimation>();; //will be all the single animations that need to be updated until deleted from the list

	//static GUI images
	private BufferedImage healthBarImage;
	private BufferedImage borderImage;
	private BufferedImage expBarImage;
	private BufferedImage itemsBarImage;
	private BufferedImage mapBarImage;

	private void getImages(){
		try{
			this.healthBarImage = ImageIO.read(new File("imgs/healthAndManaBar2.png"));
			this.borderImage = ImageIO.read(new File("imgs/border.png"));
			this.expBarImage = ImageIO.read(new File("imgs/expBar.png"));
			this.itemsBarImage = ImageIO.read(new File("imgs/itemsBar.png"));
			this.mapBarImage = ImageIO.read(new File("imgs/mapBar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void update(int deltaTime){
		
		//IF IN MENU
		if (currentState.equals("menu")){
			menu.update();
		}
		
		//IF IN GAME
		else if (currentState.equals("game")){
			selectionAnimation.update();
			if (player != null)
				player.updateAnimation();
			for (int i=0; i<anims.size(); i++){
				if (anims.get(i).animationRunning())
					anims.get(i).updateAnimation();
				else
					anims.remove(i);
			}
			if (game != null && game.running()){
				for (Enemy e : game.getDungeon().getEnemies()){
					e.updateAnimation();
				}
			}
			x += deltaTime * 0.2;
			while(x > 1000){
				x -= 1000;
			}
		}
		
		//IF IN INVENTORY
		else if (currentState.equals("inventory")){
			inventory.update();
		}
	}
	
	public void setMenu(Menu m){
		this.menu = m;
	}

	protected void render(Graphics2D g){
		g.setFont(defaultFont);
		
		
		//IF IN MENU
		if (currentState.equals("menu")){
			ArrayList<Image> imgs = menu.getImageList();
			for (Image img : imgs){
				g.drawImage(img.getImage(),img.getX(),img.getY(),null);
			}
		}
		
		//IF IN GAME
		if (currentState.equals("game")){
			int x = 0;
			int y = 0;
			if (map != null){
	
				for (int xX=xOff-12;xX<xOff+13;xX++){
					y = 0;
					for (int yY=yOff-10;yY<yOff+10;yY++){
						g.drawImage(map[xX][yY].getImage(),x*32,y*32,null);
						if (map[xX][yY].hasPlayer())
							g.drawImage(player.getAnimation().getSprite(), x*32, y*32, null);
						else if (map[xX][yY].hasEnemy() && game.running()){
							Enemy u = (Enemy) map[xX][yY].getUnit();
							Animation a = u.getAnimation();
							if (a != null){
								//g.drawImage(map[xX][yY].getUnit().getStanding("right").getSprite(),x*32,y*32,null);
								g.drawImage(a.getSprite(),x*32,y*32,null);
							}
						}
						if (map[xX][yY].isSelected)
							g.drawImage(selectionAnimation.getSprite(),x*32,y*32,null);
	
						y++;
					}
					x++;
				}
				x = 0;
				y = 0;
				for (int xX=xOff-12;xX<xOff+13;xX++){
					y = 0;
					for (int yY=yOff-10;yY<yOff+10;yY++){
						g.setColor(black);
						g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,map[xX][yY].getTransparency() * 0.1f));
						g.fillRect(x*32,y*32,32,32);
	
						y++;
					}
					x++;
				}
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,10 * 0.1f));
				//all the static GUI images
				g.drawImage(healthBarImage,700,358,null);
				g.drawImage(expBarImage,248,0,null);
				g.drawImage(itemsBarImage,0,150,null);
				if (minimap)
					g.drawImage(mapBarImage,0,407,null);
				//g.drawImage(borderImage,0,0,null);
	
				if (minimap){
					for (int xX=0; xX<map.length;xX++){
						for (int yY=0; yY<map[0].length; yY++){
							if(map[xX][yY].getType().equals("floor"))
								g.setColor(new Color(205,133,63));
							else if(map[xX][yY].getType().equals("wall"))
								g.setColor(new Color(139,69,19));
							else if(map[xX][yY].getType().equals("solid"))
								g.setColor(new Color(0,0,0));
							else if(map[xX][yY].getType().equals("door"))
								g.setColor(new Color(255,255,19));
							if (map[xX][yY].hasPlayer())
								g.setColor(new Color(10,250,40));
							else if (map[xX][yY].hasEnemy())
								g.setColor(new Color(220,20,10));
							if (!map[xX][yY].hasBeenSeen())
								g.setColor(black);
							g.fillRect(xX*2+14, yY*2+425, 4, 4);
						}
					}
				}
				g.setColor(healthColor); //240 5 20
				//g.drawString(""+player.getHP()+" / "+player.getMaxHp(),680,390);
				g.fillRect(724,400+(200-(200*player.getHP())/player.getMaxHp()),18,player.getHP()*2);
				//g.drawRect(700,400+(200-(200*player.getMaxHp())/player.getMaxHp()),20,player.getMaxHp()*2);
				g.setColor(manaColor);
				g.fillRect(756,400+(200-(200*player.getMP())/player.getMaxMp()),20,(100*player.getMP()/player.getMaxMp())*2);
				g.setColor(expColor);
				g.drawString(""+player.getExp()+" / "+player.getLvlUpCost(),430,15);
				g.fillRect(300,24,((200*player.getExp())/player.getLvlUpCost()),20);
				if (player.getWeapon() != null)
					g.drawImage(player.getWeapon().getImage(),14,222,null);
				//g.drawRect(300,24,((200*player.getLvlUpCost())/player.getLvlUpCost()),20);
				for (SingleAnimation a : anims){
					if (a instanceof LevelUp)
						g.drawImage(a.getAnimation().getSprite(),(int)((player.getX()-(xOff-12))*32),(int)((player.getY()-(yOff-9))*32),null);
					else
						g.drawImage(a.getAnimation().getSprite(),(int)((a.getX()-(xOff-12))*32),(int)((a.getY()-(yOff-10))*32),null);
				}
				location = MouseInfo.getPointerInfo().getLocation();
				mouseX = location.getX();
				mouseY = location.getY();
				if ((xOff-12)+(int)(mouseX/32) > 0 && (xOff-12)+(int)(mouseX/32) < 100 && (yOff-10)+(int)((mouseY-48)/32) > 0 && (yOff-10)+(int)((mouseY-48)/32) < 100){
					if (map[(xOff-12)+(int)(mouseX/32)][(yOff-10)+(int)((mouseY-48)/32)].hasEnemy()){
						Tile t = map[(xOff-12)+(int)(mouseX/32)][(yOff-10)+(int)((mouseY-48)/32)];
						g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
						g.setColor(grey1);
						g.fillRoundRect((int)mouseX-8,(int)(mouseY-64)-8,85,24,10,10);
						g.setColor(white);
						if (t != null && t.getUnit() != null){
							g.drawString(((Enemy) t.getUnit()).getName(),(int)mouseX,(int)(mouseY-64));
							g.drawString(""+((Enemy) t.getUnit()).getHP()+" / "+((Enemy) t.getUnit()).getMaxHP(),(int)mouseX,(int)(mouseY-50));
						}
					}
				}
			}
			else{
				g.fillRect((int)x, 0, 200, 200);
			}
		}
		
		//IF IN INVENTORY
		if (currentState.equals("inventory")){
			ArrayList<Image> imgs = inventory.getImageList();
			for (Image img : imgs){
				g.drawImage(img.getImage(),img.getX(),img.getY(),null);
			}
		}

	}

	public void setGame(Game g){
		this.game = g;
	}

	public void drawMap(Tile[][] Newmap){
		this.map = Newmap;
	}

	public void setPlayer(Player p){
		this.player = p;
		inventory = new Inventory(this,player);
	}

	public void checkPlayer(){
		if (player.getX() > 12 && player.getX() < 88)
			this.xOff = player.getX();
		else{
			if (player.getX() <= 12)
				this.xOff = 12;
			else
				this.xOff = 86;
		}
		if (player.getY() > 10 && player.getY() < 90)
			this.yOff = player.getY();
		else{
			if (player.getY() <= 10)
				this.yOff = 10;
			else
				this.yOff = 88;
		}
	}


	public void keyPressed(KeyEvent e) {	
		//IF IN MENU
		if (currentState.equals("menu")){
			String result = null;
			int keyCode = e.getKeyCode();
			switch(keyCode){
			case KeyEvent.VK_M:
				menu.setCharacter("mage");
				break;
			case KeyEvent.VK_K:
				menu.setCharacter("knight");
			break;
			case KeyEvent.VK_A:
				menu.setCharacter("archer");
				break;
			}
		}
		
		//IF IN GAME
		else if (currentState.equals("game")){
			String result = null;
			int keyCode = e.getKeyCode();
			switch(keyCode){
			case KeyEvent.VK_UP:
				if (player.getY() > 0){
					result = game.movePlayer("up");
					checkPlayer();
					if (result.equals("conflict")){
						anims.add(new Conflict(player.getX(),player.getY()-0.5));
						//game.fight(map[player.getX()][player.getY()-1].getUnit());
					}
				}
				break;
			case KeyEvent.VK_LEFT:
				if (player.getX() > 0){
					result = game.movePlayer("left");
					player.setDirection("left");
					checkPlayer();
					if (result.equals("conflict"))
						anims.add(new Conflict(player.getX()-0.5,player.getY()));
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (player.getX() < 100){
					result = game.movePlayer("right");
					player.setDirection("right");
					checkPlayer();
					if (result.equals("conflict"))
						anims.add(new Conflict(player.getX()+0.5,player.getY()));
				}
				break;
			case KeyEvent.VK_DOWN:
				if (player.getY() < 100){
					result = game.movePlayer("down");
					checkPlayer();
					if (result.equals("conflict"))
						anims.add(new Conflict(player.getX(),player.getY()+0.5));
				}
				break;
				case KeyEvent.VK_W:
					if (player.getY() > 0){
						result = game.movePlayer("up");
						checkPlayer();
						if (result.equals("conflict")){
							anims.add(new Conflict(player.getX(),player.getY()-0.5));
							//game.fight(map[player.getX()][player.getY()-1].getUnit());
						}
					}
					break;
				case KeyEvent.VK_A:
					if (player.getX() > 0){
						result = game.movePlayer("left");
						player.setDirection("left");
						checkPlayer();
						if (result.equals("conflict"))
							anims.add(new Conflict(player.getX()-0.5,player.getY()));
					}
					break;
				case KeyEvent.VK_D:
					if (player.getX() < 100){
						result = game.movePlayer("right");
						player.setDirection("right");
						checkPlayer();
						if (result.equals("conflict"))
							anims.add(new Conflict(player.getX()+0.5,player.getY()));
					}
					break;
				case KeyEvent.VK_S:
					if (player.getY() < 100){
						result = game.movePlayer("down");
						checkPlayer();
						if (result.equals("conflict"))
							anims.add(new Conflict(player.getX(),player.getY()+0.5));
					}
					break;
			case KeyEvent.VK_SPACE:
				game.newDung();
				break;
			case KeyEvent.VK_M:
				minimap = !minimap;
				break;
			case KeyEvent.VK_I:
				currentState = "inventory";
				break;
			}
	
			if (result != null && (result.equals("moved") || result.equals("conflict"))){
				updateShadows();
				game.moveEnemies();
			}
		}
		
		//IF IN INVENTORY
		else if (currentState.equals("inventory")){
			String result = null;
			int keyCode = e.getKeyCode();
			switch(keyCode){
			case KeyEvent.VK_I:
				currentState = "game";
				break;
			}
		}

	}

	private void updateShadows(){
		int dis;
		for (int x=xOff-12; x<xOff+13; x++){
			for (int y=yOff-10; y<yOff+10; y++){
				dis = (x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY());
				if (dis < 49){
					map[x][y].seen();
					if (dis < 25)
						map[x][y].setTransparency(1);
					else if (dis < 36)
						map[x][y].setTransparency(2);
					else if (dis < 49)
						map[x][y].setTransparency(3);
					else
						map[x][y].setTransparency(5);
				}
				else
					map[x][y].setTransparency(10);
			}
		}
	}
	
	//change the state of the display class
	public void changeState(String newState){
		if (newState.equals("menu") || newState.equals("game") || newState.equals("inventory"))
			currentState = newState;
		else
			System.out.println("not a valid state");
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void addAnim(SingleAnimation a){
		anims.add(a);
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
		//IF IN MENU
		if (currentState.equals("menu")){
			menu.mousePressed(e);
		}
		
		//IF IN GAME
		else if (currentState.equals("game")){
			if (selectedX != -1){
				map[selectedX][selectedY].deselect();
			}
			selectedX = ((int)e.getX()/32)+xOff-12; //gets pos of mouse on screen
			selectedY = ((int)e.getY()/32)+yOff-10;
			if (map != null)
				map[selectedX][selectedY].select(); //changes tile

			if(map[selectedX][selectedY].hasEnemy() && player.getWeapon().getRange() > 1) //RANGED ATTACKS ARE PROCESSED HERE
			{
				if(Math.sqrt(Math.pow(selectedX-player.getX(),2) + Math.pow(selectedY-player.getY(), 2)) <= player.getWeapon().getRange()) // Distance formula to check range
				{
					if(player.currentAttackType() == 1) { //basic ranged attack
						
						Enemy a= (Enemy)map[selectedX][selectedY].getUnit();
						a.setHP(a.getHP()-player.getAtk()-player.getWeapon().getAttack());
						player.setMP(player.getMP()-player.getWeapon().mpCost(player));
						anims.add(new Conflict(selectedX, selectedY));

						if (a.getHP() <= 0){
							game.getDungeon().kill(a);
							map[a.getX()][a.getY()].removeUnit();
							player.giveExp((int)(2+Math.pow(1.08,a.getLvl()+3)));
							player.checkLvlUp();
						}
						game.moveEnemies();
					}
				}
			}

			
			
		}
		
		//IF IN INVENTORY
		else if (currentState.equals("inventory")){
			inventory.mousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		//IF IN MENU
		if (currentState.equals("menu")){
			menu.mouseReleased(e);
		}
		
		//IF IN INVENTORY
		else if (currentState.equals("inventory")){
			inventory.mouseReleased(e);
		}
	}
	
	public static void main(String[] args){
		DunginGame gm = new DunginGame();
		new Thread(gm).start();
		Menu mainMenu = new Menu(gm);
		gm.setMenu(mainMenu);
		
	}

}