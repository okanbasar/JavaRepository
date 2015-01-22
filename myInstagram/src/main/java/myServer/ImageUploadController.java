package myServer;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

// Spring MVC controller mapping
@Controller
public class ImageUploadController {

    @RequestMapping(value="/imageUpload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/imageUpload", method=RequestMethod.POST)
    public @ResponseBody String handlefileUploadDB(
    		@RequestParam("user_name") String username, 
    		@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
            	
            	int userID = Application.getDBController().getUserIdByUserName(username);
            	if(userID == -1) {
            		return Application.getUIController().uploadImageUserNotRegistered(username);
            	}
            	
            	//decide on the name of the file by querying the last updated image number from DB
            	int fileSeq = 1 + Application.getDBController().getLastUploadedImageIdByUserID(userID);
            	
            	//write the image to the file system
            	Application.getFSController().writeImage(file, userID, fileSeq);
                
                //insert the image to the database
            	Application.getDBController().insertImage(userID, fileSeq);
                
            	return Application.getUIController().uploadImageSuccess(userID, fileSeq);
            	
            } catch (Exception e) {
                return Application.getUIController().uploadImageFailure(username, e.getMessage());
            }
        } else {
            return Application.getUIController().uploadEmptyImage(username);
        }
    }

}
