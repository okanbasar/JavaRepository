package myServer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;


public class FSController {
	
	
	public void createUserDirectory(int userID) {
		File userDirectory = new File(Application.FS_ROOT + userID + "/");
		userDirectory.mkdirs();
	}
	
	public void writeImage(MultipartFile file, int userID, int fileSeq) throws IOException {
		byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(Application.FS_ROOT + userID + "/" + fileSeq + ".jpg")));
        stream.write(bytes);
        stream.close();
	}
	
	public FSController() {
		
	}
}
