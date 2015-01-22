package myServer;

public class UIController {

	//DB connection messages
	public String dbConnectionFailure() {
		return "SQL exception has thrown. Connection to DB failed!";
	}
	
	//Add user messages
	public String addUserSQLFailure(String username) {
		return "You failed while registering a new user: " + username + " because of an SQL exception!";
	}
	
	public String addUserParameterMissing(String username) {
		return "You failed while registering a new user: " + username + " because of the missing parameters!";
	}
	
	public String addUserSuccess(String username) {
		return "You successfully registered a new user: " + username;
	}
	
	//Upload image messages
	public String uploadImageSuccess(int userID, int fileSeq) {
		return "You successfully uploaded the image as /fileStore/" + userID + "/" + fileSeq + ".jpg !";
	}
	
	public String uploadImageFailure(String username, String exceptionMessage) {
		return "You failed to upload an image for " + username + " => " + exceptionMessage;
	}
	
	public String uploadEmptyImage(String username) {
		return "You failed to upload an image for " + username + " because the file was empty.";
	}
	
	public String uploadImageUserNotRegistered(String username) {
		return "You failed to upload an image for " + username + " because the user was NOT registered yet!";
	}
	
	
	
}
