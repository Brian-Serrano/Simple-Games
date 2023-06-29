package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.Timer;

import Toolbox.BottomLeftButton1;
import Toolbox.RightButton1;
import UserInput.ButtonMouseListener;
import UserInput.CannonMouseAdapter;
import Util.Audio;
import Util.Background;
import Util.Image;
import Util.SaveResources;
import Util.Variables;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	public CannonMouseAdapter cannonMouseAdapter;
	public ButtonMouseListener buttonMouseListener;
	public SaveResources saveResources;
	public Background background;
	public RightButton1 pauseButton;
	public BottomLeftButton1 fastForwardButton;
	public List<int[][]> arrays;
	public List<Blocks> blocks;
	public Cannon cannon;
	public Image image;
	public int blockCreation;
	public Color[] COLOR_CONSTANTS;
	public Audio blockDestroy;
	public Timer timer;
	public int sleep_in_milliseconds = 16;

	public Game(Image image, Background background, SaveResources saveResources) {
		this.saveResources = saveResources;
		this.image = image;
		this.background = background;
		this.blockDestroy = new Audio("Assets/Audio/block_destroy.wav");
		this.COLOR_CONSTANTS = new Color[] {Color.RED, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GREEN, Color.YELLOW, Color.PINK, Color.ORANGE};
		this.pauseButton = new RightButton1();
		this.fastForwardButton = new BottomLeftButton1();
		this.arrays = new ArrayList<int[][]>();
		this.blocks = new ArrayList<Blocks>();
		this.cannon = new Cannon(image.cannon, COLOR_CONSTANTS);
		this.cannonMouseAdapter = new CannonMouseAdapter(this.cannon);
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseMotionListener(cannonMouseAdapter);
		this.addMouseListener(buttonMouseListener);
		this.blockCreation = 0;
		this.getCSVFile();
	}
	
	public void update() {
		cannon.update();
		collision();
		
		for(Blocks b : blocks) {
			b.update();
		}

		if(cannon.isMoved) {
			if(cannon.balls.isEmpty() && cannon.ballsLaunched == 2) {
				moveBlocks();
			}
		}
		if(blockCreation >= 7) {
			createBlocks();
			blockCreation = 0;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		background.draw(g2);
		
		for(Blocks b : blocks) {
			b.draw(g2);
		}

		g2.drawImage(image.menuButton, pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height, null);
		g2.setFont(new Font("Impact", Font.BOLD, 30));
    	g2.setColor(new Color(0, 0, 102));
    	g2.drawString("Score: " + Variables.score, 10, 40);
    	
    	g2.drawImage(image.fastForwardButton, fastForwardButton.x, fastForwardButton.y, fastForwardButton.width, fastForwardButton.height, null);
    	
		g2.setFont(new Font("Impact", Font.PLAIN, 20));
        String speedText = getSpeed(sleep_in_milliseconds);
        FontMetrics fontMetrics = g2.getFontMetrics();
        int textX = Variables.WINDOW_WIDTH - fontMetrics.stringWidth(speedText) - 10;
    	int textY = Variables.WINDOW_HEIGHT - fontMetrics.getHeight() - 10 + fontMetrics.getAscent();
    	g2.drawString(speedText, textX, textY);
		
		cannon.draw(g2);
    	
		g2.dispose();
	}
	
	public String getSpeed(int speed) {
		if(speed == 16) {
			return "x1";
		} else {
			return "x2";
		}
	}
	
	public void getCSVFile() {
		for (int k = 0; k < 10; k++) {
			try (BufferedReader br = new BufferedReader(
					new FileReader("Assets/BlocksMap/Blocks_" + (k + 1) + ".csv"))) {
				String line;
				int[][] array = new int[7][14];
				int i = 0;
				while ((line = br.readLine()) != null) {
					int[] arr = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
					for (int j = 0; j < arr.length; j++) {
						array[i][j] = arr[j];
					}
					i++;
				}
				arrays.add(array);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public void moveBlocks() {
		for(Blocks b : blocks) {
			for(Tile t : b.tiles) {
				t.y += t.size;
				if(t.isCollidedWithCannon(cannon) || t.isCollidedWithBottomOfWindow()) {
					sleep_in_milliseconds = 16;
					timer.setDelay(sleep_in_milliseconds);
					Variables.selectedPanel = 2;
					if(Variables.highscore < Variables.score) {
		    			Variables.highscore = Variables.score;
		    		}
					saveResources.saveResources();
				}
			}
		}
		blockCreation++;
		cannon.ballsLaunched = 0;
		cannon.isMoved = false;
	}
	
	public void createBlocks() {
		blocks.add(new Blocks(arrays.get((int) (Math.random() * 10)), COLOR_CONSTANTS));
	}
	
	public void collision() {
		for(Blocks block : blocks) {
			for(Tile tile : block.tiles) {
				for(Ball ball : cannon.balls) {
					switch(getCollisionDirection(ball, tile)) {
					case "none":
						break;
					case "bottom":
						ball.vertical = "bottom";
						Variables.score++;
						tile.number--;
						break;
					case "left":
						ball.horizontal = "left";
						Variables.score++;
						tile.number--;
						break;
					case "right":
						ball.horizontal = "right";
						Variables.score++;
						tile.number--;
						break;
					case "top":
						ball.vertical = "top";
						Variables.score++;
						tile.number--;
						break;
					}
					if(tile.number <= 0) {
						tile.delete = true;
						blockDestroy.pause();
						blockDestroy.setTime(0);
						blockDestroy.play();
					}
				}
			}
		}
	}
	
	public void resetGame() {
		Variables.score = 0;
		cannon.angle = -90;
		cannon.isMoved = false;
		cannon.ballsLaunched = 0;
		cannon.ballCreateTime = false;
		cannon.ballCreateTimer = 0;
		cannon.ballCreateTimeTimer = 0;
		blockCreation = 0;
		for(int i = 0; i < blocks.size(); i++) {
			blocks.remove(i);
		}
		blocks = blocks.stream().filter(block -> false).collect(Collectors.toList());
		for(int i = 0; i < cannon.balls.size(); i++) {
			cannon.balls.remove(i);
		}
		cannon.balls = cannon.balls.stream().filter(ball -> false).collect(Collectors.toList());
		createBlocks();
		moveBlocks();
		moveBlocks();
	}
	
	public String getCollisionDirection(Ball ball, Tile tile) {
	    double w = 0.5 * (ball.size + tile.size);
	    double h = 0.5 * (ball.size + tile.size);
	    double dx = ball.x + ball.size / 2 - (tile.x + tile.size / 2);
	    double dy = ball.y + ball.size / 2 - (tile.y + tile.size / 2);

	    if (Math.abs(dx) <= w && Math.abs(dy) <= h) {
	        double wy = w * dy;
	        double hx = h * dx;

	        if (wy > hx) {
	            return (wy + hx > 0) ? "bottom" : "left";
	        } else {
	            return (wy + hx > 0) ? "right" : "top";
	        }
	    }
	    return "none";
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > pauseButton.x && 
		   x < pauseButton.x + pauseButton.width && 
		   y > pauseButton.y && 
		   y < pauseButton.y + pauseButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			sleep_in_milliseconds = 16;
			timer.setDelay(sleep_in_milliseconds);
			Variables.selectedPanel = 3;
		}
		if(x > fastForwardButton.x && 
		   x < fastForwardButton.x + fastForwardButton.width && 
		   y > fastForwardButton.y && 
		   y < fastForwardButton.y + fastForwardButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			if(sleep_in_milliseconds == 16) {
				sleep_in_milliseconds = 8;
				timer.setDelay(sleep_in_milliseconds);
			} else {
				sleep_in_milliseconds = 16;
				timer.setDelay(sleep_in_milliseconds);
			}
		}
	}
	
	public void createBall(int x, int y) {
		boolean checkPause = x > pauseButton.x && x < pauseButton.x + pauseButton.width && y > pauseButton.y && y < pauseButton.y + pauseButton.height;
		boolean checkFastForward = x > fastForwardButton.x && x < fastForwardButton.x + fastForwardButton.width && y > fastForwardButton.y && y < fastForwardButton.y + fastForwardButton.height;
		boolean checkCannonHitbox = x > cannon.hitboxX && x < cannon.hitboxX + cannon.hitboxWidth && y > cannon.hitboxY && y < cannon.hitboxY + cannon.hitboxHeight;
		
		if(!checkPause && !checkFastForward && !checkCannonHitbox) {
			cannon.ballCreateTime = true;
			cannon.ballsLaunched++;
			cannon.isMoved = false;
		}
	}
}
