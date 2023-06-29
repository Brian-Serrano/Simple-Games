package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Blocks {
	public List<Tile> tiles;
	public int[][] array;
	public Random random;
	public Color[] colors;
	
	public Blocks(int[][] array, Color[] colors) {
		this.colors = colors;
		this.tiles = new ArrayList<Tile>();
		this.array = array;
		this.random = new Random();
		this.createObjects();
	}
	
	public void update() {
		tiles = tiles.stream().filter(tile -> !tile.delete).collect(Collectors.toList());
	}
	
	public void draw(Graphics2D g2) {
		for(Tile t : tiles) {
			t.draw(g2);
		}
	}
	
	public void createObjects() {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] > 0) {
					tiles.add(new Tile(i, j, random.nextInt(20) + (array[i][j] * 20), colors));
				}
			}
		}
	}
}
