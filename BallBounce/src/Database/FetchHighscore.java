package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Window;
import Util.ToastMessage;

public class FetchHighscore extends Database {
	public Object[][] data = {{"user", 0}, {"user", 0}, {"user", 0}, {"user", 0}, {"user", 0}};
	
	public void fetchHighscore(Window window) {
		query(window);
	}
	
	public void query(Window window) {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username, score FROM game ORDER BY score DESC LIMIT 5;");

            int row = 0;
            while (rs.next()) {
                for (int col = 0; col < 2; col++) {
                    data[row][col] = rs.getObject(col+1);
                }
                row++;
            }
			rs.close();
			stmt.close();
			if (connect() != null) {
				connect().close();
			}
		} catch (ClassNotFoundException e) {
			ToastMessage.showToastMessage(window, "Failed to connect to database", 2000);
			e.printStackTrace();
		} catch (SQLException e) {
			ToastMessage.showToastMessage(window, "Failed to connect to database", 2000);
			e.printStackTrace();
		}
	}
}
