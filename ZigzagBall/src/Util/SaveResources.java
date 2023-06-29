package Util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Main.Window;

public class SaveResources {
	public File file;
	public Window window;
	
	public SaveResources() {
		this.file = new File("res.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void saveResources() {
        try {
            FileWriter writer = new FileWriter(file);
            String ballColorString = String.format("%d:%d:%d", Variables.ballColor.getRed(), Variables.ballColor.getGreen(), Variables.ballColor.getBlue());
            String BG1ColorString = String.format("%d:%d:%d", Variables.BG1Color.getRed(), Variables.BG1Color.getGreen(), Variables.BG1Color.getBlue());
            String BG2ColorString = String.format("%d:%d:%d", Variables.BG2Color.getRed(), Variables.BG2Color.getGreen(), Variables.BG2Color.getBlue());
            String originalObstacleColorString = String.format("%d:%d:%d", Variables.originalObstacleColor.getRed(), Variables.originalObstacleColor.getGreen(), Variables.originalObstacleColor.getBlue());
            String newObstacleColorString = String.format("%d:%d:%d", Variables.newObstacleColor.getRed(), Variables.newObstacleColor.getGreen(), Variables.newObstacleColor.getBlue());
            writer.write(ballColorString + "," + BG1ColorString + "," + BG2ColorString + "," + originalObstacleColorString + "," + newObstacleColorString + "," + Variables.highscore);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void fetchResources() {
		try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                int[] red = new int[5];
                int[] green = new int[5];
                int[] blue = new int[5];
                for(int i = 0; i < 5; i++) {
                	String[] colorValues = arr[i].split(":");
                    red[i] = Integer.parseInt(colorValues[0]);
                    green[i] = Integer.parseInt(colorValues[1]);
                    blue[i] = Integer.parseInt(colorValues[2]);
                }
                Variables.ballColor = new Color(red[0], green[0], blue[0]);
                Variables.BG1Color = new Color(red[1], green[1], blue[1]);
                Variables.BG2Color = new Color(red[2], green[2], blue[2]);
                Variables.originalObstacleColor = new Color(red[3], green[3], blue[3]);
                Variables.newObstacleColor = new Color(red[4], green[4], blue[4]);
                Variables.highscore = Integer.parseInt(arr[5]);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
