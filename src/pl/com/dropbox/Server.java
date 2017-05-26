package pl.com.dropbox;

public class Server {
	private String path1;
	private String path2;
	private String path3;
	private String path4;
	private String path5;
	
	public Server(String p1, String p2, String p3, String p4, String p5){
		this.path1 = p1;
		this.path2 = p2;
		this.path3 = p3;
		this.path4 = p4;
		this.path5 = p5;
	}
	
	public void createThreads(){
		//Create threads for each of folders
	}

	public void saveFile(){
		//Function to transfer file from client to server
		//Name of the file + pass it to handler?
	}
	
	public int getSize(){
		//Rand number 1-15 - time of thread sleep. It simulates size of file
		return 0;
	}
	
	
	
}
