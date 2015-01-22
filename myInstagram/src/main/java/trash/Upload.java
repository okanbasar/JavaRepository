package trash;

public class Upload {
	
	private final long fileId;
	private final String filename;
	
	public Upload(long id, String filename){
		this.fileId = id;
		this.filename = filename;
	}
	
	public long getFileId(){
		return fileId;
	}
	
	public String getFilename(){
		return filename;
	}
}
