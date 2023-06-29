package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Main.Window;

public class SaveResources {
	public File file;
	public Window window;
	
	public SaveResources(Window window) {
		this.file = new File("res.txt");
		this.window = window;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            	ToastMessage.showToastMessage(window, "An error occurred while creating the file.", 2000);
                e.printStackTrace();
            }
        }
	}
	
	public void saveResources() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(Variables.selectedPaddle + "," + Variables.selectedColor + "," + Variables.highscore);
            writer.close();
            ToastMessage.showToastMessage(window, "Successfully saved data.", 2000);
        } catch (IOException e) {
        	ToastMessage.showToastMessage(window, "An error occurred while saving data.", 2000);
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
                Variables.selectedPaddle = Integer.parseInt(arr[0]);
                Variables.selectedColor = Integer.parseInt(arr[1]);
                Variables.highscore = Integer.parseInt(arr[2]);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
        	ToastMessage.showToastMessage(window, "An error occurred while loading the data.", 2000);
            e.printStackTrace();
        }
	}
}
