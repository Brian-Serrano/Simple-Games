package Database;

import java.sql.SQLException;
import java.sql.Statement;

import Main.Window;
import Util.ToastMessage;
import Util.Variables;

public class SyncData extends Database {
	public void syncData(Window window) {
		query(window);
	}
	
	public void query(Window window) {
		try {
			Statement stmt = connect().createStatement();
			stmt.executeUpdate("UPDATE game SET paddle='" + Variables.selectedPaddle + "', color='" + Variables.selectedColor + "', score='" + Variables.highscore + "' WHERE username='" + Variables.username + "';");
			stmt.close();
			if (connect() != null) {
				ToastMessage.showToastMessage(window, "Data synced successfully", 2000);
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
