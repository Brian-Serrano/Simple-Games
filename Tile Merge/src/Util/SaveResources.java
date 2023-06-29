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

	public void saveResources(int highScore) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(String.valueOf(highScore));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int fetchResources() {
		try {
			FileReader fileReader = new FileReader(file);
			try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					return Integer.parseInt(line);
				}
				bufferedReader.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
