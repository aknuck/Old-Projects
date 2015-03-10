import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private static BufferedImage playerSheet = loadSprite("playerSheet");
	private static BufferedImage stoneDungeonSheet = loadSprite("stoneDungeonSheet");
	private static BufferedImage waterDungeonSheet = loadSprite("waterDungeonSheet");
	private static BufferedImage fireDungeonSheet = loadSprite("fireDungeonSheet");
	private static BufferedImage grassDungeonSheet = loadSprite("grassDungeonSheet");
	private static BufferedImage singleAnimationsSheet = loadSprite("singleAnimationsSheet");
	private static BufferedImage knightSheet = loadSprite("knightSheet");
	private static BufferedImage itemsSheet = loadSprite("itemsSheet");
	private static BufferedImage itemsLargeSheet = loadSprite("itemsLargeSheet");
    private static final int TILE_SIZE = 32;
    protected static String file;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File("imgs/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid, String sheet) {

    	if (sheet.equals("playerSheet"))
    		return playerSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("stoneDungeonSheet"))
    		return stoneDungeonSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("singleAnimationsSheet"))
    		return singleAnimationsSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("knightSheet"))
    		return knightSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("waterDungeonSheet"))
    		return waterDungeonSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("fireDungeonSheet"))
    		return fireDungeonSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("grassDungeonSheet"))
    		return grassDungeonSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("itemsSheet"))
    		return itemsSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	else if (sheet.equals("itemsLargeSheet"))
    		return itemsLargeSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	return null;
    }
    
    public static BufferedImage getSprite(int xGrid, int yGrid, String sheet, int newTileSize) {

    	if (sheet.equals("playerSheet"))
    		return playerSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("stoneDungeonSheet"))
    		return stoneDungeonSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("singleAnimationsSheet"))
    		return singleAnimationsSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("knightSheet"))
    		return knightSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("waterDungeonSheet"))
    		return waterDungeonSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("fireDungeonSheet"))
    		return fireDungeonSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("grassDungeonSheet"))
    		return grassDungeonSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("itemsSheet"))
    		return itemsSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	else if (sheet.equals("itemsLargeSheet"))
    		return itemsLargeSheet.getSubimage(xGrid * newTileSize, yGrid * newTileSize, newTileSize, newTileSize);
    	return null;
    }
    
    public String getFile(){
    	return file;
    }

}