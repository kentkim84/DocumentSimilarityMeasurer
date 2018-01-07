package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {
	public static void main(String[] args) {
		String fileName = "test.txt";
		int BOUND = 10;


		BlockingQueue<Shingle> blockingQueue = new LinkedBlockingQueue<>(BOUND);

		new Thread(new FileParser(blockingQueue, fileName), "TF1").start();
		
		new Thread(new MinHash(blockingQueue), "TM1").start();
	}
}
