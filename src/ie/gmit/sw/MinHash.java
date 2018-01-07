package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;

public class MinHash implements Runnable {
	private BlockingQueue<Shingle> blockingQueue;

	public MinHash(BlockingQueue<Shingle> blockingQueue) {		
		this.blockingQueue = blockingQueue;
	}
	
	public void minHash() throws InterruptedException {
		Shingle shingle;
		while((shingle = blockingQueue.take()).getDocumentID() != -1){
			
			
			
			
			
			
			
			System.out.println("id: " + shingle.getDocumentID());
			System.out.println("sh: "+shingle.getShingleList());
			int i = 0;
			for (String sh : shingle.getShingleList()) {
				System.out.println(++i + " : " + sh);
			}			
		}
	}
	
	public void run() {
		try {
			minHash();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
}
