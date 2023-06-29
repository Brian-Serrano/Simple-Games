package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Util.Image;
import Util.ImageColorChanger;
import Util.Variables;

public class Obstacle {
	public ArrayList<Tile> tiles;
	public int[][] array;
	public Image image;
	public int height, tileSize;
	public Color tileColor1, tileColor2;
	public BufferedImage slopeColor, blockOneColor, blockTwoColor;
	
	public Obstacle(int[][] array, int height, Image image, Color tileColor1, Color tileColor2) {
		this.array = array;
		this.image = image;
		this.tileColor1 = tileColor1;
		this.tileColor2 = tileColor2;
		this.slopeColor = ImageColorChanger.colorImage(image.slope, tileColor1, tileColor2);
		this.blockOneColor = ImageColorChanger.colorImage(image.blockOne, tileColor1, tileColor2);
		this.blockTwoColor = ImageColorChanger.colorImage(image.blockTwo, tileColor1, tileColor2);
		this.tileSize = Variables.WINDOW_WIDTH / 14;
		this.height = height - Variables.WINDOW_HEIGHT - 100;
		this.tiles = new ArrayList<Tile>();
		this.createObjects();
	}
	
	public void draw(Graphics2D g2) {
		for(Tile t : tiles) {
			t.draw(g2);
		}
	}
	
	public void createObjects() {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				switch(array[i][j]) {
					case 0:
						break;
					case 1:
						tiles.add(new Tile(tileSize, i, j, slopeColor, 1, height, 0, 0, tileSize, tileSize));
						break;
					case 2:
						tiles.add(new Tile(tileSize, i, j, slopeColor, 2, height, tileSize, 0, 0, tileSize));
						break;
					case 3:
						tiles.add(new Tile(tileSize, i, j, slopeColor, 3, height, 0, 0, tileSize, tileSize));
						break;
					case 4:
						tiles.add(new Tile(tileSize, i, j, slopeColor, 0, height, tileSize, 0, 0, tileSize));
						break;
					case 5:
						tiles.add(new Tile(tileSize, i, j, blockOneColor, 1, height));
						break;
					case 6:
						tiles.add(new Tile(tileSize, i, j, blockOneColor, 2, height));
						break;
					case 7:
						tiles.add(new Tile(tileSize, i, j, blockOneColor, 3, height));
						break;
					case 8:
						tiles.add(new Tile(tileSize, i, j, blockOneColor, 0, height));
						break;
					case 9:
						tiles.add(new Tile(tileSize, i, j, blockTwoColor, 0, height));
						break;
				}
			}
		}
	}
}
