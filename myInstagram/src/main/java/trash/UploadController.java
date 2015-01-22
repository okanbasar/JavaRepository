package trash;

//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Restful web service controller mapping
@RestController
public class UploadController {
	//private static final String greetingText = "Please select a photo to upload.";
	private static final String successText = "The filename is: %s";
	private static final String failureText = "No filename!";
	//private AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/upload")
	public String upload(@RequestParam(value="filename", defaultValue="") String fileName){
		//Upload myUpload;
		if (fileName.equals("")) {
			//myUpload = new Upload(-1,failureText);
			return failureText;
		}
		else {
			//myUpload = new Upload(counter.incrementAndGet(),String.format(successText, fileName));
			return String.format(successText, fileName);
		}
	}
}
