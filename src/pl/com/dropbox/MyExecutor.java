package pl.com.dropbox;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyExecutor implements Runnable{

	private BlockingQueue<Message> main_queue;
	private static final float serv_capacity = 10;
	
	private BlockingQueue<Message> s1_queue = new ArrayBlockingQueue<>(30);
	private BlockingQueue<Message> s2_queue = new ArrayBlockingQueue<>(30);
	private BlockingQueue<Message> s3_queue = new ArrayBlockingQueue<>(30);
	private BlockingQueue<Message> s4_queue = new ArrayBlockingQueue<>(30);
	private BlockingQueue<Message> s5_queue = new ArrayBlockingQueue<>(30);
	
	private enum Serv{
		S1, S2, S3, S4, S5
	}
	
	
	public MyExecutor(BlockingQueue<Message> q){
		this.main_queue = q;
	}
	
	//return server name with the biggest capacity
	private int findServWithBiggestCapacity(){
		
		float tab_capp[] = new float[5];
		tab_capp[0] = s1_queue.size()/serv_capacity;
		tab_capp[1] = s2_queue.size()/serv_capacity;
		tab_capp[2] = s3_queue.size()/serv_capacity;
		tab_capp[3] = s4_queue.size()/serv_capacity;
		tab_capp[4] = s5_queue.size()/serv_capacity;
		/*
		System.out.println("S1 cap = " + tab_capp[0]);
		System.out.println("S2 cap = " + tab_capp[1]);
		System.out.println("S3 cap = " + tab_capp[2]);
		System.out.println("S4 cap = " + tab_capp[3]);
		System.out.println("S5 cap = " + tab_capp[4]);
		*/
		float min_cap = 11;
		int min_cap_index = 0; //dummy value - always changed
		
		for(int i=0; i<5; i++){
			if(tab_capp[i]<min_cap){
				min_cap=tab_capp[i];
				min_cap_index = i;
			}
		}
		
		return min_cap_index;
		
	}
	
	private void addMessageToQueue(Message msg, int queue_nmbr){
		switch(findServWithBiggestCapacity()){
			case 0:
				s1_queue.add(msg);
				System.out.println("Message put to s1 || SIZE = " + s1_queue.size());
				serverThreadStart(msg, "S1", s1_queue);
				break;
			case 1:
				s2_queue.add(msg);
				System.out.println("Message put to s2 || SIZE = " + s2_queue.size());
				serverThreadStart(msg, "S2", s2_queue);
				break;
			case 2:
				s3_queue.add(msg);
				System.out.println("Message put to s3 || SIZE = " + s3_queue.size());
				serverThreadStart(msg, "S3", s3_queue);
				break;
			case 3:
				s4_queue.add(msg);
				System.out.println("Message put to s4 || SIZE = " + s4_queue.size());
				serverThreadStart(msg, "S4", s4_queue);
				break;
			case 4:
				s5_queue.add(msg);
				System.out.println("Message put to s5 || SIZE = " + s5_queue.size());
				serverThreadStart(msg, "S5", s5_queue);
				break;	
		}
	}
	
	private void serverThreadStart(Message msg, String s_name, BlockingQueue<Message> q){
		if(!q.isEmpty()){
			Server s = new Server(msg.getUserName(), s_name, q);
			Thread ts = new Thread(s);
			ts.start();
		}
	}
	
	
	private void searchForBlockingUsers(){
		//check if one user us
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message msg;
		
		while(true){
			try {
				msg = main_queue.take();
				addMessageToQueue(msg, findServWithBiggestCapacity());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
