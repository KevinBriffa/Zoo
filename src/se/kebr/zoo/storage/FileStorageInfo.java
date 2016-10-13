package se.kebr.zoo.storage;

public final class FileStorageInfo {

	private final String directoryName;
	private final String fileName;
	private final String fileExtension;
	
	public FileStorageInfo(String directoryName, String fileName,String fileExtension) {
		this.directoryName = directoryName;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
	}
	
	public String getDirectoryName(){
		return this.directoryName;
	}
	public String getFileName() {
		return this.fileName;
	}
	public String getFileExtension() {
		return this.fileExtension;
	}
	
}
