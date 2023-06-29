package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

import UserInput.ButtonClick;
import UserInput.SlideNumbers;
import Util.SaveResources;
import Util.Variables;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	public int[][] numberArray;
	public SlideNumbers slider;
	public ButtonClick button;
	public RestartButton restart;
	public GameOverRestart gameOverRestart;
	public SaveResources saveAndLoad;
	public int score;
	public int highScore;
	public boolean gameOver = false;

	public Game() {
		this.numberArray = new int[7][7];
		this.restart = new RestartButton();
		this.gameOverRestart = new GameOverRestart();
		this.saveAndLoad = new SaveResources();
		this.slider = new SlideNumbers(this);
		this.button = new ButtonClick(this);
		this.addKeyListener(slider);
		this.addMouseListener(button);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.score = 0;
		this.highScore = saveAndLoad.fetchResources();
		this.PopulateTiles();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		double boardXOffset = Variables.WINDOW_WIDTH - (numberArray.length * Variables.TILE_SIZE_WITH_GAP) - 10;
		double boardYOffset = Variables.WINDOW_HEIGHT - (numberArray[0].length * Variables.TILE_SIZE_WITH_GAP) - 10;

		g2.setFont(new Font("Impact", Font.BOLD, 50));
		FontMetrics fontMetrics = g2.getFontMetrics();

		// Game Name
		g2.setColor(Color.DARK_GRAY);
		String gameName = "TILE MERGE";
		double gameNameX = (boardXOffset - fontMetrics.stringWidth(gameName)) / 2;
		double gameNameY = 30 + fontMetrics.getAscent();
		g2.drawString(gameName, (int) gameNameX, (int) gameNameY);
		g2.setFont(new Font("Impact", Font.BOLD, 20));
		fontMetrics = g2.getFontMetrics();

		// Restart Button
		g2.setColor(Color.CYAN);
		g2.fill(new RoundRectangle2D.Double(restart.x, restart.y, restart.width, restart.height, restart.arc,
				restart.arc));
		String restartText = "RESTART";
		double restartXOffset = restart.x + (restart.width - fontMetrics.stringWidth(restartText)) / 2;
		double restartYOffset = restart.y + (restart.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
		g2.setColor(Color.WHITE);
		g2.drawString(restartText, (int) restartXOffset, (int) restartYOffset);
		g2.setFont(new Font("Impact", Font.BOLD, 40));
		fontMetrics = g2.getFontMetrics();

		// Score
		g2.setColor(Color.DARK_GRAY);
		String highScoreText = "HIGH SCORE";
		double highScoreX = (boardXOffset - fontMetrics.stringWidth(highScoreText)) / 2;
		double highScoreY = (Variables.WINDOW_HEIGHT - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
		g2.drawString(highScoreText, (int) highScoreX, (int) highScoreY);
		String value = String.valueOf(highScore);
		double valueX = (boardXOffset - fontMetrics.stringWidth(value)) / 2;
		double valueY = (Variables.WINDOW_HEIGHT - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent() + 75;
		g2.drawString(value, (int) valueX, (int) valueY);
		String scoreText = "SCORE: " + score;
		double scoreXOffset = Variables.WINDOW_WIDTH - fontMetrics.stringWidth(scoreText) - 10;
		double scoreYOffset = 15 + fontMetrics.getAscent();
		g2.drawString(scoreText, (int) scoreXOffset, (int) scoreYOffset);
		g2.setFont(new Font("Impact", Font.BOLD, 20));
		fontMetrics = g2.getFontMetrics();

		// Board
		g2.setColor(Color.GRAY);
		g2.fill(new RoundRectangle2D.Double(boardXOffset, boardYOffset,
				numberArray.length * Variables.TILE_SIZE_WITH_GAP, numberArray[0].length * Variables.TILE_SIZE_WITH_GAP,
				Variables.TILE_RADIUS, Variables.TILE_RADIUS));

		// Tiles
		g2.translate(boardXOffset, boardYOffset);
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = 0; j < numberArray[i].length; j++) {
				int tileXOffset = j * Variables.TILE_SIZE_WITH_GAP;
				int tileYOffset = i * Variables.TILE_SIZE_WITH_GAP;

				if (numberArray[i][j] != 0) {
					g2.setColor(new Color(255 - (numberArray[i][j] % 11) * (255 / 11),
							(numberArray[i][j] % 11) * (255 / 11), 0));
					g2.fill(new RoundRectangle2D.Double(
							tileXOffset + (Variables.TILE_SIZE_WITH_GAP - Variables.TILE_SIZE) / 2,
							tileYOffset + (Variables.TILE_SIZE_WITH_GAP - Variables.TILE_SIZE) / 2, Variables.TILE_SIZE,
							Variables.TILE_SIZE, Variables.TILE_RADIUS, Variables.TILE_RADIUS));
					g2.setColor(Color.WHITE);
					String number = String.valueOf(numberArray[i][j]);
					int numberXOffset = (Variables.TILE_SIZE_WITH_GAP - fontMetrics.stringWidth(number)) / 2;
					int numberYOffset = (Variables.TILE_SIZE_WITH_GAP - fontMetrics.getHeight()) / 2
							+ fontMetrics.getAscent();
					g2.drawString(number, tileXOffset + numberXOffset, tileYOffset + numberYOffset);
				} else {
					g2.setColor(Color.LIGHT_GRAY);
					g2.fill(new RoundRectangle2D.Double(
							tileXOffset + (Variables.TILE_SIZE_WITH_GAP - Variables.TILE_SIZE) / 2,
							tileYOffset + (Variables.TILE_SIZE_WITH_GAP - Variables.TILE_SIZE) / 2, Variables.TILE_SIZE,
							Variables.TILE_SIZE, Variables.TILE_RADIUS, Variables.TILE_RADIUS));
				}
			}
		}

		if (gameOver) {
			// Game Over
			g2.translate(-boardXOffset, -boardYOffset);
			g2.setColor(new Color(0, 0, 0, 150));

			// Game Over BG
			g2.fillRect(0, 0, Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT);

			// Game Over Text
			g2.setFont(new Font("Impact", Font.BOLD, 50));
			fontMetrics = g2.getFontMetrics();
			g2.setColor(Color.BLACK);
			String gameOver = "GAME OVER";
			double gameOverX = (Variables.WINDOW_WIDTH - fontMetrics.stringWidth(gameOver)) / 2;
			double gameOverY = (Variables.WINDOW_HEIGHT - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent() - 50;
			g2.drawString(gameOver, (int) gameOverX, (int) gameOverY);

			// Game Over Restart Button
			g2.setFont(new Font("Impact", Font.BOLD, 20));
			fontMetrics = g2.getFontMetrics();
			g2.setColor(Color.CYAN);
			g2.fill(new RoundRectangle2D.Double(gameOverRestart.x, gameOverRestart.y, gameOverRestart.width,
					gameOverRestart.height, gameOverRestart.arc, gameOverRestart.arc));
			String restartText2 = "RESTART";
			double restartXOffset2 = gameOverRestart.x
					+ (gameOverRestart.width - fontMetrics.stringWidth(restartText)) / 2;
			double restartYOffset2 = gameOverRestart.y + (gameOverRestart.height - fontMetrics.getHeight()) / 2
					+ fontMetrics.getAscent();
			g2.setColor(Color.WHITE);
			g2.drawString(restartText2, (int) restartXOffset2, (int) restartYOffset2);
		}

		g2.dispose();
	}

	public void PopulateTiles() {
		for (int i = 0; i < 10; i++) {
			PlaceRandomNumber();
		}
	}

	public void PlaceRandomNumber() {
		int rowIndex = (int) (Math.random() * 7);
		int columnIndex = (int) (Math.random() * 7);
		if (numberArray[rowIndex][columnIndex] == 0) {
			numberArray[rowIndex][columnIndex] = 2;
		} else {
			PlaceRandomNumber();
		}
	}

	public void SlideTop() {
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = 0; j < numberArray[i].length; j++) {
				if (numberArray[i][j] != 0) {
					for (int minIndex = 0; minIndex < numberArray.length; minIndex++) {
						if (minIndex == i) {
							if (minIndex > 0) {
								ProcessScore(minIndex, minIndex - 1, j, j);
							}
							break;
						}
						if (numberArray[minIndex][j] == 0) {
							numberArray[minIndex][j] = numberArray[i][j];
							numberArray[i][j] = 0;
							if (minIndex > 0) {
								ProcessScore(minIndex, minIndex - 1, j, j);
							}
							break;
						}
					}
				}
			}
		}
		ProcessTiles();
	}

	public void SlideBottom() {
		for (int i = numberArray.length - 1; i >= 0; i--) {
			for (int j = 0; j < numberArray[i].length; j++) {
				if (numberArray[i][j] != 0) {
					for (int maxIndex = numberArray.length - 1; maxIndex >= 0; maxIndex--) {
						if (maxIndex == i) {
							if (maxIndex < numberArray.length - 1) {
								ProcessScore(maxIndex, maxIndex + 1, j, j);
							}
							break;
						}
						if (numberArray[maxIndex][j] == 0) {
							numberArray[maxIndex][j] = numberArray[i][j];
							numberArray[i][j] = 0;
							if (maxIndex < numberArray.length - 1) {
								ProcessScore(maxIndex, maxIndex + 1, j, j);
							}
							break;
						}
					}
				}
			}
		}
		ProcessTiles();
	}

	public void SlideLeft() {
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = 0; j < numberArray[i].length; j++) {
				if (numberArray[i][j] != 0) {
					for (int minIndex = 0; minIndex < numberArray[i].length; minIndex++) {
						if (minIndex == j) {
							if (minIndex > 0) {
								ProcessScore(i, i, minIndex, minIndex - 1);
							}
							break;
						}
						if (numberArray[i][minIndex] == 0) {
							numberArray[i][minIndex] = numberArray[i][j];
							numberArray[i][j] = 0;
							if (minIndex > 0) {
								ProcessScore(i, i, minIndex, minIndex - 1);
							}
							break;
						}
					}
				}
			}
		}
		ProcessTiles();
	}

	public void SlideRight() {
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = numberArray.length - 1; j >= 0; j--) {
				if (numberArray[i][j] != 0) {
					for (int maxIndex = numberArray[i].length - 1; maxIndex >= 0; maxIndex--) {
						if (maxIndex == j) {
							if (maxIndex < numberArray.length - 1) {
								ProcessScore(i, i, maxIndex, maxIndex + 1);
							}
							break;
						}
						if (numberArray[i][maxIndex] == 0) {
							numberArray[i][maxIndex] = numberArray[i][j];
							numberArray[i][j] = 0;
							if (maxIndex < numberArray.length - 1) {
								ProcessScore(i, i, maxIndex, maxIndex + 1);
							}
							break;
						}
					}
				}
			}
		}
		ProcessTiles();
	}

	public void ProcessScore(int rowIndex1, int rowIndex2, int columnIndex1, int columnIndex2) {
		if (numberArray[rowIndex1][columnIndex1] == numberArray[rowIndex2][columnIndex2]) {
			score += numberArray[rowIndex2][columnIndex2];
			numberArray[rowIndex2][columnIndex2] *= 2;
			numberArray[rowIndex1][columnIndex1] = 0;
		}
	}

	public void HandleClick(int x, int y) {
		if (gameOver) {
			if (x > gameOverRestart.x && x < gameOverRestart.x + gameOverRestart.width && y > gameOverRestart.y
					&& y < gameOverRestart.y + gameOverRestart.height) {
				gameOver = false;
				repaint();
			}
		} else {
			if (x > restart.x && x < restart.x + restart.width && y > restart.y && y < restart.y + restart.height) {
				SetHighScore();
				ResetGame();
				saveAndLoad.saveResources(highScore);
			}
		}
	}

	public void ResetGame() {
		score = 0;
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = 0; j < numberArray[i].length; j++) {
				numberArray[i][j] = 0;
			}
		}
		PopulateTiles();
		repaint();
	}

	public boolean CheckAvailableTiles() {
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = 0; j < numberArray[i].length; j++) {
				if (numberArray[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public void ProcessTiles() {
		if (CheckAvailableTiles()) {
			gameOver = true;
			SetHighScore();
			ResetGame();
			saveAndLoad.saveResources(highScore);
			repaint();
			return;
		}
		PlaceRandomNumber();
		repaint();
	}

	public void SetHighScore() {
		if (highScore < score) {
			highScore = score;
		}
	}
}
