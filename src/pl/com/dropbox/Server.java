package pl.com.dropbox;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Server implements Runnable {
	private String main_path; //gdzie sie znajduje glowny folder serverow
	private String serv_name;
	private String file_name; //plik ktory ma zostac utworzony (plik wysylany)
	private String user_name; //nazwa usera - stworzenie nowego folderu w folderze serv.
	
	private File user_file;   //plik tworzony
	
	public Server(String path, String f_name, String u_name, String s_name){
		this.main_path = path;
		this.file_name = f_name;
		this.user_name = u_name;
		this.serv_name = s_name;
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
	public void createFileServer(){
		try{
			String fileFullName = main_path + "\\" + serv_name + "\\"+ user_name + "\\" + file_name + ".txt";
			user_file = new File(fileFullName);
			if(!user_file.exists()){
				user_file.createNewFile();
				System.out.println("ServerFile: " + file_name + " has been created");
			} else
				System.out.println("ServerFile: " + file_name + " exist");

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
			System.out.println("File size = " + getSize());
			Thread.sleep(getSize());
			createDirectoryServer();
			createFileServer();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	
}
