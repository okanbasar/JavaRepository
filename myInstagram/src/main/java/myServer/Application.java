package myServer;

import java.sql.SQLException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@EnableAutoConfiguration
public class Application {

	private static DBController dbController;
	private static FSController fsController;
	private static UIController uiController;
	
	//Database connection parameters
	public static final String DB_HOSTNAME = "localhost";
	public static final String DB_PORT = "3306";
	public static final String DB_NAME = "test"; //"test" or "myinstagramdb"
	public static final String DB_USER_NAME = "okan";
	public static final String DB_USER_PASSWORD = "1234";
	
	//Localization parameters
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	//File system parameters
	public static final String FS_ROOT = "D:/Eclipse_Workspace/fileStore/";
	
	public static DBController getDBController() {
		return dbController;
	}
	
	public static UIController getUIController() {
		return uiController;
	}
	
	public static FSController getFSController() {
		return fsController;
	}
	
    public static void main(String[] args) {
    	    	
    	try {
    		dbController = new DBController(DB_HOSTNAME, DB_PORT, DB_NAME, DB_USER_NAME, DB_USER_PASSWORD);
		} 
    	catch (SQLException e) {
			System.out.println(getUIController().dbConnectionFailure());
			e.printStackTrace();
		}
    	
		fsController = new FSController();
		uiController = new UIController();
		SpringApplication.run(Application.class, args);
    }
}
