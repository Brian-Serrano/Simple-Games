package Util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageColorChanger {
	
	public static BufferedImage colorImage(BufferedImage image, Color tileColor1, Color tileColor2) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color originalColor = new Color(image.getRGB(x, y), true);
                if (originalColor.equals(tileColor2) && originalColor.getAlpha() == 255) {
                    image.setRGB(x, y, tileColor1.getRGB());
                }
            }
        }
        return image;
    }
}
