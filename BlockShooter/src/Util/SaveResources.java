package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveResources {
	public File file;
	
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
            writer.write(Variables.selectedBall + "," + Variables.selectedBlock + "," + Variables.highscore);
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
                Variables.selectedBall = Integer.parseInt(arr[0]);
                Variables.selectedBlock = Integer.parseInt(arr[1]);
                Variables.highscore = Integer.parseInt(arr[2]);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
