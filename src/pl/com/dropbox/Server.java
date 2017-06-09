package pl.com.dropbox;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Server implements Runnable {
	
	private BlockingQueue<Message> queue;
	
	private static final String main_path = "C:\\Servers";
	private String serv_name;
	private String user_name;
	
	private File user_file;   //plik tworzony
	
	public Server(String u_name, String s_name, BlockingQueue<Message> q){
		this.user_name = u_name;
		this.serv_name = s_name;
		this.queue = q;
	}

	public void createDirectoryServer(){
		//chcemy utworzyæ folder usera na danym serverze np: C:\\Servers\\S1 + \\ PSOB
		File directory = new File(main_path + "\\" + serv_name + "\\" + user_name);
		if(!directory.exists()){
			if(directory.mkdirs()){
				System.out.println("Server directory: " + directory + " has been created");
			} else {
				System.out.println("Failed to create server directory: " + directory);
			}
		} else {
			System.out.println("Server directory " + directory + " exists");
		}
	}
	public void createFileServer(String name){
		try{
			String fileFullName = main_path + "\\" + serv_name + "\\"+ user_name + "\\" + name + ".txt";
			user_file = new File(fileFullName);
			if(!user_file.exists()){
				user_file.createNewFile();
				System.out.println("ServerFile: " + name + " has been created");
			} else
				System.out.println("ServerFile: " + name + " exist");

		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int getSize(){
		//Rand number 1-15 - time of thread sleep. It simulates size of file
		int rand_nmbr = ThreadLocalRandom.current().nextInt(1,16);
		return rand_nmbr;
	}

	@Override
	public void run() {
		try{
			System.out.println("SERVER" + serv_name + " STARTED!");
			System.out.println("File size = " + getSize());
			Thread.sleep(getSize());
			createDirectoryServer();
			Thread.sleep(4000);
			Message msg = queue.take();
			createFileServer(msg.getFileName());
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	
}
