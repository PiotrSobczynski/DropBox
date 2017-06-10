package pl.com.dropbox;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainClass {

	public static void main(String[] args) throws IOException {

		BlockingQueue<Message> myQueue = new ArrayBlockingQueue<>(30);
		
		Client p = new Client("Pedro", "C:\\Locally\\Pedroo", myQueue);
		Thread t_p = new Thread(p);
		t_p.start();
		
		Client p2 = new Client("Staszek", "C:\\Locally\\Staszek", myQueue);
		Thread t_p2 = new Thread(p2);
		t_p2.start();
		
		MyExecutor e = new MyExecutor(myQueue);
		Thread t_e = new Thread(e);
		t_e.start();
		//Server s = new Server("C:\\Servers", "testowyplik", "psob", "S1");
		//Thread s1 = new Thread(s);
		//s1.start();
	}
}
