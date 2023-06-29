package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Window;
import Util.ToastMessage;
import Util.Variables;

public class LogInSave extends Database {
	public String username, password;
	public Window window;
	
	public LogInSave(Window window) {
		this.window = window;
	}
	
	public boolean noInput() {
		return username.isEmpty() || password.isEmpty() ||
				username.equals("ENTER USERNAME") || password.equals("ENTER PASSWORD");
	}
	
	public boolean invalidLength() {
		return username.length() > 20 || username.length() < 7 ||
				password.length() > 20 || password.length() < 7;
	}
	
	public void logInSave(String username, String password) {
		this.username = username;
		this.password = password;
		
		if(noInput()) {
			ToastMessage.showToastMessage(window, "No input", 2000);
			return;
		}
		
		if(invalidLength()) {
			ToastMessage.showToastMessage(window, "Invalid length", 2000);
			return;
		}
		
		query();
	}
	
	public void query() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM game WHERE username='" + username + "' AND password='" + password + "'");
			if(!rs.next()) {
				ToastMessage.showToastMessage(window, "User not found", 2000);
				return;
			}
			rs.close();
			stmt.close();
			if (connect() != null) {
				Variables.isLoggedIn = true;
				Variables.username = username;
				Variables.selectedPanel = 2;
				ToastMessage.showToastMessage(window, "Logged in successfully", 2000);
				connect().close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
