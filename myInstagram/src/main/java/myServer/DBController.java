package myServer;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBController {
	
	private Connection dbConnection;
	private Statement dbStatement;
	
	public Connection getConnection() {
		return dbConnection;
	}
	
	public Statement getStatement() {
		return dbStatement;
	}
	
	public int getUserIdByUserName(String username) throws SQLException {
		String dbQuery = "SELECT * FROM " + Application.DB_NAME + ".user WHERE username='" + username + "'";
		ResultSet userResultSet = getStatement().executeQuery(dbQuery);

    	if(userResultSet.next()) {
    		return Integer.parseInt(userResultSet.getString("id"));
    	}
    	else {
    		return -1;
    	}
	}
	
	public int getLastUploadedImageIdByUserID(int userID) throws SQLException {
		String dbQuery = "SELECT count(*) FROM " + Application.DB_NAME + ".photo WHERE user_id='" + userID + "'";
		ResultSet userResultSet = getStatement().executeQuery(dbQuery);

    	if(userResultSet.next()) {
    		return Integer.parseInt(userResultSet.getString(1));
    	}

    	return -1; //no such a case!
	}
	
	public void insertUser(String username, String password, String email, String firstname, String lastname) throws SQLException {
		Date currentDate = new Date();
        SimpleDateFormat mySQLDateFormat = new SimpleDateFormat(Application.FORMAT_DATETIME);
		
        String dbQuery = "INSERT INTO " + Application.DB_NAME + ".user(username,password,email,create_datetime,first_name,last_name) VALUES ('" + username + "','" + password + "','" + email + "','" + mySQLDateFormat.format(currentDate) + "','" + firstname + "','" + lastname + "')";
		getStatement().executeUpdate(dbQuery);
	}
	
	public void insertImage(int userID, int fileSeq) throws SQLException {
		Date currentDate = new Date();
        SimpleDateFormat mySQLDateFormat = new SimpleDateFormat(Application.FORMAT_DATETIME);
        
        String dbQuery = "INSERT INTO " + Application.DB_NAME + ".photo(user_id,upload_datetime,file_path) VALUES ('" + userID + "','" + mySQLDateFormat.format(currentDate) + "','" + Application.FS_ROOT + userID + "/" + fileSeq + ".jpg')";
        getStatement().executeUpdate(dbQuery);
	}

	public DBController(String host, String port, String db, String username, String password) throws SQLException {
		String dbURL = "jdbc:mysql://" + host + ":" + port + "/" + db;
		dbConnection = (Connection) DriverManager.getConnection(dbURL, username, password);
		dbStatement = dbConnection.createStatement();
	}
}
