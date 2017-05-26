package pl.com.dropbox;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {
	
	private String userName;
	private String userPath;
	private File userFile;
	
	public Client(String userName, String userPath) throws IOException{
		this.userName = userName;
		this.userPath = userPath;
		createDirectory();
		watchDirectory();
		//createFile();
	}
	
	public void createDirectory() 
			throws IOException {
		
		File directory = new File(userPath.toString());
		
		if(!directory.exists()){
			if(directory.mkdirs()){
				System.out.println("Directory " + directory + " has been created");
			} else {
				System.out.println("Failed to create directory: " + directory);
			}
		} else {
			System.out.println("Directory " + directory + " exists");
		}	
	}
	
	public void createFile() 
			throws IOException {
		
		String fileFullName = userPath + "\\" + userName + ".txt";
		userFile = new File(fileFullName);
		if(!userFile.exists())
			userFile.createNewFile();
	}
	
	//https://www.youtube.com/watch?v=fcNp2SsWOeM
	public void watchDirectory() {
		try(WatchService service = FileSystems.getDefault().newWatchService()){
			Map<WatchKey, Path> keyMap = new HashMap<>();
			Path path = Paths.get(userPath);
			keyMap.put(path.register(service,
					StandardWatchEventKinds.ENTRY_CREATE),
					//StandardWatchEventKinds.ENTRY_DELETE,
					//StandardWatchEventKinds.ENTRY_MODIFY),
					path);
			
			WatchKey watchKey;
			
			do {
				watchKey = service.take();
				Path eventDir = keyMap.get(watchKey);
				
				for(WatchEvent<?> event : watchKey.pollEvents()){
					WatchEvent.Kind<?> kind = event.kind();
					Path eventPath = (Path)event.context();
					sendFileToServer(eventPath);
				}
			} while (watchKey.reset());
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void synchronizeDirectories(){
		//synchro with server after start client
	}
	
	public void sendFileToServer(Path eventPath){
		System.out.println("Sending file: " + eventPath + " to server");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
