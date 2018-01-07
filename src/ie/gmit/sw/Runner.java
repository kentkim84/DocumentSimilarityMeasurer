package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {
	public static void main(String[] args) {
		String fileName1 = "test.txt";
		String fileName2 = "test.txt";
		int BOUND = 10;
		int N_PRODUCERS = 4;
		int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
		int poisonPill = Integer.MAX_VALUE;
		int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;

		BlockingQueue<Shingle> blockingQueue = new LinkedBlockingQueue<>(BOUND);

		new Thread(new FileParser(blockingQueue, fileName1, poisonPill, poisonPillPerProducer), "T1").start();
		//new Thread(new FileParser(blockingQueue, fileName2, poisonPill, poisonPillPerProducer), "T2").start();
		
		
		/*for (int j = 0; j < N_CONSUMERS; j++) {
			new Thread(new NumbersConsumer(queue, poisonPill)).start();
		}*/
	}
}
