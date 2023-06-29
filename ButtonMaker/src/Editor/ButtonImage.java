package Editor;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Util.Variables;

public class ButtonImage extends JPanel {
	private static final long serialVersionUID = 1L;
	public int buttonX, buttonY, buttonWidth, buttonHeight, textX, textY, textWidth, textHeight, borderRadius,
			borderWidth, fontSize, horizontalPadding, verticalPadding;
	public String buttonText, fontFamily, buttonFormat, buttonGradient, textGradient, borderGradient;
	public boolean textShadow, buttonShadow, bold, italic;
	public Color borderColor, buttonColor, textColor, borderColor2, buttonColor2, textColor2;
	public Font font;
	public FontMetrics fm;
	public RoundRectangle2D rect;
	public Color[] COLOR_CONSTANTS = new Color[] { Color.red, Color.green, Color.blue, Color.magenta, Color.cyan,
			Color.yellow, Color.black, Color.white, Color.gray, Color.darkGray, Color.lightGray, Color.orange,
			Color.pink };

	public ButtonImage() {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBounds(0, 200, Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT - 250);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		apply(g2d);
	}

	public void save() {
		BufferedImage image = new BufferedImage(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT - 250,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();

		apply(g2d);

		BufferedImage croppedImage;

		if (!buttonShadow) {
			croppedImage = image.getSubimage((int) rect.getX() - borderWidth, (int) rect.getY() - borderWidth,
					(int) rect.getWidth() + (borderWidth * 2), (int) rect.getHeight() + (borderWidth * 2));
		} else {
			croppedImage = image.getSubimage((int) rect.getX() - borderWidth, (int) rect.getY() - borderWidth,
					(int) rect.getWidth() + (borderWidth * 2) + (5 + (borderWidth / 2)),
					(int) rect.getHeight() + (borderWidth * 2) + 5 + (borderWidth / 2));
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Image");
		int userSelection = fileChooser.showSaveDialog(new JFrame());
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();

			if (!fileToSave.getAbsolutePath().endsWith(".png")) {
				fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
			}

			try {
				ImageIO.write(croppedImage, "png", fileToSave);
				System.out.println("Image saved as " + fileToSave.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void apply(Graphics2D g2d) {
		changeFont();

		g2d.setFont(font);

		fm = g2d.getFontMetrics();

		textWidth = fm.stringWidth(buttonText);
		textHeight = fm.getHeight();
		textX = (this.getWidth() - this.textWidth) / 2;
		textY = (this.getHeight() - this.textHeight) / 2 + fm.getAscent();

		buttonX = (this.getWidth() - this.buttonWidth) / 2;
		buttonY = (this.getHeight() - this.buttonHeight) / 2;

		checkSizingType();

		isButtonShadowed(g2d);

		g2d.setStroke(new BasicStroke(borderWidth));

		checkGradient(g2d, buttonGradient, buttonColor, buttonColor2, rect);
		g2d.fill(rect);

		checkGradient(g2d, borderGradient, borderColor, borderColor2, rect);
		g2d.draw(rect);

		isTextShadowed(g2d);

		checkGradient(g2d, textGradient, textColor, textColor2, rect);
		g2d.drawString(buttonText, textX, textY);
	}

	private void isTextShadowed(Graphics2D g2d) {
		if (textShadow) {
			Color shadowColor = Color.GRAY;
			int shadowOffset = 2;

			Paint oldPaint = g2d.getPaint();
			Composite oldComposite = g2d.getComposite();

			g2d.setPaint(shadowColor);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

			g2d.translate(shadowOffset, shadowOffset);
			g2d.drawString(buttonText, textX, textY);
			g2d.translate(-shadowOffset, -shadowOffset);

			g2d.setPaint(oldPaint);
			g2d.setComposite(oldComposite);
		}
	}

	private void isButtonShadowed(Graphics2D g2d) {
		if (buttonShadow) {
			Color shadowColor = Color.GRAY;
			int shadowOffset = 5 + (borderWidth / 2);

			Paint oldPaint = g2d.getPaint();
			Composite oldComposite = g2d.getComposite();

			g2d.setPaint(shadowColor);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

			g2d.translate(shadowOffset, shadowOffset);
			g2d.fill(rect);
			g2d.translate(-shadowOffset, -shadowOffset);

			g2d.setPaint(oldPaint);
			g2d.setComposite(oldComposite);
		}
	}

	private void changeFont() {
		if (bold && !italic) {
			font = new Font(fontFamily, Font.BOLD, fontSize);
		} else if (!bold && italic) {
			font = new Font(fontFamily, Font.ITALIC, fontSize);
		} else if (bold && italic) {
			font = new Font(fontFamily, Font.BOLD | Font.ITALIC, fontSize);
		} else {
			font = new Font(fontFamily, Font.PLAIN, fontSize);
		}
	}

	private void checkSizingType() {
		if (buttonFormat == "Fixed") {
			rect = new RoundRectangle2D.Double(buttonX, buttonY, buttonWidth, buttonHeight, borderRadius, borderRadius);
		} else {
			rect = new RoundRectangle2D.Double(textX - horizontalPadding, textY - fm.getAscent() - verticalPadding,
					textWidth + (horizontalPadding * 2), textHeight + (verticalPadding * 2), borderRadius,
					borderRadius);
		}
	}

	private void checkGradient(Graphics2D g2d, String gradient, Color color1, Color color2, RoundRectangle2D rect) {
		switch (gradient) {
		case "Linear":
			g2d.setPaint(new GradientPaint((int) rect.getX(), (int) rect.getY(), color1,
					(int) rect.getX() + (int) rect.getWidth(), (int) rect.getY() + (int) rect.getHeight(), color2));
			break;
		case "Radial":
			g2d.setPaint(new RadialGradientPaint((int) rect.getX() + ((int) rect.getWidth() / 2),
					(int) rect.getY() + ((int) rect.getHeight() / 2), (int) rect.getWidth() / 2, new float[] { 0f, 1f },
					new Color[] { color1, color2 }));
			break;
		case "Uni":
			g2d.setColor(color1);
			break;
		}
	}
}
