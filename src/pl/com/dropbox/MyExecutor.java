package pl.com.dropbox;

import java.util.concurrent.BlockingQueue;

public class MyExecutor implements Runnable{

	private BlockingQueue<Message> queue;
	
	public MyExecutor(BlockingQueue<Message> q){
		this.queue = q;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message msg;
		
		while(true){
			try {
				msg = queue.take();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
