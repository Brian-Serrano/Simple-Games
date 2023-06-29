package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import Toolbox.RightButton1;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Background;
import Util.Image;
import Util.SaveResources;
import Util.Variables;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	public SaveResources saveResources;
	public Background background;
	public Image image;
	public RightButton1 pauseButton;
	public Ball ball;
	public Camera camera;
	public ButtonMouseListener buttonMouseListener;
	public List<Obstacle> obstacles;
	public ArrayList<int[][]> arrays;
	public int obstacleNumber;
	public Color tileColor1, tileColor2;
	public boolean startMoving;

	public Game(Image image, Background background, SaveResources saveResources) {
		this.saveResources = saveResources;
		this.startMoving = false;
		this.image = image;
		this.background = background;
		this.tileColor1 = Variables.newObstacleColor;
		this.tileColor2 = Variables.originalObstacleColor;
		this.pauseButton = new RightButton1();
		this.ball = new Ball();
		this.camera = new Camera();
		this.obstacles = new ArrayList<Obstacle>();
		this.buttonMouseListener = new ButtonMouseListener(this, ball);
		this.addMouseListener(buttonMouseListener);
		this.arrays = new ArrayList<int[][]>();
		this.obstacleNumber = 0;
		this.getCSVFile();
	}

	public void update() {
		ball.update();
		camera.follow(ball);
		if (ball.obstacleCreation <= -1200) {
			obstacleNumber++;
			createObstacle();
			if(obstacles.size() >= 3) {
				obstacles = obstacles.stream().skip(1).collect(Collectors.toCollection(ArrayList::new));
			}
			ball.obstacleCreation += 1200;
		}
		collision();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		
		AffineTransform reset = g2.getTransform();
		g2.translate(0, -camera.y);
		ball.draw(g2);
		for (Obstacle o : obstacles) {
			o.draw(g2);
		}
		g2.setTransform(reset);
		
		if(!startMoving) {
			g2.setFont(new Font("Impact", Font.BOLD, 35));
	        String game = "CLICK TO START";
	        FontMetrics fontMetrics = g2.getFontMetrics();
	        int gameX = (Variables.WINDOW_WIDTH - fontMetrics.stringWidth(game)) / 2;
	    	int gameY = 120;
	        g2.setColor(new Color(102, 0, 0));
	    	g2.drawString(game, gameX, gameY);
	    	g2.setColor(new Color(204, 0, 0));
	    	g2.drawString(game, gameX - 3, gameY - 1);
		}

		g2.drawImage(image.pauseButton, pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height, null);
		g2.setFont(new Font("Impact", Font.BOLD, 30));
        g2.setColor(new Color(102, 0, 0));
    	g2.drawString("Score: " + Variables.score, 10, 40);
    	g2.setColor(new Color(204, 0, 0));
    	g2.drawString("Score: " + Variables.score, 10 - 3, 40 - 1);
    	
		g2.dispose();
	}

	public void createObstacle() {
		int index = 0;
        if(this.obstacleNumber > 0) {
            index = (int) (Math.random() * 10);
        }
		obstacles.add(new Obstacle(arrays.get(index), obstacleNumber * -1200, image, tileColor1, tileColor2));
	}

	public void getCSVFile() {
		for (int k = 0; k < 10; k++) {
			try (BufferedReader br = new BufferedReader(
					new FileReader("Assets/ObstacleCSV/Obstacle" + (k + 1) + ".csv"))) {
				String line;
				int[][] array = new int[24][14];
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

	public void collision() {
		for (Obstacle o : obstacles) {
			for (Tile t : o.tiles) {
				if (circleLineCollision(ball.x, ball.y, ball.size, t.lineX1, t.lineY1, t.lineX2, t.lineY2)) {
					Variables.selectedPanel = 2;
					if(Variables.highscore < Variables.score) {
		    			Variables.highscore = Variables.score;
		    		}
					saveResources.saveResources();
				}
			}
		}
	}

	public static boolean circleLineCollision(double circleX, double circleY, double circleRadius, double lineX1,
			double lineY1, double lineX2, double lineY2) {
		double distance = calculateDistance(circleX, circleY, lineX1, lineY1, lineX2, lineY2);

		return distance <= circleRadius;
	}

	public static double calculateDistance(double circleX, double circleY, double lineX1, double lineY1, double lineX2,
			double lineY2) {
		double a = circleX - lineX1;
		double b = circleY - lineY1;
		double c = lineX2 - lineX1;
		double d = lineY2 - lineY1;

		double dot = a * c + b * d;
		double len_sq = c * c + d * d;
		double param = dot / len_sq;

		double xx, yy;

		if (param < 0) {
			xx = lineX1;
			yy = lineY1;
		} else if (param > 1) {
			xx = lineX2;
			yy = lineY2;
		} else {
			xx = lineX1 + param * c;
			yy = lineY1 + param * d;
		}

		double dx = circleX - xx;
		double dy = circleY - yy;

		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public void resetGame() {
		Variables.score = 0;
		ball.x = Variables.WINDOW_WIDTH / 2 - 100;
		ball.y = Variables.WINDOW_HEIGHT - 100;
		ball.obstacleCreation = -700;
		ball.velX = 8;
		obstacleNumber = 0;
		for(int i = 0; i < obstacles.size(); i++) {
			obstacles.remove(i);
		}
		obstacles = obstacles.stream().filter(obstacle -> false).collect(Collectors.toList());
		for(int i = 0; i < ball.particles.size(); i++) {
			ball.particles.remove(i);
		}
		ball.particles = ball.particles.stream().filter(obstacle -> false).collect(Collectors.toList());
		createObstacle();
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > pauseButton.x && 
		   x < pauseButton.x + pauseButton.width && 
		   y > pauseButton.y && 
		   y < pauseButton.y + pauseButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 3;
		}
	}
}
