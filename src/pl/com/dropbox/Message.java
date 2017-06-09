package pl.com.dropbox;

public class Message {

	private String userName;
	private String fileName;
	
	public Message(String u_name, String f_name){
		this.userName = u_name;
		this.fileName = f_name;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getFileName(){
		return fileName;
	}
}
