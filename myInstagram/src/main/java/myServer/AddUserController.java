package myServer;

import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//Spring MVC controller mapping
@Controller
public class AddUserController {

	@RequestMapping(value="/addUser", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can register a user by posting to this same URL.";
    }

    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public @ResponseBody String handleAddUser(
    	@RequestParam("user_name") 	String username, 
    	@RequestParam("password") 	String password, 
    	@RequestParam("email") 		String email, 
    	@RequestParam("first_name") String firstname, 
    	@RequestParam("last_name") 	String lastname) {
    	
    	if(!(username.isEmpty() | password.isEmpty() | email.isEmpty())) {
    		try {
    			Application.getDBController().insertUser(username, password, email, firstname, lastname);
    			Application.getFSController().createUserDirectory(Application.getDBController().getUserIdByUserName(username));
    		} 
    		catch (SQLException e) {
				return Application.getUIController().addUserSQLFailure(username);
			}
    		return Application.getUIController().addUserSuccess(username);
    	}
    	else {
    		return Application.getUIController().addUserParameterMissing(username);
    	}
    }
	
}
